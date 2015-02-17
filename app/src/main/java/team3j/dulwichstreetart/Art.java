package team3j.dulwichstreetart;


import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Ananda on 04/02/2015.
 */
public class Art {


    String name;
    LatLng loc;
    int pic;
    int postId;

    public Art(String name,LatLng loc,int pic)
    {
        this.name = name;
        this.loc = loc;
        this.pic = pic;
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


}
