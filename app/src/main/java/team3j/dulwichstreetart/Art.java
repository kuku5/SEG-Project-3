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


    //main constructor passing through art information -

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

    public String getName(){
        return name;
    }

    public LatLng getLoc()
    {
        return loc;
    }

    public int getPic(){
        return pic;
    }

    public int getInspiredPic() {return inspiredPic;}

    public String getDesc() {return desc;}

    public String getInspirationTitle() {return inspirationTitle;}

    public String getArtistName() {return artistName;}

    public String getInspirationArtist() { return inspirationArtist;}

    public void  setName( String name){
        this.name = name;
    }

    public String getFbLink() {return fbLink;}

    public String getArtAddress() { return artAddress;}


    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(){this.visited = true;}


    public String[] getWebLinks() {
        return webLinks;
    }
}
