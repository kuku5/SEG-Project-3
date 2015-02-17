package team3j.dulwichstreetart;


import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by JGill on 25/01/15.
 * TODO add the map api decide what is actually going to be displayed and what they link to when buttons pressed
 *
 *
 */


public class MapFragmentTab extends Fragment {
    private TextView textView;
    private  LatLng locStart ;

    final Art arts[] = new Art[8];

    private SupportMapFragment fragment;
    private GoogleMap map;

    public static MapFragmentTab getInstance(int position) {
        MapFragmentTab myFragmentTab = new MapFragmentTab();
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
        locStart = new LatLng(51.454013, -0.080496);
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.art0);
        BitmapDrawable res = new BitmapDrawable(getActivity().getResources(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.art0));
//        arts[0] = new Art("Roa 2013",(new LatLng(51.467224, -0.072160)), new BitmapDrawable(getActivity().getResources(), BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.art0)));
//        arts[1] = new Art("Remi Rough & System 2013",(new LatLng(51.461675, -0.079872)),res) ;
//        arts[2] = new Art("Conor Harrington 2013",(new LatLng(51.460628, -0.075084)),res) ;
//        arts[3] = new Art("Stik 2012",(new LatLng(51.456109, -0.076058)),res) ;
//        arts[4] = new Art("Stik 2012",(new LatLng(51.456219, -0.076794)),res) ;
//        arts[5] = new Art("Beerens, Christiaan Nagel",(new LatLng(51.455990, -0.076442)),res) ;
//        arts[6] = new Art("Mear One",(new LatLng(51.454552, -0.077042)),res) ;
//        arts[7] = new Art("Stik 2012",(new LatLng(51.453060, -0.078765)),res) ;

//        arts[8] = new Art("Phlegm 2013",(new LatLng(51.451632, -0.071597)),getActivity().getDrawable(R.drawable.art8)) ;
//        arts[9] = new Art("Nunca 2013",(new LatLng(51.449235, -0.074160)),getActivity().getDrawable(R.drawable.art0)) ;
//        arts[10] = new Art("Stik 2012",(new LatLng(51.446414, -0.073434)),getActivity().getDrawable(R.drawable.art10)) ;
//        arts[11] = new Art("Stik 2012",(new LatLng(51.447416, -0.076093)),getActivity().getDrawable(R.drawable.art11)) ;
//        arts[12] = new Art("Stik 2012",(new LatLng(51.445317, -0.079216)),getActivity().getDrawable(R.drawable.art12)) ;
//        arts[13] = new Art("Thierry Noir 2013",(new LatLng(51.445228, -0.079050)),getActivity().getDrawable(R.drawable.art13)) ;
//        arts[14] = new Art("The Inspiration Dulwich Picture Gallery 1811",(new LatLng(51.445988, -0.086360)),getActivity().getDrawable(R.drawable.art0)) ;
//        arts[15] = new Art("David Shillinglaw 2013",(new LatLng(51.452656, -0.102931)),getActivity().getDrawable(R.drawable.art15)) ;
//        arts[16] = new Art("MadC 2013",(new LatLng(51.441452, -0.091381)),getActivity().getDrawable(R.drawable.art16)) ;
//        arts[17] = new Art("Reka 2013",(new LatLng(51.427863, -0.086302)),getActivity().getDrawable(R.drawable.art17)) ;
//        arts[18] = new Art("RUN",(new LatLng(51.437918, -0.054667)),getActivity().getDrawable(R.drawable.art18)) ;
//
//
//



//
//         fragment = ((SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map));
//        map = fragment.getMap();
//
//
////        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locStart, 13);
////       map.animateCamera(update);
//       map.setMyLocationEnabled(true);
//

        for(int i=0; i< arts.length; i++)
        {
            int pos=i+1;
            String title = pos + ". " + arts[i].getName();
            if(i==14)
            {
                map.addMarker(new MarkerOptions().position(arts[i].getLoc()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title(title));
            }else {

                map.addMarker(new MarkerOptions().position(arts[i].getLoc()).title(title));

            }
        }


        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {


                View v = getActivity().getLayoutInflater().inflate(R.layout.infowindow, null);


                LatLng latLng = arg0.getPosition();
                for(int i=0; i<arts.length; i++)
                {
                    if(latLng.equals(arts[i].getLoc()))
                    {

                        ImageView picView = (ImageView) v.findViewById(R.id.pic);
                        //picView.setImageDrawable(arts[i].getPic());
                        TextView txtView = (TextView) v.findViewById(R.id.name);
                        txtView.setText((i+1) + ". "+arts[i].getName());

                    }
                }


                return v;

            }
        });



        //kevin, darry put your code here and layout code in fragment_map_page.xml





        return layout;
    }

    public MapFragment mapFragment() {
        MapFragment mapFragment = new MapFragment();
        return mapFragment;
    }


//    private GoogleMap getGoogleMap() {
//        if (map == null && getActivity() != null && getActivity().getSupportFragmentManager()!= null) {
//            SupportMapFragment smf = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
//            if (smf != null) {
//                map = smf.getMap();
//            }
//        }
//        return map;
//    }
}