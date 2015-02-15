package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import team3j.artistdisplay.ArtistDisplayActivity;

/**
 * Created by JGill on 25/01/15.
 *
 * This is the Fragment for the artist layout
 *
 */


public class ArtistListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArtistListAdapter artistListAdapter;


    public static ArtistListFragment getInstance(int position) {
        ArtistListFragment myGalleryFragmentTab = new ArtistListFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myGalleryFragmentTab.setArguments(args);
        return myGalleryFragmentTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setup layout and elements
        View layout = inflater.inflate(R.layout.fragment_gallery_list, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid);

        //get data for the List
        ArrayList<String> artistData = GalleryData.GetArtistsData(getActivity());

        //get Adapter
        artistListAdapter = new ArtistListAdapter(getActivity(), artistData, getOnArtistItemTouchListener());

        //setup recycleView
        recyclerView.setAdapter(artistListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));


        return layout;
    }

    //create click listener
    public  ArtistListAdapter.OnArtistItemTouchListener getOnArtistItemTouchListener(){
        ArtistListAdapter.OnArtistItemTouchListener onArtistItemTouchListener = new ArtistListAdapter.OnArtistItemTouchListener() {
            @Override
            public void onItemClick(View view, int position) {
                //clicked on the entire view add more methods to method to get clicks on other parts
                //put the intent here
                Intent i = new Intent(getActivity(), ArtistDisplayActivity.class);
                i.putExtra("indexOfArtist", position);
                startActivity(i);

                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();
            }
        };

        return onArtistItemTouchListener;
    }
}