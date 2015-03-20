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

    /*TODO potentially add a pre lollipop curve to cards
        or just set up to put a separate grid view for the
        for lollipop and pre lollipop
     */
    private OnItemTouchListener onItemTouchListener;

    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<Art> galleryData;
    private Bitmap bitmap1;


    /**
     * @param context
     * @param itemTouchListener
     * @param galleryData
     */
    public VisitedAdapter(Context context, OnItemTouchListener itemTouchListener, ArrayList<Art> galleryData) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.onItemTouchListener = itemTouchListener;
        this.galleryData = galleryData;
    }


    /**
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
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position;

    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(VisitedAdapter.MyViewHolderVisited holder, int position) {


        //add image and description to the view for each gallery item
        holder.txtLineOne.setText("" + galleryData.get(position).getName());

//        if (galleryData.get(position).getVisited() == true) {
//            holder.visitedQuestion_textView.setText("Visited");
//        } else if (galleryData.get(position).getVisited() == false) {
//            holder.visitedQuestion_textView.setText("Not Visited");
//        }

        if(SplashActivity.artArrayList.get(position).getVisited() == true){
            holder.visitedQuestion_textView.setText("Visited");
            holder.visitedCard.setCardBackgroundColor(context.getResources().getColor(R.color.colorHighlight));
            holder.txtLineOne.setTextColor(context.getResources().getColor(R.color.white));
            holder.visitedQuestion_textView.setTextColor(context.getResources().getColor(R.color.white));

        }
        else if(SplashActivity.artArrayList.get(position).getVisited() == false) {
            holder.visitedQuestion_textView.setText("Not Visited");
        }

      //   bitmap1 = BitmapFactory.decodeResource(context.getResources(), galleryData.get(position).getPic());
      //  BitmapDrawable res1 = new BitmapDrawable(context.getResources(), bitmap1);
        try {
            holder.image.setImageDrawable(getAssetImage(context,galleryData.get(position).getPic()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.visited_description.setText("You last visited this art on: " + SplashActivity.artArrayList.get(position).getDateVisited());

        holder.art_address.setText("Address: " + galleryData.get(position).getArtAddress());

        //holder.art_info_area.setText("" + GalleryData.toVisit.get(position-1).getDesc());
        // holder.art_info_area.setText("" + galleryData.get(position).getDesc());


    }
    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open(filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    //custom viewHolder for each item in recycle view

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

    /**
     *
     */
    //interface need for Recycle Views to handle clicks
    public interface OnItemTouchListener {
        public void onCardViewTap(View view, int position);
    }


//    public void saveToFile(){
//        String fileName = "visitedOrNot";
//        //File file = new File(context.getFilesDir(), fileName);
//
//        FileOutputStream outputStream;
//
//        try {
//            //outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
//            outputStream = openFileOutput("visitedOrNot", Context.MODE_PRIVATE);
//            outputStream.write();
//
//        }
//
//    }


}

