package team3j.dulwichstreetart;

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

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;

/**
 * Created by JGill on 03/02/15.
 */
public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<String> data;
    private Context context;
    public ArtistListAdapter(Context context,ArrayList<String> data){
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public ArtistListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view= inflater.inflate(R.layout.artist_list_item,parent,false);

        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ArtistListAdapter.MyViewHolder holder, int position) {
        Log.d("test123",data.get(position));
        holder.title.setText(data.get(position));
        holder.numberOfArt.setText(position+"");
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


        }
    }

}

