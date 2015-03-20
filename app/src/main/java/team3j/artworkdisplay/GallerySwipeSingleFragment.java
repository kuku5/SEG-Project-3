package team3j.artworkdisplay;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.R;


/**
 * The fragment that shows the selected artwork from the gallery view.
 * @author Team 3-J
 */


public class GallerySwipeSingleFragment extends Fragment {
    private TextView textView;
    private int indexOfArtWork;
    private LinearLayout toolbar;
    private RecyclerView recyclerView;
    private ImageButton backButton;
    private ArrayList<Comment> comments;
    private ArrayList<Comment> supercomments;
    private CommentListAdapter commentListAdapter;
    private boolean success = false;
    private int loopCounter = 0;
    private String facebookPostID;
    private int facebookCode = 10;
    private String comment;
    private String commentID;
    private boolean userLikes;
    private String userId;
    public static boolean filt;
    public static String ind;
    /**
     * Constructs a GallerySwipeSingleFragment with given position and the index of the artwork
     * @param position position given for "swiping"
     * @param indexOfArtWork the index that it possesses as the artwork
     * @return
     */
    public static GallerySwipeSingleFragment getInstance(int position, int indexOfArtWork) {
        GallerySwipeSingleFragment myFragmentTab = new GallerySwipeSingleFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("indexOfArtWork", indexOfArtWork);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }


    @Override
    public void onDetach() {
        System.gc();
        super.onDetach();
    }

    @Override
    // Menu for the fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_splash, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    // Given details of/actions of the swipe fragment
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //sort layout
        View layout = inflater.inflate(R.layout.fragment_artwork_display, container, false);
        setRetainInstance(true);

        String commentAmount = null;
        //set toolbar appearance

        textView = (TextView) layout.findViewById(R.id.position);
        toolbar=(LinearLayout) layout.findViewById(R.id.toolbar_quick_return);
        //get arguments passed in and handle
        Bundle bundle = getArguments();

        indexOfArtWork = bundle.getInt("indexOfArtWork");
        String title = GalleryData.get().GetGalleryData().get(indexOfArtWork).getName();
        textView.setText(title);

