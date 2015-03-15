package team3j.dulwichstreetart;

import android.content.Context;
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Team 3-J
 *This is the RecyclerView.Adapter for the Gallery recycler view and loads images in background thread
 *
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
     * @param context
     * @param galleryData
     * @param itemTouchListener
     */
    public GalleryAdapter(Context context,ArrayList<Art> galleryData, OnItemTouchListener itemTouchListener){
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.galleryData=galleryData;
        this.onItemTouchListener = itemTouchListener;
         mPlaceHolderBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.loadimage);
    }


    /**
     * This method inflates the layout for each individual ViewHolder item in the gallery cells
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        View view= inflater.inflate(R.layout.grid_item,parent,false);

        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }


    /**
     * onBindViewHolder is called to fill elements in each viewholder in the gallery with information
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(GalleryAdapter.MyViewHolder holder, int position) {
        //add image and description to the view for each gallery item

       //load bitmap in background thread
       loadBitmap( galleryData.get(position).getPic(),  holder.dynamicHeightImageView);

       holder.txtLineOne.setText(galleryData.get(position).getName());
       holder.descriptionTextView.setText(galleryData.get(position).getDesc());



    }

    /**
     *
     * @param resId
     * @param imageView
     */
    public void loadBitmap(int resId, ImageView imageView) {
        if (cancelPotentialWork(resId, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView,context.getResources());
            final AsyncDrawable asyncDrawable =
                    new AsyncDrawable(context.getResources(), mPlaceHolderBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
        }
    }

    /**
     *
     * @param data
     * @param imageView
     * @return
     */
    public static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            // If bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    /**
     *
     * @param imageView
     * @return
     */
    private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    /**
     *
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    /**
     * This Class is the View Holder for each item in the recycler view and holds access to all elements within the view
     * extends RecyclerView.ViewHolder
     */

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




    /**
     * interface need for Recycle Views to handle clicks
     */
    public interface OnItemTouchListener {
        public void onCardViewTap(View view, int position);
        }

    /**
     *
     */

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView, Resources resources) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return decodeSampledBitmapFromResource(context.getResources(), data, 200, 200);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                final BitmapWorkerTask bitmapWorkerTask =
                        getBitmapWorkerTask(imageView);
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
    /**

     */
    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             BitmapWorkerTask bitmapWorkerTask) {
            super(res, bitmap);
            bitmapWorkerTaskReference =
                    new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBitmapWorkerTask() {
            return bitmapWorkerTaskReference.get();
        }
    }


}


