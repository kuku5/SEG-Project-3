package team3j.artworkdisplay;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.R;


/**
 * Created by JGill on 06/02/15.
 */


public class ArtworkTabOneFragment extends Fragment {
    private TextView textView;
    int indexOfArtWork;
    private DynamicHeightImageView dynamicHeightImageView;
    private Toolbar toolbar;

    public static ArtworkTabOneFragment getInstance(int position, int indexOfArtWork) {
        ArtworkTabOneFragment myFragmentTab = new ArtworkTabOneFragment();
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


        return layout;
    }
}