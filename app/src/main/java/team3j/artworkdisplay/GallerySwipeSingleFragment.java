package team3j.artworkdisplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import team3j.dulwichstreetart.Art;
import team3j.dulwichstreetart.ArtistListAdapter;
import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.GalleryFragment;
import team3j.dulwichstreetart.HomePageFragment;
import team3j.dulwichstreetart.R;


/**
 * Created by JGill on 06/02/15.
 */


public class GallerySwipeSingleFragment extends Fragment {
    private TextView textView;
    int indexOfArtWork;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private ImageButton backButton;

    private ArrayList<Comment> comments;
    private ArrayList<Comment> supercomments;

    private CommentListAdapter commentListAdapter;

    private boolean success = false;

    private int loopCounter = 0;

    public static GallerySwipeSingleFragment getInstance(int position, int indexOfArtWork) {
        GallerySwipeSingleFragment myFragmentTab = new GallerySwipeSingleFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("indexOfArtWork", indexOfArtWork);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_splash, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //sort layout
        View layout = inflater.inflate(R.layout.fragment_artwork_display, container, false);
        setRetainInstance(true);

        String commentAmount = null;

        //set toolbar appearance

        textView = (TextView) layout.findViewById(R.id.position);

        //get arguments passed in and handle
        Bundle bundle = getArguments();

        indexOfArtWork = bundle.getInt("indexOfArtWork");
        String title = GalleryData.get().GetGalleryData().get(indexOfArtWork).getName();
        textView.setText(title);

        backButton=(ImageButton) layout.findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFragment.goToMaps();

                getActivity().onBackPressed();
            }
        });

