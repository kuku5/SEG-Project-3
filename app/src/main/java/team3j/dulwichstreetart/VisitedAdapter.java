package team3j.dulwichstreetart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Team 3-J
 *This is the adapter that puts elements into the Gallery
 *
 */
public class VisitedAdapter extends RecyclerView.Adapter<VisitedAdapter.MyViewHolderVisited> {

    /*TODO potentially add a pre lollipop curve to cards
        or just set up to put a separate grid view for the
        for lollipop and pre lollipop
     */
    private OnItemTouchListener onItemTouchListener;

    private final LayoutInflater inflater;
    private Context context;
    private Bitmap bitmap;
    private ArrayList<Art> galleryData;


    /**
     *
     * @param context
     * @param itemTouchListener
     * @param galleryData
     */
    public VisitedAdapter(Context context, OnItemTouchListener itemTouchListener, ArrayList<Art> galleryData){
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.onItemTouchListener = itemTouchListener;
        this.galleryData = galleryData;
    }



    @Override
    public VisitedAdapter.MyViewHolderVisited onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflates the correct view
        View view = inflater.inflate(R.layout.visited_list_item, parent, false);

        MyViewHolderVisited myViewHolder = new MyViewHolderVisited(view, viewType);

        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }

    @Override
    public void onBindViewHolder(VisitedAdapter.MyViewHolderVisited holder, int position) {



            //add image and description to the view for each gallery item
            holder.txtLineOne.setText("" + galleryData.get(position).getName());

            Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), GalleryData.toVisit.get(position).getPic());
            BitmapDrawable res1 = new BitmapDrawable(context.getResources(), bitmap1);
            holder.image.setImageDrawable(res1);

            //holder.art_info_area.setText("" + GalleryData.toVisit.get(position-1).getDesc());
           // holder.art_info_area.setText("" + galleryData.get(position).getDesc());





    }

    @Override
    public int getItemCount() {
        return GalleryData.toVisit.size()+GalleryData.visited.size();
    }

    //custom viewHolder for each item in recycle view

    class MyViewHolderVisited extends RecyclerView.ViewHolder  {
        // view holder for each grid  cell
        TextView txtLineOne;
        TextView art_info_area;
        RelativeLayout expandArea;
        ImageView image;

        boolean expanded=false;
        public MyViewHolderVisited(View itemView, int viewType) {
            super(itemView);


                    txtLineOne = (TextView) itemView.findViewById(R.id.textview_visited_item);
                    expandArea = (RelativeLayout) itemView.findViewById(R.id.expand_area);
                    image = (ImageView) itemView.findViewById(R.id.image_area);
                    art_info_area = (TextView) itemView.findViewById(R.id.visited_description);


                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(expanded){
                                expandArea.setVisibility(View.GONE);

                                expanded=false;
                            }else{
                                expandArea.setVisibility(View.VISIBLE);
                                expanded=true;

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

}

