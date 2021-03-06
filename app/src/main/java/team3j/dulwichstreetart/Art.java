package team3j.dulwichstreetart;


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

/**
 * Holds a representation of an Art object
 * @author Team 3-J
 */
public class Art {


    private final String[] webLinks;
    private String desc;
    private String inspirationTitle;
    private String inspirationArtist;
    private String name;
    private String artistName;
    private LatLng loc;
    private String pic;
    private String inspiredPic;
    private String fbLink;
    private String artAddress;
    private Boolean visited;
    private String dateVisited;

    private Drawable drawableInspired;
    private Drawable drawableStreet;


    /**
     * main constructor passing through art information -
     * @param context Required activity
     * @param name Name of the artwork
     * @param artistName Name of the artist
     * @param inspirationTitle Name of the inspiration art
     * @param inspirationArtist Name of the inspiration art artist
     * @param desc description of the artwork
     * @param pic the image of the artwork
     * @param inspiredPic the inspiration artwork
     * @param loc the coordinates of the location of the artwork
     * @param fbLink the code that links to the facebook version of the picture
     * @param artAddress the address of the artwork
     * @param visited whether the user has visited the artwork or not
     * @param webLinks array of extra links associated to the artist
     * @param  dateVisited date once the art work is visited
     */
    public Art(Context context,String name, String artistName, String inspirationTitle, String inspirationArtist, String desc, String pic, String inspiredPic, LatLng loc, String fbLink, String artAddress, Boolean visited, String[] webLinks, String dateVisited){
        this.name=name;
        this.artistName = artistName;
        this.desc=desc;
        this.inspirationTitle=inspirationTitle;
        this.pic=pic;
        this.inspiredPic=inspiredPic;
        this.inspirationArtist=inspirationArtist;
        this.loc = loc;
        this.fbLink=fbLink;
        this.artAddress = artAddress;
        this.visited = visited;
        this.webLinks=webLinks;
        this.dateVisited = dateVisited;

        try {
            this.drawableInspired=getAssetImage(context,inspiredPic);
            this.drawableStreet=getAssetImage(context,pic);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the drawable street work
     * @return drawable of the street art image
     */
    public Drawable getDrawableStreet() {
        return drawableStreet;
    }

    /**
     * Gets the drawable asset image
     * @param context activity being used on
     * @param filename filename of image
     * @return Bitmap drawable
     * @throws IOException if the filename does not match an image
     */
    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("" + filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * Get the name of the artwork
     * @return name of artwork
     */
    public String getName(){
        return name;
    }

    /**
     * Get the coordinates of the artwork
     * @return coordinates of the artwork
     */
    public LatLng getLoc()
    {
        return loc;
    }

    /**
     * Get the artwork itself
     * @return the picture of the artwork
     */
    public String getPic(){
        return pic;
    }

    /**
     *  Gets the inspiration picture
     * @return the inspiration artwork
     */
    public String getInspiredPic() {return inspiredPic;}

    /**
     * Gets the description of the artwork
     * @return description of artwork
     */
    public String getDesc() {return desc;}

    /**
     * Gets the name of the inspiration artwork
     * @return name of the inspiration artwork
     */
    public String getInspirationTitle() {return inspirationTitle;}

    /**
     * Gets the artist's name
     * @return the artist's name
     */
    public String getArtistName() {return artistName;}

    /**
     * Gets the inspiration artist name
     * @return inspiration artist name
     */
    public String getInspirationArtist() { return inspirationArtist;}

    /**
     * Set the name of the artwork
     * @param name name of the artwork
     */
    public void  setName( String name){
        this.name = name;
    }

    /**
     * Gets the facebook code for the picture
     * @return Facebook code
     */
    public String getFbLink() {return fbLink;}

    /**
     * Gets the actual address of the artwork
     * @return Art address
     */
    public String getArtAddress() { return artAddress;}

    /**
     * Gets if the user has visited the artwork or not
     * @return true if they have visted
     */
    public Boolean getVisited() {
        return visited;
    }

    /**
     * Sets whether they have visited the artwork or not
     */
    public void setVisited(boolean visited){this.visited = visited;}

    /**
     * Gets the array of extra links for the artist
     * @return extra links for the artist
     */
    public String[] getWebLinks() {
        return webLinks;
    }

    /**
     * Gets the date when the art-work was visited
     * @return Date visited
     */
    public String getDateVisited(){ return dateVisited;}

    /**
     * Sets the date of the visited artwork. Default is "--/--/----"
     * @param newDate Date string
     */
    public void setDateVisited(String newDate){this.dateVisited = newDate;}
}
