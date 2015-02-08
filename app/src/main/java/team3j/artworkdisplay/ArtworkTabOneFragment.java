package team3j.artworkdisplay;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import team3j.dulwichstreetart.ArtistListFragment;
import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.GalleryFragment;
import team3j.dulwichstreetart.HomePageFragment;
import team3j.dulwichstreetart.MapFragment;
import team3j.dulwichstreetart.R;


/**
 * Created by JGill on 06/02/15.
 */


public class ArtworkTabOneFragment extends Fragment {
    private TextView textView;
    int indexOfArtWork;
    private DynamicHeightImageView dynamicHeightImageView;
    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

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


        tabHost = (MaterialTabHost) layout.findViewById(R.id.tab_host_artwork);
        viewPager = (ViewPager) layout.findViewById(R.id.view_pager_artwork);

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

        //update tabs
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),recycleViewScrollViewListener);
//        viewPager.setAdapter(adapter);
//        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                tabHost.setSelectedNavigationItem(position);
//            }
//        });
//
//        for (int i = 0; i < adapter.getCount(); i++) {
//            MaterialTab materialTab=
//                    tabHost.newTab()
//                            .setText(adapter.getPageTitle(i))
//                            .setTabListener(this);
//            tabHost.addTab(materialTab);
//
//            materialTab.setTextColor(getResources().getColor(R.color.colorAccent));
//
//
//        }


        return layout;
    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private GalleryFragment.RecycleViewScrollViewListener recycleViewScrollViewListener;


        public ViewPagerAdapter(FragmentManager fragmentManager, GalleryFragment.RecycleViewScrollViewListener recycleViewScrollViewListener) {
            super(fragmentManager);

            this.recycleViewScrollViewListener=recycleViewScrollViewListener;
        }


        public Fragment getItem(int num) {
            /*
            returns a fragment to place in the tab
             */


            switch(num){
                case 0:
//                    HomePageFragment homePageFragment=  HomePageFragment.getInstance(num);
                    //add send through the interface here.
                    // homePageFragment.addScrollListerner();






                    return HomePageFragment.getInstance(num);

                case 1:
                    GalleryFragment galleryFragment= GalleryFragment.getInstance(num);
                    galleryFragment.setRecycleViewScrollViewListener(recycleViewScrollViewListener);


                    return galleryFragment;
                case 2:
                    return ArtistListFragment.getInstance(num);
                case 3:
                    return MapFragment.getInstance(num);
                case 4:
                    return MapFragment.getInstance(num);

                default:
                    return HomePageFragment.getInstance(num);


            }

        }


        @Override
        public int getCount() {
            return 5;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

//        private Drawable getIcon(int position) {
//            return getResources().getDrawable(icons[position]);
//        }


    }

}