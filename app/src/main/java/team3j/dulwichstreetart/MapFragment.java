package team3j.dulwichstreetart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JGill on 25/01/15.
 * TODO add the map api decide what is actually going to be displayed and what they link to when buttons pressed
 *
 *
 */


public class MapFragment extends Fragment {
    private TextView textView;

    public static MapFragment getInstance(int position) {
        MapFragment myFragmentTab = new MapFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_map_page, container, false);
        textView = (TextView) layout.findViewById(R.id.position);
        Bundle bundle = getArguments();
        if (bundle != null) {
           if(bundle.getInt("position")==4){
               textView.setText("possible map of previously visited or" +
                       "list of Visited");


           }else{

               textView.setText("Map of Street Art  ");

           }

        }

        //kevin, darry put your code here and layout code in fragment_map_page.xml



        return layout;
    }
}