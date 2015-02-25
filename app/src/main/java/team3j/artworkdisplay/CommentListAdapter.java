package team3j.artworkdisplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;

import team3j.dulwichstreetart.GalleryData;
import team3j.dulwichstreetart.R;

/**
 * Created by JGill on 03/02/15.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final int indexOfArtwork;
    private String commentAmount;
    private ArrayList<Comment> data;
    private Context context;
    private OnArtistItemTouchListener onArtistItemTouchListener;


    public CommentListAdapter(Context context,ArrayList<Comment> data,int position, String commentAmount){
        this.commentAmount = commentAmount;
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.indexOfArtwork=position;
        System.out.println(data);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType=0; // view type = 0 is a comment

        if(position==0){
            viewType=1;

        }

        return viewType;

    }

    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view= inflater.inflate(R.layout.comment_item,parent,false);
        MyViewHolder myViewHolder;

        if(viewType==1){
            view= inflater.inflate(R.layout.swipe_fragment_header,parent,false);


        }else{
            view= inflater.inflate(R.layout.comment_item,parent,false);


        }
        myViewHolder= new MyViewHolder(view,viewType);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.MyViewHolder holder, int position) {

        if(position==0) {


            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), GalleryData.GetArtWorkImageLocations()[indexOfArtwork]);
            BitmapDrawable res = new BitmapDrawable(context.getResources(), bitmap);


            //update header
            holder.dynamicHeightImageView.setImageDrawable(res);
            holder.commentTitle.setText(commentAmount);




        }else{

            Comment commentInfo = data.get(position-1);
            holder.posterName.setText(commentInfo.getPosterName());
            holder.message.setText(commentInfo.getMessage());
            holder.timestamp.setText(commentInfo.getTime());


        }

    }

    @Override
    public int getItemCount() {
        if(data.size()==0){
            return 1;
        }else{
            return data.size()+1;
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        // view holder for each grid  cell
        TextView posterName;
        TextView message;
        TextView timestamp;
        private TextView commentTitle;
        private DynamicHeightImageView dynamicHeightImageView;

        public MyViewHolder(View itemView,int viewType) {
            super(itemView);
            if(viewType==0) {
                posterName = (TextView) itemView.findViewById(R.id.name);
                message = (TextView) itemView.findViewById(R.id.comment);
                timestamp = (TextView) itemView.findViewById(R.id.time);

                posterName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //onArtistItemTouchListener.onItemClick(v, getPosition());
                    }
                });
            }
            else{
                // for the header view
                dynamicHeightImageView = (DynamicHeightImageView) itemView.findViewById(R.id.dynamic_imageview_artwork_display);
                commentTitle = (TextView) itemView.findViewById(R.id.commentAmount);


            }
        }


    }



    public interface OnArtistItemTouchListener{
        public void onItemClick(View view,int position);
    }





}

