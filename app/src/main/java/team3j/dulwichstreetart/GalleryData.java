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
    private ArrayList<Art> artworkList;
    private static GalleryData mGalleryData;


    public static GalleryData get() {
        return mGalleryData;
    }

    public GalleryData() {
        artworkList=GetGalleryData();
    }


    public static GalleryData create() {
        if (mGalleryData == null) {
            mGalleryData = new GalleryData();
        }

        return mGalleryData;
    }

    public ArrayList<Art> getArtworkList() {
        return artworkList;
    }

    public ArrayList<Art> GetGalleryData(){

        //The Struggle was real
        ArrayList<Art> artArrayList=new ArrayList<>();

        artArrayList.add(new Art("The Guardian Angel","Stik","The Guardian Angel","Marcantonio Franceschini","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.",R.drawable.lowresstikguardianangel,R.drawable.lowresinspiredtheguardianangel));
        artArrayList.add(new Art("Three Boys","Stik","Three Boys","Bartolomé Esteban Murillo","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status. ",R.drawable.lowresstikthreeboys,R.drawable.lowresinspiredtheguardianangel));
        artArrayList.add(new Art("Triumph of David Village","RUN","Triumph of David","Nicolas Poussin","RUN chose a detail from ‘The Translation of St Rita of Cascia’ by Poussin, the saint herself. ‘St Rita, I felt, was such a destroyed soul that I want to give her a bit of peace in her life.’",R.drawable.lowresdogrun,R.drawable.lowresinspiredtriumphofdavid));
        ///Phlegm
        artArrayList.add(new Art("The Triumph of David 2013","Phlegm","The Triumph of David 1628,31","Nicolas Poussin","The 9 year old son of the owner of the wall helped Phlegm paint his picture.  His mum said, ‘My son loved giving him a hand and I am so grateful to him for letting him have an input.  He feels proud to be part of it and now feels very protective towards the wall.’ ",R.drawable.lowresphlegm,R.drawable.lowresinspiredtriumphofdavid));

        //Mear One
        artArrayList.add(new Art("New World Revolution 2013","Mear One","The Madonna of The Rosary 1670-80","Bartolomé Esteban Murillo","Mear One modernised Murillo’s delicate Virgin of the Rosary by turning her and her child into powerful, mixed race  characters giving power salutes. ‘Equality’ is inscribed on her halo.  ",R.drawable.lowresmearone,R.drawable.lowresinspiredtriumphofdavid));

        //Conor Harrington
        artArrayList.add(new Art("Fight Club","Conor Harrington","The Massacre of The Innocents","Charles Le Brun","Harrington’s fighting men in Regency costume continue their fight on 4 more walls in the USA and Costa Rica.  The bald guy wins.",R.drawable.lowresconorharrington,R.drawable.lowresinspiredthemassacreoftheinnocentsbycharleslebrun));

        //Agent Provocateur
        artArrayList.add(new Art("Happy Hour","Agent Provocateur","The Three Graces","Sir Peter Paul Rubens ","AP is the only stencil artist in this project.  Once cut, stencil paintings can be reproduced easily, by anyone.  The work of art AP chose to interpret was a sketch by Rubens, highly prized because it is by him alone, whereas the finished piece would have had a great deal of workshop input.",R.drawable.lowresagentprovocateur,R.drawable.lowresinspiredthethreegracesbysirpeterpaulrubens));
        //Ben Wilson
        artArrayList.add(new Art("Saint Catherine","Ben Wilson","St Catherine of Siena","Carlo Dolci","Wilson beautifies the disgusting old bits of chewing gum trodden into pavements.  He took the sad St Catherine of Siena and cheered her up by giving her a vision of a banana.  ",R.drawable.lowresstcatherinestone,R.drawable.lowresinspiredstcatherineofsienabycarlodolci));

        //David Shillinglaw
        artArrayList.add(new Art("Samson and Delilah 2013","David Shillinglaw","Samson and Delilah 1618","Anthony van Dyck","Shillinglaw took Van Dyke’s Samson and Delilah – ‘the long hair, the scissors, the broken heart, the beautiful girl and the powerful man’ and mixed them creating a single figure ‘sharing the same body, 2 hearts, 4 eyes and a whole lot of heartbreak’.  ",R.drawable.lowresdavidshillinglaw,R.drawable.lowresinspiredsamsonanddelilahbyanthonyvandyckinterpretedbydavidshillinglaw));
        //Faith47
        artArrayList.add(new Art("Europa and the Winged Bird","Faith47","Europa and the Bull","Guido Reni","The absence of the bull and the introduction of a guiding bird are suggestive of a premonition of the abduction to come, her inner emotions and thoughts or perhaps a new interpretation of the ancient fable. ",R.drawable.lowreseuropaandthebull,R.drawable.lowresinspiredeuropaandthebullbyguidoreni));
        //Inkie, Pure Evil, AP is going to have custom view for each for zoomed in view of each artist  NEEDS A CUSTOM setup
        artArrayList.add(new Art("Old Nun's Head Mural","Inkie, Pure Evil and AP","St Catherine of Siena","Carlo Dolci","Three different internationally renowned street artists have painted a panel each on The Old Nun’s Head pub in Nunhead.  They have taken their inspiration from the same painting in Dulwich Picture Gallery, one of a nun’s head, St Catherine of Siena, painted in 1665 by Carlo Dolci. ",R.drawable.lowresoldnunsheadmurals,R.drawable.lowresinspiredstcatherineofsienabycarlodolci));
        //MadC need better scaled image may crop width    NEEDS A CUSTOM setup
        artArrayList.add(new Art("Vase With Flowers 2013","MadC","Vase with Flowers c1720","Jan van Huysum","One of only two female artist in the project, MadC, took elements from a 17th century Dutch flower painting, the insects, tulips and butterflies, and added them to her tag, creating an astonishingly beautiful back to a tennis practice wall in Belair Park.  ",R.drawable.lowresmadc,R.drawable.lowresinspiredvasewithflowersbyjanvanhuysum));
        //Reka
        artArrayList.add(new Art("Europa and the Bull c1700","Reka","Europa and the Bull 2013","Guido Reni","Reka’s  Europa from Reni’s ‘Europa and the Bull’ beautifies a wall of a South London pub.  Her hair curls delicately around its windows.",R.drawable.lowresreka,R.drawable.lowresinspiredeuropaandthebull));
        //Remi Rough and System // NEEDS A CUSTOM setup
        artArrayList.add(new Art("Girl at a Window 2013","Remi Rough and System","Girl at a Window 1645","Rembrandt van Rijn","System’s modernised ‘Girl at a Window’ by Rembrandt, leans out from Remi Roughs abstraction of ‘The Triumph of David’ by Poussin.  This ordinary young girl in a hoodie and cap with spray cans at her elbow, parallels Rembrandt’s 17th century serving girl.  ",R.drawable.lowresremiroughandsystem,R.drawable.lowresinspiredgirlatthewindow));
        //ROA
        artArrayList.add(new Art("Landscape with Sportsmen and Game 2013","ROA","Landscape with Sportsmen and Game c.1665","Adam Pynacker","ROA chose a detail of Pynacker’s ‘Sportsman with Game’ - a shitting dog that Pynacker placed in the foreground of his painting.  ROA’s version has provoked some criticism, people being more shockable in the 21st century than in the 17th century.",R.drawable.lowresdoginlandscape,R.drawable.lowresinspiredlandscapewithsportsman));
        //RUN
        artArrayList.add(new Art("The Translation of Saint Rita of Cascia","RUN","The Translation of Saint Rita of Cascia c1630","Nicolas Poussin","RUN chose a detail from ‘The Translation of St Rita of Cascia’ by Poussin, the saint herself. ‘St Rita, I felt, was such a destroyed soul that I want to give her a bit of peace in her life.’  ",R.drawable.lowresrunstrita,R.drawable.lowresinspiredtranslationofstrita));
        //STIK
        artArrayList.add(new Art("The Fall of Man","Stik","The Fall of Man","Pieter Coecke van Aelst","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status. ",R.drawable.lowresstikfallofman,R.drawable.lowresinspiredfallofman));
        artArrayList.add(new Art("Eliza and Mary Davidson","Stik","Eliza and Mary Davidson","Tilly Kettlesm","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ",R.drawable.lowresstikelizaandmarydavidsontilly,R.drawable.lowresinspiredelizaandmarydavidson));
        artArrayList.add(new Art("Eliza and Mary Linley","Stik","Eliza and Mary Linley","Thomas Gainsborough","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ",R.drawable.lowresstikelizabethandmary,R.drawable.lowresinspiredelizabethandlinley));
        artArrayList.add(new Art("A Couple in a Landscape","Stik","A Couple in a Landscape","Thomas Gainsborough","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ",R.drawable.lowresstikacoupleinlandscape,R.drawable.lowresinspiredacoupleinalandscape));

        //Thierry Noir
        artArrayList.add(new Art("Context","Thierry Noir","Joseph Receiving Pharaoh’s Ring","Giambattista Tiepolo","Famed for painting the Berlin Wall at risk of his life for 10 years in the 1980s, Noir’s simple style was born of need for speed and clarity.  His versions of the characters in Tiepolo’s ‘Joseph Receiving Pharaoh’s Ring’ interact similarly, but with Noir, size relates to importance as in medieval times.   ",R.drawable.lowresnoir,R.drawable.lowresinspiredthierrynoir));
        //Walter Kershaw
        artArrayList.add(new Art("Finished Wall","Walter Kershaw","Landscape with Windmills Near Haarlem","Jacob Van Ruisdael","‘Landscape with Windmills near Haarlem’ painted first by Jacob Van Ruisdael in c 1650, then copied by John Constable in c 1830, then copied by Walter Kershaw as a huge mural in 2014.  Spot the differences!  ",R.drawable.lowreswalterlandscape,R.drawable.lowresinspiredlandscapewithwindmill));



        return artArrayList;
    }




    public static ArrayList<Artist> GetArtistsDataReal(Activity context) {
        final ArrayList<Artist> data = new ArrayList<Artist>();


        data.add(new Artist("Stik","",""));
        data.add(new Artist("Three Boys","",""));
        data.add(new Artist("Phlegm","",""));
        data.add(new Artist("Mear One","",""));
        data.add(new Artist("Conor Harrington","",""));
        data.add(new Artist("Agent Provocateur","",""));
        data.add(new Artist("Ben Wilson","",""));
        data.add(new Artist("David Shillinglaw","",""));
        data.add(new Artist("Faith47","",""));
        data.add(new Artist("Inkie, Pure Evil and AP","",""));
        data.add(new Artist("MadC","",""));
        data.add(new Artist("Reka","",""));
        data.add(new Artist("Remi Rough and System","",""));
        data.add(new Artist("ROA","",""));
        data.add(new Artist("RUN","",""));
        data.add(new Artist("Thierry Noir","",""));
        data.add(new Artist("Walter Kershaw","",""));



        return data;
    }



    public static ArrayList<String> GetArtistsData(Activity context) {
        final ArrayList<String> data = new ArrayList<String>(18);



        for (int i = 0; i < 18; i++) {
            data.add( context.getResources().getStringArray(R.array.artists)[i]);
        }

        return data;
    }





   // alphabetical
