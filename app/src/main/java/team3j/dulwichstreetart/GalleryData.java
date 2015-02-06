package team3j.dulwichstreetart;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JGill on 26/01/15.
 *
 * This returns the data for the specific artwork also stores array of images
 */
//TODO maybe change getimages to return bitmaps

public class GalleryData {


    public static ArrayList<String> GetArtWorkData(Activity context) {
        final ArrayList<String> data = new ArrayList<String>(35);



        for (int i = 0; i < 35; i++) {
            data.add( context.getResources().getStringArray(R.array.artwork_list)[i]);
        }

        return data;
    }

    public static ArrayList<String> GetArtistsData(Activity context) {
        final ArrayList<String> data = new ArrayList<String>(18);



        for (int i = 0; i < 18; i++) {
            data.add( context.getResources().getStringArray(R.array.artists)[i]);
        }

        return data;
    }

    public static int[] GetArtWorkImageLocations(Activity context) {
        // return image location potentially could return bitmaps ready to go into Imageview

        final int imageSet[] = {R.drawable.thethreegrace,
                R.drawable.emanuelphilibert,
                R.drawable.jesuscarryingthecross,
                R.drawable.judgementofparis,
                R.drawable.pharaohsring,
                R.drawable.stcatherine,
                R.drawable.conorharrington,
                R.drawable.davidshillinglawvandyck,
                R.drawable.judithwiththeheadofholofernes,
                R.drawable.europaandthebull,
                R.drawable.vasewithflowers,
                R.drawable.thevirginoftherosary,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross,
                R.drawable.jesuscarryingthecross

        };

        return imageSet;
    }

}