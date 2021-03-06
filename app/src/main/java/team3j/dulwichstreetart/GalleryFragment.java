package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author Team 3-J
 * Gallery Fragment for to be displayed in the tab Fragment
 */

public class GalleryFragment extends Fragment {

    public static RecyclerView recyclerView;

    int positionToScroll = 0;

    /**
     * This returns an Instance of Gallery Fragment to be displayed in Gallery Tab
     *
     * @param position
     * @return
     */
    public static GalleryFragment getInstance(int position) {
        GalleryFragment myGalleryFragmentTab = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myGalleryFragmentTab.setArguments(args);
        return myGalleryFragmentTab;
    }


    /**
     * Creates the view of the gallery with the appropriate layouts and given adapter
     *
     * @param inflater Layout inflator
     * @param container view group container
     * @param savedInstanceState bundle of savedinstance
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get layout and elements
        View layout = inflater.inflate(R.layout.fragment_gallery_list, container, false);
        setRetainInstance(true);


        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid);

        //get Image Locations and descriptions
        ArrayList<Art> galleryData = GalleryData.get().getArtworkList();
        //create recycle view Adapter


        updateLayoutForRotation();

        //set adapter
        recyclerView.setAdapter(new GalleryAdapter(getActivity(), galleryData, getGalleryClickListener()));

        //adapt view for the Screen orientation


        return layout;
    }


    /**
     * This method creates and returns a Gallery CLick Listener for the Gallery recycler view
     *
     * @return itemTouchListener - Gallery ClickListener
     */
    public GalleryAdapter.OnItemTouchListener getGalleryClickListener() {
        GalleryAdapter.OnItemTouchListener itemTouchListener = new GalleryAdapter.OnItemTouchListener() {

            @Override
            public void onCardViewTap(View view, int position) {
                //open Activity to display for Artwork Display
                Intent i = new Intent(getActivity(), GallerySwipeHolder.class);
                i.putExtra("indexOfArtWork", position);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.swipeback_slide_right_in,
                        R.anim.swipeback_stack_to_back);

                positionToScroll = position;
            }
        };

        return itemTouchListener;

    }


    /**
     * This method adapt recycler view for the Screen Rotation by updating
     * the Layout Manager of the recycler view
     */
    public void updateLayoutForRotation() {
        //adapt recycle view for the Screen Rotation
        int screenOrientation = this.getResources().getConfiguration().orientation;

        if (screenOrientation == Surface.ROTATION_0 + 1) {
            //For portrait mode
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        } else if (screenOrientation == Surface.ROTATION_90 + 1) {
            //For landscape mode
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, 1));
        }
        System.gc();

    }


    /**
     *
     */
    @Override
    public void onDestroyView() {

        System.gc();
        super.onDestroyView();

    }

}