//
//        toolbar= (Toolbar) layout.findViewById(R.id.toolbar_gallery);
//        toolbar.setTitle(title);
//
//        toolbar.setTitleTextColor(getActivity().getResources().getColor(R.color.dark_grey));
//

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid1);

        commentListAdapter = new CommentListAdapter(this,getActivity() ,indexOfArtWork,GalleryData.get().getArtworkList());

        recyclerView.setAdapter(commentListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.smoothScrollToPosition(1);
        return layout;
    }
    //Acts like the an Observer who looks for Session changes and invokes onSessionStateChanged
    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };


    public void getFbData(final CustomProcessDialog customProcessDialog) {
        customProcessDialog.show();
        comments = new ArrayList<Comment>();

        Bundle b1 = new Bundle();
        b1.putBoolean("summary", true);     //includes a summary in the request
        b1.putString("filter", "toplevel");
        //b1.putString("filter", "stream");   //gets the chronological order of comments
        b1.putString("limit", "100");        //gets max of 100
        new Request(Session.getActiveSession(), "779466045468925/comments", b1, HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        if (response != null) {
                            try {
                                System.out.println(response);
                                //System.out.println(response.getGraphObject().toString());
                                //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +response.getGraphObject().getInnerJSONObject().getJSONObject("summary").toString());

                                int x = response.getGraphObject().getInnerJSONObject().getJSONArray("data").length();
                                System.out.println(x);
                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +response.getGraphObject().getInnerJSONObject().getJSONArray("data"));
                                for (int i = 0; i < x; i++) {
                                    //System.out.println(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i));
                                    Comment commentInfo = new Comment();
                                    System.out.println("NUMBER LIKES"+Integer.parseInt(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("like_count").toString()));
                                    commentInfo.setNumberLikes(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("like_count").toString());
                                    commentInfo.setPosterURL(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("id").toString());
                                    commentInfo.setPosterName(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("name").toString());
                                    commentInfo.setMessage(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message").toString());
                                    commentInfo.setTime(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("created_time").toString());
                                    commentInfo.setCommentID(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("id").toString());
                                    commentInfo.setUserLikes((Boolean) response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("user_likes"));
                                    commentInfo.setIsAReply(false);
                                    comments.add(commentInfo);

                                }

                                //sorts the comments in descending order by time
                                Collections.sort(comments, new Comparator<Comment>() {
                                    public int compare(Comment c1, Comment c2) {
                                        return c2.getTime().compareTo(c1.getTime());
                                    }
                                });
                                getReplies();
                                customProcessDialog.hide();



                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();




        //System.out.println("FBdata" + comments);


    }
    public void getReplies() {
        supercomments = new ArrayList<Comment>();

        Thread getComments = new Thread() {
            public void run() {
                Bundle b1 = new Bundle();
                b1.putString("filter", "stream");   //gets the chronological order of comments
                b1.putString("limit", "100");        //gets max of 100

                for(; loopCounter<comments.size(); loopCounter++) {
                    final ArrayList<Comment> replyComments = new ArrayList<Comment>();
                    //System.out.println(comments.get(loopCounter));
                    supercomments.add(comments.get(loopCounter)); //Add the original comment to the list
                    new Request(Session.getActiveSession(), comments.get(loopCounter).getCommentID()+"/comments", b1, HttpMethod.GET,
                            new Request.Callback() {
                                public void onCompleted(Response response) {
                                    if (response != null) {
                                        try {

                                            System.out.println(response);
                                            if(!response.getGraphObject().getInnerJSONObject().getJSONArray("data").equals("")) {


                                                int x = response.getGraphObject().getInnerJSONObject().getJSONArray("data").length();
                                                System.out.println(x);
                                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + response.getGraphObject().getInnerJSONObject().getJSONArray("data"));
                                                for (int i = 0; i < x; i++) {
                                                    //System.out.println(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i));
                                                    Comment commentInfo = new Comment();
                                                    commentInfo.setNumberLikes(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("like_count").toString());
                                                    commentInfo.setPosterURL(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("id").toString());
                                                    commentInfo.setPosterName(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("name").toString());
                                                    commentInfo.setMessage(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message").toString());
                                                    commentInfo.setTime(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("created_time").toString());
                                                    commentInfo.setCommentID(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("id").toString());
                                                    commentInfo.setUserLikes((Boolean) response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("user_likes"));
                                                    commentInfo.setIsAReply(true);
                                                    replyComments.add(commentInfo);

                                                }
                                                System.out.println(replyComments);
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
        if (success) {
            commentListAdapter.commentsChanged(supercomments);
            success = false;
        }
    }


    //handler for the log in button
    public void onClickLogin() {

        Session session = Session.getActiveSession();

        if((session==null) || session.isClosed()) {
            Session.openActiveSession(getActivity(), this, true, statusCallback);


        }
        if(Session.getActiveSession().isOpened()) {
            Session.getActiveSession().refreshPermissions();
            getFbData(new CustomProcessDialog(this.getActivity()));
        }
        //System.out.println("onClickLogin" + comments);


    }

    //Display different things depending on if the user is logged in
    private void onSessionStateChange(Session session,
                                      SessionState state, Exception exception) {
        System.out.println(state);
        Session.setActiveSession(session);
        if(state.equals(SessionState.OPENED_TOKEN_UPDATED)){

            System.out.println(session.getPermissions());
        }
        if (state.isOpened()) {
            //If logged in, show this
            Log.i("MainActivity", "Logged in...");
            //test.setText("");
            //retrieveInfo(session);
            //isLoggedIn = true;
//            Request.newMeRequest(session, new Request.GraphUserCallback() {
//                // callback after Graph API response with user object
//                @Override
//                public void onCompleted(GraphUser user, Response response) {
//                    if (user != null) {
//                       // HomePageFragment.facebookCardText.setText(user.getFirstName() + "\nLog Out.");
//                    }
//                }
//            }).executeAsync();


        } else if (state.isClosed()) {
            //If logged out, show this
            Log.i("GallerySwipeFragment", "Logged out...");

        }

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("GallerySwipeFragment", "Visible"+indexOfArtWork);
            //vince put it here
            if(recyclerView!=null) {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            //I have found that the setUserVisibleHint method gets called BEFORE
            //the onCreateView gets called and this makes it difficult to track any initialization. so do some null checks
        }
        else {  }
    }
    //Method to post a comment to facebook
    public void postComment(String comment, final CustomProcessDialog customProcessDialog){
        //TODO check if active session is not null and opened
        Session session = Session.getActiveSession();
        //customProcessDialog.show();
        //Might not be able to use this method to get permissions
        List<String> permissions = session.getPermissions();
        System.out.println(permissions);
        if(permissions.contains("publish_actions")){
            System.out.println("has publish actions");
            Bundle params = new Bundle();
            params.putString("message", comment);
            /* make the API call */
            new Request(session, "/779466045468925/comments", params,
                    HttpMethod.POST,
                    new Request.Callback() {
                        public void onCompleted(Response response) {
                            System.out.println(response.getGraphObject().getInnerJSONObject());
                            //TODO REFRESH PAGE HERE


                            getFbData(new CustomProcessDialog(getActivity()));

                        }
                    }
            ).executeAsync();
        } else {
            //Request posting permissions
            Session.getActiveSession().requestNewPublishPermissions(new Session.NewPermissionsRequest(this, Arrays.asList("publish_actions")));
            //TODO something after the request been made

        }


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);

    }

    public void likeComment(String commentID, Boolean userLikes){
        System.out.println(commentID);
        Session session = Session.getActiveSession();

        //Might not be able to use this method to get permissions
        List<String> permissions = session.getPermissions();
        System.out.println(permissions);
        HttpMethod method;
        if(userLikes){
            method = HttpMethod.DELETE;
        }
        else{
            method = HttpMethod.POST;
        }
        if(permissions.contains("publish_actions")){
            System.out.println("has publish actions");
            Bundle params = new Bundle();

            /* make the API call */
            new Request(session, "/" + commentID + "/likes", params,
                    method,
                    new Request.Callback() {
                        public void onCompleted(Response response) {
                            //TODO REFRESH PAGE HERE
                            try {
                                System.out.println(response.getError());
                                boolean success = response.getGraphObject().getInnerJSONObject().getBoolean("success");

                                if(success) {
                                    getFbData(new CustomProcessDialog(getActivity()));
                                }
                                else{
                                    System.out.println(response.getError());
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
            //TODO something after the request been made

        }

    }

}
