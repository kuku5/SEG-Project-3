package team3j.artworkdisplay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vincent on 19/02/2015.
 */
public class Comment implements Parcelable {

    private String message;
    private String posterName;
    private int icon;

    public void setIcon(int icon) {
        this.icon = icon;

    }

    public void setMessage(String message) {
        this.message = message;

    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;

    }

    public String getMessage() {
        return message;

    }

    public String getPosterName() {
        return posterName;

    }

    public int getIcon() {
        return icon;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
