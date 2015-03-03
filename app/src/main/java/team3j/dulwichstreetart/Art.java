package team3j.dulwichstreetart;


import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Ananda on 04/02/2015.
 */
public class Art {


    private String desc;
    private String inspirationTitle;
    private String inspirationArtist;
    private String name;
    private String artistName;
    private LatLng loc;
    private int pic;
    private int inspiredPic;
    private int postId;

    public Art(String name,LatLng loc,int pic)
    {
        this.name = name;
        this.loc = loc;
        this.pic = pic;
    }
    public Art(String name,String artistName,String inspirationTitle,String inspirationArtist,String desc,int pic,int inspiredPic){
        this.name=name;
        this.desc=desc;
        this.inspirationTitle=inspirationTitle;
        this.pic=pic;
        this.inspiredPic=inspiredPic;
    }

    public Art(String name,int postId)
    {
        this.name = name;
        this.postId=postId;

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
}
