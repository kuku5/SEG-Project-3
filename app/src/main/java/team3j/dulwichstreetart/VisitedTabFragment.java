package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by JGill on 25/01/15.
 * Gallery Fragment for to be displayed in the tab Fragment
 */

//TODO maybe add different button to cardviews, also add final images

public class VisitedTabFragment extends Fragment {

    private RecyclerView recyclerView;



    public static VisitedTabFragment getInstance(int position) {
        VisitedTabFragment visitedTabFragment = new VisitedTabFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        visitedTabFragment.setArguments(args);
        return visitedTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get layout and elements
        View layout = inflater.inflate(R.layout.fragment_visited_tab, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_visited);

        GoogleMapFragmentSmall.toVisit.size();
        //get Image Locations and descriptions
        ArrayList<String> galleryData = GalleryData.GetArtWorkData(getActivity());
        int imageSet[] = GalleryData.GetArtWorkImageLocations();

        //create recycle view Adapter

        //set adapter
        recyclerView.setAdapter(new GalleryAdapter(getActivity(), galleryData, imageSet, getGalleryClickListener()));

        //adapt view for the Screen orientation
        updateLayoutForRotation();

        return layout;
    }


    //return a Click Listener for the Recycle View
    public GalleryAdapter.OnItemTouchListener getGalleryClickListener(){
        GalleryAdapter.OnItemTouchListener itemTouchListener = new GalleryAdapter.OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                //tap the entire view
                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();
                //open Activity to display for Artwork Display
                Intent i = new Intent(getActivity(), GallerySwipeHolder.class);
                i.putExtra("indexOfArtWork", position);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.swipeback_slide_right_in,
                        R.anim.swipeback_stack_to_back);

            }
        };

        return itemTouchListener;

    }

    //adapt recycle view for the Screen Rotation
    public void updateLayoutForRotation(){
        int screenOrientation = this.getResources().getConfiguration().orientation;

        if (screenOrientation == Surface.ROTATION_0 + 1) {
            //For portrait mode
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));


        } else if (screenOrientation == Surface.ROTATION_90 + 1) {
            //For landscape mode
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, 1));

        }

    }


}