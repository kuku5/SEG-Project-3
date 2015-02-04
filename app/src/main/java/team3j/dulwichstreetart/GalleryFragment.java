package team3j.dulwichstreetart;


import android.database.DataSetObserver;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;

/**
 * Created by JGill on 25/01/15.
 */

//TODO

public class GalleryFragment extends Fragment {
    private TextView textView;

    private RecyclerView recyclerView;
    private ArrayList<String> gallerData;
    private GalleryAdapter galleryAdapter;

    private int imageSet[] = {R.drawable.thethreegrace,
            R.drawable.emanuelphilibert,
            R.drawable.jesuscarryingthecross,
            R.drawable.judgementofparis,
            R.drawable.pharaohsring,
            R.drawable.stcatherine,
            R.drawable.conorharrington,
            R.drawable.davidshillinglawvandyck,
            R.drawable.judithwiththeheadofholofernes,
            R.drawable.europaandthebull,
            R.drawable.vasewithflowers,
            R.drawable.thevirginoftherosary,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross,
            R.drawable.jesuscarryingthecross

    };

    public static GalleryFragment getInstance(int position) {
        GalleryFragment myGalleryFragmentTab = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myGalleryFragmentTab.setArguments(args);
        return myGalleryFragmentTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid);


        if (gallerData == null) {
            gallerData = GalleryData.generateSampleData(getActivity());
        }

        galleryAdapter = new GalleryAdapter(getActivity(), gallerData, imageSet);

        recyclerView.setAdapter(galleryAdapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));



        Bundle bundle = getArguments();
        if (bundle != null) {

        //textView.setText("The Page Selected Is " + bundle.getInt("position"));
        }



        return layout;
    }
}