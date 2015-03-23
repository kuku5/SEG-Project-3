package team3j.dulwichstreetart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author Team 3-J
 * This is the Fragment for the artist layout
 */

public class ArtistListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArtistListAdapter artistListAdapter;


    /**
     * returns an instance of the fragment
     *
     * @param position position of tab
     * @return the instance of the fragment
     */
    public static ArtistListFragment getInstance(int position) {
        ArtistListFragment myGalleryFragmentTab = new ArtistListFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myGalleryFragmentTab.setArguments(args);
        return myGalleryFragmentTab;
    }

    /**
     * Creates the Artist fragment with the layouts and adapter
     *
     * @param inflater LayoutInflator
     * @param container Viewgroup
     * @param savedInstanceState bundle of instance
     * @return layout view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setup layout and elements
        View layout = inflater.inflate(R.layout.fragment_gallery_list, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid);
        setRetainInstance(true);

        //get data for the List
        ArrayList<Artist> artistData = GalleryData.GetArtistsDataReal(getActivity());

        //get Adapter
        artistListAdapter = new ArtistListAdapter(getActivity(), artistData);

        //setup recycleView
        recyclerView.setAdapter(artistListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));


        return layout;
    }


    @Override
    public void onDestroyView() {
        System.gc();
        super.onDestroyView();
    }
}