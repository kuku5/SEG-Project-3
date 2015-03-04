package team3j.dulwichstreetart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;

/**
 * Created by JGill on 03/02/15.
 *This is the adapter that puts elements into the Gallery
 *
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    /*TODO potentially add a pre lollipop curve to cards
        or just set up to put a separate grid view for the
        for lollipop and pre lollipop
     */
    private OnItemTouchListener onItemTouchListener;

    private final LayoutInflater inflater;
    private ArrayList<String> data;
    private ArrayList<Art> galleryData;
    private Context context;
    private int[] imageSet;

    public GalleryAdapter(Context context,ArrayList<Art> galleryData, OnItemTouchListener itemTouchListener){
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.galleryData=galleryData;
        this.onItemTouchListener = itemTouchListener;
    }



    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view= inflater.inflate(R.layout.grid_item,parent,false);

        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.MyViewHolder holder, int position) {
        //add image and description to the view for each gallery item
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),galleryData.get(position).getPic());
        BitmapDrawable res = new BitmapDrawable(context.getResources(), bitmap);
        holder.dynamicHeightImageView.setImageDrawable(res);
        holder.txtLineOne.setText(galleryData.get(position).getName());
        holder.descriptionTextView.setText(galleryData.get(position).getDesc());
        holder.txtLineOne.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.descriptionTextView.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.dynamicHeightImageView.setBackgroundColor(context.getResources().getColor(R.color.white));
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));


    }

    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    //custom viewHolder for each item in recycle view
    class MyViewHolder extends RecyclerView.ViewHolder  {
        // view holder for each grid  cell
        TextView txtLineOne;
        TextView descriptionTextView;
        DynamicHeightImageView dynamicHeightImageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtLineOne = (TextView) itemView.findViewById(R.id.txt_line1);
            dynamicHeightImageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_imageView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_textview);
            cardView= (CardView) itemView.findViewById(R.id.card_view_1_large_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onCardViewTap(v, getPosition());
                }
            });


        }
    }

    //interface need for Recycle Views to handle clicks
    public interface OnItemTouchListener {
        public void onCardViewTap(View view, int position);
        }

}

