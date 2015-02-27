package team3j.artworkdisplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.GoogleMapFragmentSmall;
import team3j.dulwichstreetart.MainActivity;
import team3j.dulwichstreetart.R;

/**
 * Created by JGill on 03/02/15.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final int indexOfArtwork;
    private GallerySwipeSingleFragment gallerySwipeSingleFragment;
    private String commentAmount;
    private ArrayList<Comment> data;
    private Context context;
    private ImageView shareButton;
    private ImageView mapButton;
    private Bitmap bitmap;

    private final int Header_View_Type = 1;
    private final int Comment_View_Type = 0;



    public CommentListAdapter(GallerySwipeSingleFragment gallerySwipeSingleFragment, Context context, ArrayList<Comment> data, int position, String commentAmount) {
        this.commentAmount = commentAmount;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.indexOfArtwork = position;
        System.out.println(data);
        this.gallerySwipeSingleFragment = gallerySwipeSingleFragment;
    }


    @Override
    public int getItemViewType(int position) {
        int viewType = Comment_View_Type;

        if (position == 0) {
            viewType = Header_View_Type;
        }

        return viewType;

    }

    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view = inflater.inflate(R.layout.comment_item, parent, false);
        MyViewHolder myViewHolder;

        if (viewType == Header_View_Type) {
            view = inflater.inflate(R.layout.swipe_fragment_header, parent, false);

        } else {
            view = inflater.inflate(R.layout.comment_item, parent, false);

        }
        myViewHolder = new MyViewHolder(view, viewType);

        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(CommentListAdapter.MyViewHolder holder, int position) {

        if (position == 0) {

            bitmap = BitmapFactory.decodeResource(context.getResources(), GalleryData.GetArtWorkImageLocations()[indexOfArtwork]);
            BitmapDrawable res = new BitmapDrawable(context.getResources(), bitmap);
            //update header
            holder.dynamicHeightImageView.setImageDrawable(res);
            holder.commentTitle.setText(commentAmount);

        } else {

            Comment commentInfo = data.get(position - 1);
            holder.posterName.setText(commentInfo.getPosterName());
            holder.message.setText(commentInfo.getMessage());
            int year = Integer.parseInt(commentInfo.getTime().substring(0, 4));
            int month = Integer.parseInt(commentInfo.getTime().substring(5, 7)) - 1;
            int day = Integer.parseInt(commentInfo.getTime().substring(8, 10));
            int hour = Integer.parseInt(commentInfo.getTime().substring(11, 13));
            int minute = Integer.parseInt(commentInfo.getTime().substring(14, 16));
            System.out.println("TEEEEEEEEEEEEST   " + year + "   " + month + "   " + day + "   " + hour + "   " + minute);
            Calendar postDate = GregorianCalendar.getInstance();
            postDate.set(year, month, day, hour, minute);
            Map<TimeUnit, Long> timeSincePost = getTimeDifference(postDate.getTime(), new Date()); // new Date = current
            System.out.println(commentInfo.getTime());
            System.out.println("MONTH " + month + "  HOUR " + hour + " MINUTE " + minute);
            System.out.println(postDate.getTime());
            if (timeSincePost.get(TimeUnit.DAYS) > 0 && timeSincePost.get(TimeUnit.DAYS) < 8) {
                holder.timestamp.setText(timeSincePost.get(TimeUnit.DAYS) + " days ago");
            } else if (timeSincePost.get(TimeUnit.DAYS) > 7) {
                holder.timestamp.setText(postDate.getTime().toString().substring(4, 10) + " at " + postDate.getTime().toString().substring(11, 16));
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
            return data.size() + 1;
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


    class MyViewHolder extends RecyclerView.ViewHolder {
        // view holder for each grid  cell
        TextView posterName;
        TextView message;
        TextView timestamp;

        private TextView commentTitle;
        private DynamicHeightImageView dynamicHeightImageView;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);

            switch (viewType) {
                case Comment_View_Type:
                    //setup comment view elements
                    posterName = (TextView) itemView.findViewById(R.id.name);
                    message = (TextView) itemView.findViewById(R.id.comment);
                    timestamp = (TextView) itemView.findViewById(R.id.time);
                    posterName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //onArtistItemTouchListener.onItemClick(v, getPosition());
                        }
                    });


                    break;

                case Header_View_Type:
                    // setup the header view elements
                    dynamicHeightImageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_imageview_artwork_display);
                    commentTitle = (TextView) itemView.findViewById(R.id.commentAmount);
                    shareButton = (ImageView) itemView.findViewById(R.id.shareIcon);
                    mapButton = (ImageView) itemView.findViewById(R.id.mapIcon);
                    commentTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gallerySwipeSingleFragment.onClickLogin();
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

}

