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

    public Art(String name,LatLng loc,int pic)
    {
        this.name = name;
        this.loc = loc;
        this.pic = pic;
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
