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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team3j.artworkdisplay.ArtworkDisplayActivity;


/**
 * Created by JGill on 25/01/15.
 */

//TODO maybe add different button to cardviews, also add final images

public class GalleryFragment extends Fragment {
    private TextView textView;

    private RecyclerView recyclerView;
    private ArrayList<String> gallerData;
    private GalleryAdapter galleryAdapter;



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
            gallerData = GalleryData.GetArtWorkData(getActivity());
        }

        GalleryAdapter.OnItemTouchListener itemTouchListener = new GalleryAdapter.OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                //tap the entire view
                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getActivity(), ArtworkDisplayActivity.class);
                i.putExtra("indexOfArtWork",position);
                startActivity(i);

            }

            @Override
            public void onButton1Click(View view, int position) {
                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();

//                Toast.makeText(getActivity(), "Clicked Button1 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButton2Click(View view, int position) {
                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();

//                Toast.makeText(getActivity(), "Clicked Button2 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        };

        int imageSet[]=GalleryData.GetArtWorkImageLocations(getActivity());

        galleryAdapter = new GalleryAdapter(getActivity(), gallerData, imageSet,itemTouchListener);

        recyclerView.setAdapter(galleryAdapter);
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        int screenOrientation = this.getResources().getConfiguration().orientation;

        if (screenOrientation==Surface.ROTATION_0 + 1 ) {
            //For portrait mode
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));


        } else if (screenOrientation==Surface.ROTATION_90 + 1 ) {
            //For landscape mode
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, 1));

            }




        Bundle bundle = getArguments();
        if (bundle != null) {

        //textView.setText("The Page Selected Is " + bundle.getInt("position"));
        }



        return layout;
    }
}