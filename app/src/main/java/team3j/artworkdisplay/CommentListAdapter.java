package team3j.artworkdisplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import team3j.dulwichstreetart.R;

/**
 * Created by JGill on 03/02/15.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Comment> data;
    private Context context;
    private OnArtistItemTouchListener onArtistItemTouchListener;
    public CommentListAdapter(Context context,ArrayList<Comment> data){
        this.data=data;
        inflater=LayoutInflater.from(context);
        this.context=context;
        System.out.println(data);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType=0; // view type = 0 is a comment

//        switch(position){
//            case 0:viewType=1; break;//firstCard
//            case -1:viewType=1; break;//secondCard
//            case -2:viewType=1; break;
//
//        }

        return viewType;

    }

    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //add view to the grid cell for the first time
        //this stores the view in the cache meaning the images dont have to be reloaded over
        //and over mean its should be faster than a Listview/Gridview which does
        View view= inflater.inflate(R.layout.comment_item,parent,false);

        MyViewHolder myViewHolder= new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.MyViewHolder holder, int position) {
        //Log.d("test123",data.get(position));
        //holder.posterName.setText(data.get(position));
        //holder.message.setText("");

        Comment commentInfo = data.get(position);
        holder.posterName.setText(commentInfo.getPosterName());
        holder.message.setText(commentInfo.getMessage());
        holder.timestamp.setText(commentInfo.getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        // view holder for each grid  cell
        TextView posterName;
        TextView message;
        TextView timestamp;

        public MyViewHolder(View itemView) {
            super(itemView);
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


    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{
        // view holder for each grid  cell
        TextView posterName;
        TextView message;

        public MyViewHolder1(View itemView) {
            super(itemView);

            posterName = (TextView) itemView.findViewById(R.id.name);


        }


    }
    public interface OnArtistItemTouchListener{
        public void onItemClick(View view,int position);
    }





}

