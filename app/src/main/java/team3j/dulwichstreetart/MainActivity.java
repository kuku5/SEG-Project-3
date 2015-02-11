package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by JGill on 25/01/15.
 *
 * This Activity
 *
 *
 *
 */

//TODO This


public class MainActivity extends ActionBarActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;
    private boolean actionBarVisible=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create view with tab layout on create
        setContentView(R.layout.tab_layout);

        //add custom tool bar and tabs
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHideOnContentScrollEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        toolbar.setCollapsible(true);

        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ActionBar actionBar = getSupportActionBar();

        actionBar.hide();
        //fragment page adapter for the tabs displays a fragment and handles loading of fragments for each tab


//        cardView.setMinimumWidth(width);



        GalleryFragment.RecycleViewScrollViewListener recycleViewScrollViewListener= new GalleryFragment.RecycleViewScrollViewListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

            @Override
            public void ScrollUp(int dy) {
//                getSupportActionBar().setHideOffset(dy);
//                toolbar.setSc
                if(!actionBarVisible){

//                    ActionBar actionBar = getSupportActionBar();
//                    actionBar.show();
////                    actionBarVisible=true;
                }

//                toolbar.setScrollY(dy);
//                toolbar.collapseActionView();
                Log.d("scrolling", "going up" + dy);

            }

            @Override
            public void ScrollDown(int dy) {
                if(actionBarVisible){
////
                    ActionBar actionBar = getSupportActionBar();
//                    actionBar.hide();
//                    tabHost.setVisibility(View.INVISIBLE);
//                    actionBarVisible=false;
                }
                Log.d("scrolling", "going down" + dy);


            }
        };

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),recycleViewScrollViewListener);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            MaterialTab materialTab=
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this);
            tabHost.addTab(materialTab);

            materialTab.setTextColor(getResources().getColor(R.color.colorAccent));


        }







        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

//        if (id == R.id.navigate) {
//            startActivity(new Intent(this, HelpActivity.class));
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {


        //viewPager.arrowScroll(0);
    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    /*
    ViewPagerAdapter
    adapter for the tab layout fill each space with a fragment and manages loading
     */


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
