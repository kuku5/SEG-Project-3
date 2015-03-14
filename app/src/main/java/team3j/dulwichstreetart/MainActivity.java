package team3j.dulwichstreetart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * @author Team 3-J
 * This is the Main Activity this creates the tabview and adds the fragment for each tab
 *
 */

//TODO This


public class MainActivity extends ActionBarActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    public static ViewPager viewPager;

    private HomePageFragment.OnClickInsideFragment onClickInsideFragment;
    private GoogleMapFragmentSmall googleMapFragmentSmall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create view with tab layout on create
        setContentView(R.layout.tab_layout);

        //add custom tool bar and tabs
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar = (Toolbar) findViewById(R.id.app_bar);



        updateToolBarLayout();//update tool bar
        setUpTabsAdapter();//setup tabs

        onClickInsideFragment= new HomePageFragment.OnClickInsideFragment() {
            @Override
            public void onCardViewTap() {
                viewPager.setCurrentItem(3);

            }
        };

    }

    public void setUpTabsAdapter(){
        //fragment page adapter for the tabs displays a fragment and handles loading of fragments for each tab

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),onClickInsideFragment);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });
        viewPager.setOffscreenPageLimit(3);


        tabHost.setPrimaryColor(getResources().getColor(R.color.colorAccentReal));
        //adds the titles to each tab and changes colors of text

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



    public void updateToolBarLayout(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        toolbar.setCollapsible(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccentReal));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
        Log.d("testing2", "opened tab " + materialTab.getPosition());
    }



    @Override
    public void onTabReselected(MaterialTab materialTab) {


    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    /*
    ViewPagerAdapter
    adapter for the tab layout fill each space with a fragment and manages loading
     */

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {


        public ViewPagerAdapter(FragmentManager fragmentManager,HomePageFragment.OnClickInsideFragment onClickInsideFragment1) {
            super(fragmentManager);

        }


        public Fragment getItem(int num) {

           // this returns a fragment for each tab space




            switch (num) {
                case 0:
                    HomePageFragment homePageFragment= HomePageFragment.getInstance(num);
                    homePageFragment.setupClickInsideFragment(onClickInsideFragment);

                    return homePageFragment;
                case 1:
                    return GalleryFragment.getInstance(num);
                case 2:
                    return ArtistListFragment.getInstance(num);
                case 3:

                     googleMapFragmentSmall= GoogleMapFragmentSmall.getInstance(num);

                    return googleMapFragmentSmall;
                case 4:



                    return VisitedTabFragment.getInstance(num);

                default:
                    return HomePageFragment.getInstance(num);


            }

        }


        @Override
        public int getCount() {
            //returns the number of tabs to be displayed
            return 5;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            //this returns the page title for each tab from the xml strings file
            String title=getResources().getStringArray(R.array.tabs)[position];
            return Html.fromHtml("<html><body><b>"+title+"</b>  </body><html>");
        }

    }


}
