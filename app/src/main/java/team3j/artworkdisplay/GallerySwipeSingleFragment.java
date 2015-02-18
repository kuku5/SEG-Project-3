package team3j.artworkdisplay;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;
import java.util.HashMap;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.HomePageFragment;
import team3j.dulwichstreetart.R;


/**
 * Created by JGill on 06/02/15.
 */


public class GallerySwipeSingleFragment extends Fragment {
    private TextView textView;
    int indexOfArtWork;
    private DynamicHeightImageView dynamicHeightImageView;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<String> artistData;
    private SliderLayout mDemoSlider;


    private CommentListAdapter commentListAdapter;

    public static GallerySwipeSingleFragment getInstance(int position, int indexOfArtWork) {
        GallerySwipeSingleFragment myFragmentTab = new GallerySwipeSingleFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("indexOfArtWork", indexOfArtWork);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_splash, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //sort layout
        View layout = inflater.inflate(R.layout.fragment_artwork_display, container, false);



        //set toolbar appearance

             //getActivity().setActionBar();
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

                    dynamicHeightImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(getActivity(), FullScreenDisplay.class);
                    i.putExtra("indexOfArtWork",indexOfArtWork);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.swipeback_slide_right_in,
                            R.anim.swipeback_stack_to_back);


                }
            });



        //recycle viewer

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_grid1);

        artistData = GalleryData.GetArtistsData(getActivity());

        commentListAdapter = new CommentListAdapter(getActivity(), artistData);


        recyclerView.setAdapter(commentListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        setupLibraryAnimations(layout);

        return layout;
    }
    private void setupLibraryAnimations(View layout) {

        mDemoSlider = (SliderLayout)layout.findViewById(R.id.slider1);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();

        file_maps.put("Conor Harrington",R.drawable.lowresconorharrington);
        file_maps.put("Walter Landscape",R.drawable.lowreswalterlandscape);
        file_maps.put("Conor Harrington",R.drawable.lowresconorharrington);
        file_maps.put("Walter Landscape",R.drawable.lowreswalterlandscape);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout

            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new OnSliderClickListener());

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        //mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
        //mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

        // mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());



    }
}

class OnSliderClickListener implements BaseSliderView.OnSliderClickListener{


    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {


    }
}
