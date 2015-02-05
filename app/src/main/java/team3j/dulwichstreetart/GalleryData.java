package team3j.dulwichstreetart;

/**
 * Created by JGill on 26/01/15.
 */


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

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

}