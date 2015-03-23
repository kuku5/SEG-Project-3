package team3j.dulwichstreetart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import team3j.artworkdisplay.GallerySwipeSingleFragment;

/**
 * @author Team 3-J
 *
 * This is the Fragment Adapter for the Large Gallery Display
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {


    /**
     * This is the Constructor for the FragmentAdapter that passes in the fragment manager for the
     * FragmentStatePagerAdapter to handle the loading of fragments
     *
     * @param fm fragment manager
     */
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    /**
     * this gets the correct artwork fragment for each position in the viewpager
     *
     * @param i item position
     * @return position in the viewpager
     */
    @Override
    public Fragment getItem(int i) {
        return GallerySwipeSingleFragment.getInstance(i, i);
    }

    /**
     * this returns the count of the artwork
     *
     * @return the count of the artwork
     */
    @Override
    public int getCount() {
        return GalleryData.get().getArtworkList().size();
    }

}