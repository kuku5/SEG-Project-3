package team3j.dulwichstreetart;


import android.app.Activity;
import android.content.Context;

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


    public static ArrayList<Art> visited = new ArrayList<>();
    public static ArrayList<Art> toVisit = new ArrayList<>();



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



    public static ArrayList<Art> GetGalleryImage(){

        //The Struggle was real
        ArrayList<Art> artArrayList=new ArrayList<>();
        artArrayList.add(new Art("","","","","",R.drawable.lowresstcatherinestone,R.drawable.lowresstcatherinestone));
        //Agent Provocateur
        artArrayList.add(new Art("Happy Hour","Agent Provocateur","The Three Graces","Sir Peter Paul Rubens ","AP is the only stencil artist in this project.  Once cut, stencil paintings can be reproduced easily, by anyone.  The work of art AP chose to interpret was a sketch by Rubens, highly prized because it is by him alone, whereas the finished piece would have had a great deal of workshop input.",R.drawable.lowresagentprovocateur,R.drawable.lowresagentprovocateur));
        //Ben Wilson
        artArrayList.add(new Art("Saint Catherine","Ben Wilson","St Catherine of Siena","Carlo Dolci","",R.drawable.lowresstcatherinestone,R.drawable.lowresstcatherinestone));
        //Conor Harrington
        artArrayList.add(new Art("Fight Club","Conor Harrington","The Massacre of The Innocents","Charles Le Brun","Harrington’s fighting men in Regency costume continue their fight on 4 more walls in the USA and Costa Rica.  The bald guy wins.",R.drawable.lowresconorharrington,R.drawable.lowresconorharrington));
        //David Shillinglaw
        artArrayList.add(new Art("Samson and Delilah 2013","David Shillinglaw","Samson and Delilah 1618","Anthony van Dyck","",R.drawable.lowresdavidshillinglaw,R.drawable.lowresdavidshillinglaw));
        //Faith47
        artArrayList.add(new Art("Europa and the Winged Bird","Faith47","Europa and the Bull","Guido Reni","The absence of the bull and the introduction of a guiding bird are suggestive of a premonition of the abduction to come, her inner emotions and thoughts or perhaps a new interpretation of the ancient fable. ",R.drawable.lowreseuropaandthebull,R.drawable.lowreseuropaandthebull));
        //Inkie, Pure Evil, AP is going to have custom view for each for zoomed in view of each artist  NEEDS A CUSTOM setup
        artArrayList.add(new Art("Old Nun's Head Mural","Inkie, Pure Evil and AP","St Catherine of Siena","Carlo Dolci","Three different internationally renowned street artists have painted a panel each on The Old Nun’s Head pub in Nunhead.  They have taken their inspiration from the same painting in Dulwich Picture Gallery, one of a nun’s head, St Catherine of Siena, painted in 1665 by Carlo Dolci. ",R.drawable.lowresoldnunsheadmurals,R.drawable.lowresoldnunsheadmurals));
        //MadC need better scaled image may crop width    NEEDS A CUSTOM setup
        artArrayList.add(new Art("Vase With Flowers 2013","MadC","Vase with Flowers c1720","Jan van Huysum","One of only two female artist in the project, MadC, took elements from a 17th century Dutch flower painting, the insects, tulips and butterflies, and added them to her tag, creating an astonishingly beautiful back to a tennis practice wall in Belair Park.  ",R.drawable.lowresmadc,R.drawable.lowresmadc));
        //Mear One
        artArrayList.add(new Art("New World Revolution 2013","Mear One","The Madonna of The Rosary 1670-80","Bartolomé Esteban Murillo","Mear One modernised Murillo’s delicate Virgin of the Rosary by turning her and her child into powerful, mixed race  characters giving power salutes. ‘Equality’ is inscribed on her halo.  ",R.drawable.lowresmearone,R.drawable.lowresmearone));
        ///Phlegm
        artArrayList.add(new Art("The Triumph of David 2013","Phlegm","The Triumph of David 1628,31","Nicolas Poussin","The 9 year old son of the owner of the wall helped Phlegm paint his picture.  His mum said, ‘My son loved giving him a hand and I am so grateful to him for letting him have an input.  He feels proud to be part of it and now feels very protective towards the wall.’ ",R.drawable.lowresphlegm,R.drawable.lowresphlegm));
        //Reka
        artArrayList.add(new Art("Europa and the Bull c1700","Reka","Europa and the Bull 2013","Guido Reni","Reka’s  Europa from Reni’s ‘Europa and the Bull’ beautifies a wall of a South London pub.  Her hair curls delicately around its windows.",R.drawable.lowresreka,R.drawable.lowresreka));
        //Remi Rough and System // NEEDS A CUSTOM setup
        artArrayList.add(new Art("Girl at a Window 2013","Remi Rough and System","Girl at a Window 1645","Rembrandt van Rijn","System’s modernised ‘Girl at a Window’ by Rembrandt, leans out from Remi Roughs abstraction of ‘The Triumph of David’ by Poussin.  This ordinary young girl in a hoodie and cap with spray cans at her elbow, parallels Rembrandt’s 17th century serving girl.  ",R.drawable.lowresremiroughandsystem,R.drawable.lowresremiroughandsystem));
        //ROA
        artArrayList.add(new Art("Landscape with Sportsmen and Game 2013","ROA","Landscape with Sportsmen and Game c.1665","Adam Pynacker","ROA chose a detail of Pynacker’s ‘Sportsman with Game’ - a shitting dog that Pynacker placed in the foreground of his painting.  ROA’s version has provoked some criticism, people being more shockable in the 21st century than in the 17th century.",R.drawable.lowresstcatherinestone,R.drawable.lowresstcatherinestone));
        //RUN
        artArrayList.add(new Art("","","","","",R.drawable.lowresdogrun,R.drawable.lowresdogrun));
        artArrayList.add(new Art("The Translation of Saint Rita of Cascia","RUN","The Translation of Saint Rita of Cascia c1630","Nicolas Poussin","",R.drawable.lowresstcatherinestone,R.drawable.lowresstcatherinestone));



        return artArrayList;
    }


    //nandu change this one below to match the titles

    public static int[] GetArtWorkImageLocations() {
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