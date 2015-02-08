package team3j.artworkdisplay;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.R;


/**
 * Created by JGill on 06/02/15.
 */


public class ArtworkTabOneFragment extends Fragment {
    private TextView textView;
    private ScrollView scrollView;
    int indexOfArtWork;
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
        View layout = inflater.inflate(R.layout.fragment_artwork_display, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        indexOfArtWork= bundle.getInt("indexOfArtWork");
        String title=  GalleryData.GetArtWorkData(getActivity()).get(indexOfArtWork);
        getActivity().setTitle(title);

        if (bundle != null) {


            textView.setText("Art  " +    title );

        }


        return layout;
    }
}