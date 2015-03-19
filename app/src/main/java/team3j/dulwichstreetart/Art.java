package team3j.dulwichstreetart;


import com.google.android.gms.maps.model.LatLng;

/**
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
    private int pic;
    private int inspiredPic;
    private String fbLink;
    private String artAddress;
    private Boolean visited;

    /**
     * main constructor passing through art information -
     *  @param name
     * @param artistName
     * @param inspirationTitle
     * @param inspirationArtist
     * @param desc
     * @param pic
     * @param inspiredPic
     * @param loc
     * @param fbLink
     * @param artAddress
     * @param visited
     * @param webLinks
     */

    public Art(String name, String artistName, String inspirationTitle, String inspirationArtist, String desc, int pic, int inspiredPic, LatLng loc, String fbLink, String artAddress, Boolean visited, String[] webLinks){
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
    public int getPic(){
        return pic;
    }

    /**
     *  Gets the inspiration picture
     * @return the inspiration artwork
     */
    public int getInspiredPic() {return inspiredPic;}

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
     * @return
     */
    public String getFbLink() {return fbLink;}

    /**
     * Gets the actual address of the artwork
     * @return
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
    public void setVisited(){this.visited = true;}

    /**
     * Gets the array of extra links for the artist
     * @return extra links for the artist
     */
    public String[] getWebLinks() {
        return webLinks;
    }
}
