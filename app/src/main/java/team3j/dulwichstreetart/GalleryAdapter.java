package team3j.dulwichstreetart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
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
    private Context context;
    private int[] imageSet;

    public GalleryAdapter(Context context,ArrayList<String> data,int[] imageSet, OnItemTouchListener itemTouchListener){
        this.data=data;
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.imageSet=imageSet;
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
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),imageSet[position]);
        BitmapDrawable res = new BitmapDrawable(context.getResources(), bitmap);
        holder.dynamicHeightImageView.setImageDrawable(res);
        holder.txtLineOne.setText(data.get(position));


    }

    @Override
    public int getItemCount() {
        return 35;
    }

    //custom viewHolder for each item in recycle view
    class MyViewHolder extends RecyclerView.ViewHolder  {
        // view holder for each grid  cell
        TextView txtLineOne;
        DynamicHeightImageView dynamicHeightImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtLineOne = (TextView) itemView.findViewById(R.id.txt_line1);
            dynamicHeightImageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_imageView);


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

