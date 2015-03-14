package team3j.dulwichstreetart;

/**
 * @author Team 3-J
 */
public class Artist {
    private final String name;
    private final String description;
    private final String website;
    private final int artistPhoto;

    public Artist(String name,String description, String website, int artistPhoto){

        this.name=name;
        this.description=description;
        this.website=website;
        this.artistPhoto = artistPhoto;

    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }
    public int getArtistPhoto () {
        return artistPhoto;
    }
}
