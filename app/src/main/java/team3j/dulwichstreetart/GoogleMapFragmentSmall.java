package team3j.dulwichstreetart;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * A fragment that launches other parts of the demo application.
 */


public class GoogleMapFragmentSmall extends Fragment {

    public static MapView mMapView;
    private GoogleMap googleMap;
    private LinearLayout linearLayout2;
    private CardView cardView;
    private ImageButton imageButton;
    private LatLng locStart;
    public static ArrayList<Art> visited = new ArrayList<Art>();
    public static ArrayList<Art> toVisit = new ArrayList<Art>();
    private Art[] arts;

    public static GoogleMapFragmentSmall getInstance(int position) {
        GoogleMapFragmentSmall myFragmentTab = new GoogleMapFragmentSmall();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_location_info, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        imageButton= (ImageButton) v.findViewById(R.id.fab_image_button);
        mMapView.onResume();// needed to get the map to display immediately


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


       if(isGoogleMapsInstalled()) {

            setUpMap();
            setUpVisitedListener();
       }

        // Perform any camera updates here
        return v;
    }

    private void setUpVisitedListener() {
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if(googleMap.getMyLocation()==null){

                }else {
                    for(int i=0; i<arts.length - 1; i++)
                    {

                        double tolerance=1000;
                        //checks all locations
                        LatLng artLoc = arts[i].getLoc();
                        if((googleMap.getMyLocation().getLatitude()<= artLoc.latitude + tolerance) && (googleMap.getMyLocation().getLatitude()>= artLoc.latitude - tolerance) )
                        {
                            if((googleMap.getMyLocation().getLongitude()<= artLoc.longitude + tolerance) &&(googleMap.getMyLocation().getLongitude()>= artLoc.longitude - tolerance) )
                            {
                                //if the user is at the street art
                                for(int j=0; j<toVisit.size(); j++)
                                {
                                    if(toVisit.get(j)== arts[i])
                                    {
                                        visited.add(arts[i]);
                                        toVisit.remove(j);
                                    }
                                }

                            }
                        }
                    }
                }


            }

        });


    }

    public boolean isGoogleMapsInstalled()
    {
        try
        {
            ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0 );
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
       mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void setUpMap(){
         arts = GalleryData.getMapArtwork(getActivity());

        googleMap = mMapView.getMap();
        zoom();
        googleMap.setMyLocationEnabled(true);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locStart, 13);
                googleMap.animateCamera(update);
            }
        });

        for (int i = 0; i < arts.length; i++) {
            int pos = i + 1;
            String title = pos + ". ";
            if (i == 14) {
                googleMap.addMarker(new MarkerOptions().position(arts[i].getLoc()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title(title));
            } else {
                googleMap.addMarker(new MarkerOptions().position(arts[i].getLoc()).title(title));
            }
        }


        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

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
                for (int i = 0; i < arts.length; i++) {
                    if (latLng.equals(arts[i].getLoc())) {

                        ImageView picView = (ImageView) v.findViewById(R.id.pic);
                        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), arts[i].getPic());
                        BitmapDrawable res = new BitmapDrawable(getActivity().getResources(), bitmap);
                        picView.setImageDrawable(res);
                        TextView txtView = (TextView) v.findViewById(R.id.name);
                        txtView.setText((i + 1) + ". " + arts[i].getName());

                    }
                }


                return v;

            }
        });
    }

    public void zoom(){

        locStart = new LatLng(51.454013, -0.080496);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locStart, 13);

        googleMap.animateCamera(update);


    }

}