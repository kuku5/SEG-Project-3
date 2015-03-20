package team3j.dulwichstreetart;

/**
 * @author Team 3-J
 *
 * This is the Fragment Adapter for the Large Gallery Display
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import team3j.artworkdisplay.GallerySwipeSingleFragment;


public class FragmentAdapter extends FragmentStatePagerAdapter {


    /**
     * This is the Constructor for the FragmentAdapter that passes in the fragment manager for the
     * FragmentStatePagerAdapter to handle the loading of fragments
     * @param fm
     */
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    /**
     * this gets the correct artwork fragment for each position in the viewpager
     * @param i item position
     * @return position in the viewpager
     */
    @Override
    public Fragment getItem(int i) {

        return GallerySwipeSingleFragment.getInstance(i, i);

    }


    /**
     * this returns the count of the artwork
     * @return the count of the artwork
     */
    //todo change this to final number
    @Override
    public int getCount() {
        return 22;
    }

}