package team3j.dulwichstreetart;

/**
 * Created by JGill on 08/02/15.
 *
 * This is the Fragment Adapter for the Large Gallery Display
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import team3j.artworkdisplay.GallerySwipeSingleFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private int indexOfFirstArtwork;

    public FragmentAdapter(FragmentManager fm,int indexOfFirstArtwork) {
        super(fm);
        this.indexOfFirstArtwork=indexOfFirstArtwork-1;
    }



    @Override
    public Fragment getItem(int i) {
        ++indexOfFirstArtwork;
        if(indexOfFirstArtwork>=35){
            indexOfFirstArtwork=0;
        }


        return GallerySwipeSingleFragment.getInstance(i, indexOfFirstArtwork);

    }

    @Override
    public int getCount() {
        return 35;
    }

}