//public static ArrayList<Art> GetGalleryData(){
//
//        //The Struggle was real
//        ArrayList<Art> artArrayList=new ArrayList<>();
//        //Agent Provocateur
//        artArrayList.add(new Art("Happy Hour","Agent Provocateur","The Three Graces","Sir Peter Paul Rubens ","AP is the only stencil artist in this project.  Once cut, stencil paintings can be reproduced easily, by anyone.  The work of art AP chose to interpret was a sketch by Rubens, highly prized because it is by him alone, whereas the finished piece would have had a great deal of workshop input.",R.drawable.lowresagentprovocateur,R.drawable.lowresagentprovocateur));
//        //Ben Wilson
//        artArrayList.add(new Art("Saint Catherine","Ben Wilson","St Catherine of Siena","Carlo Dolci","",R.drawable.lowresstcatherinestone,R.drawable.lowresstcatherinestone));
//        //Conor Harrington
//        artArrayList.add(new Art("Fight Club","Conor Harrington","The Massacre of The Innocents","Charles Le Brun","Harrington’s fighting men in Regency costume continue their fight on 4 more walls in the USA and Costa Rica.  The bald guy wins.",R.drawable.lowresconorharrington,R.drawable.lowresconorharrington));
//        //David Shillinglaw
//        artArrayList.add(new Art("Samson and Delilah 2013","David Shillinglaw","Samson and Delilah 1618","Anthony van Dyck","",R.drawable.lowresdavidshillinglaw,R.drawable.lowresdavidshillinglaw));
//        //Faith47
//        artArrayList.add(new Art("Europa and the Winged Bird","Faith47","Europa and the Bull","Guido Reni","The absence of the bull and the introduction of a guiding bird are suggestive of a premonition of the abduction to come, her inner emotions and thoughts or perhaps a new interpretation of the ancient fable. ",R.drawable.lowreseuropaandthebull,R.drawable.lowreseuropaandthebull));
//        //Inkie, Pure Evil, AP is going to have custom view for each for zoomed in view of each artist  NEEDS A CUSTOM setup
//        artArrayList.add(new Art("Old Nun's Head Mural","Inkie, Pure Evil and AP","St Catherine of Siena","Carlo Dolci","Three different internationally renowned street artists have painted a panel each on The Old Nun’s Head pub in Nunhead.  They have taken their inspiration from the same painting in Dulwich Picture Gallery, one of a nun’s head, St Catherine of Siena, painted in 1665 by Carlo Dolci. ",R.drawable.lowresoldnunsheadmurals,R.drawable.lowresoldnunsheadmurals));
//        //MadC need better scaled image may crop width    NEEDS A CUSTOM setup
//        artArrayList.add(new Art("Vase With Flowers 2013","MadC","Vase with Flowers c1720","Jan van Huysum","One of only two female artist in the project, MadC, took elements from a 17th century Dutch flower painting, the insects, tulips and butterflies, and added them to her tag, creating an astonishingly beautiful back to a tennis practice wall in Belair Park.  ",R.drawable.lowresmadc,R.drawable.lowresmadc));
//        //Mear One
//        artArrayList.add(new Art("New World Revolution 2013","Mear One","The Madonna of The Rosary 1670-80","Bartolomé Esteban Murillo","Mear One modernised Murillo’s delicate Virgin of the Rosary by turning her and her child into powerful, mixed race  characters giving power salutes. ‘Equality’ is inscribed on her halo.  ",R.drawable.lowresmearone,R.drawable.lowresmearone));
//        ///Phlegm
//        artArrayList.add(new Art("The Triumph of David 2013","Phlegm","The Triumph of David 1628,31","Nicolas Poussin","The 9 year old son of the owner of the wall helped Phlegm paint his picture.  His mum said, ‘My son loved giving him a hand and I am so grateful to him for letting him have an input.  He feels proud to be part of it and now feels very protective towards the wall.’ ",R.drawable.lowresphlegm,R.drawable.lowresphlegm));
//        //Reka
//        artArrayList.add(new Art("Europa and the Bull c1700","Reka","Europa and the Bull 2013","Guido Reni","Reka’s  Europa from Reni’s ‘Europa and the Bull’ beautifies a wall of a South London pub.  Her hair curls delicately around its windows.",R.drawable.lowresreka,R.drawable.lowresreka));
//        //Remi Rough and System // NEEDS A CUSTOM setup
//        artArrayList.add(new Art("Girl at a Window 2013","Remi Rough and System","Girl at a Window 1645","Rembrandt van Rijn","System’s modernised ‘Girl at a Window’ by Rembrandt, leans out from Remi Roughs abstraction of ‘The Triumph of David’ by Poussin.  This ordinary young girl in a hoodie and cap with spray cans at her elbow, parallels Rembrandt’s 17th century serving girl.  ",R.drawable.lowresremiroughandsystem,R.drawable.lowresremiroughandsystem));
//        //ROA
//        artArrayList.add(new Art("Landscape with Sportsmen and Game 2013","ROA","Landscape with Sportsmen and Game c.1665","Adam Pynacker","ROA chose a detail of Pynacker’s ‘Sportsman with Game’ - a shitting dog that Pynacker placed in the foreground of his painting.  ROA’s version has provoked some criticism, people being more shockable in the 21st century than in the 17th century.",R.drawable.lowresdoginlandscape,R.drawable.lowresdoginlandscape));
//        //RUN
//        artArrayList.add(new Art("Triumph of David Village","RUN","Triumph of David","Nicolas Poussin","",R.drawable.lowresdogrun,R.drawable.lowresdogrun));
//        artArrayList.add(new Art("The Translation of Saint Rita of Cascia","RUN","The Translation of Saint Rita of Cascia c1630","Nicolas Poussin","",R.drawable.lowresrunstrita,R.drawable.lowresrunstrita));
//        //STIK
//        artArrayList.add(new Art("The Guardian Angel","Stik","The Guardian Angel","Stik","Marcantonio Franceschini",R.drawable.lowresstikguardianangel,R.drawable.lowresstikguardianangel));
//        artArrayList.add(new Art("Three Boys","Stik","Three Boys","Bartolomé Esteban Murillo","",R.drawable.lowresstikthreeboys,R.drawable.lowresstikthreeboys));
//        artArrayList.add(new Art("The Fall of Man","Stik","The Fall of Man","Pieter Coecke van Aelst"," ",R.drawable.lowresstikfallofman,R.drawable.lowresstikfallofman));
//        artArrayList.add(new Art("Eliza and Mary Davidson","Stik","Eliza and Mary Davidson","Tilly Kettlesm"," ",R.drawable.lowresstikelizaandmarydavidsontilly,R.drawable.lowresstikelizaandmarydavidsontilly));
//        artArrayList.add(new Art("Eliza and Mary Linley","Stik","Eliza and Mary Linley","Thomas Gainsborough"," ",R.drawable.lowresstikelizabethandmary,R.drawable.lowresstikelizabethandmary));
//        artArrayList.add(new Art("A Couple in a Landscape","Stik","A Couple in a Landscape","Thomas Gainsborough"," ",R.drawable.lowresdoginlandscape,R.drawable.lowresdoginlandscape));
//
//        //Thierry Noir
//        artArrayList.add(new Art("Context","Thierry Noir","Joseph Receiving Pharaoh’s Ring","Giambattista Tiepolo"," ",R.drawable.lowresnoir,R.drawable.lowresnoir));
//        //Walter Kershaw
//        artArrayList.add(new Art("Finished Wall","Walter Kershaw","Landscape with Windmills Near Haarlem","Jacob Van Ruisdael"," ",R.drawable.lowreswalterlandscape,R.drawable.lowreswalterlandscape));
//
//
//
//        return artArrayList;
//    }


    //nandu change this one below to match the titles



    public static Art[] getMapArtwork(Activity context) {
        final Art arts[] = new Art[19];

        //add faith47
        //couple in landscape now available stik
        //delete Beerens
        //delete Nunca




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