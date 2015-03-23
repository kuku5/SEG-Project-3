package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.widget.LikeView;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * @author Team 3-J
 * This is the Main Activity this creates the tabview and adds the fragment for each tab
 *
 */


public class MainActivity extends ActionBarActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    public static ViewPager viewPager;


    /**
     * onCreate inflates the layout to be viewed for the fragment and setups up the on screen elements
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create view with tab layout on create
        setContentView(R.layout.tab_layout);

        //add custom tool bar and tabs
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar = (Toolbar) findViewById(R.id.app_bar);

        //setup tabs
        setUpTabsAdapter();


    }

    /**
     * this method creates the tab layout and the viewpager which displays the fragments below the tabs
     */
    public void setUpTabsAdapter(){
        //fragment page adapter for the tabs displays a fragment and handles loading of fragments for each tab

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });
        viewPager.setOffscreenPageLimit(5);


        tabHost.setPrimaryColor(getResources().getColor(R.color.colorAccentReal));
        //adds the titles to each   tab and changes colors of text

        for (int i = 0; i < adapter.getCount(); i++) {
            String pageTitle = ""+adapter.getPageTitle(i);

            MaterialTab materialTab =
                    tabHost.newTab()
                            .setText(pageTitle)
                            .setTabListener(this);
            tabHost.addTab(materialTab);

            materialTab.setTextColor(getResources().getColor(R.color.white));

        }
        tabHost.setAccentColor(getResources().getColor(R.color.white));
    }



    /**
     * this method makes the tab view pager go to the selected tab
     * @param materialTab
     */
    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }


    /**
     *  this method gets when a tab is reselected by the user
     * @param materialTab
     */
    @Override
    public void onTabReselected(MaterialTab materialTab) {


    }

    /**
     *  this method gets when a tab is Unselected
     * @param materialTab
     */
    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LikeView.handleOnActivityResult(this, requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
    ViewPagerAdapter
    adapter for the tab layout fill each space with a fragment and manages loading
     */
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        /**
         * constructor view pager takes fragment manager as parameter
         * @param fragmentManager
         */
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);


        }


        /**
         * this returns a fragment for each tab space
         * @param num
         * @return
         */
        public Fragment getItem(int num) {

           // this returns a fragment for each tab space


            switch (num) {
                case 0:
                    return HomePageFragment.getInstance(num);
                case 1:
                    return GalleryFragment.getInstance(num);
                case 2:
                    return ArtistListFragment.getInstance(num);
                case 3:
                    return GoogleMapFragmentSmall.getInstance(num);
                case 4:
                    return VisitedTabFragment.getInstance(num);
                default:
                    return HomePageFragment.getInstance(num);


            }

        }


        /**
         * this gets the number of pages in the view pager
         * @return
         */
        @Override
        public int getCount() {
            //returns the number of tabs to be displayed
            return 5;
        }


        /**
         * this gets the title of each tab depending on the position
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            //this returns the page title for each tab from the xml strings file
            String title=getResources().getStringArray(R.array.tabs)[position];
            return Html.fromHtml("<html><body><b>"+title+"</b>  </body><html>");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        System.gc();

    }



}
