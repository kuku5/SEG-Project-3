package team3j.dulwichstreetart;

/**
 * Created by JGill on 08/02/15.
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import team3j.artworkdisplay.ArtworkTabOneFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private int indexOfFirstArtwork;
    private final int colors[] = { Color.parseColor("#FA5F67"),
            Color.parseColor("#D973D5"), Color.parseColor("#6D64CC"),
            Color.parseColor("#64CC9D"), Color.parseColor("#E6DD7A") };

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


        return ArtworkTabOneFragment.getInstance(i,indexOfFirstArtwork);

    }

    @Override
    public int getCount() {
        return 35;
    }

}