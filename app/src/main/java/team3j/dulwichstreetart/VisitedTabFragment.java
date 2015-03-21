package team3j.dulwichstreetart;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * @author Team 3-J
 * Gallery Fragment for to be displayed in the tab Fragment
 */

//TODO maybe add different button to cardviews, also add final images

public class VisitedTabFragment extends Fragment implements MaterialTabListener {

    private RecyclerView recyclerView;
    private VisitedAdapter visitedAdapter;
    private Button infoButton;
    private Button resetButton;
    final Context context = this.context;

    public static VisitedTabFragment getInstance(int position) {
        VisitedTabFragment visitedTabFragment = new VisitedTabFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        visitedTabFragment.setArguments(args);
        return visitedTabFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get layout and elements
        View layout = inflater.inflate(R.layout.fragment_visited_tab, container, false);
        setRetainInstance(true);

        // add data




            recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_visited);

            //create recycle view Adapter

            //set adapter
            visitedAdapter = new VisitedAdapter(getActivity(), getVisitedClickListener(), GalleryData.get().getArtworkList());
            recyclerView.setAdapter(visitedAdapter);

            infoButton = (Button) layout.findViewById(R.id.info_button_visited);
            resetButton = (Button) layout.findViewById(R.id.reset_button_visited);

            //Set Layout Animation
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
                @Override
                protected int getExtraLayoutSpace(RecyclerView.State state) {
                    return 200;
                }
            };


            recyclerView.setLayoutManager(linearLayoutManager);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder infoDialogBuilder = new AlertDialog.Builder( v.getContext());
                infoDialogBuilder.setTitle("Information")

                        .setMessage("The Visited tab displays and keeps track of the " +
                                "arts that have been visited" +
                                " and the ones that are " +
                                "yet to be visited.")
                        .setPositiveButton("Ok", null);
                AlertDialog infoAlert = infoDialogBuilder.create();
                infoAlert.setCanceledOnTouchOutside(true);
                infoAlert.show();
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder resetDialogBuilder = new AlertDialog.Builder( v.getContext());
                resetDialogBuilder.setTitle("Caution")

                        .setMessage("All the arts will be set as \"Not Visited\" and cannot be restored. Do you wish to continue?");
                resetDialogBuilder.setNeutralButton("Cancel", null);
                        resetDialogBuilder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resetVisitedList();
                            }
                        });



                AlertDialog resetAlert = resetDialogBuilder.create();
                resetAlert.setCanceledOnTouchOutside(true);
                resetAlert.show();
            }
        });
        return layout;
    }


    //return a Click Listener for the Recycle View
    public VisitedAdapter.OnItemTouchListener getVisitedClickListener(){
        VisitedAdapter.OnItemTouchListener itemTouchListener = new VisitedAdapter.OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                //tap the entire view
                Toast.makeText(getActivity(), "Tapped " + position, Toast.LENGTH_SHORT).show();
                //open Activity to display for Artwork Display
            }
        };

        return itemTouchListener;

    }


    public void resetVisitedList()
    {
        SharedPreferences visitedPref = getActivity().getSharedPreferences("VisitedList", Context.MODE_PRIVATE);
        SharedPreferences datePref = getActivity().getSharedPreferences("VisitedDate", Context.MODE_PRIVATE);
        for (int i = 0; i < SplashActivity.artArrayList.size(); i++)
        {
            VisitedAdapter.galleryData.get(i).setVisited(false);
            SplashActivity.artArrayList.get(i).setVisited(false);
            SplashActivity.artArrayList.get(i).setDateVisited("--/--/----");

            visitedPref.edit().putBoolean(SplashActivity.artArrayList.get(i).getName(),false).apply();
            datePref.edit().putString(SplashActivity.artArrayList.get(i).getName(), "--/--/----").apply();


        }


    }


    @Override
    public void onTabSelected(MaterialTab materialTab) {

    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }




}