package team3j.dulwichstreetart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;

/**
 * @author Team 3-J
 *
 * This is the Recycle View Adapter for the Artist List in the artist tab
 *
 */
public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Artist> data;
    private Context context;

    /**
     *
     * @param context
     * @param data
     */
    public ArtistListAdapter(Context context,ArrayList<Artist> data){
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ArtistListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view= inflater.inflate(R.layout.artist_list_item,parent,false);

        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ArtistListAdapter.MyViewHolder holder, int position) {
        //add data to for each layout of the list

        holder.title.setText(data.get(position).getName());
        holder.description.setText(data.get(position).getDescription());
        holder.website.setText(data.get(position).getWebsite());
        holder.artistPhoto.setImageResource(data.get(position).getArtistPhoto());
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position+100;
    }

    /**
     * This Class is the Viewholder for each item in the
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        // view holder for each grid  cell
        TextView title;
        TextView description;
        TextView website;
        ImageView artistPhoto;
        boolean expanded=false;
        CardView expandArea;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.artist_list_item_title);
            description = (TextView) itemView.findViewById(R.id.artist_list_item_desc);
            website = (TextView) itemView.findViewById(R.id.website);
            artistPhoto = (ImageView)itemView.findViewById(R.id.artistPhoto);
            expandArea= (CardView) itemView.findViewById(R.id.artist_list_item_card);


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




}

