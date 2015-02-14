package team3j.dulwichstreetart;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JGill on 25/01/15.
 * This is the fragment of the Homepage to be displayed in the tab
 */

//TODO incomplete homepage needs a clear idea of design

public class HomePageFragment extends Fragment {
    private TextView textView;
    private CardView cardView;

    //return an instance of this Fragment with a bundle into the tab adapter
    public static HomePageFragment getInstance(int position) {
        HomePageFragment myFragmentTab = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }




    //this creates a view
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       //creates view that setups what is displayed
        View layout = inflater.inflate(R.layout.fragment_home_page, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        cardView = (CardView) layout.findViewById(R.id.card_view_1_welcome1);
        Bundle bundle = getArguments();

        //retrieves the bundle
        if (bundle != null) {

            textView.setText("Home Page Selected at page  " + bundle.getInt("position"));
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardView.setVisibility(View.GONE);

                }
            });
        }



        return layout;
    }





}




/*

/// LEAVE THIS MIGHT PUT BACK IN


//        String message=
//                "Here you can locate and navigate to your favourite street artist in Dulwich " +
//                        "and interact with other Street art Enthusiasts ";
//
//
//        new AlertDialog.Builder(getActivity())
//                .setTitle("Welcome to the Dulwich Outdoor Gallery")
//                .setMessage(message)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
//                .setIcon(R.drawable.ic_blob)
//                .show();
 */

