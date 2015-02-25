package team3j.artworkdisplay;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

import java.util.ArrayList;
import java.util.HashMap;

import team3j.artistdisplay.ArtistDisplayActivity;
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
    private DynamicHeightImageView dynamicHeightImageView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<String> artistData;
    private SliderLayout mDemoSlider;
    private LinearLayout image1;
    private LinearLayout image2;

    private ArrayList<Comment> comments;

    private boolean firstImage=true;
    private CommentListAdapter commentListAdapter;
    private ArtistListAdapter artistListAdapter;

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

        TextView commentAmount = (TextView) layout.findViewById(R.id.commentAmount);

        //set toolbar appearance

//        //getActivity().setActionBar();
        textView = (TextView) layout.findViewById(R.id.position);
//        //dynamicHeightImageView = (DynamicHeightImageView) layout.findViewById(R.id.dynamic_imageView);
//        dynamicHeightImageView = (DynamicHeightImageView) layout.findViewById(R.id.dynamic_imageview_artwork_display);
//
//        //get arguments passed in and handle
        Bundle bundle = getArguments();

        indexOfArtWork = bundle.getInt("indexOfArtWork");
        String title = GalleryData.GetArtWorkData(getActivity()).get(indexOfArtWork);
         textView.setText(title);


        //update imageview

//        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), GalleryData.GetArtWorkImageLocations(getActivity())[indexOfArtWork]);
//        BitmapDrawable res = new BitmapDrawable(getActivity().getResources(), bitmap);
//
//
//        //update textview
//        if (bundle != null) {
//            textView.setText(title);
//            dynamicHeightImageView.setImageDrawable(res);
//
//        }
//



        //recycle viewer
        final Session session = Session.getActiveSession();
        comments = new ArrayList<Comment>();
        Thread getComments = new Thread(){
            public void run(){
                Bundle b1 = new Bundle();
                b1.putBoolean("summary", true);     //includes a summary in the request
                b1.putString("filter", "stream");   //gets the chronological order of comments
                b1.putString("limit", "100");        //gets max of 100
                new Request(session, "726958990741991/comments", b1, HttpMethod.GET,
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
                                            commentInfo.setPosterName(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).getJSONObject("from").get("name").toString());
                                            commentInfo.setMessage(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message").toString());
                                            commentInfo.setTime(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("created_time").toString());
                                            comments.add(commentInfo);

                                        }
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                }
                            }
                        }).executeAndWait();

            }
        };

        if(!(session==null) && session.isOpened()) {
            getComments.start();
            try {
                getComments.join();
                commentAmount.setText(comments.size() + " comments");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            commentAmount.setText("Please log in to Facebook to view comments");

        }


        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid1);


        artistData = GalleryData.GetArtistsData(getActivity());
        ArrayList<String> artistData = GalleryData.GetArtistsData(getActivity());
//
//        ArtistListAdapter.OnArtistItemTouchListener onArtistItemTouchListener = new ArtistListAdapter.OnArtistItemTouchListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //clicked on the entire view add more methods to method to get clicks on other parts
//                //put the intent here
//                Intent i = new Intent(getActivity(), ArtistDisplayActivity.class);
//                i.putExtra("indexOfArtist", position);
//                startActivity(i);
//
//                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();
//            }
//        };
//        //get Adapter
//        artistListAdapter = new ArtistListAdapter(getActivity(), artistData, onArtistItemTouchListener);
//
//        //setup recycleView
//        recyclerView.setAdapter(artistListAdapter);





        commentListAdapter = new CommentListAdapter(getActivity(), comments,indexOfArtWork);


        recyclerView.setAdapter(commentListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));

        return layout;
    }

}
