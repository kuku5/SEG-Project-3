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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import team3j.artworkdisplay.GallerySwipeSingleFragment;

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
    private Art[] arts;
    private ArrayList<Art> artArrayList;
    public static boolean filter;
    public static String name;
    private Marker filterMarker;
    private View v;





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
        setRetainInstance(true);

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
                    for(int i=0; i<artArrayList.size(); i++)
                    {

                        double tolerance=1000;
                        //checks all locations
                        LatLng artLoc = artArrayList.get(i).getLoc();
                        if((googleMap.getMyLocation().getLatitude()<= artLoc.latitude + tolerance) && (googleMap.getMyLocation().getLatitude()>= artLoc.latitude - tolerance) )
                        {
                            if((googleMap.getMyLocation().getLongitude()<= artLoc.longitude + tolerance) &&(googleMap.getMyLocation().getLongitude()>= artLoc.longitude - tolerance) )
                            {
                                //if the user is at the street art

//                                for(int j=0; j<GalleryData.toVisit.size(); j++)
//                                {
//                                    if(GalleryData.toVisit.get(j)== artArrayList.get(i))
//                                    {
//                                        GalleryData.visited.add(artArrayList.get(i));
//                                        GalleryData.toVisit.remove(j);
//                                    }
//                                }


                                artArrayList.get(i).setVisited();

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
        filter = GallerySwipeSingleFragment.filt;
        name = GallerySwipeSingleFragment.ind;
        System.out.println("on resume filter: "+GoogleMapFragmentSmall.filter+" index: "+name);

        if(isGoogleMapsInstalled()) {

            setUpMap();

        }
        mMapView.onResume();

        GoogleMapFragmentSmall.filter = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mMapView!=null) {
            mMapView.onPause();
        }
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


    public void setUpNoFilterMap()
    {
        GallerySwipeSingleFragment.filt = false;
        setUpMap();
    }

    public void setUpMap(){


        arts = GalleryData.getMapArtwork(getActivity());
        artArrayList = GalleryData.create().GetGalleryData();
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

        /*SharedPreferences preference = getActivity().getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        filter = preference.getBoolean("filter",true);
        index = preference.getInt("index", 0);
        */





        googleMap.clear();


       /* for (int i = 0; i < artArrayList.size(); i++) {
            int pos = i + 1;
            String title = pos + ". ";
            System.out.println("filter is "+ GoogleMapFragmentSmall.filter);
           // System.out.println(GoogleMapFragmentSmall.index);



            if(filter==false) {
                googleMap.addMarker(new MarkerOptions().position(new LatLng(51.445988, -0.0863601)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("The Inspiration Dulwich Picture Gallery 1811"));
            }
            if(artArrayList.get(i).getName()==name && GoogleMapFragmentSmall.filter == true)
            {
                System.out.println("when filter works: filter is "+ GoogleMapFragmentSmall.filter +" name is: "+name);
                googleMap.addMarker(new MarkerOptions().position(artArrayList.get(i).getLoc()).title(title));




                break;
            }else if(GoogleMapFragmentSmall.filter == false)
            {

                    googleMap.addMarker(new MarkerOptions().position(artArrayList.get(i).getLoc()).title(title));

            }


        }
        */


        for (int i = 0; i < artArrayList.size(); i++) {
            int pos = i + 1;
            String title = pos + ". ";
            System.out.println("filter is " + GoogleMapFragmentSmall.filter);
            // System.out.println(GoogleMapFragmentSmall.index);

            if (filter == true && name == artArrayList.get(i).getName()) {
                filterMarker = googleMap.addMarker(new MarkerOptions().position(artArrayList.get(i).getLoc()).title(title));
            } else
            {
                googleMap.addMarker(new MarkerOptions().position(artArrayList.get(i).getLoc()).title(title));
            }

        }
        googleMap.addMarker(new MarkerOptions().position(new LatLng(51.445988, -0.0863601)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("The Inspiration Dulwich Picture Gallery 1811"));



        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {


                v = getActivity().getLayoutInflater().inflate(R.layout.infowindow, null);




                LatLng latLng = arg0.getPosition();


                if (latLng.equals(new LatLng(51.452656, -0.102931))) {

                    ImageView picView = (ImageView) v.findViewById(R.id.pic);
                    Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.dulwichpicturegallery);
                    BitmapDrawable res = new BitmapDrawable(getActivity().getResources(), bitmap);
                    picView.setImageDrawable(res);
                    TextView txtView = (TextView) v.findViewById(R.id.markerName);
                    txtView.setText("The Inspiration Dulwich Picture Gallery 1811");

                }

                for (int i = 0; i < artArrayList.size(); i++) {
                    if (latLng.equals(artArrayList.get(i).getLoc())) {

                        ImageView picView = (ImageView) v.findViewById(R.id.pic);
                        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), artArrayList.get(i).getPic());
                        BitmapDrawable res = new BitmapDrawable(getActivity().getResources(), bitmap);
                        picView.setImageDrawable(res);
                        TextView txtView = (TextView) v.findViewById(R.id.markerName);
                        txtView.setText(artArrayList.get(i).getName());



                    }


                }



                return v;




            }

        });



        if(filter == true)
        {

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(filterMarker.getPosition(), 13);
            googleMap.animateCamera(update);
            filterMarker.showInfoWindow();
            filter = false;
            GallerySwipeSingleFragment.filt = false;
        }


    }

    public void zoom(){

        locStart = new LatLng(51.454013, -0.080496);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locStart, 13);

        googleMap.animateCamera(update);



    }





}