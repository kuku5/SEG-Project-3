package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * @author Team 3-J
 * Gallery Fragment for to be displayed in the tab Fragment
 */

//TODO maybe add different button to cardviews, also add final images

public class VisitedTabFragment extends Fragment implements MaterialTabListener {

    private RecyclerView recyclerView;
    private VisitedAdapter visitedAdapter;


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
        setRetainInstance(true);

        // add data




            recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_visited);

            //create recycle view Adapter

            //set adapter
            visitedAdapter = new VisitedAdapter(getActivity(), getVisitedClickListener(), GalleryData.get().getArtworkList());
            recyclerView.setAdapter(visitedAdapter);

            //Set Layout Animation
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
                @Override
                protected int getExtraLayoutSpace(RecyclerView.State state) {
                    return 200;
                }
            };


            recyclerView.setLayoutManager(linearLayoutManager);


        return layout;
    }


    //return a Click Listener for the Recycle View
    public VisitedAdapter.OnItemTouchListener getVisitedClickListener(){
        VisitedAdapter.OnItemTouchListener itemTouchListener = new VisitedAdapter.OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                //tap the entire view
                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();
                //open Activity to display for Artwork Display
            }
        };

        return itemTouchListener;

    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }


    @Override
    public void onStop() {

        visitedAdapter.recycleBitmap();

        super.onStop();
    }



}