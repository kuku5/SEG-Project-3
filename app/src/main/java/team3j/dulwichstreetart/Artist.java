package team3j.dulwichstreetart;

/**
 * Created by JGill on 11/03/15.
 */
public class Artist {
    private final String name;
    private final String description;
    private final String website;

    public Artist(String name,String description, String website){

        this.name=name;
        this.description=description;
        this.website=website;

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
}