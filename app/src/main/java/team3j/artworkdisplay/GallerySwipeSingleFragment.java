package team3j.artworkdisplay;


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

import java.util.ArrayList;

import team3j.dulwichstreetart.ArtistListAdapter;
import team3j.dulwichstreetart.GalleryData;
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

    private CommentListAdapter commentListAdapter;

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

        String commentAmount = null;

        //set toolbar appearance

        textView = (TextView) layout.findViewById(R.id.position);

        //get arguments passed in and handle
        Bundle bundle = getArguments();

        indexOfArtWork = bundle.getInt("indexOfArtWork");
        String title = GalleryData.GetArtWorkData(getActivity()).get(indexOfArtWork);
        textView.setText(title);

        backButton=(ImageButton) layout.findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        commentListAdapter = new CommentListAdapter(this,getActivity() ,indexOfArtWork);

        recyclerView.setAdapter(commentListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));

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

    public void getFbData() {

        comments = new ArrayList<Comment>();
        Thread getComments = new Thread(){
            public void run(){
                Bundle b1 = new Bundle();
                b1.putBoolean("summary", true);     //includes a summary in the request
                b1.putString("filter", "stream");   //gets the chronological order of comments
                b1.putString("limit", "100");        //gets max of 100
                new Request(Session.getActiveSession(), "726958990741991/comments", b1, HttpMethod.GET,
                        new Request.Callback() {
                            public void onCompleted(Response response) {
                                if (response != null) {
                                    try {
                                        //System.out.println(response.getGraphObject().toString());
                                        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +response.getGraphObject().getInnerJSONObject().getJSONObject("summary").toString());

                                        int x = response.getGraphObject().getInnerJSONObject().getJSONArray("data").length();
                                        System.out.println(x);
                                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +response.getGraphObject().getInnerJSONObject().getJSONArray("data"));
                                        for (int i = 0; i < x; i++) {
                                            //System.out.println(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message"));
                                            Comment commentInfo = new Comment();
                                            commentInfo.setPosterURL(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("id").toString());
                                            commentInfo.setPosterName(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("name").toString());
                                            commentInfo.setMessage(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message").toString());
                                            commentInfo.setTime(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("created_time").toString());
                                            comments.add(commentInfo);

                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).executeAndWait();

            }
        };
        getComments.start();
        try {
            getComments.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("FBdata" + comments);


    }

    //handler for the log in button
    public ArrayList<Comment> onClickLogin() {
        Session session = Session.getActiveSession();
        if((session==null) || session.isClosed()) {
            Session.openActiveSession(getActivity(), this, true, statusCallback);
        }

        getFbData();
        //System.out.println("onClickLogin" + comments);
        return comments;

    }

    //Display different things depending on if the user is logged in
    private void onSessionStateChange(Session session,
                                      SessionState state, Exception exception) {
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



        }
        else {  }
    }

}