        backButton=(ImageButton) layout.findViewById(R.id.back_button);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });


        facebookPostID = GalleryData.get().getArtworkList().get(indexOfArtWork).getFbLink();


        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid1);
        commentListAdapter = new CommentListAdapter(this,getActivity() ,indexOfArtWork,GalleryData.get().getArtworkList(),getMapItemTouchListener());

        recyclerView.setAdapter(commentListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        return layout;
    }



    /**
     * The action of the map button on the fragment
     * @return the action listener
     */

    public CommentListAdapter.OnMapButtonPressTouchListener getMapItemTouchListener(){
        CommentListAdapter.OnMapButtonPressTouchListener itemTouchListener = new CommentListAdapter.OnMapButtonPressTouchListener() {
            @Override
            public void onMapButtonPress(  boolean filter, String name) {
                getActivity().onBackPressed();
                filt = true;
                ind = name;


            }

        };
        return itemTouchListener;
    }
    /**
     * Acts like the an Observer who looks for Session changes and invokes onSessionStateChanged
     *
     */
    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    /**
     * Gets facebook comments for this post
     * @param code to determine what toast the method will return from the async
     * @param customProcessDialog the process dialog
     */
    public void getFbData(final int code, final CustomProcessDialog customProcessDialog) {
        comments = new ArrayList<Comment>();

        Bundle b1 = new Bundle();
        b1.putBoolean("summary", true);     //includes a summary in the request
        b1.putString("filter", "toplevel");  //gets the chronological order of comments
        b1.putString("limit", "10000");        //gets max of 100
        new Request(Session.getActiveSession(), facebookPostID + "/comments", b1, HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        if (response != null) {
                            try {
                                JSONArray data = response.getGraphObject().getInnerJSONObject().getJSONArray("data");

                                int x = data.length();

                                for (int i = 0; i < x; i++) {
                                    boolean isPage = false;
                                    //checks if it has a category if it does, it is definitely not a profile
                                    if(data.getJSONObject(i).getJSONObject("from").has("category")) {
                                        isPage = true;
                                    }
                                    //Checks to see if message is empty
                                    if(data.getJSONObject(i).get("message").toString().length() == 0){
                                        continue;
                                    }
                                    //Setting comment information such as; number of likes, message, time, url of the post etc..
                                    Comment commentInfo = new Comment();
                                    commentInfo.setNumberLikes(data.getJSONObject(i).getInt("like_count"));
                                    commentInfo.setPosterURL(data.getJSONObject(i).getJSONObject("from").get("id").toString());
                                    commentInfo.setPosterName(data.getJSONObject(i).getJSONObject("from").get("name").toString());
                                    commentInfo.setMessage(data.getJSONObject(i).get("message").toString());
                                    commentInfo.setTime(data.getJSONObject(i).get("created_time").toString());
                                    commentInfo.setCommentID(data.getJSONObject(i).get("id").toString());
                                    commentInfo.setUserLikes(data.getJSONObject(i).getBoolean("user_likes"));
                                    commentInfo.setCanDelete(data.getJSONObject(i).getBoolean("can_remove"));
                                    commentInfo.setIsPage(isPage);
                                    commentInfo.setIsAReply(false);
                                    comments.add(commentInfo); //adding the comment information to array

                                }

                                //sorts the comments in descending order by time
                                Collections.sort(comments, new Comparator<Comment>() {
                                    public int compare(Comment c1, Comment c2) {
                                        return c2.getTime().compareTo(c1.getTime());
                                    }
                                });

                                new MyAsync(code,customProcessDialog).execute();


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();

    }

    /**
     * Gets replies to each individual comments
     */
    public void getReplies() {
        supercomments = new ArrayList<Comment>();

        Thread getComments = new Thread() {
            public void run() {
                Bundle b1 = new Bundle();
                b1.putString("filter", "stream");   //gets the chronological order of comments
                b1.putString("limit", "100");        //gets max of 100

                for(; loopCounter<comments.size(); loopCounter++) {
                    final ArrayList<Comment> replyComments = new ArrayList<Comment>();
                    supercomments.add(comments.get(loopCounter)); //Add the original comment to the list
                    new Request(Session.getActiveSession(), comments.get(loopCounter).getCommentID()+"/comments", b1, HttpMethod.GET,
                            new Request.Callback() {
                                public void onCompleted(Response response) {
                                    if (response != null) {
                                        try {
                                            JSONArray data = response.getGraphObject().getInnerJSONObject().getJSONArray("data");
                                            if(!data.equals("")) {

                                                int x = data.length();

                                                for (int i = 0; i < x; i++) {
                                                    boolean isPage = false;
                                                    //checks if it has a category if it does, it is definitely not a profile
                                                    if(data.getJSONObject(i).getJSONObject("from").has("category")) {
                                                        isPage = true;
                                                    }
                                                    //Checks to see if message is empty
                                                    if(data.getJSONObject(i).get("message").toString().length() == 0){
                                                        continue;
                                                    }
                                                    Comment commentInfo = new Comment();
                                                    commentInfo.setNumberLikes(data.getJSONObject(i).getInt("like_count"));
                                                    commentInfo.setPosterURL(data.getJSONObject(i).getJSONObject("from").get("id").toString());
                                                    commentInfo.setPosterName(data.getJSONObject(i).getJSONObject("from").get("name").toString());
                                                    commentInfo.setMessage(data.getJSONObject(i).get("message").toString());
                                                    commentInfo.setTime(data.getJSONObject(i).get("created_time").toString());
                                                    commentInfo.setCommentID(data.getJSONObject(i).get("id").toString());
                                                    commentInfo.setUserLikes(data.getJSONObject(i).getBoolean("user_likes"));
                                                    commentInfo.setCanDelete(data.getJSONObject(i).getBoolean("can_remove"));
                                                    commentInfo.setIsPage(isPage);
                                                    commentInfo.setIsAReply(true);
                                                    replyComments.add(commentInfo);

                                                }
                                                success = true;
                                                supercomments.addAll(replyComments); //Add the list of reply comments
                                            }


                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }).executeAndWait();
                }
            }
        };
        getComments.start();
        try {
            getComments.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loopCounter = 0;

    }


    /**
     * Logs you into facebook, receiving the user's name, and retrieves data from fb to populate the recycler view's comment view type
     */
    public void onClickLogin() {

        facebookCode = 0;
        if(checkIfActiveSession()){
            Request.newMeRequest(Session.getActiveSession(), new Request.GraphUserCallback() {
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    if (user != null) {
                        commentListAdapter.nameChange(user.getFirstName());
                        userId = user.getId();
                        getLikes();

                    }
                }
            }).executeAsync();
            CustomProcessDialog dialog = new CustomProcessDialog(getActivity());
            dialog.show();
            getFbData(0,dialog);
        }

    }

    /**
     * Checks if there is an active facebook session open
     * @return true if there is, false if there isn't and opens a new session
     */
    public boolean checkIfActiveSession(){
        Session session = Session.getActiveSession();

        if((session==null) || session.isClosed()) {
            Session.openActiveSession(getActivity(), this, true, statusCallback);
            return false;
        }
        else if(session.isOpened()){
            Session.getActiveSession().refreshPermissions();
            return true;
        }
        return false;

    }

    /**
     * Display different things depending on if the user is logged in
     *
     */
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        Session.setActiveSession(session);

        if (state.isOpened()) {
            //If logged in
            //Loads comments after login
            if(facebookCode == 0){
                onClickLogin();
                facebookCode = 10; //reset code
            }


        } else if (state.isClosed()) {
            //If logged out
        }

    }


    /**
     * Checks if any session changes have occured and notify data changes
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            if(recyclerView!=null) {
                recyclerView.getAdapter().notifyDataSetChanged();
                Session session = Session.getActiveSession();
                if((session==null) || session.isClosed()) {
                    commentListAdapter.commentsChanged(new ArrayList<Comment>());
                    commentListAdapter.likePostChange(null,false);
                }
            }
        }

    }

    /**
     * Posts a comment to the facebook post
     * @param comment the comment that you are replying to if replying to a comment
     * @param commentID The comment's ID
     */
    public void postComment(String comment, String commentID){
        String replyTo = facebookPostID; //ID of the post
        final CustomProcessDialog dialog = new CustomProcessDialog(getActivity());
        dialog.show();
        if(!commentID.isEmpty()){
            replyTo = commentID; //ID of the comment (for replies)
        }


        if(checkIfActiveSession()) {
            Session session = Session.getActiveSession();

            //Might not be able to use this method to get permissions
            List<String> permissions = session.getPermissions();
            if (permissions.contains("publish_actions")) {
                Bundle params = new Bundle();
                params.putString("message", comment);

                new Request(session, replyTo +"/comments", params,
                        HttpMethod.POST,
                        new Request.Callback() {
                            public void onCompleted(Response response) {
                                getFbData(1,dialog);

                            }
                        }
                ).executeAsync();
            } else {
                //Request posting permissions
                dialog.hide();
                Session.getActiveSession().requestNewPublishPermissions(new Session.NewPermissionsRequest(this, Arrays.asList("publish_actions")));
                this.comment = comment;
                this.commentID = commentID;
                facebookCode = 1;


            }
        }


    }

    /**
     * Handles results from any facebook dialog and recalls the method which was used to invoke the dialog
     * @param requestCode request code of result
     * @param resultCode result code of result
     * @param data Any data from the result
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean success = Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
        if(success) {
            Session session = Session.getActiveSession();
            if (session.isPermissionGranted("publish_actions")) {
                if (facebookCode == 1) {
                    postComment(comment, commentID);
                    facebookCode = 10;
                    comment = null;
                    commentID = null;
                } else if (facebookCode == 2) {
                    deleteComment(commentID);
                    facebookCode = 10;
                    commentID = null;
                } else if (facebookCode == 3) {
                    likeComment(commentID, userLikes);
                    facebookCode = 10;
                    commentID = null;

                } else if (facebookCode == 4) {
                    likePhotoPost(userLikes);
                    facebookCode = 10;

                }

            }
        }
    }

    /**
     * Deletes a specified comment, only if the user has permissions to delete
     * @param commentID the comment ID of the comment requesting to be deleted
     */
    public void deleteComment(String commentID) {
        if(checkIfActiveSession()) {
            Session session = Session.getActiveSession();
            List<String> permissions = session.getPermissions();
            final CustomProcessDialog dialog = new CustomProcessDialog(getActivity());
            dialog.show();
            if (permissions.contains("publish_actions")) {
                new Request(
                        session,
                        commentID,
                        null,
                        HttpMethod.DELETE,
                        new Request.Callback() {
                            public void onCompleted(Response response) {

                                try {
                                    boolean success = response.getGraphObject().getInnerJSONObject().getBoolean("success");

                                    if (success) {
                                        //if successfully deleted, refresh the comments on the recycler view
                                        getFbData(2,dialog);

                                    } else {
                                        dialog.hide();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                ).executeAsync();

            } else {
                //Request posting permissions
                dialog.hide();
                Session.getActiveSession().requestNewPublishPermissions(new Session.NewPermissionsRequest(this, Arrays.asList("publish_actions")));
                this.commentID = commentID;
                facebookCode = 2;

            }
        }
    }

    /**
     * Likes a specified comment
     * @param commentID the ID of the comment being liked
     * @param userLikes checks if the user has already liked the comment or not
     */
    public void likeComment(String commentID, Boolean userLikes){
        if(checkIfActiveSession()) {
            final int code;
            Session session = Session.getActiveSession();

            //Might not be able to use this method to get permissions
            List<String> permissions = session.getPermissions();
            HttpMethod method;
            if (userLikes) {
                method = HttpMethod.DELETE;
                code = 4;
            } else {
                method = HttpMethod.POST;
                code = 3;
            }
            if (permissions.contains("publish_actions")) {
                Bundle params = new Bundle();

            /* make the API call */
                new Request(session, commentID + "/likes", params,
                        method,
                        new Request.Callback() {
                            public void onCompleted(Response response) {
                                try {
                                    boolean success = response.getGraphObject().getInnerJSONObject().getBoolean("success");

                                    if (success) {
                                        //if successfully liked the comment
                                        CustomProcessDialog dialog = new CustomProcessDialog(getActivity());
                                        dialog.show();
                                        getFbData(code,dialog);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                ).executeAsync();

            } else {
                //Request posting permissions
                Session.getActiveSession().requestNewPublishPermissions(new Session.NewPermissionsRequest(this, Arrays.asList("publish_actions")));
                facebookCode = 3;
                this.commentID = commentID;
                this.userLikes = userLikes;
            }
        }

    }

    /**
     * Gets the number of likes on a post as well as if the user likes the post
     */
    public void getLikes(){
        Bundle b1 = new Bundle();
        b1.putBoolean("summary", true);     //includes a summary in the request
        b1.putString("filter", "toplevel");
        b1.putString("limit", "10000");        //gets max of 100
        new Request(Session.getActiveSession(), facebookPostID + "/likes" , b1, HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        if (response != null) {
                            try {
                                int numberOfLikes = response.getGraphObject().getInnerJSONObject().getJSONArray("data").length();
                                boolean userLikes = false;
                                for(int i = 0; i < numberOfLikes; i++){
                                    if(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("id").equals(userId)){
                                        userLikes = true;
                                        break;
                                    }
                                }
                                commentListAdapter.likePostChange(Integer.toString(numberOfLikes), userLikes);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();
    }

    /**
     * Likes and delikes the facebook post
     * @param userLikes true if user likes, false if not
     */
    public void likePhotoPost(boolean userLikes) {
        if(checkIfActiveSession()) {
            Session session = Session.getActiveSession();

            //Might not be able to use this method to get permissions
            List<String> permissions = session.getPermissions();
            HttpMethod method;
            if (userLikes) {
                method = HttpMethod.DELETE;

            } else {
                method = HttpMethod.POST;

            }
            if (permissions.contains("publish_actions")) {
                Bundle params = new Bundle();

            /* make the API call */
                new Request(session, facebookPostID + "/likes", params,
                        method,
                        new Request.Callback() {
                            public void onCompleted(Response response) {
                                try {
                                    boolean success = response.getGraphObject().getInnerJSONObject().getBoolean("success");
                                    if (success) {
                                        getLikes();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                ).executeAsync();

            } else {
                //Request posting permissions
                Session.getActiveSession().requestNewPublishPermissions(new Session.NewPermissionsRequest(this, Arrays.asList("publish_actions")));
                facebookCode = 4;
                this.userLikes = userLikes;
            }
        }
    }

    /**
     * Async task for the process dialog to run whilst loading data from Facebook
     */
    class MyAsync extends AsyncTask<Void, Void, Void> {

        private CustomProcessDialog customProcessDialog;
        private int code;

        /**
         * Constructs an MyAsync with the code type which determines the "toast" to return
         * @param code the code for what "toast" to return
         * @param customProcessDialog the process dialog
         */
        public MyAsync(int code, CustomProcessDialog customProcessDialog) {
            this.customProcessDialog = customProcessDialog;
            this.code = code;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected Void doInBackground(Void... params) {

            getReplies();
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (success) {
                commentListAdapter.commentsChanged(supercomments);
                success = false;
            }
            if (code == 1) {
                Toast.makeText(getActivity(), "Comment posted", Toast.LENGTH_SHORT).show();
            }
            else if (code == 2) {
                Toast.makeText(getActivity(), "Comment deleted", Toast.LENGTH_SHORT).show();
            }
            else if(code == 3){
                Toast.makeText(getActivity(), "Comment liked", Toast.LENGTH_SHORT).show();
            }
            else if(code == 4){
                Toast.makeText(getActivity(), "Comment unliked", Toast.LENGTH_SHORT).show();
            }
            customProcessDialog.hide();
        }
    }
}
