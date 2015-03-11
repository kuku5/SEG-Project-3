package team3j.artworkdisplay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Team 3-J.
 * Stores the details of a particular comment from a facebook post
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

    /**
     * Set profile picture of the post
     * @param icon The picture to be set
     */
    public void setIcon(int icon) {
        this.icon = icon;

    }

    /**
     * Sets the message for the post
     * @param message The message to be set
     */
    public void setMessage(String message) {
        this.message = message;

    }

    /**
     * Sets the poster's name
     * @param posterName The poster's name
     */
    public void setPosterName(String posterName) {
        this.posterName = posterName;

    }

    /**
     * Sets number of likes for the comment
     * @param numberLikes The amount of likes for the comment
     */
    public void setNumberLikes(String numberLikes) {

        this.numberLikes = numberLikes;
    }

    /**
     * Poster's link to their facebook account
     * @param posterURL The link of the poster's profile page on facebook
     */
    public void setPosterURL(String posterURL) {

        this.posterURL = posterURL;
    }

    /**
     * Set the time for the post
     * @param time The time of the post
     */
    public void setTime(String time) {
        this.time = time;

    }

    /**
     * Sets the comment's ID
     * @param commentID the comment's ID
     */
    public void setCommentID(String commentID) {
        this.commentID = commentID;

    }

    /**
     * Sets if the user has liked the comment or not
     * @param userLikes boolean true if the user has "liked" the specified comment
     */
    public void setUserLikes(Boolean userLikes) {
        this.userLikes = userLikes;

    }

    /**
     * Sets the comment as a reply comment to a top level comment
     * @param isAReply boolean true if it is a reply comment
     */
    public void setIsAReply(Boolean isAReply){

        this.isAReply = isAReply;
    }

    /**
     * Sets the comments to "deletable" that are available to be deleted by the user
     * @param canDelete boolean true if the user is able to delete the specified comment
     */
    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;

    }

    /**
     * Returns if the user is able to delete the specified comment or not
     * @return boolean true if the user can delete the comment
     */
    public boolean getCanDelete() {
        return canDelete;
    }

    /**
     * Gets the message of this specific "Comment"
     * @return the message itself
     */
    public String getMessage() {
        return message;

    }

    /**
     * Gets the name of who posted the comment
     * @return the name of the poster
     */
    public String getPosterName() {
        return posterName;

    }
    public String getNumberLikes() {
        return numberLikes;
    }

    /**
     * Gets the poster's facebook profile link
     * @return the link to the poster's facebook profile
     */
    public String getPosterURL() {
        return posterURL;
    }

    /**
     * Gets the time of when the comment was posted
     * @return the time at which the comment was posted
     */
    public String getTime() {
        return time;

    }

    /**
     * Gets the ID of the specific comment for identification
     * @return the comment's ID
     */
    public String getCommentID() {
        return commentID;

    }

    /**
     * Gets the amount of the likes for the comment
     * @return the amount of likes for the comment
     */
    public boolean getUserLikes() {
        return userLikes;

    }

    /**
     * Gets if it is a "reply-type comment"
     * @return boolean true if it is a reply comment
     */
    public boolean getIsAReply(){

        return isAReply;
    }

    /**
     * Gets the profile picture of the poster
     * @return the profile picture
     */
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
