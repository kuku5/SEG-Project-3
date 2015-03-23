package team3j.dulwichstreetart;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Team 3-J
 *         This is the RecyclerView.Adapter for the Gallery recycler view and loads images in background thread
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private OnItemTouchListener onItemTouchListener;
    private final LayoutInflater inflater;
    private ArrayList<Art> galleryData;
    private Context context;
    private Bitmap mPlaceHolderBitmap;

    /**
     * This is the Constructor for the Gallery adapter and takes the gallery data and click listeners as parameters
     * so they can be used to setup the recycler view
     *
     * @param context           activity
     * @param galleryData       arraylist of gallery information
     * @param itemTouchListener listener for clicking in gallery tab
     */
    public GalleryAdapter(Context context, ArrayList<Art> galleryData, OnItemTouchListener itemTouchListener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.galleryData = galleryData;
        this.onItemTouchListener = itemTouchListener;
        mPlaceHolderBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.loadimage);
    }


    /**
     * This method inflates the layout for each individual ViewHolder item in the gallery cells
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        View view = inflater.inflate(R.layout.grid_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    /**
     * onBindViewHolder is called to fill elements in each viewholder in the gallery with information
     *
     * @param holder   view holder
     * @param position current position
     */

    @Override
    public void onBindViewHolder(GalleryAdapter.MyViewHolder holder, int position) {

        holder.dynamicHeightImageView.setImageDrawable(galleryData.get(position).getDrawableStreet());
        holder.txtLineOne.setText(galleryData.get(position).getName());
        holder.descriptionTextView.setText(galleryData.get(position).getDesc());

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
        InputStream buffer = new BufferedInputStream((assets.open("" + filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }


    /**
     * Returns number of items in the gallery
     *
     * @return gallery data size
     */
    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    /**
     * This Class is the View Holder for each item in the recycler view and holds access to all elements within the view
     * extends RecyclerView.ViewHolder
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        // view holder for each grid  cell
        private TextView txtLineOne;
        private TextView descriptionTextView;
        private DynamicHeightImageView dynamicHeightImageView;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtLineOne = (TextView) itemView.findViewById(R.id.txt_line1);
            dynamicHeightImageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_imageView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_textview);
            cardView = (CardView) itemView.findViewById(R.id.card_view_1_large_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onCardViewTap(v, getPosition());
                }
            });


        }
    }

    /**
     * interface need for Recycle Views to handle clicks
     */
    public interface OnItemTouchListener {
        public void onCardViewTap(View view, int position);
    }


}


