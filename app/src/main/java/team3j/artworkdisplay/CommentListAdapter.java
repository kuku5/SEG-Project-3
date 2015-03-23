package team3j.artworkdisplay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.facebook.Session;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import team3j.dulwichstreetart.Art;
import team3j.dulwichstreetart.MainActivity;
import team3j.dulwichstreetart.R;

/**
 * Adapter for the recycler view that holds everything that is viewable on GallerySwipeSingleFragment
 *
 * @author Team 3-J
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final int indexOfArtwork;
    private GallerySwipeSingleFragment gallerySwipeSingleFragment;
    private String commentAmount = null;
    private ArrayList<Comment> data;
    private ArrayList<Art> galleryData;
    private Context context;
    private final int Header_View_Type = 1;
    private final int Post_View_Type = 2;
    private boolean checkIfLogIn = false;
    private String name;
    private OnMapButtonPressTouchListener onMapButtonPressTouchListener;
    private String numberOfLikesPost;
    private boolean userLikes;


    /**
     * Constructs a CommentListAdapter, with the specified data that will be shown on the recycler view
     *
     * @param gallerySwipeSingleFragment    The instance of GallerySwipeSingleFragment
     * @param context                       The activity of GallerySwipeSingleFragment
     * @param position                      The position of the current fragment
     * @param galleryData                   The data of the gallery pictures
     * @param onMapButtonPressTouchListener The button that allows you access the map fragment
     */
    public CommentListAdapter(GallerySwipeSingleFragment gallerySwipeSingleFragment, Context context, int position, ArrayList<Art> galleryData, OnMapButtonPressTouchListener onMapButtonPressTouchListener) {

        data = new ArrayList<>();
        this.galleryData = galleryData;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.indexOfArtwork = position;
        this.gallerySwipeSingleFragment = gallerySwipeSingleFragment;
        this.onMapButtonPressTouchListener = onMapButtonPressTouchListener;
    }


    /**
     * The view type of the recycler view, depending on the position on the recycler view
     *
     * @param position the position of the view in the recycler view
     * @return viewType
     */
    @Override
    public int getItemViewType(int position) {
        int viewType = 100;

        if (position == 0) {
            viewType = Header_View_Type;
        }

        if (position == 1) {
            viewType = Post_View_Type;
        }

        return viewType;

    }

    /**
     * Determines the XML Layout that matches with the particular view type and inflates it
     *
     * @param parent   ViewGroup
     * @param viewType viewType
     * @return ViewHolder
     */
    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view;
        MyViewHolder myViewHolder;

        if (viewType == Header_View_Type) {
            view = inflater.inflate(R.layout.swipe_fragment_header, parent, false);

        } else if (viewType == Post_View_Type) {

            view = inflater.inflate(R.layout.post_comment, parent, false);
        } else {
            view = inflater.inflate(R.layout.comment_item, parent, false);

        }
        myViewHolder = new MyViewHolder(view, viewType);

        return myViewHolder;
    }

    /**
     * Reads the image from Assets and returns a bitmap drawable
     *
     * @param context  Context of Activity
     * @param filename Filename of the image
     * @return BitmapDrawable of the image
     * @throws IOException If the image can not be found
     */
    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();

        if (filename.equals("ic_map") || filename.equals("ic_share")) {
            filename += ".png";
        } else {
            filename += ".jpg";

        }
        InputStream buffer = new BufferedInputStream((assets.open(filename)));

        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }


    /**
     * Reads the image from Assets and returns a bitmap
     *
     * @param context  Context of Activity
     * @param filename Filename of the image
     * @return Bitmap image
     * @throws IOException If the image can not be found
     */
    public static Bitmap getBitmapAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();

        if (filename.equals("ic_map") || filename.equals("ic_share")) {
            filename += ".png";
        } else {
            filename += ".jpg";

        }
        InputStream buffer = new BufferedInputStream((assets.open(filename)));

        return BitmapFactory.decodeStream(buffer);
    }

    /**
     * Binds the actions of the layout depending on which layout is at that position
     *
     * @param holder   Holder of CommentListAdapter
     * @param position Position of view
     */
    @Override
    public void onBindViewHolder(final CommentListAdapter.MyViewHolder holder, int position) {
        if (position == 0) {
            //Set images


            try {
                holder.dynamicHeightImageView.setImageDrawable(getAssetImage(context, galleryData.get(indexOfArtwork).getPic()));
                holder.inspirationArtworkImageView.setImageDrawable(getAssetImage(context, galleryData.get(indexOfArtwork).getInspiredPic()));

            } catch (IOException e) {
                e.printStackTrace();
            }


            //Set the text and links
            holder.descriptionTitle.setText(galleryData.get(indexOfArtwork).getName());
            holder.descriptionTitleArtist.setText("By " + galleryData.get(indexOfArtwork).getArtistName());
            holder.description.setText(galleryData.get(indexOfArtwork).getDesc());
            String linksText = "";
            for (int i = 0; i < galleryData.get(indexOfArtwork).getWebLinks().length; i++) {
                linksText += galleryData.get(indexOfArtwork).getWebLinks()[i] + "\n";
            }
            holder.extraLinksText.setText(linksText);
            holder.inspirationTitle.setText("\"" + galleryData.get(indexOfArtwork).getInspirationTitle() + "\"");
            holder.inspirationTitleArtist.setText("By " + galleryData.get(indexOfArtwork).getInspirationArtist());
            //OnClickListener for opening facebook profile
            holder.linkToFbPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linkToFb("779461175469412/photos/" + galleryData.get(indexOfArtwork).getFbLink(), false);
                }
            });

            Bitmap bitmaplikeun = BitmapFactory.decodeResource(context.getResources(), R.drawable.likeunpressed);
            Bitmap bitmaplike = BitmapFactory.decodeResource(context.getResources(), R.drawable.likepressed);
            BitmapDrawable likeunpressed = new BitmapDrawable(context.getResources(), bitmaplikeun);
            BitmapDrawable likepressed = new BitmapDrawable(context.getResources(), bitmaplike);

            if (numberOfLikesPost != null) {
                if (userLikes) {
                    holder.likePostButton.setImageDrawable(likepressed);

                } else {
                    holder.likePostButton.setImageDrawable(likeunpressed);

                }
                String people = "";
                if (Integer.parseInt(numberOfLikesPost) == 1) {
                    holder.likeFbPost.setText(numberOfLikesPost + " person likes this.");

                } else if (Integer.parseInt(numberOfLikesPost) == 0) {
                    holder.likeFbPost.setText("No one likes this yet. Be the first!");
                } else {
                    holder.likeFbPost.setText(numberOfLikesPost + " people like this.");
                }
                holder.likeFbPost.setVisibility(View.VISIBLE);
                holder.likePostButton.setVisibility(View.VISIBLE);

            } else {
                holder.likePostButton.setVisibility(View.GONE);
                holder.likeFbPost.setVisibility(View.GONE);
            }

            String logout = "";
            final Session session = Session.getActiveSession();
            if (!(session == null) && session.isOpened()) {
                String viewComment = "View comments and likes";
                logout = "Logout (Facebook)";
                commentAmount = viewComment.replace("View", "<font color = '#009672'> View </font>");
                checkIfLogIn = true;
            } else {
                String login = "Log in to Facebook to view comments and likes";
                commentAmount = login.replace("Log in", "<font color = '#009672'> Log in </font>");
                checkIfLogIn = false;
                holder.likePostButton.setVisibility(View.GONE);
                holder.likeFbPost.setVisibility(View.GONE);
            }
            if (data.size() > 0) {
                commentAmount = data.size() + " comments";
            }
            holder.commentTitle.setText(Html.fromHtml(commentAmount));
            holder.logout.setText(Html.fromHtml("<font color = '#009672'>" + logout + "</font>"));

            holder.likePostButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isInternetAvailable()) {
                        gallerySwipeSingleFragment.likePhotoPost(userLikes);

                    } else {
                        Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            holder.logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isInternetAvailable()) {
                        showLogoutDialog();
                    } else {
                        Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
                    }
                }

            });


        } else if (position == 1) {
            //Post box item
            if (name != null) {

                holder.postBox.setHint(Html.fromHtml("<b>" + "Posting as " + name + "</b>"));
            }

            holder.postBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (holder.postBox.getText().length() > 0) {
                        holder.post.setEnabled(true);
                    } else {
                        holder.post.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            holder.post.setEnabled(false);
            holder.post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isInternetAvailable()) {
                        gallerySwipeSingleFragment.postComment(holder.postBox.getText().toString(), "");
                        holder.postBox.setText("");
                    } else {
                        Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        } else {
            //Comment Items
            final Comment commentInfo = data.get(position - 2);
            holder.posterName.setText(commentInfo.getPosterName());
            holder.posterName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isPage = commentInfo.isPage();
                    String link = commentInfo.getPosterURL();
                    linkToFb(link, isPage);
                }
            });

            holder.message.setText(commentInfo.getMessage());

            if (commentInfo.getCanDelete()) {
                // If the comment is deletable by the user
                holder.deleteIcon.setImageResource(R.drawable.cross_grey);
                holder.deleteIcon.setVisibility(View.VISIBLE);
            } else {
                holder.deleteIcon.setVisibility(View.INVISIBLE);

            }
            holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isInternetAvailable()) {
                        showDeleteDialog(commentInfo);

                    } else {
                        Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if (commentInfo.getIsAReply()) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(75, 0, 0, 0);
                holder.message.setTextSize(13);
                holder.numberLikes.setTextSize(11);
                holder.likeWord.setTextSize(11);
                holder.posterName.setTextSize(15);
                holder.timestamp.setTextSize(11);
                holder.reply.setVisibility(View.INVISIBLE);
                holder.reply.setOnClickListener(null); //Removes reply button listener if it's a reply.
                holder.itemView.setLayoutParams(params);
            } else {
                holder.reply.setVisibility(View.VISIBLE);
                holder.reply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isInternetAvailable()) {
                            showCommentsDialog(commentInfo);
                        } else {
                            Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.message.setTextSize(15);
                holder.numberLikes.setTextSize(13);
                holder.likeWord.setTextSize(13);
                holder.posterName.setTextSize(17);
                holder.timestamp.setTextSize(13);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 0);
                holder.itemView.setLayoutParams(params);
            }
            if (commentInfo.getNumberLikes() >= 1) {
                holder.numberLikes.setVisibility(View.VISIBLE);
                holder.likeIcon.setVisibility(View.VISIBLE);
                holder.numberLikes.setText(commentInfo.getNumberLikes() + "");
                holder.likeIcon.setImageResource(R.drawable.ic_facebook_like_thumb);
            } else {
                holder.numberLikes.setVisibility(View.INVISIBLE);
                holder.likeIcon.setVisibility(View.INVISIBLE);
            }

            if (commentInfo.getUserLikes()) {
                holder.likeWord.setText("Unlike");
            } else {
                holder.likeWord.setText("Like");
            }
            holder.likeWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isInternetAvailable()) {
                        gallerySwipeSingleFragment.likeComment(commentInfo.getCommentID(), commentInfo.getUserLikes());
                    } else {
                        Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            int year = Integer.parseInt(commentInfo.getTime().substring(0, 4));
            int month = Integer.parseInt(commentInfo.getTime().substring(5, 7)) - 1;
            int day = Integer.parseInt(commentInfo.getTime().substring(8, 10));
            int hour = Integer.parseInt(commentInfo.getTime().substring(11, 13));
            int minute = Integer.parseInt(commentInfo.getTime().substring(14, 16));
            Calendar postDate = GregorianCalendar.getInstance();
            postDate.set(year, month, day, hour, minute);

            Date d = postDate.getTime();
            if (d.compareTo(new Date()) > 0) { //current time is before posttime
                holder.timestamp.setText(postDate.getTime().toString().substring(4, 10) + " " + year + " at " + postDate.getTime().toString().substring(11, 16));
            } else {
                Map<TimeUnit, Long> timeSincePost = getTimeDifference(postDate.getTime());
                if (timeSincePost.get(TimeUnit.DAYS) > 0 && timeSincePost.get(TimeUnit.DAYS) < 8) {
                    if (timeSincePost.get(TimeUnit.DAYS) == 1) {
                        holder.timestamp.setText("One day ago");
                    } else {
                        holder.timestamp.setText(timeSincePost.get(TimeUnit.DAYS) + " days ago");
                    }
                } else if (timeSincePost.get(TimeUnit.DAYS) > 7) {
                    holder.timestamp.setText(postDate.getTime().toString().substring(4, 10) + " " + year + " at " + postDate.getTime().toString().substring(11, 16));
                } else if (timeSincePost.get(TimeUnit.DAYS) == 0) {
                    if (timeSincePost.get(TimeUnit.HOURS) == 1) {
                        holder.timestamp.setText("About one hour ago");
                    } else if (timeSincePost.get(TimeUnit.HOURS) > 1) {
                        holder.timestamp.setText(timeSincePost.get(TimeUnit.HOURS) + " hours ago");
                    } else if (timeSincePost.get(TimeUnit.HOURS) == 0) {
                        if (timeSincePost.get(TimeUnit.MINUTES) == 1) {
                            holder.timestamp.setText("About one minute ago");
                        } else {
                            holder.timestamp.setText(timeSincePost.get(TimeUnit.MINUTES) + " minutes ago");
                        }
                    }
                }
            }
        }


    }

    /**
     * Opens a link in the facebook app if it is installed on the users phone else it will open on a web browser
     *
     * @param link
     * @param isPage
     */
    public void linkToFb(String link, boolean isPage) {
        String facebookUrl = "https://www.facebook.com/" + link;
        try {
            int versionCode = context.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                Uri uri;
                if (isPage) {
                    //works for pages
                    uri = Uri.parse("fb://page/" + link);

                } else {
                    // /works for photos and profile
                    uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);

                }
                context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } else {
                // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + link)));
            }
        } catch (PackageManager.NameNotFoundException e) {
            // Facebook is not installed. Open the browser
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
        }
    }

    @Override
    // Returns the size of the recycler view based on the size of the data
    public int getItemCount() {
        if (data.size() == 0) {
            return 1;
        } else {
            return data.size() + 2;
        }
    }

    /**
     * Calculates difference between two dates and returns as Map containing keys for DAY, HOURS, MINUTES, SECONDS, MILLISECONDS etc etc...
     *
     * @param date1 Date of Facebook comment
     * @return Difference in date and time between comment and current time
     */
    public Map<TimeUnit, Long> getTimeDifference(Date date1) {
        Date date2 = new Date();
        long diffInMill = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);

        Map<TimeUnit, Long> result = new LinkedHashMap<>();
        long millRest = diffInMill;
        for (TimeUnit unit : units) {
            long diff = unit.convert(millRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            millRest = millRest - diffInMilliesForUnit;
            result.put(unit, diff);
        }
        return result;
    }

    /**
     * Notifies when comments data has changed
     *
     * @param data The "new" data
     */
    public void commentsChanged(ArrayList<Comment> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    /**
     * This is the Interface for allowing clicks to go to the maps
     */
    public interface OnMapButtonPressTouchListener {
        public void onMapButtonPress(boolean filter, String name);
    }

    /**
     * Changes the poster's name
     *
     * @param name Poster's name
     */
    public void nameChange(String name) {
        this.name = name;
    }

    /**
     * Changes the number of likes and whether the user likes the post
     *
     * @param numberOfLikesPost Number of likes on the facebook post
     * @param userLikes         true if user likes the post
     */
    public void likePostChange(String numberOfLikesPost, boolean userLikes) {
        this.userLikes = userLikes;
        this.numberOfLikesPost = numberOfLikesPost;
        notifyDataSetChanged();
    }

    /**
     * Shows log in to facebook confirmation dialog
     */
    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(gallerySwipeSingleFragment.getActivity());
        builder.setTitle("Login to Facebook");
        builder.setMessage(R.string.log_in_fb_about);
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gallerySwipeSingleFragment.onClickLogin();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setLayout(400, 400);
        dialog.show();
    }

    /**
     * Shows a delete comment verification dialog
     *
     * @param commentInfo Comments for the specified position to get the ID of the comment
     */
    private void showDeleteDialog(final Comment commentInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(gallerySwipeSingleFragment.getActivity());
        builder.setTitle("Delete comment");
        builder.setMessage(R.string.delete_fb_comment);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gallerySwipeSingleFragment.deleteComment(commentInfo.getCommentID());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setLayout(400, 200);
        dialog.show();
    }

    /**
     * Shows a FB Logout confirmation dialog
     */
    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(gallerySwipeSingleFragment.getActivity());
        builder.setTitle("Log out of Facebook");
        builder.setMessage(R.string.logout_fb);
        builder.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Session.getActiveSession() != null) {
                    Session.getActiveSession().closeAndClearTokenInformation();
                }

                Session.setActiveSession(null);
                numberOfLikesPost = null;
                data.clear();
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setLayout(400, 200);
        dialog.show();
    }

    /**
     * Shows a reply to comment dialog
     *
     * @param commentInfo The comment's details for a specific position
     */
    private void showCommentsDialog(final Comment commentInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(gallerySwipeSingleFragment.getActivity());
        builder.setTitle("Reply to " + commentInfo.getPosterName());
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(50, 0, 50, 0);
        TextView message = new TextView(context);
        message.setText(commentInfo.getMessage());
        message.setMaxLines(7);
        message.setTextColor(Color.BLACK);
        message.setMovementMethod(new ScrollingMovementMethod());

        final EditText input = new EditText(context);
        input.setSingleLine(false);
        input.setMaxLines(3);
        input.setTextColor(Color.BLACK);
        linearLayout.addView(message);
        linearLayout.addView(input);
        builder.setView(linearLayout);


        builder.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().length() > 0) {
                    gallerySwipeSingleFragment.postComment(input.getText().toString(), commentInfo.getCommentID());
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * Checks if you have internet connection on the phone
     * Though this does not handle the fact that the servers of the providers may be down. (Such as fb servers being down)
     *
     * @return boolean true if there is connection
     */
    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) gallerySwipeSingleFragment.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Recycler view's details, based on the view type cases
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        // view holder for each grid  cell
        private ImageView likePostButton;
        private TextView posterName;
        private TextView logout;
        private EditText postBox;
        private Button post;
        private TextView message;
        private TextView timestamp;
        private TextView description;
        private TextView descriptionTitle;
        private TextView descriptionTitleArtist;
        private TextView likeWord;
        private TextView numberLikes;
        private TextView reply;
        private ImageView likeIcon;
        private ImageView deleteIcon;
        private TextView linkToFbPost;
        private TextView likeFbPost;

        private ImageView shareButton;
        private ImageView mapButton;
        private TextView commentTitle;
        private TextView inspirationTitle;
        private TextView inspirationTitleArtist;
        private TextView extraLinksText;
        private DynamicHeightImageView dynamicHeightImageView;
        private DynamicHeightImageView inspirationArtworkImageView;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);

            switch (viewType) {
                default:
                    //setup comment view elements
                    posterName = (TextView) itemView.findViewById(R.id.name);
                    message = (TextView) itemView.findViewById(R.id.comment);
                    timestamp = (TextView) itemView.findViewById(R.id.time);
                    numberLikes = (TextView) itemView.findViewById(R.id.number_likes);
                    likeIcon = (ImageView) itemView.findViewById(R.id.like_picture);
                    deleteIcon = (ImageView) itemView.findViewById(R.id.delete_comment);
                    likeWord = (TextView) itemView.findViewById(R.id.like);
                    reply = (TextView) itemView.findViewById(R.id.reply);

                    break;

                case Post_View_Type:
                    //setup comment box elements
                    postBox = (EditText) itemView.findViewById(R.id.post_box);
                    post = (Button) itemView.findViewById(R.id.post);
                    break;

                case Header_View_Type:
                    // setup the header view elements
                    dynamicHeightImageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_imageview_artwork_display);
                    inspirationArtworkImageView = (DynamicHeightImageView) itemView.findViewById(R.id.inspiration_artwork);
                    description = (TextView) itemView.findViewById(R.id.description);

                    descriptionTitle = (TextView) itemView.findViewById(R.id.comment_list_description_title);
                    descriptionTitleArtist = (TextView) itemView.findViewById(R.id.comment_list_description_title_artist);


                    inspirationTitle = (TextView) itemView.findViewById(R.id.comment_list_inspiration_title);
                    inspirationTitleArtist = (TextView) itemView.findViewById(R.id.comment_list_inspiration_title_artist);

                    extraLinksText = (TextView) itemView.findViewById(R.id.comment_list_more_info_links);

                    linkToFbPost = (TextView) itemView.findViewById(R.id.comment_list_fb_links);
                    likeFbPost = (TextView) itemView.findViewById(R.id.like_post);
                    likePostButton = (ImageView) itemView.findViewById(R.id.like_post_icon);


                    commentTitle = (TextView) itemView.findViewById(R.id.commentAmount);
                    shareButton = (ImageView) itemView.findViewById(R.id.shareIcon);
                    mapButton = (ImageView) itemView.findViewById(R.id.mapIcon);
                    logout = (TextView) itemView.findViewById(R.id.logout);

                    commentTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isInternetAvailable()) {
                                if (checkIfLogIn) {
                                    //if logged in just get the comments and show
                                    gallerySwipeSingleFragment.onClickLogin();
                                } else if (!checkIfLogIn) {
                                    // if not logged in show dialog box telling them what happens if they log in
                                    showLoginDialog();
                                }
                            } else {
                                Toast.makeText(gallerySwipeSingleFragment.getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    mapButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.viewPager.setCurrentItem(3, true);
                            onMapButtonPressTouchListener.onMapButtonPress(false, galleryData.get(indexOfArtwork).getName());
                        }
                    });

                    shareButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            OutputStream output;
                            // Retrieve the image from the res folder

                            // Find the SD Card path
                            File filepath = Environment.getExternalStorageDirectory();

                            // Create a new folder AndroidBegin in SD Card
                            File dir = new File(filepath.getAbsolutePath() + "/Dulwich Outdoor Gallery/");
                            dir.mkdirs();

                            // Create a name for the saved image
                            File file = new File(dir, "sample_wallpaper.png");

                            try {

                                // Share Intent
                                Intent share = new Intent(Intent.ACTION_SEND);

                                // Type of file to share
                                share.setType("image/jpeg");


                                output = new FileOutputStream(file);

                                // Compress into png format image from 0% - 100%
                                getBitmapAssetImage(context, galleryData.get(indexOfArtwork).getInspiredPic()).compress(Bitmap.CompressFormat.PNG, 100, output);
                                output.flush();
                                output.close();

                                // Locate the image to Share
                                Uri uri = Uri.fromFile(file);

                                // Pass the image into an Intent
                                share.putExtra(Intent.EXTRA_STREAM, uri);

                                // Show the social share chooser list
                                context.startActivity(Intent.createChooser(share, "Dulwich Outdoor Art"));


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    });

                    break;
            }

        }


    }

}

