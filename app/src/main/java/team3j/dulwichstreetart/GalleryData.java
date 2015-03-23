package team3j.dulwichstreetart;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import twitter4j.Status;


/**
 * @author Team 3-J
 *
 * This returns the data for the specific artwork also stores array of images
 * returns data from xml strings
 */

public class GalleryData {

    private ArrayList<Status> todaysTweets;
    private ArrayList<Art> artworkList;
    private static GalleryData mGalleryData;
    private Drawable mapButton;
    private Drawable aboutDulwich;
    private Drawable tweetBird;

    /**
     * Gets the data of all the artworks
     *
     * @return gallerydata
     */
    public static GalleryData get() {
        return mGalleryData;
    }

    /**
     * Constructs a GalleryData
     *
     * @param context context of applicationn
     */
    public GalleryData(Context context) {
        artworkList = CreateGalleryData(context);

        //setup homepage elements
        todaysTweets = new ArrayList<>();

        try {
            mapButton = getAssetImage(context, "mapbannersquare");
            aboutDulwich = getAssetImage(context, "dulwichpicturegallery");
            tweetBird = getAssetImage(context, "twitterbird");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads the image from Assets and returns a bitmap drawable
     *
     * @param context  Context of Activity
     * @param filename Filename of the image
     * @return BitmapDrawable of the image
     * @throws IOException If the image can not be found
     */
    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        String exte = ".jpg";
        if (filename.contains("twitterbird")) {
            exte = ".png";
        }
        InputStream buffer = new BufferedInputStream((assets.open("" + filename + exte)));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }


    /**
     * Get tweets
     *
     * @return tweets
     */
    public ArrayList<Status> getTodaysTweets() {
        return todaysTweets;
    }

    /**
     * Creates the gallerydata
     * @return newly created gallery data
     */
    public static GalleryData create(Context context) {
        if (mGalleryData == null) {
            mGalleryData = new GalleryData(context);
        }

        return mGalleryData;
    }


    /**
     * List of artwork information
     *
     * @return arraylist of artwork information
     */
    public ArrayList<Art> getArtworkList() {
        return artworkList;
    }


    /**
     * Gets data of all the artwork, by setting them into an arraylist to return
     *
     * @param context context of activity
     * @return data of artwork
     */


    public ArrayList<Art> CreateGalleryData(Context context) {


        ArrayList<Art> artArrayList = new ArrayList<>();

        artArrayList.add(new Art(context, "The Guardian Angel ", "Stik", "The Guardian Angel 1716 ", "Marcantonio Franceschini", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.", "lowresstikguardianangel", "lowresinspiredtheguardianangel", new LatLng(51.456219, -0.076794), "783162061765990", "Blackwater Court, London, SE22 8RS", false, new String[]{"www.stik.org.uk", "https://www.youtube.com/watch?v=Vu_DcXWAjzk"}, "--/--/----"));
        artArrayList.add(new Art(context, "Three Boys", "Stik", "Three Boys", "Bartolomé Esteban Murillo", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status. ", "lowresstikthreeboys", "lowresinspiredthreeboys", new LatLng(51.445317, -0.079216), "783162051765991", "The Bowling Building Dulwich Park", false, new String[]{"www.stik.org.uk"}, "--/--/----"));
        artArrayList.add(new Art(context, "Triumph of David Village", "RUN", "Triumph of David", "Nicolas Poussin", "RUN chose a detail from ‘The Translation of St Rita of Cascia’ by Poussin, the saint herself. ‘St Rita, I felt, was such a destroyed soul that I want to give her a bit of peace in her life.’", "lowresdogrun", "lowresinspiredtriumphofdavid", new LatLng(51.449318, -0.085131), "783162055099324", "265 Lordship Lane, London SE22", false, new String[]{"www.runabc.org"}, "--/--/----"));
        ///Phlegm
        artArrayList.add(new Art(context, "The Triumph of David 2013", "Phlegm", "The Triumph of David 1628,31", "Nicolas Poussin", "The 9 year old son of the owner of the wall helped Phlegm paint his picture.  His mum said, ‘My son loved giving him a hand and I am so grateful to him for letting him have an input.  He feels proud to be part of it and now feels very protective towards the wall.’ ", "lowresphlegm", "lowresinspiredtriumphofdavid", new LatLng(51.451632, -0.071597), "783165355098994", "Side of 206 Barry Road, London, SE22 0JW", false, new String[]{"www.phlegmcomics.com"}, "--/--/----"));

        //Mear One
        artArrayList.add(new Art(context, "New World Revolution 2013", "Mear One", "The Madonna of The Rosary 1670-80", "Bartolomé Esteban Murillo", "Mear One modernised Murillo’s delicate Virgin of the Rosary by turning her and her child into powerful, mixed race  characters giving power salutes. ‘Equality’ is inscribed on her halo.  ", "lowresmearone", "lowresinspiredtriumphofdavid", new LatLng(51.454552, -0.077042), "783165361765660", "On the side of The Patch, 211 Lordship Lane, London SE22 8HA", false, new String[]{"www.mearone.com"}, "--/--/----"));

        //Conor Harrington
        artArrayList.add(new Art(context, "Fight Club", "Conor Harrington", "The Massacre of The Innocents", "Charles Le Brun", "Harrington’s fighting men in Regency costume continue their fight on 4 more walls in the USA and Costa Rica.  The bald guy wins.", "lowresconorharrington", "lowresinspiredthemassacreoftheinnocentsbycharleslebrun", new LatLng(51.460628, -0.075084), "783165351765661", "Spurling Road, Opposite East Dulwich Tavern, London, SE22 8EW", false, new String[]{"www.conorsaysboom.wordpress.com", "https://www.youtube.com/watch?v=D2Zst2qjmNA"}, "--/--/----"));

        //Agent Provocateur
        artArrayList.add(new Art(context, "Happy Hour", "Agent Provocateur", "The Three Graces", "Sir Peter Paul Rubens ", "AP is the only stencil artist in this project.  Once cut, stencil paintings can be reproduced easily, by anyone.  The work of art AP chose to interpret was a sketch by Rubens, highly prized because it is by him alone, whereas the finished piece would have had a great deal of workshop input.", "lowresagentprovocateur", "lowresinspiredthethreegracesbysirpeterpaulrubens", new LatLng(51.440805, -0.056619), "783165371765659", "30 Waldenshaw Road, Forest Hill, London, SE23 3XP", false, new String[]{"www.aprovocateur.co.uk"}, "--/--/----"));
        //Ben Wilson
        artArrayList.add(new Art(context, "Saint Catherine", "Ben Wilson", "St Catherine of Siena", "Carlo Dolci", "Wilson beautifies the disgusting old bits of chewing gum trodden into pavements.  He took the sad St Catherine of Siena and cheered her up by giving her a vision of a banana.  ", "lowresstcatherinestone", "lowresinspiredstcatherineofsienabycarlodolci", new LatLng(51.445672, -0.085659), "783165365098993", "Dulwich Picture Gallery, Gallery Road, London SE21 7AD", false, new String[]{"http://en.wikipedia.org/wiki/Ben_Wilson_(artist)"}, "--/--/----"));

        //David Shillinglaw
        artArrayList.add(new Art(context, "Samson and Delilah 2013", "David Shillinglaw", "Samson and Delilah 1618", "Anthony van Dyck", "Shillinglaw took Van Dyke’s Samson and Delilah – ‘the long hair, the scissors, the broken heart, the beautiful girl and the powerful man’ and mixed them creating a single figure ‘sharing the same body, 2 hearts, 4 eyes and a whole lot of heartbreak’.  ", "lowresdavidshillinglaw", "lowresinspiredsamsonanddelilahbyanthonyvandyckinterpretedbydavidshillinglaw", new LatLng(51.452656, -0.102931), "783165358432327", "Side of The Florence, 131-133 Dulwich Road, London, London SE24 0NG", false, new String[]{"www.cargocollective.com/davidshillinglaw"}, "--/--/----"));
        //Faith47
        artArrayList.add(new Art(context, "Europa and the Winged Bird", "Faith47", "Europa and the Bull", "Guido Reni", "The absence of the bull and the introduction of a guiding bird are suggestive of a premonition of the abduction to come, her inner emotions and thoughts or perhaps a new interpretation of the ancient fable. ", "lowreseuropaandthebull", "lowresinspiredeuropaandthebullbyguidoreni", new LatLng(51.465731, -0.061023), "783165415098988", "Consort Road SE15", false, new String[]{"www.faith47.com"}, "--/--/----"));
        //Inkie, Pure Evil, AP is going to have custom view for each for zoomed in view of each artist  NEEDS A CUSTOM setup
        artArrayList.add(new Art(context, "Old Nun's Head Mural", "Inkie, Pure Evil and AP", "St Catherine of Siena", "Carlo Dolci", "Three different internationally renowned street artists have painted a panel each on The Old Nun’s Head pub in Nunhead.  They have taken their inspiration from the same painting in Dulwich Picture Gallery, one of a nun’s head, St Catherine of Siena, painted in 1665 by Carlo Dolci. ", "lowresoldnunsheadmurals", "lowresinspiredstcatherineofsienabycarlodolci", new LatLng(51.465289, -0.059352), "783165411765655", "The Old Nuns Head, 15 Nunhead Green, London SE15 3QQ", false, new String[]{"www.inkie.co.uk", "https://www.youtube.com/watch?v=pfVioeN_gUw"}, "--/--/----"));
        //MadC need better scaled image may crop width    NEEDS A CUSTOM setup
        artArrayList.add(new Art(context, "Vase With Flowers 2013", "MadC", "Vase with Flowers c1720", "Jan van Huysum", "One of only two female artist in the project, MadC, took elements from a 17th century Dutch flower painting, the insects, tulips and butterflies, and added them to her tag, creating an astonishingly beautiful back to a tennis practice wall in Belair Park.  ", "lowresmadc", "lowresinspiredvasewithflowersbyjanvanhuysum", new LatLng(51.441452, -0.091381), "783165445098985", "•\tThe back of the tennis practice wall, Belair Park, near South Circular Road, London, SE21", false, new String[]{"www.madc.tv", "https://www.youtube.com/watch?v=66YTtq6bB0s"}, "--/--/----"));
        //Reka
        artArrayList.add(new Art(context, "Europa and the Bull c1700", "Reka", "Europa and the Bull 2013", "Guido Reni", "Reka’s  Europa from Reni’s ‘Europa and the Bull’ beautifies a wall of a South London pub.  Her hair curls delicately around its windows.", "lowresreka", "lowresinspiredeuropaandthebull", new LatLng(51.427863, -0.086302), "783165465098983", "The Paxton Pub, 255 Gipsy Road, London, SE27 9QY", false, new String[]{"www.rekaone.com"}, "--/--/----"));
        //Remi Rough and System // NEEDS A CUSTOM setup
        artArrayList.add(new Art(context, "Girl at a Window 2013", "Remi Rough and System", "Girl at a Window 1645", "Rembrandt van Rijn", "System’s modernised ‘Girl at a Window’ by Rembrandt, leans out from Remi Roughs abstraction of ‘The Triumph of David’ by Poussin.  This ordinary young girl in a hoodie and cap with spray cans at her elbow, parallels Rembrandt’s 17th century serving girl.  ", "lowresremiroughandsystem", "lowresinspiredgirlatthewindow", new LatLng(51.461675, -0.079872), "783165368432326", "17 Grove Vale, London, SE22 8ET", false, new String[]{"www.remirough.com"}, "--/--/----"));
        //ROA
        artArrayList.add(new Art(context, "Landscape with Sportsmen and Game", "ROA", "Landscape with Sportsmen and Game c.1665", "Adam Pynacker", "ROA chose a detail of Pynacker’s ‘Sportsman with Game’ - a shitting dog that Pynacker placed in the foreground of his painting.  ROA’s version has provoked some criticism, people being more shockable in the 21st century than in the 17th century.", "lowresdoginlandscape", "lowresinspiredlandscapewithsportsman", new LatLng(51.467224, -0.072160), "783165451765651", "Side of Victoria Inn, 77-79 Choumert Road, London, SE15 4AR", false, new String[]{"www.roaweb.tumblr.com", "https://www.youtube.com/watch?v=Spye5Yk_awY"}, "--/--/----"));
        //RUN
        artArrayList.add(new Art(context, "The Translation of Saint Rita of Cascia", "RUN", "The Translation of Saint Rita of Cascia c1630", "Nicolas Poussin", "RUN chose a detail from ‘The Translation of St Rita of Cascia’ by Poussin, the saint herself. ‘St Rita, I felt, was such a destroyed soul that I want to give her a bit of peace in her life.’  ", "lowresrunstrita", "lowresinspiredtranslationofstrita", new LatLng(51.437918, -0.054667), "783165475098982", "30 Dartmouth Road, Forest Hill, London, SE23 3XZ", false, new String[]{"www.runabc.org"}, "--/--/----"));
        //STIK
        artArrayList.add(new Art(context, "The Fall of Man", "Stik", "The Fall of Man", "Pieter Coecke van Aelst", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status. ", "lowresstikfallofman", "lowresinspiredfallofman", new LatLng(51.453060, -0.078765), "783165481765648", "The Moorings Townley Road/Beauval Road, London, SE22 8SW", false, new String[]{"www.stik.org.uk", "https://www.youtube.com/watch?v=RCVECYTJ-0U"}, "--/--/----"));
        artArrayList.add(new Art(context, "Eliza and Mary Davidson", "Stik", "Eliza and Mary Davidson", "Tilly Kettlesm", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ", "lowresstikelizaandmarydavidsontilly", "lowresinspiredelizaandmarydavidson", new LatLng(51.447416, -0.076093), "783165488432314", "150 Court Lane, London, SE21 7EB", false, new String[]{"www.stik.org.uk"}, "--/--/----"));
        artArrayList.add(new Art(context, "Eliza and Mary Linley", "Stik", "Eliza and Mary Linley", "Thomas Gainsborough", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ", "lowresstikelizabethandmary", "lowresinspiredelizabethandlinley", new LatLng(51.446414, -0.073434), "783165538432309", "184 Court Lane, London, SE21 7EB", false, new String[]{"www.stik.org.uk"}, "--/--/----"));
        artArrayList.add(new Art(context, "A Couple in a Landscape", "Stik", "A Couple in a Landscape", "Thomas Gainsborough", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ", "lowresstikacoupleinlandscape", "lowresinspiredacoupleinalandscape", new LatLng(51.456109, -0.076058), "783165478432315", "On the side of Property In estate agent on the corner of Hansler Road and 133 Lordship Lane, London, SE22 8HX", false, new String[]{"www.stik.org.uk", "https://www.youtube.com/watch?v=RqYOzLoHBf0"}, "--/--/----"));

        //Thierry Noir
        artArrayList.add(new Art(context, "Context", "Thierry Noir", "Joseph Receiving Pharaoh’s Ring", "Giambattista Tiepolo", "Famed for painting the Berlin Wall at risk of his life for 10 years in the 1980s, Noir’s simple style was born of need for speed and clarity.  His versions of the characters in Tiepolo’s ‘Joseph Receiving Pharaoh’s Ring’ interact similarly, but with Noir, size relates to importance as in medieval times.   ", "lowresnoir", "lowresinspiredthierrynoir", new LatLng(51.445228, -0.079050), "783165548432308", "The Bowling Building, Dulwich Park, London, SE21", false, new String[]{"www.galerie-noir.de", "https://www.youtube.com/watch?v=jGZ9Ou_xFfo"}, "--/--/----"));
        //Walter Kershaw
        artArrayList.add(new Art(context, "Finished Wall", "Walter Kershaw", "Landscape with Windmills Near Haarlem", "Jacob Van Ruisdael", "‘Landscape with Windmills near Haarlem’ painted first by Jacob Van Ruisdael in c 1650, then copied by John Constable in c 1830, then copied by Walter Kershaw as a huge mural in 2014.  Spot the differences!  ", "lowreswalterlandscape", "lowresinspiredlandscapewithwindmill", new LatLng(51.467546, -0.072552), "783165585098971", "166 Bellenden Road, London, SE15 4QY", false, new String[]{"http://www.walterkershaw.co.uk/"}, "--/--/----"));

        return artArrayList;
    }


    /**
     * Gets data of all the artists, by setting them into an arraylist to return
     *
     * @param context activity
     * @return array of artist information
     */
    public static ArrayList<Artist> GetArtistsDataReal(Activity context) {
        final ArrayList<Artist> data = new ArrayList<Artist>();

        //FORMAT: data.add(new Artist("artist","description","website", "artist image""));

        data.add(new Artist("Stik", "Stik is one of London's best know and most loved street artists. He is renowned for painting simple, androgynous stick figures which although simple nevertheless convey complex body language and emotion." +
                "Themes of human vulnerability and infused into Stik's work which reflects both the human spirit and his own life story and experiences with homelessness.\n\nInitially Stik developed his simple style through having to execute works illegally. He painted his characteristic figures on London streets in this manner for years and was instrumental in raising the British public's appreciation of street art. Now, routinely invited to paint walls legally, Stik maintains his minimal simplicity for aesthetic reasons. Stik now paints murals across the UK, Europe, Asia and America in association with organisations such as The British Council, Amnesty International and Big Issue Foundation. He has staged sell-out exhibitions of his work in the UK and the USA.", "www.stik.org.uk", "profilestik"));


        data.add(new Artist("Phlegm", "Phlegm is a Sheffield-based muralist and artist who first developed his fantastical illustrations in self-published comics. " +
                "His work now extends to the urban landscape, and can mostly be seen in run-down and disused spaces. Phlegm creates surral illustrations to an untold story, weaving a visual narrative that explores the unreal through creatures from his imagination. His storybook-like imagery is half childlike, half menacing, set in built up cityscapes with castles, turrets and winding stairways.\n\n" +
                "At other times the city itself is the setting for his long limbed half-human, half-woodland creatures. In this dreamworld a viewer comes across impossible flying machines and complex networks of levers, pulleys and cogs, set beside telescopes, magnifying glasses and zephyrs. Working soley in monochrome, his fine technique and intracate detail can be seen as a curiosity cabinate of the mind. Each drawing forms part of a grand narrative that extends worldwide, in countries including Norway, Canada, Switzerland, Sri Lanka, USA, Belgium, Poland, Italy, Slovakia and Spain.", "www.phlegmcomics.com", "profilephlegm"));


        data.add(new Artist("Mear One", "MEAR ONE has been at the forefront of LAís graffiti an mural culture for nearly three decades. He is famous for having pioneered the Melrose graffiti art movement in the late 80s. Early on in his career MEAR gained recognition for building the bridge between graffiti art and fine art.\n\nMEAR was the first graffiti artist to exhibit at the infamous 01 Gallery on Melrose, as well as at 33 1/3 Gallery in Silverlake, where Banksy would later debut his first North American show and he was part of the Art in the Streets 2011 exhibit at the Los Angeles Museum of Contemporary Art. \n\nHe is perhaps best known for constructing powerful narratives juxtaposing philosophy, ancient mythology and modern politics. This interpretation of reality is achieved through dialogue between realism and the supernatural.", "www.mearone.com", "profilemearone"));

        data.add(new Artist("Conor Harrington", "From Cork in Ireland, but now based in London, Harrington fuses graffiti and fine art techniques to produce huge murals on city walls. These combine realist figurative portrayals of figures generally in 18th century costume, with the abstract raw energy of graffiti. They are often scenes of Conflicts and are infused with movement and drama.\n\nHarrington is a classically trained artist who nonetheless began in the world of graffiti. He reflects that he saw his first piece of graffiti in National Geographic magazine at the age of 12 and never looked back, soon venturing out onto the streets of Cork to put up his first typographical work in 1994. Harrington has painted on the city streets all over the world. He has staged many successful exhibitions of his work in galleries, notably at Lazarides gallery in London.", "www.conorsaysboom.wordpress.com", "profileconorharrington"));

        data.add(new Artist("Agent Provocateur", "Agent Provocateur is from south London. He studied at Central Saint Martins College, London, getting an MA in Communication Design before becoming a political designer, which culminated in designing the Labour Party Manifesto in 2005. Primarily known for stencils, Agent Provocateurís simple and often humorous images encompass a wide variety of pop culture themes; consumerism, politics, religion and the cult of celebrity being a few.\n\nAlthough stencilling is Agent Provocateurís passion, pigeonholing him as a one-trick pony would be wrong. His diverse CV also includes dodgy royal souvenirs, shop disloyalty cards, beer labels and wedding portraits. Most recently heís added gallerist to his list. His last Herrods pop-up gallery held in a local junk shop not only showcased work by a selection of world-renowned artists, it also questioned established perceptions of contemporary art, elitism and greed.", "www.aprovocateur.co.uk", "lowresagentprovocateur"));

        data.add(new Artist("Ben Wilson", "Ben Wilson, born in Cambridge in 1963, started working as an artist on a large scale in wood, creating life size wooden sculptures hidden in woods and forests. Since 2004 he has been painstakingly painting on discarded chewing gum in London.\n\n" +
                "\n" +
                "Wilsonís technique involves super-heating the gum with a blow torch, spraying it with lacquer, then using tiny brushes, creating a tiny image in enamel paint before sealing the gum with a final layer of clear lacquer. Wilson estimates that he has painted thousands of pieces of gum around London notably along the Millennium Bridge across the Thames. \n\n" +
                "\n" +
                "The highest concentration of his work can be found in Musewell Hill where he lives.\n\n" +
                "Wilsonsís work encompasses a wide subject matter, often relating to the immediate contextual environment or to people that he meets on his travels.", "http://en.wikipedia.org/wiki/Ben_Wilson_(artist)", "profilebenwilson"));

        data.add(new Artist("David Shillinglaw", "Born in 1982 in Saudi Arabia to British parents, David Shillinglaw is currently based in London. At home in both the streets and in the studio, Shillinglaw applies his fine arts graphic skills to everything from small hand-made books to large murals focusing on the follies and wisdom of the human race.\n\nAlthough he was formally educated with a degree in Fine Art, Shillinglaw contends that the best lessons that he's learned have been self-taught. With mixed-media work that has been exhibited in galleries throughout the world including China, Japan and Turkey, Shillinglaw draws on material from both ancient mythology and popular culture. His favourite surfaces include objects such as old road signs and doors that he finds in the streets.", "www.cargocollective.com/davidshillinglaw", "profiledavidshillinglaw"));

        data.add(new Artist("Faith47", "Faith47 is best known as a street artist. She has received recognition for her work beyond her home country of South Africa and has participated in gallery shows and projects world-wide.\n\nFollowing an active street art career spanning more than fifteen years, her work can now be found in major cities around the world. Using a wide range of media, including graphite, spray paint, oil paint, ink, photography and collage, her approach is explorative and substrate appropriate ñ from found and rescued objects, to time-layered and history-textured city walls and their accretions, to studio prepared canvas and wood.\n" +
                "\n" +
                "Through her work, Faith47 attempts to disarm the strategies of global realpolitik, in order to advance the expression of personal truth. In this way, her work is both an internal and spiritual release that speaks to the complexities of the human condition, its deviant histories and existential search.", "www.faith47.com", "profilefaith47"));

        data.add(new Artist("Inkie & Agent Provocateur", "This artist is one of the most notorious and prolific graffiti writers in UK history to emerge out of the 80's Bristol scene. Painting alongside 3D, Crime Inc, Nick Walker and Banksy, in 1989 the 'Kingpin' was arrested as the head of 72 other writers in the UK's largest ever Graffiti bust, Operation Anderson and went on to come 2nd in the 1989 world street art championships. \n" +
                "\n" +
                "Inkie has since worked as head of design for SEGA, XBOX, Jade Jaggerís in-house designer as well as running a West London design studio creating prints, illustrations, clothing and with his trademark beauty on large-scale pieces, the globally respected artist, whose diverse inspirations collect Mayan architecture, William Morris, Mouse & Kelly, Alphons Mucha, The Arts & Crafts movement and Islamic geometry, has exhibited worldwide, been denounced as Banksyís right hand man by The Daily Mail and simultaneously lauded by The Times, his art published in the books Banksyís Bristol, Children of the Can, Graffiti World, Street Fonts and magazines GQ, Rolling Stone, Computer Arts, Huck, Graphotism and Dazed & Confused.", "www.inkie.co.uk", "profileinkieandap"));

        data.add(new Artist("MadC", "MadC was born in 1980 in East Germany. She studied at the University of Art and Design Burg Giebichenstein in Halle Germany and at Central Saint Martins College, London. MadC painted her first piece in 1996 and has since gone onto paint, in over 35 countries. Of particular note was the '700 Wall' which she painted in 2010. This is a 700 square metre wall, the largest ever painted by a single artist. \n\nTechnically gifted, MadC is known for her bright and accurate letter style but also her huge and detailed concept walls. Fonts and the energy combined letters inspired her imagery. She works with bright colours, which she connects through transparent layers to result in an energetic abstract piece of art. MadC has exhibited her artwork on canvas in galleries all over the world.", "www.madc.tv", "profilemadc"));

        data.add(new Artist("RekaOne", "Growing up next to a major train line in Melbourne, Australia, REKA was introduced to the world of graffiti at an early age first hitting the walls of his native city in the late 1990s. He gradually made the shift from graffiti into what is now known as street art. One of the original members of the famed Everfresh crew, REKA has painted large murals all over the world. REKAís body of work comprises surrealist, abstracted creatures communicated through strong lines, dynamic movement and bold colours.\n\nREKAís work is held in the permanent collection the National Gallery of Australia and he has exhibited his work in galleries all around the world.", "www.rekaone.com", "profilerekaone"));


        data.add(new Artist("Remi Rough and System", "South London born and bred, Rough has been breaking boundaries with the aid of a spray can and a paintbrush for nearly 30 years. His compositions are abstract, consisting of carefully combined colours and straight-edged shapes.\n" +
                "\n" +
                "Rough moved from the streets to the galleries with his debut art show in 1989 and has since gone on to exhibit in London, Paris, Perth, Tokyo, Los Angeles, Hong Kong, and other cities dotted around the globe. Rough was invited to speak on the under group history of UK graffiti at the Tate Modern, as part of its Street Art exhibition in 2008, and in 2009 he published his first book Lost Colours and Alibis.\n" +
                "\n" +
                "He is a member of the collective Agents of Change which in 2009 painted the largest mural in London, on the Megaro Hotel, and an abandoned Scottish village, the Ghost Village Project.", "www.remirough.com", "profileremirough"));


        data.add(new Artist("ROA", "ROA is a legendary Belgian artist from Ghent, internationally acclaimed for his arresting and visceral murals of animals depicted in black and white. He frequently reveals the skeletons and internal organs of the animals and birds which are often piled on top of each other. ROA has painted large scale murals of indigenous animals in cities throughout the globe on an unmatched scale.", "www.roaweb.tumblr.com", "profileroa"));


        data.add(new Artist("RUN", "RUN is a London-based Italian artist whose works can be seen adorning street corners from China to Senegal. His style shows a level of detail and complexity rarely seen in street art today, often evidenced through his vivid rendering of interlocking hands and faces in bright, arresting colours. RUN is interested in street art as a language of communication, creating playful characters that speak to diverse audiences on multiple levels. The expansive scale of his work captivates the viewer.", "www.runabc.org", "profilerun"));


        data.add(new Artist("Thierry Noir", "Thierry Noir, a forerunner of modern street art, was born in 1958 in Lyon, France, and came to Berlin in 1982. At this time until its fall in 1989, Noir painted mile upon mile of the Berlin Wall risking his life in the process. He covered the Wall with bright, vivid colours, aiming not to embellish it but to demystify it. His vivid murals were both a personal response and a poignant political statement. Painting on the Wall was absolutely forbidden; it was built three metres beyond the official border so the East German soldiers were able to arrest any person standing near it. Noir had to paint as quickly as possible, using the recipe of 'two ideas, three colours' as a celebration of the 'eternal youth'. Despite their bright colours and playful nature, the murals leave a lingering sense of melancholy. As Noir says, " + "I did nothing but react to its sadness.\n\nAfter the Berlin Wall came down in 1989, his paintings became a symbol of new-found freedom associated with the reunification of Germany and the end of the Cold War. The fall of the wall gave birth to the East Side Gallery, a 1.3 kilometre stretch of wall on which 118 artists, including Noir, from 21 counties applied their designs, leaving a lasting testimony for future generations. Noir's artwork and original pieces of the Berlin Wall can be found in public museums and private art collections throughout the world.", "www.galerie-noir.de", "profilenoir"));


        data.add(new Artist("Walter Kershaw", "Walter Kershaw is a pioneer of large scale, external, mural paintings in England and Brazil. He is perhaps best-known for the famous Trafford Park Murals at White City, Manchester, during the 1980s and 1990s.\n\nHe also works in oils and watercolours, and has work in public collections worldwide; including the Victoria and Albert museum in London, the Arts Council, the Calouste Gulbenkian Foundation and the National Collection of Brazilian Art, FAAP in São Paulo.", "http://www.walterkershaw.co.uk/", "profilewalter"));


        return data;
    }


    /**
     * get drawable for map button homepage
     *
     * @return map button
     */
    public Drawable getMapButton() {
        return mapButton;
    }

    /**
     * get drawable for about button homepage
     *
     * @return gets about dulwich
     */
    public Drawable getAboutDulwich() {
        return aboutDulwich;
    }

    /**
     * get drawable for tweet bird homepage
     *
     * @return twitter bird logo
     */
    public Drawable getTweetBird() {
        return tweetBird;
    }

}