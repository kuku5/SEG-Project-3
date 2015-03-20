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
     *  {@inheritDoc}
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
     * @param holder viewholder
     * @param position current position
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

    /**
     * Reads the image from Assets and returns a bitmap drawable
     * @param context Context of Activity
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
     * this method returns the number of items in the Artist list
     * @return data size
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * The view type
     * @param position current position
     * @return current position
     */
    @Override
    public int getItemViewType(int position) {
        return position+100;
    }

    /**
     * This Class is the Viewholder for each item in the list
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        // view holder for each grid  cell
        private TextView title;
        private TextView description;
        private TextView website;
        private ImageView artistPhoto;
        private boolean expanded=false;
        private CardView expandArea;
        private LinearLayout artist_list_item_relative;

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

