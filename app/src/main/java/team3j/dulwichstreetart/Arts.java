package team3j.dulwichstreetart;

import android.content.res.Resources;

import com.google.android.gms.maps.model.LatLng;


/**
 * Created by Ananda on 10/02/2015.
 */
public class Arts {

    Art arts[] = new Art[19];




    public Arts()
    {



        Resources res= Resources.getSystem();
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
    }

    public Art[] getArts()
    {
        return arts;
    }
}
