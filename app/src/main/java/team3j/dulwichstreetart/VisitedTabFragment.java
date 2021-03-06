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

/**
 * @author Team 3-J
 * Visited Tab Fragment for to be displayed in the tab Fragment
 */


public class VisitedTabFragment extends Fragment {
    private static VisitedTabFragment visitedTabFragment;
    private RecyclerView recyclerView;
    private VisitedAdapter visitedAdapter;
    private Button infoButton;
    private Button resetButton;

    /**
     * returns an instance of the fragment
     *
     * @param position position of tab
     * @return the instance of the fragment
     */
    public static VisitedTabFragment getInstance(int position) {
        if (visitedTabFragment == null) {
            visitedTabFragment = new VisitedTabFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            visitedTabFragment.setArguments(args);
            return visitedTabFragment;
        }
        return visitedTabFragment;
    }

    /**
     * Creates the visited tab fragment with the correct layout.
     * Sets onClickListener for both buttons - Information and Reset button.
     * Dialog box created for each button press.
     *
     * @param inflater LayoutInflator
     * @param container Viewgroup
     * @param savedInstanceState bundle of instance
     * @return layout view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get layout and elements
        View layout = inflater.inflate(R.layout.fragment_visited_tab, container, false);
        setRetainInstance(true);
        // add data

        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_visited);

        //create recycle view Adapter

        //set adapter
        visitedAdapter = new VisitedAdapter(getActivity(), GalleryData.get().getArtworkList());
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
                AlertDialog.Builder infoDialogBuilder = new AlertDialog.Builder(v.getContext());
                infoDialogBuilder.setTitle("How To Use")

                        .setMessage(getString(R.string.howToUseVisited))
                        .setPositiveButton("OK", null);
                AlertDialog infoAlert = infoDialogBuilder.create();
                infoAlert.setCanceledOnTouchOutside(true);
                infoAlert.show();
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder resetDialogBuilder = new AlertDialog.Builder(v.getContext());
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


    /**
     * Resets the data saved back to default value.
     * Updates both SharedPreferences - visitedPref and datePref to save the default value and remove the user data.
     * Updates both instances of the arrayList in GalleryData and SplashActivity.
     */
    public void resetVisitedList() {
        SharedPreferences visitedPref = getActivity().getSharedPreferences("VisitedList", Context.MODE_PRIVATE);
        SharedPreferences datePref = getActivity().getSharedPreferences("VisitedDate", Context.MODE_PRIVATE);
        for (int i = 0; i < SplashActivity.artArrayList.size(); i++) {
            VisitedAdapter.galleryData.get(i).setVisited(false);
            SplashActivity.artArrayList.get(i).setVisited(false);
            SplashActivity.artArrayList.get(i).setDateVisited("--/--/----");

            visitedPref.edit().putBoolean(SplashActivity.artArrayList.get(i).getName(), false).apply();
            datePref.edit().putString(SplashActivity.artArrayList.get(i).getName(), "--/--/----").apply();


        }
        updateList();

    }

    /**
     * Update the visited list onscreen
     */
    public void updateList() {
        if (visitedAdapter != null) {
            visitedAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {

        System.gc();
        super.onDestroyView();

    }

}