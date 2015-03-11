package team3j.artworkdisplay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vincent on 19/02/2015.
 */
public class Comment implements Parcelable {

    private String message;
    private String posterName;
    private String time;
    private int icon;
    private String posterURL;
    private String commentID;
    private boolean userLikes;
    private String numberLikes;
    private Boolean isAReply;
    private Boolean canDelete;

    public void setIcon(int icon) {
        this.icon = icon;

    }

    public void setMessage(String message) {
        this.message = message;

    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;

    }

    public void setNumberLikes(String numberLikes) {

        this.numberLikes = numberLikes;
    }

    public void setPosterURL(String posterURL) {

        this.posterURL = posterURL;
    }
    public void setTime(String time) {
        this.time = time;

    }
    public void setCommentID(String commentID) {
        this.commentID = commentID;

    }
    public void setUserLikes(Boolean userLikes) {
        this.userLikes = userLikes;

    }
    public void setIsAReply(Boolean isAReply){

        this.isAReply = isAReply;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;

    }

    public boolean getCanDelete() {
        return canDelete;
    }

    public String getMessage() {
        return message;

    }
    public String getPosterName() {
        return posterName;

    }
    public String getNumberLikes() {
        return numberLikes;
    }
    public String getPosterURL() {
        return posterURL;
    }

    public String getTime() {
        return time;

    }
    public String getCommentID() {
        return commentID;

    }
    public boolean getUserLikes() {
        return userLikes;

    }

    public boolean getIsAReply(){

        return isAReply;
    }

    public int getIcon() {
        return icon;
    }


    public String toString(){
        return posterName + " " + message + " " + commentID + " " + userLikes;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
