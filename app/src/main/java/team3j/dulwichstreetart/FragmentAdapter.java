package team3j.dulwichstreetart;

/**
 * Created by JGill on 08/02/15.
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

    private int indexOfFirstArtwork;

    public FragmentAdapter(FragmentManager fm,int indexOfFirstArtwork) {
        super(fm);
        this.indexOfFirstArtwork=indexOfFirstArtwork-1;

    }



    @Override
    public Fragment getItem(int i) {

        return GallerySwipeSingleFragment.getInstance(i, i);

    }



    @Override
    public int getCount() {
        return 35;
    }

}