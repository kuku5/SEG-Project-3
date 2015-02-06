package team3j.artworkdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import team3j.dulwichstreetart.HomePageFragment;
import team3j.dulwichstreetart.MapFragment;
import team3j.dulwichstreetart.R;


public class ArtworkDisplayActivity extends ActionBarActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_layout);

        toolbar = (Toolbar) findViewById(R.id.app_bar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        Intent myIntent = getIntent(); // gets the previously created intent
        int indexOfArtWork = myIntent.getIntExtra("indexOfArtWork", 0);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), indexOfArtWork);

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            MaterialTab materialTab =
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this);
            tabHost.addTab(materialTab);

            materialTab.setTextColor(getResources().getColor(R.color.colorPrimaryText));

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artwork_display, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {


        int indexOfArtWork;

        public ViewPagerAdapter(FragmentManager fragmentManager, int indexOfArtWork) {
            super(fragmentManager);
            this.indexOfArtWork = indexOfArtWork;
        }


        public Fragment getItem(int num) {
            /*
            returns a fragment to place in the tab can put a switch statement to place different
             */


            switch (num) {
                case 0:
                    return ArtworkTabOneFragment.getInstance(num,indexOfArtWork);
                case 1:
                    return ArtworkTabOneFragment.getInstance(num,indexOfArtWork);
                case 2:
                    return ArtworkTabOneFragment.getInstance(num,indexOfArtWork);
                case 3:
                    return ArtworkTabOneFragment.getInstance(num,indexOfArtWork);
                case 4:
                    return ArtworkTabOneFragment.getInstance(num,indexOfArtWork);

                default:
                    return ArtworkTabOneFragment.getInstance(num,indexOfArtWork);


            }

        }


        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.artworkdisplaytabs)[position];
        }

//        private Drawable getIcon(int position) {
//            return getResources().getDrawable(icons[position]);
//        }


    }


}
