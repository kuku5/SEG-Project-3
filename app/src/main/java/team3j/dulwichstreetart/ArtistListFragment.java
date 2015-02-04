package team3j.dulwichstreetart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JGill on 25/01/15.
 */

//TODO

public class ArtistListFragment extends Fragment {
    private TextView textView;

    private RecyclerView recyclerView;
    private ArrayList<String> galleryData;
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

    public static ArtistListFragment getInstance(int position) {
        ArtistListFragment myGalleryFragmentTab = new ArtistListFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myGalleryFragmentTab.setArguments(args);
        return myGalleryFragmentTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid);


        if (galleryData == null) {
            galleryData = GalleryData.generateSampleData(getActivity());
        }

        galleryAdapter = new GalleryAdapter(getActivity(), galleryData, imageSet);

        recyclerView.setAdapter(galleryAdapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),1,false));



        Bundle bundle = getArguments();
        if (bundle != null) {

            //textView.setText("The Page Selected Is " + bundle.getInt("position"));
        }



        return layout;
    }
}