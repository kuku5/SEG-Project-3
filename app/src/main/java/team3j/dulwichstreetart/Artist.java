package team3j.dulwichstreetart;

/**
 * @author Team 3-J
 */
public class Artist {
    private final String name;
    private final String description;
    private final String website;
    private final String artistPhoto;

    /**
     * Constructs an Artist with the following
     * @param name Artist name
     * @param description Description of artist
     * @param website the artist's website
     * @param artistPhoto a photo of the artist
     */
    public Artist(String name,String description, String website, String artistPhoto){

        this.name=name;
        this.description=description;
        this.website=website;
        this.artistPhoto = artistPhoto;

    }



    /**
     * this method returns the description of the artist
     * @return description of artist
     */
    public String getDescription() {
        return description;
    }

    /**
     * this method returns the name of the artist
     * @return name of the artist
     */
    public String getName() {
        return name;
    }

    /**
     * this method returns the website address of the artist
     * @return the website address of the artist
     */
    public String getWebsite() {
        return website;
    }

    /**
     * this method returns the photo location of the artist
     * @return photo location of the artist
     */
    public String getArtistPhoto () {
        return artistPhoto;
    }
}
