package team3j.dulwichstreetart;


import android.app.Activity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by JGill on 26/01/15.
 *
 * This returns the data for the specific artwork also stores array of images
 * returns data from xml strings
 */
//TODO maybe change getimages to return bitmaps

public class GalleryData {

    // nandu u need to update this array in xml
    public static ArrayList<String> GetArtWorkData(Activity context) {
        final ArrayList<String> data = new ArrayList<String>(35);

        for (int i = 0; i < 35; i++) {
            //go to this
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




    //nandu change this one below to match the titles

    public static int[] GetArtWorkImageLocations(Activity context) {
        // return image location potentially could return bitmaps ready to go into Imageview

        final int imageSet[] = {
                R.drawable.lowresstikguardianangel,
                R.drawable.lowresstikthreeboys,
                R.drawable.lowresdogrun,
                R.drawable.lowresphlegm,
                R.drawable.lowresruntriumphofdavid,
                R.drawable.lowrespablodelgadoseven,
                R.drawable.lowresmearone,
                R.drawable.lowresconorharrington,
                R.drawable.lowresapnunshead,
                R.drawable.lowrespureevilnunshead,
                R.drawable.lowresoldnunsheadmurals,
                R.drawable.lowresstcatherinefloor,
                R.drawable.lowresstcatherinestone,
                R.drawable.lowresdavidshillinglaw,
                R.drawable.lowreseuropaandthebull,
                R.drawable.lowresmadc,
                R.drawable.lowrespablodelgadoone,
                R.drawable.lowrespablodelgadothree,
                R.drawable.lowrespablodelgadosix,
                R.drawable.lowresjesuscarryingthecross,
                R.drawable.lowrespablodelgadofour,
                R.drawable.lowreseuropaandthebull,
                R.drawable.lowresremiroughandsystem,
                R.drawable.lowresdoginlandscape,
                R.drawable.lowresrunstrita,
                R.drawable.lowresstikacoupleinlandscape,
                R.drawable.lowresstikelizaandmarydavidsontilly,
                R.drawable.lowresstikelizabethandmary,
                R.drawable.lowresstikmoodyandsons,
                R.drawable.lowresstikfallofman,
                R.drawable.lowresagentprovocateur,
                R.drawable.lowresnoir,
                R.drawable.lowreswalterlandscape,
                R.drawable.lowresemmanuelphilibert,
                R.drawable.lowresjesuscarryingthecross,
                R.drawable.lowresjudgementofparis,
                R.drawable.lowrespharaohsring

        };

        return imageSet;
    }

    public static Art[] getMapArtwork(Activity context) {
       final Art arts[] = new Art[19];

        arts[0] = new Art("Roa 2013",(new LatLng(51.467224, -0.072160)),R.drawable.art0);
        arts[1] = new Art("Remi Rough & System 2013",(new LatLng(51.461675, -0.079872)),R.drawable.art0) ;
        arts[2] = new Art("Conor Harrington 2013",(new LatLng(51.460628, -0.075084)),R.drawable.art2) ;
        arts[3] = new Art("Stik 2012",(new LatLng(51.456109, -0.076058)),R.drawable.art3) ;
        arts[4] = new Art("Stik 2012",(new LatLng(51.456219, -0.076794)),R.drawable.art4) ;
        arts[5] = new Art("Beerens, Christiaan Nagel",(new LatLng(51.455990, -0.076442)),R.drawable.art0) ;
        arts[6] = new Art("Mear One",(new LatLng(51.454552, -0.077042)),R.drawable.art6) ;
        arts[7] = new Art("Stik 2012",(new LatLng(51.453060, -0.078765)),R.drawable.art7) ;
        arts[8] = new Art("Phlegm 2013",(new LatLng(51.451632, -0.071597)),R.drawable.art8) ;
        arts[9] = new Art("Nunca 2013",(new LatLng(51.449235, -0.074160)),R.drawable.art0) ;
        arts[10] = new Art("Stik 2012",(new LatLng(51.446414, -0.073434)),R.drawable.art10) ;
        arts[11] = new Art("Stik 2012",(new LatLng(51.447416, -0.076093)),R.drawable.art11) ;
        arts[12] = new Art("Stik 2012",(new LatLng(51.445317, -0.079216)),R.drawable.art12);
        arts[13] = new Art("Thierry Noir 2013",(new LatLng(51.445228, -0.079050)),R.drawable.art13) ;
        arts[14] = new Art("The Inspiration Dulwich Picture Gallery 1811",(new LatLng(51.445988, -0.086360)),R.drawable.art0) ;
        arts[15] = new Art("David Shillinglaw 2013",(new LatLng(51.452656, -0.102931)),R.drawable.art15) ;
        arts[16] = new Art("MadC 2013",(new LatLng(51.441452, -0.091381)),R.drawable.art16) ;
        arts[17] = new Art("Reka 2013",(new LatLng(51.427863, -0.086302)),R.drawable.art17) ;
        arts[18] = new Art("RUN",(new LatLng(51.437918, -0.054667)),R.drawable.art18) ;

        return arts;
    }
}