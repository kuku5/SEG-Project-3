package team3j.dulwichstreetart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
     *
     * @param position
     * @return
     */
    public static ArtistListFragment getInstance(int position) {
        ArtistListFragment myGalleryFragmentTab = new ArtistListFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myGalleryFragmentTab.setArguments(args);
        return myGalleryFragmentTab;
    }

    /**
     * Creates the Artist fragment with the layous and adapter
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
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


}