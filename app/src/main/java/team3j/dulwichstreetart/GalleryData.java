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

    public ArrayList<Art> GetGalleryData() {

        //The Struggle was real
        ArrayList<Art> artArrayList = new ArrayList<>();

        artArrayList.add(new Art("The Guardian Angel ","Stik","The Guardian Angel 1716 ","Marcantonio Franceschini","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.",R.drawable.lowresstikguardianangel,R.drawable.lowresinspiredtheguardianangel));
        artArrayList.add(new Art("Three Boys","Stik","Three Boys","Bartolomé Esteban Murillo","Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status. ",R.drawable.lowresstikthreeboys,R.drawable.lowresinspiredthreeboys));
        artArrayList.add(new Art("Triumph of David Village","RUN","Triumph of David","Nicolas Poussin","RUN chose a detail from ‘The Translation of St Rita of Cascia’ by Poussin, the saint herself. ‘St Rita, I felt, was such a destroyed soul that I want to give her a bit of peace in her life.’",R.drawable.lowresdogrun,R.drawable.lowresinspiredtriumphofdavid));
        ///Phlegm
        artArrayList.add(new Art("The Triumph of David 2013", "Phlegm", "The Triumph of David 1628,31", "Nicolas Poussin", "The 9 year old son of the owner of the wall helped Phlegm paint his picture.  His mum said, ‘My son loved giving him a hand and I am so grateful to him for letting him have an input.  He feels proud to be part of it and now feels very protective towards the wall.’ ", R.drawable.lowresphlegm, R.drawable.lowresinspiredtriumphofdavid));

        //Mear One
        artArrayList.add(new Art("New World Revolution 2013", "Mear One", "The Madonna of The Rosary 1670-80", "Bartolomé Esteban Murillo", "Mear One modernised Murillo’s delicate Virgin of the Rosary by turning her and her child into powerful, mixed race  characters giving power salutes. ‘Equality’ is inscribed on her halo.  ", R.drawable.lowresmearone, R.drawable.lowresinspiredtriumphofdavid));

        //Conor Harrington
        artArrayList.add(new Art("Fight Club", "Conor Harrington", "The Massacre of The Innocents", "Charles Le Brun", "Harrington’s fighting men in Regency costume continue their fight on 4 more walls in the USA and Costa Rica.  The bald guy wins.", R.drawable.lowresconorharrington, R.drawable.lowresinspiredthemassacreoftheinnocentsbycharleslebrun));

        //Agent Provocateur
        artArrayList.add(new Art("Happy Hour", "Agent Provocateur", "The Three Graces", "Sir Peter Paul Rubens ", "AP is the only stencil artist in this project.  Once cut, stencil paintings can be reproduced easily, by anyone.  The work of art AP chose to interpret was a sketch by Rubens, highly prized because it is by him alone, whereas the finished piece would have had a great deal of workshop input.", R.drawable.lowresagentprovocateur, R.drawable.lowresinspiredthethreegracesbysirpeterpaulrubens));
        //Ben Wilson
        artArrayList.add(new Art("Saint Catherine", "Ben Wilson", "St Catherine of Siena", "Carlo Dolci", "Wilson beautifies the disgusting old bits of chewing gum trodden into pavements.  He took the sad St Catherine of Siena and cheered her up by giving her a vision of a banana.  ", R.drawable.lowresstcatherinestone, R.drawable.lowresinspiredstcatherineofsienabycarlodolci));

        //David Shillinglaw
        artArrayList.add(new Art("Samson and Delilah 2013", "David Shillinglaw", "Samson and Delilah 1618", "Anthony van Dyck", "Shillinglaw took Van Dyke’s Samson and Delilah – ‘the long hair, the scissors, the broken heart, the beautiful girl and the powerful man’ and mixed them creating a single figure ‘sharing the same body, 2 hearts, 4 eyes and a whole lot of heartbreak’.  ", R.drawable.lowresdavidshillinglaw, R.drawable.lowresinspiredsamsonanddelilahbyanthonyvandyckinterpretedbydavidshillinglaw));
        //Faith47
        artArrayList.add(new Art("Europa and the Winged Bird", "Faith47", "Europa and the Bull", "Guido Reni", "The absence of the bull and the introduction of a guiding bird are suggestive of a premonition of the abduction to come, her inner emotions and thoughts or perhaps a new interpretation of the ancient fable. ", R.drawable.lowreseuropaandthebull, R.drawable.lowresinspiredeuropaandthebullbyguidoreni));
        //Inkie, Pure Evil, AP is going to have custom view for each for zoomed in view of each artist  NEEDS A CUSTOM setup
        artArrayList.add(new Art("Old Nun's Head Mural", "Inkie, Pure Evil and AP", "St Catherine of Siena", "Carlo Dolci", "Three different internationally renowned street artists have painted a panel each on The Old Nun’s Head pub in Nunhead.  They have taken their inspiration from the same painting in Dulwich Picture Gallery, one of a nun’s head, St Catherine of Siena, painted in 1665 by Carlo Dolci. ", R.drawable.lowresoldnunsheadmurals, R.drawable.lowresinspiredstcatherineofsienabycarlodolci));
        //MadC need better scaled image may crop width    NEEDS A CUSTOM setup
        artArrayList.add(new Art("Vase With Flowers 2013", "MadC", "Vase with Flowers c1720", "Jan van Huysum", "One of only two female artist in the project, MadC, took elements from a 17th century Dutch flower painting, the insects, tulips and butterflies, and added them to her tag, creating an astonishingly beautiful back to a tennis practice wall in Belair Park.  ", R.drawable.lowresmadc, R.drawable.lowresinspiredvasewithflowersbyjanvanhuysum));
        //Reka
        artArrayList.add(new Art("Europa and the Bull c1700", "Reka", "Europa and the Bull 2013", "Guido Reni", "Reka’s  Europa from Reni’s ‘Europa and the Bull’ beautifies a wall of a South London pub.  Her hair curls delicately around its windows.", R.drawable.lowresreka, R.drawable.lowresinspiredeuropaandthebull));
        //Remi Rough and System // NEEDS A CUSTOM setup
        artArrayList.add(new Art("Girl at a Window 2013", "Remi Rough and System", "Girl at a Window 1645", "Rembrandt van Rijn", "System’s modernised ‘Girl at a Window’ by Rembrandt, leans out from Remi Roughs abstraction of ‘The Triumph of David’ by Poussin.  This ordinary young girl in a hoodie and cap with spray cans at her elbow, parallels Rembrandt’s 17th century serving girl.  ", R.drawable.lowresremiroughandsystem, R.drawable.lowresinspiredgirlatthewindow));
        //ROA
        artArrayList.add(new Art("Landscape with Sportsmen and Game 2013", "ROA", "Landscape with Sportsmen and Game c.1665", "Adam Pynacker", "ROA chose a detail of Pynacker’s ‘Sportsman with Game’ - a shitting dog that Pynacker placed in the foreground of his painting.  ROA’s version has provoked some criticism, people being more shockable in the 21st century than in the 17th century.", R.drawable.lowresdoginlandscape, R.drawable.lowresinspiredlandscapewithsportsman));
        //RUN
        artArrayList.add(new Art("The Translation of Saint Rita of Cascia", "RUN", "The Translation of Saint Rita of Cascia c1630", "Nicolas Poussin", "RUN chose a detail from ‘The Translation of St Rita of Cascia’ by Poussin, the saint herself. ‘St Rita, I felt, was such a destroyed soul that I want to give her a bit of peace in her life.’  ", R.drawable.lowresrunstrita, R.drawable.lowresinspiredtranslationofstrita));
        //STIK
        artArrayList.add(new Art("The Fall of Man", "Stik", "The Fall of Man", "Pieter Coecke van Aelst", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status. ", R.drawable.lowresstikfallofman, R.drawable.lowresinspiredfallofman));
        artArrayList.add(new Art("Eliza and Mary Davidson", "Stik", "Eliza and Mary Davidson", "Tilly Kettlesm", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ", R.drawable.lowresstikelizaandmarydavidsontilly, R.drawable.lowresinspiredelizaandmarydavidson));
        artArrayList.add(new Art("Eliza and Mary Linley", "Stik", "Eliza and Mary Linley", "Thomas Gainsborough", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ", R.drawable.lowresstikelizabethandmary, R.drawable.lowresinspiredelizabethandlinley));
        artArrayList.add(new Art("A Couple in a Landscape", "Stik", "A Couple in a Landscape", "Thomas Gainsborough", "Stik takes portraits of wealthy, fashionable, landed gentry by Gainsborough, and strips away all pretention, leaving his simple stick figures to brilliantly convey the emotion and relationships behind the symbols of status.  ", R.drawable.lowresstikacoupleinlandscape, R.drawable.lowresinspiredacoupleinalandscape));

        //Thierry Noir
        artArrayList.add(new Art("Context", "Thierry Noir", "Joseph Receiving Pharaoh’s Ring", "Giambattista Tiepolo", "Famed for painting the Berlin Wall at risk of his life for 10 years in the 1980s, Noir’s simple style was born of need for speed and clarity.  His versions of the characters in Tiepolo’s ‘Joseph Receiving Pharaoh’s Ring’ interact similarly, but with Noir, size relates to importance as in medieval times.   ", R.drawable.lowresnoir, R.drawable.lowresinspiredthierrynoir));
        //Walter Kershaw
        artArrayList.add(new Art("Finished Wall", "Walter Kershaw", "Landscape with Windmills Near Haarlem", "Jacob Van Ruisdael", "‘Landscape with Windmills near Haarlem’ painted first by Jacob Van Ruisdael in c 1650, then copied by John Constable in c 1830, then copied by Walter Kershaw as a huge mural in 2014.  Spot the differences!  ", R.drawable.lowreswalterlandscape, R.drawable.lowresinspiredlandscapewithwindmill));



        return artArrayList;
    }




    public static ArrayList<Artist> GetArtistsDataReal(Activity context) {
        final ArrayList<Artist> data = new ArrayList<Artist>();

        //data.add(new Artist("artist","description","website"));

        data.add(new Artist("Stik", "Stik is one of London's best know and most loved street artists. He is renowned for painting simple, androgynous stick figures which although simple nevertheless convey complex body language and emotion." +
                "Themes of human vulnerability and infused into Stik's work which reflects both the human spirit and his own life story and experiences with homelessness.\n\nInitially Stik developed his simple style through having to execute works illegally. He painted his characteristic figures on London streets in this manner for years and was instrumental in raising the British public's appreciation of street art. Now, routinely invited to paint walls legally, Stik maintains his minimal simplicity for aesthetic reasons. Stik now paints murals across the UK, Europe, Asia and America in association with organisations such as The British Council, Amnesty International and Big Issue Foundation. He has staged sell-out exhibitions of his work in the UK and the USA.", "www.stik.org.uk", R.drawable.profilestik));


        data.add(new Artist("Phlegm", "Phlegm is a Sheffield-based muralist and artist who first developed his fantastical illustrations in self-published comics. " +
                "His work now extends to the urban landscape, and can mostly be seen in run-down and disused spaces. Phlegm creates surral illustrations to an untold story, weaving a visual narrative that explores the unreal through creatures from his imagination. His storybook-like imagery is half childlike, half menacing, set in built up cityscapes with castles, turrets and winding stairways.\n\n" +
                "At other times the city itself is the setting for his long limbed half-human, half-woodland creatures. In this dreamworld a viewer comes across impossible flying machines and complex networks of levers, pulleys and cogs, set beside telescopes, magnifying glasses and zephyrs. Working soley in monochrome, his fine technique and intracate detail can be seen as a curiosity cabinate of the mind. Each drawing forms part of a grand narrative that extends worldwide, in countries including Norway, Canada, Switzerland, Sri Lanka, USA, Belgium, Poland, Italy, Slovakia and Spain.", "www.phlegmcomics.com", R.drawable.profilephlegm));


        data.add(new Artist("Mear One", "MEAR ONE has been at the forefront of LA’s graffiti an mural culture for nearly three decades. He is famous for having pioneered the Melrose graffiti art movement in the late 80s. Early on in his career MEAR gained recognition for building the bridge between graffiti art and fine art.\n\nMEAR was the first graffiti artist to exhibit at the infamous 01 Gallery on Melrose, as well as at 33 1/3 Gallery in Silverlake, where Banksy would later debut his first North American show and he was part of the Art in the Streets 2011 exhibit at the Los Angeles Museum of Contemporary Art. \n\nHe is perhaps best known for constructing powerful narratives juxtaposing philosophy, ancient mythology and modern politics. This interpretation of reality is achieved through dialogue between realism and the supernatural.", "www.mearone.com", R.drawable.profilemearone));

        data.add(new Artist("Conor Harrington", "From Cork in Ireland, but now based in London, Warrington fuses graffiti and fine art techniques to produce huge murals on city walls. These combine realist figurative portrayals of figures generally in 18th century costume, with the abstract raw energy of graffiti. They are often scenes of Conflicts and are infused with movement and drama.\n\nHarrington is a classically trained artist who nonetheless began in the world of graffiti. He reflects that he saw his first piece of graffiti in National Geographic magazine at the age of 12 and never looked back, soon venturing out onto the streets of Cork to put up his first typographical work in 1994. Harrington has painted on the city streets all over the world. He has staged many successful exhibitions of his work in galleries, notably at Lazarides gallery in London.", "www.conorsaysboom.wordpress.com", R.drawable.profileconorharrington));

        data.add(new Artist("Agent Provocateur", "Agent Provocateur is from south London. He studied at Central Saint Martins College, London, getting an MA in Communication Design before becoming a political designer, which culminated in designing the Labour Party Manifesto in 2005. Primarily known for stencils, Agent Provocateur’s simple and often humorous images encompass a wide variety of pop culture themes; consumerism, politics, religion and the cult of celebrity being a few.\n\nAlthough stencilling is Agent Provocateur’s passion, pigeonholing him as a one-trick pony would be wrong. His diverse CV also includes dodgy royal souvenirs, shop disloyalty cards, beer labels and wedding portraits. Most recently he’s added gallerist to his list. His last Herrods pop-up gallery held in a local junk shop not only showcased work by a selection of world-renowned artists, it also questioned established perceptions of contemporary art, elitism and greed.", "www.aprovocateur.co.uk", R.drawable.lowresagentprovocateur));

        data.add(new Artist("Ben Wilson", "Ben Wilson, born in Cambridge in 1963, started working as an artist on a large scale in wood, creating life size wooden sculptures hidden in woods and forests. Since 2004 he has been painstakingly painting on discarded chewing gum in London.\n\n" +
                "\n" +
                "Wilson’s technique involves super-heating the gum with a blow torch, spraying it with lacquer, then using tiny brushes, creating a tiny image in enamel paint before sealing the gum with a final layer of clear lacquer. Wilson estimates that he has painted thousands of pieces of gum around London notably along the Millennium Bridge across the Thames. \n\n" +
                "\n" +
                "The highest concentration of his work can be found in Musewell Hill where he lives.\n\n" +
                "Wilsons’s work encompasses a wide subject matter, often relating to the immediate contextual environment or to people that he meets on his travels.", "", R.drawable.profilebenwilson));

        data.add(new Artist("David Shillinglaw", "Born in 1982 in Saudi Arabia to British parents, David Shillinglaw is currently based in London. At home in both the streets and in the studio, Shillinglaw applies his fine arts graphic skills to everything from small hand-made books to large murals focusing on the follies and wisdom of the human race.\n\nAlthough he was formally educated with a degree in Fine Art, Shillinglaw contends that the best lessons that he’s learned have been self-taught. With mixed-media work that has been exhibited in galleries throughout the world including China, Japan and Turkey, Shillinglaw draws on material from both ancient mythology and popular culture. His favourite surfaces include objects such as old road signs and doors that he finds in the streets.", "www.cargocollective.com/davidshillinglaw", R.drawable.profiledavidshillinglaw));

        data.add(new Artist("Faith47", "Faith47 is best known as a street artist. She has received recognition for her work beyond her home country of South Africa and has participated in gallery shows and projects world-wide.\n\nFollowing an active street art career spanning more than fifteen years, her work can now be found in major cities around the world. Using a wide range of media, including graphite, spray paint, oil paint, ink, photography and collage, her approach is explorative and substrate appropriate – from found and rescued objects, to time-layered and history-textured city walls and their accretions, to studio prepared canvas and wood.\n" +
                "\n" +
                "Through her work, Faith47 attempts to disarm the strategies of global realpolitik, in order to advance the expression of personal truth. In this way, her work is both an internal and spiritual release that speaks to the complexities of the human condition, its deviant histories and existential search.", "www.faith47.com", R.drawable.profilefaith47));

        data.add(new Artist("Inkie & Agent Provocateur", "This artist is one of the most notorious and prolific graffiti writers in UK history to emerge out of the 80’s Bristol scene. Painting alongside 3D, Crime Inc, Nick Walker and Banksy, in 1989 the ‘Kingpin’ was arrested as the head of 72 other writers in the UK's largest ever Graffiti bust, Operation Anderson and went on to come 2nd in the 1989 world street art championships. \n" +
                "\n" +
                "Inkie has since worked as head of design for SEGA, Xbox, Jade Jagger’s in-house designer as well as running a West London design studio creating prints, illustrations, clothing and with his trademark beauty on large-scale pieces, the globally respected artist, whose diverse inspirations collect Mayan architecture, William Morris, Mouse & Kelly, Alphons Mucha, The Arts & Crafts movement and Islamic geometry, has exhibited worldwide, been denounced as Banksy’s right hand man by The Daily Mail and simultaneously lauded by The Times, his art published in the books Banksy’s Bristol, Children of the Can, Graffiti World, Street Fonts and magazines GQ, Rolling Stone, Computer Arts, Huck, Graphotism and Dazed & Confused.", "www.inkie.co.uk", R.drawable.profileinkieandap));

        data.add(new Artist("MadC", "MadC was born in 1980 in East Germany. She studied at the University of Art and Design Burg Giebichenstein in Halle Germany and at Central Saint Martins College, London. MadC painted her first piece in 1996 and has since gone onto paint, in over 35 countries. Of particular note was the ‘700 Wall’ which she painted in 2010. This is a 700 square metre wall, the largest ever painted by a single artist. \n\nTechnically gifted, MadC is known for her bright and accurate letter style but also her huge and detailed concept walls. Fonts and the energy combined letters inspired her imagery. She works with bright colours, which she connects through transparent layers to result in an energetic abstract piece of art. MadC has exhibited her artwork on canvas in galleries all over the world.", "www.madc.tv", R.drawable.profilemadc));

        data.add(new Artist("RekaOne", "Growing up next to a major train line in Melbourne, Australia, REKA was introduced to the world of graffiti at an early age first hitting the walls of his native city in the late 1990s. He gradually made the shift from graffiti into what is now known as street art. One of the original members of the famed Everfresh crew, REKA has painted large murals all over the world. REKA’s body of work comprises surrealist, abstracted creatures communicated through strong lines, dynamic movement and bold colours.\n\nREKA’s work is held in the permanent collection the National Gallery of Australia and he has exhibited his work in galleries all around the world.", "www.rekaone.com", R.drawable.profilerekaone));


        data.add(new Artist("Remi Rough and System", "South London born and bred, Rough has been breaking boundaries with the aid of a spray can and a paintbrush for nearly 30 years. His compositions are abstract, consisting of carefully combined colours and straight-edged shapes.\n" +
                "\n" +
                "Rough moved from the streets to the galleries with his debut art show in 1989 and has since gone on to exhibit in London, Paris, Perth, Tokyo, Los Angeles, Hong Kong, and other cities dotted around the globe. Rough was invited to speak on the under group history of UK graffiti at the Tate Modern, as part of its Street Art exhibition in 2008, and in 2009 he published his first book Lost Colours and Alibis.\n" +
                "\n" +
                "He is a member of the collective Agents of Change which in 2009 painted the largest mural in London, on the Megaro Hotel, and an abandoned Scottish village, the Ghost Village Project.", "www.remirough.com", R.drawable.profileremirough));


        data.add(new Artist("ROA", "ROA is a legendary Belgian artist from Ghent, internationally acclaimed for his arresting and visceral murals of animals depicted in black and white. He frequently reveals the skeletons and internal organs of the animals and birds which are often piled on top of each other. ROA has painted large scale murals of indigenous animals in cities throughout the globe on an unmatched scale.", "www.roaweb.tumblr.com", R.drawable.profileroa));


        data.add(new Artist("RUN", "RUN is a London-based Italian artist whose works can be seen adorning street corners from China to Senegal. His style shows a level of detail and complexity rarely seen in street art today, often evidenced through his vivid rendering of interlocking hands and faces in bright, arresting colours. RUN is interested in street art as a language of communication, creating playful characters that speak to diverse audiences on multiple levels. The expansive scale of his work captivates the viewer.", "www.runabc.org", R.drawable.profilerun));


        data.add(new Artist("Thierry Noir", "Thierry Noir, a forerunner of modern street art, was born in 1958 in Lyon, France, and came to Berlin in 1982. At this time until its fall in 1989, Noir painted mile upon mile of the Berlin Wall risking his life in the process. He covered the Wall with bright, vivid colours, aiming not to embellish it but to demystify it. His vivid murals were both a personal response and a poignant political statement. Painting on the Wall was absolutely forbidden; it was built three metres beyond the official border so the East German soldiers were able to arrest any person standing near it. Noir had to paint as quickly as possible, using the recipe of ‘two ideas, three colours’ as a celebration of the ‘eternal youth’. Despite their bright colours and playful nature, the murals leave a lingering sense of melancholy. As Noir says, “I did nothing but react to its sadness”.\n\nAfter the Berlin Wall came down in 1989, his paintings became a symbol of new-found freedom associated with the reunification of Germany and the end of the Cold War. The fall of the wall gave birth to the East Side Gallery, a 1.3 kilometre stretch of wall on which 118 artists, including Noir, from 21 counties applied their designs, leaving a lasting testimony for future generations. Noir’s artwork and original pieces of the Berlin Wall can be found in public museums and private art collections throughout the world.", "www.galerie-noir.de", R.drawable.profilenoir));


        data.add(new Artist("Walter Kershaw", "Walter Kershaw is a pioneer of large scale, external, mural paintings in England and Brazil. He is perhaps best-known for the famous Trafford Park Murals at White City, Manchester, during the 1980s and 1990s.\n\nHe also works in oils and watercolours, and has work in public collections worldwide; including the Victoria and Albert museum in London, the Arts Council, the Calouste Gulbenkian Foundation and the National Collection of Brazilian Art, FAAP in São Paulo.", "http://www.walterkershaw.co.uk/", R.drawable.profilewalter));


        return data;
    }



    public static ArrayList<String> GetArtistsData(Activity context) {
        final ArrayList<String> data = new ArrayList<String>(18);



        for (int i = 0; i < 18; i++) {
            data.add(context.getResources().getStringArray(R.array.artists)[i]);
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


        arts[0] = new Art("Roa 2013", (new LatLng(51.467224, -0.072160)), R.drawable.art0);
        arts[1] = new Art("Remi Rough & System 2013", (new LatLng(51.461675, -0.079872)), R.drawable.art0);
        arts[2] = new Art("Conor Harrington 2013", (new LatLng(51.460628, -0.075084)), R.drawable.art2);
        arts[3] = new Art("Stik 2012", (new LatLng(51.456109, -0.076058)), R.drawable.art3);
        arts[4] = new Art("Stik 2012", (new LatLng(51.456219, -0.076794)), R.drawable.art4);
        arts[5] = new Art("Beerens, Christiaan Nagel", (new LatLng(51.455990, -0.076442)), R.drawable.art0);
        arts[6] = new Art("Mear One", (new LatLng(51.454552, -0.077042)), R.drawable.art6);
        arts[7] = new Art("Stik 2012", (new LatLng(51.453060, -0.078765)), R.drawable.art7);
        arts[8] = new Art("Phlegm 2013", (new LatLng(51.451632, -0.071597)), R.drawable.art8);
        arts[9] = new Art("Nunca 2013", (new LatLng(51.449235, -0.074160)), R.drawable.art0);
        arts[10] = new Art("Stik 2012", (new LatLng(51.446414, -0.073434)), R.drawable.art10);
        arts[11] = new Art("Stik 2012", (new LatLng(51.447416, -0.076093)), R.drawable.art11);
        arts[12] = new Art("Stik 2012", (new LatLng(51.445317, -0.079216)), R.drawable.art12);
        arts[13] = new Art("Thierry Noir 2013", (new LatLng(51.445228, -0.079050)), R.drawable.art13);
        arts[14] = new Art("The Inspiration Dulwich Picture Gallery 1811", (new LatLng(51.445988, -0.086360)), R.drawable.art0);
        arts[15] = new Art("David Shillinglaw 2013", (new LatLng(51.452656, -0.102931)), R.drawable.art15);
        arts[16] = new Art("MadC 2013", (new LatLng(51.441452, -0.091381)), R.drawable.art16);
        arts[17] = new Art("Reka 2013", (new LatLng(51.427863, -0.086302)), R.drawable.art17);
        arts[18] = new Art("RUN", (new LatLng(51.437918, -0.054667)), R.drawable.art18);

        return arts;
    }
}