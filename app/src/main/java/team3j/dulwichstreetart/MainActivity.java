package team3j.dulwichstreetart;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;


import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by JGill on 25/01/15.
 */

public class MainActivity extends ActionBarActivity implements MaterialTabListener {

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

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
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

            materialTab.setTextColor(getResources().getColor(R.color.colorPrimaryText));

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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

        viewPager.arrowScroll(0);
    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

//        int icons[] = {R.drawable.ic_action_home,
//                R.drawable.ic_action_articles,
//                R.drawable.ic_action_personal,
//                R.drawable.ic_action_home,
//                R.drawable.ic_action_articles,
//                R.drawable.ic_action_personal,
//                R.drawable.ic_action_home,
//                R.drawable.ic_action_articles,
//                R.drawable.ic_action_personal};

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        public Fragment getItem(int num) {
            /*
            returns a fragment to place in the tab can put a switch statement to place different
             */


            switch(num){
                case 0:
                    return HomePageFragment.getInstance(num);
                case 1:
                    return GalleryFragment.getInstance(num);
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
