package team3j.dulwichstreetart;

/**
 * Created by JGill on 26/01/15.
 */


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class GalleryData {

    public static final int NumberOfStreetArt = 35;

    public static ArrayList<String> generateSampleData(Activity context) {
        final ArrayList<String> data = new ArrayList<String>(NumberOfStreetArt);



        for (int i = 0; i < NumberOfStreetArt; i++) {
            data.add( context.getResources().getStringArray(R.array.artwork_list)[i]);
        }

        return data;
    }

}