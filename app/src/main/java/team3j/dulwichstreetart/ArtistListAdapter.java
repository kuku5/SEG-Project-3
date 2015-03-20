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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
     * Constructs an ArtistListAdapter
     * @param context the activity
     * @param data the array of artists
     */
    public ArtistListAdapter(Context context,ArrayList<Artist> data){
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
    }

    /**
     *  View Holder for the display of the artist tab
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
     * Properties given to the holder when it starts
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ArtistListAdapter.MyViewHolder holder, int position) {
        //add data to for each layout of the list

        holder.title.setText(data.get(position).getName());
        holder.description.setText(data.get(position).getDescription());
        holder.website.setText(data.get(position).getWebsite());

        try {
            holder.artistPhoto.setImageDrawable(getAssetImage(context,data.get(position).getArtistPhoto()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("" + filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * Size of the view
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * The view type
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
        LinearLayout artist_list_item_relative;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.artist_title);
            description = (TextView) itemView.findViewById(R.id.artist_description);
            website = (TextView) itemView.findViewById(R.id.website);
            artistPhoto = (ImageView)itemView.findViewById(R.id.artistPhoto);
            expandArea= (CardView) itemView.findViewById(R.id.artist_list_card);
            artist_list_item_relative = (LinearLayout) itemView.findViewById(R.id.artist_list_item_relative);


            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(expanded){
                        expandArea.setVisibility(View.GONE);
                        artist_list_item_relative.setVisibility(View.GONE);
                        expanded=false;
                    }else{
                        expandArea.setVisibility(View.VISIBLE);
                        artist_list_item_relative.setVisibility(View.VISIBLE);

                        expanded=true;

                    }

                }
            });


        }


    }




}

