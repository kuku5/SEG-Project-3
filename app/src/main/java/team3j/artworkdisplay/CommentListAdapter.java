package team3j.artworkdisplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;

import team3j.dulwichstreetart.R;

/**
 * Created by JGill on 03/02/15.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<String> data;
    private Context context;
    private OnArtistItemTouchListener onArtistItemTouchListener;
    public CommentListAdapter(Context context,ArrayList<String> data){
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
        System.out.println(data);
    }

    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view= inflater.inflate(R.layout.artist_list_item,parent,false);

        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.MyViewHolder holder, int position) {
        //Log.d("test123",data.get(position));
        //holder.title.setText(data.get(position));
        //holder.numberOfArt.setText("");
    }

    @Override
    public int getItemCount() {
        return 18;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        // view holder for each grid  cell
        TextView title;
        TextView numberOfArt;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_item_title);
            numberOfArt = (TextView) itemView.findViewById(R.id.list_item_number);


            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //onArtistItemTouchListener.onItemClick(v, getPosition());
                }
            });

        }


    }

    public interface OnArtistItemTouchListener{
        public void onItemClick(View view,int position);
    }





}

