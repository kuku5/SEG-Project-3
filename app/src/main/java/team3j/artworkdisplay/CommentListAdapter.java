package team3j.artworkdisplay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.facebook.Session;

import java.io.File;
import java.io.FileOutputStream;
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
import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.GoogleMapFragmentSmall;
import team3j.dulwichstreetart.HomePageFragment;
import team3j.dulwichstreetart.MainActivity;
import team3j.dulwichstreetart.R;

/**
 * Created by JGill on 03/02/15.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final int indexOfArtwork;
    private GallerySwipeSingleFragment gallerySwipeSingleFragment;
    private String commentAmount = null;
    private ArrayList<Comment> data;
    private ArrayList<Art> galleryData;
    private Context context;
    private ImageView shareButton;
    private ImageView mapButton;
    private Bitmap bitmap;

    private final int Header_View_Type = 1;
    private final int Comment_View_Type = 0;
    private final int Post_View_Type = 2;
    private boolean checkIfLogIn = false;



    public CommentListAdapter(GallerySwipeSingleFragment gallerySwipeSingleFragment, Context context, int position,ArrayList<Art> galleryData) {
        //this.commentAmount = commentAmount;
        data = new ArrayList<Comment>();
        this.galleryData=galleryData;
        //this.data = data;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.indexOfArtwork = position;
        //System.out.println(data);
        this.gallerySwipeSingleFragment = gallerySwipeSingleFragment;
    }


    @Override
    public int getItemViewType(int position) {
        int viewType = position+100 ;

        if (position == 0) {
            viewType = Header_View_Type;
        }

        if (position == 1) {
            viewType = Post_View_Type;
        }

        return viewType;

    }

    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view ;
        MyViewHolder myViewHolder;

        if (viewType == Header_View_Type) {
            view = inflater.inflate(R.layout.swipe_fragment_header, parent, false);

        } else if (viewType == Post_View_Type) {

            view = inflater.inflate(R.layout.post_comment, parent, false);
        }

        else {
            view = inflater.inflate(R.layout.comment_item, parent, false);

        }
        myViewHolder = new MyViewHolder(view, viewType);

        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(final CommentListAdapter.MyViewHolder holder, int position) {

        if (position == 0) {

            Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), galleryData.get(indexOfArtwork).getInspiredPic());
            BitmapDrawable res1 = new BitmapDrawable(context.getResources(), bitmap1);

            bitmap = BitmapFactory.decodeResource(context.getResources(), galleryData.get(indexOfArtwork).getPic());
            BitmapDrawable res = new BitmapDrawable(context.getResources(), bitmap);

            //update header
            holder.dynamicHeightImageView.setImageDrawable(res1);

            holder.inspirationArtworkImageView.setImageDrawable(res);

            holder.descriptionTitle.setText(galleryData.get(indexOfArtwork).getInspirationTitle());
            holder.description.setText(galleryData.get(indexOfArtwork).getDesc());

            Session session = Session.getActiveSession();
            if(!(session==null) && session.isOpened()) {
                String viewComment = "View comments";
                String htmlTextView = viewComment.replace("View", "<font color = '#009672'> View </font>");
                commentAmount = htmlTextView;
                checkIfLogIn = true;
            }
            else {
                commentAmount = "Click here to log in to Facebook to view comments";

                String login = "Log in to Facebook to view comments";
                String htmlTextLog = login.replace("Log in", "<font color = '#009672'> Log in </font>");
                commentAmount = htmlTextLog;
                checkIfLogIn = false;
            }
            if(data.size() > 0){
                commentAmount = data.size() + " comments";
            }
            //holder.commentTitle.setText(commentAmount);
            holder.commentTitle.setText(Html.fromHtml(commentAmount));

        } else if (position == 1) {
            holder.post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //THIS IS WHERE THE POST COMMENT TO FACEBOOK CODE WILL GO
                    //System.out.println(holder.postBox.getText().toString());
                    CustomProcessDialog customProcessDialog = new CustomProcessDialog(gallerySwipeSingleFragment.getActivity());

                    gallerySwipeSingleFragment.postComment(holder.postBox.getText().toString(), customProcessDialog);
                    holder.postBox.setText("");

                }
            });

        }


        else {


            final Comment commentInfo = data.get(position - 2);

//            if (holder.posterName != null) {
//                holder.posterName.setText(Html.fromHtml("<a href=\"http://www.facebook.com/"+commentInfo.getPosterURL()+"\">"+commentInfo.getPosterName()+"</a> "));
//                holder.posterName.setMovementMethod(LinkMovementMethod.getInstance());
//            }
            holder.posterName.setText(commentInfo.getPosterName());
            holder.posterName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String facebookUrl = "https://www.facebook.com/"+commentInfo.getPosterURL();
                    try {
                        int versionCode = context.getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                        System.out.println("VERSION CODE HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE "+versionCode);
                        if (versionCode >= 3002850) {
                            Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));;
                        } else {
                            // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+commentInfo.getPosterURL())));
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        // Facebook is not installed. Open the browser
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
                    }
                }
            });


            if(commentInfo.getIsAReply()){
                holder.message.setText("(Reply) " + commentInfo.getMessage());
            }else {
                holder.message.setText(commentInfo.getMessage());
            }
            if (Integer.parseInt(commentInfo.getNumberLikes())>=1) {
                holder.numberLikes.setVisibility(View.VISIBLE);
                holder.likeIcon.setVisibility(View.VISIBLE);
                holder.numberLikes.setText(commentInfo.getNumberLikes());
                holder.likeIcon.setImageResource(R.drawable.ic_facebook_like_thumb);
            }
            else{
                holder.numberLikes.setVisibility(View.INVISIBLE);
                holder.likeIcon.setVisibility(View.INVISIBLE);
            }

            if (commentInfo.getUserLikes() == true) {
                holder.likeWord.setText("Unlike");
            }
            else{
                holder.likeWord.setText("Like");
            }
            holder.likeWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gallerySwipeSingleFragment.likeComment(commentInfo.getCommentID(), commentInfo.getUserLikes());
                }
            });

            int year = Integer.parseInt(commentInfo.getTime().substring(0, 4));
            int month = Integer.parseInt(commentInfo.getTime().substring(5, 7)) - 1;
            int day = Integer.parseInt(commentInfo.getTime().substring(8, 10));
            int hour = Integer.parseInt(commentInfo.getTime().substring(11, 13));
            int minute = Integer.parseInt(commentInfo.getTime().substring(14, 16));
            //System.out.println("TEEEEEEEEEEEEST   " + year + "   " + month + "   " + day + "   " + hour + "   " + minute);
            Calendar postDate = GregorianCalendar.getInstance();
            postDate.set(year, month, day, hour, minute);
            Map<TimeUnit, Long> timeSincePost = getTimeDifference(postDate.getTime(), new Date()); // new Date = current
            //System.out.println(commentInfo.getTime());
            //System.out.println("MONTH " + month + "  HOUR " + hour + " MINUTE " + minute);
            //System.out.println(postDate.getTime());
            if (timeSincePost.get(TimeUnit.DAYS) > 0 && timeSincePost.get(TimeUnit.DAYS) < 8) {
                if (timeSincePost.get(TimeUnit.DAYS) == 1) {
                    holder.timestamp.setText("One day ago");
                }
                else {
                    holder.timestamp.setText(timeSincePost.get(TimeUnit.DAYS) + " days ago");
                }
            } else if (timeSincePost.get(TimeUnit.DAYS) > 7) {
                holder.timestamp.setText(postDate.getTime().toString().substring(4, 10) +" "+ year+" at " + postDate.getTime().toString().substring(11, 16));
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

    @Override
    public int getItemCount() {
        if (data.size() == 0) {
            return 1;
        } else {
            return data.size() + 2;
        }
    }


    //Calculates difference between two dates and returns as Map containing keys for DAY, HOURS, MINUTES, SECONDS, MILLISECONDS etc etc...
    public Map<TimeUnit, Long> getTimeDifference(Date date1, Date date2) {
        long diffInMill = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);

        Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
        long millRest = diffInMill;
        for (TimeUnit unit : units) {
            long diff = unit.convert(millRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            millRest = millRest - diffInMilliesForUnit;
            result.put(unit, diff);
        }
        return result;
    }


    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(gallerySwipeSingleFragment.getActivity());
        builder.setTitle("Login to Facebook");
        builder.setMessage(R.string.log_in_fb_about);
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gallerySwipeSingleFragment.onClickLogin();
                //notifyDataSetChanged();
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        EditText postBox;
        Button post;
        // view holder for each grid  cell
        TextView posterName;
        TextView message;
        TextView timestamp;
        TextView description;
        TextView descriptionTitle;
        TextView likeWord;
        TextView numberLikes;
        ImageView likeIcon;

        private TextView commentTitle;
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
                    likeWord = (TextView) itemView.findViewById(R.id.like);
                    posterName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //onArtistItemTouchListener.onItemClick(v, getPosition());
                        }
                    });

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
                    descriptionTitle = (TextView) itemView.findViewById(R.id.description_title_single);


                    commentTitle = (TextView) itemView.findViewById(R.id.commentAmount);
                    shareButton = (ImageView) itemView.findViewById(R.id.shareIcon);
                    mapButton = (ImageView) itemView.findViewById(R.id.mapIcon);

                    commentTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkIfLogIn == true) {
                                //if logged in just get the comments and show
                                gallerySwipeSingleFragment.onClickLogin();
                                //notifyDataSetChanged();
                            }
                            else if (checkIfLogIn == false){
                                // if not logged in show dialog box telling them what happens if they log in
                                showLoginDialog();
                            }
                            //data = gallerySwipeSingleFragment.onClickLogin();
                            System.out.println("CommentsListAdapter" + data);

                            //notifyDataSetChanged();
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
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                                output.flush();
                                output.close();

                                // Locate the image to Share
                                Uri uri = Uri.fromFile(file);

                                // Pass the image into an Intent
                                share.putExtra(Intent.EXTRA_STREAM, uri);

                                // Show the social share chooser list
                                context.startActivity(Intent.createChooser(share, "Dulwich Outdoor Art"));


                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }


                    });


                    break;
            }

        }


    }


    public void commentsChanged(ArrayList<Comment> data){
        this.data = data;
        notifyDataSetChanged();
    }

}

