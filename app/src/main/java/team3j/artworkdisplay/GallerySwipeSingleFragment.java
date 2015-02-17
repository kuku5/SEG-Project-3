package team3j.artworkdisplay;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;

import team3j.dulwichstreetart.GalleryData;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //sort layout
        View layout = inflater.inflate(R.layout.fragment_artwork_display, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        dynamicHeightImageView= (DynamicHeightImageView) layout.findViewById(R.id.dynamic_imageview_artwork_display);


        //get arguments passed in and handle
        Bundle bundle = getArguments();
        indexOfArtWork= bundle.getInt("indexOfArtWork");
        String title=  GalleryData.GetArtWorkData(getActivity()).get(indexOfArtWork);


        //update imageview

        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), GalleryData.GetArtWorkImageLocations(getActivity())[indexOfArtWork]);
        BitmapDrawable res = new BitmapDrawable(getActivity().getResources(), bitmap);


        //update textview
        if (bundle != null) {
            textView.setText( title );

            dynamicHeightImageView.setImageDrawable(res);


        }

                    dynamicHeightImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getActivity(), FullScreenDisplay.class);
                    i.putExtra("indexOfArtWork",indexOfArtWork);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.swipeback_slide_right_in,
                            R.anim.swipeback_stack_to_back);


                }
            });



        //recycle viewer
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid1);

        artistData = GalleryData.GetArtistsData(getActivity());

        commentListAdapter = new CommentListAdapter(getActivity(), artistData);


        recyclerView.setAdapter(commentListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        //

        return layout;
    }
}