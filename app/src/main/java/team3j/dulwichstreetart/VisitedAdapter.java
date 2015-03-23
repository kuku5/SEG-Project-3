package team3j.dulwichstreetart;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Team 3-J
 *         This is the adapter that puts elements into the Gallery
 */
public class VisitedAdapter extends RecyclerView.Adapter<VisitedAdapter.MyViewHolderVisited> {


    private final LayoutInflater inflater;
    private Context context;
    public static ArrayList<Art> galleryData;



    /**
     * This is the Constructor for the Visited adapter and takes the gallery data and click listeners as parameters
     * so they can be used to setup the recycler view
     * @param context
     * @param galleryData
     */
    public VisitedAdapter(Context context,  ArrayList<Art> galleryData) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.galleryData = galleryData;
    }


    /**
     * This method inflates the layout for each individual ViewHolder item in the visited tab cards
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public VisitedAdapter.MyViewHolderVisited onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflates the correct view
        View view = inflater.inflate(R.layout.visited_list_item, parent, false);

        MyViewHolderVisited myViewHolder = new MyViewHolderVisited(view, viewType);

        return myViewHolder;
    }

    /**
     * @param position
     * @return position
     */
    @Override
    public int getItemViewType(int position) {
        return position;

    }

    /**
     *
     * onBindViewHolder is called to fill elements in each card in the visited tab with its respective information and design
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final VisitedAdapter.MyViewHolderVisited holder,final int position) {

        //add image and description to the view for each gallery item
        holder.txtLineOne.setText("" + galleryData.get(position).getName());

        if(SplashActivity.artArrayList.get(position).getVisited() == true){
            holder.visitedQuestion_textView.setText("Visited");
            holder.visitedCard.setCardBackgroundColor(context.getResources().getColor(R.color.colorHighlight));
            holder.txtLineOne.setTextColor(context.getResources().getColor(R.color.white));
            holder.visitedQuestion_textView.setTextColor(context.getResources().getColor(R.color.white));

        }
        else if(SplashActivity.artArrayList.get(position).getVisited() == false) {
            holder.visitedQuestion_textView.setText("Not Visited");
            holder.visitedCard.setCardBackgroundColor(context.getResources().getColor(R.color.visitedTabGrey));
            holder.txtLineOne.setTextColor(context.getResources().getColor(R.color.colorHighlight));
            holder.visitedQuestion_textView.setTextColor(context.getResources().getColor(R.color.colorHighlight));
        }

        holder.image.setImageDrawable(galleryData.get(position).getDrawableStreet());

        holder.visited_description.setText("You last visited this art on: " + SplashActivity.artArrayList.get(position).getDateVisited());

        holder.art_address.setText("Address: " + galleryData.get(position).getArtAddress());

    }

    /**
     *
     * Reads the image from Assets and returns a bitmap drawable
     * @param context
     * @param filename
     * @return BitmapDrawable of the image
     * @throws IOException If the image cannot be found
     */
    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open(filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * @return size of of the Visited list
     */
    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    /**
     * Custom viewHolder for each item in recycle view
     */
    class MyViewHolderVisited extends RecyclerView.ViewHolder {
        // view holder for each grid  cell
        TextView txtLineOne;
        TextView art_info_area;
        TextView art_address;
        TextView visitedQuestion_textView;
        TextView visited_description;
        RelativeLayout expandArea;
        ImageView image;
        RelativeLayout cardRelative;
        android.support.v7.widget.CardView visitedCard;
//        Button infoButton;
//        Button resetButton;

        boolean expanded = true;

        public MyViewHolderVisited(View itemView, int viewType) {
            super(itemView);

            txtLineOne = (TextView) itemView.findViewById(R.id.textview_visited_item);
            expandArea = (RelativeLayout) itemView.findViewById(R.id.expand_area);
            image = (ImageView) itemView.findViewById(R.id.image_area);
            art_info_area = (TextView) itemView.findViewById(R.id.visited_description);
            art_address = (TextView) itemView.findViewById(R.id.art_address);
            visitedQuestion_textView = (TextView) itemView.findViewById(R.id.visitedQuestion_textView);
            visited_description = (TextView)itemView.findViewById(R.id.visited_description);
            visitedCard = (CardView) itemView.findViewById(R.id.card_view_1_welcome1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expanded) {
                        expandArea.setVisibility(View.GONE);
                        expanded = false;
                    } else {
                        expandArea.setVisibility(View.VISIBLE);
                        expanded = true;

                    }

                }
            });

        }
    }


}

