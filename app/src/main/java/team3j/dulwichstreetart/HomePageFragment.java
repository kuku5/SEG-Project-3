package team3j.dulwichstreetart;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JGill on 25/01/15.
 */

//TODO incomplete homepage needs a clear idea of design

public class HomePageFragment extends Fragment {
    private TextView textView;

    public static HomePageFragment getInstance(int position) {
        HomePageFragment myFragmentTab = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home_page, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText("Home Page Selected at page  " + bundle.getInt("position"));
        }

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

        return layout;
    }
}