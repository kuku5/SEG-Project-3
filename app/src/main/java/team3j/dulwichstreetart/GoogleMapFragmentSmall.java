package team3j.dulwichstreetart;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.melnykov.fab.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import team3j.artworkdisplay.GallerySwipeSingleFragment;

/**
 * GoogleMap Fragment for to be displayed in the tab viewer
 */


public class GoogleMapFragmentSmall extends Fragment {


    public MapView mMapView;
    private GoogleMap googleMap;

    private FloatingActionButton imageButton;
    private LatLng locStart;
    private ArrayList<Art> artArrayList;
    public static boolean filter;
    public static String name;
    private Marker filterMarker;
    private View v;
    private Calendar currentDate = new GregorianCalendar();


    /**
     * returns an instance of the fragment
     *
     * @param position position of tab
     * @return the instance of the fragment
     */
    public static GoogleMapFragmentSmall getInstance(int position) {

        GoogleMapFragmentSmall myFragmentTab = new GoogleMapFragmentSmall();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         mMapView.onSaveInstanceState(outState);
    }

    /**
     * Creates the google maps fragment with the correct layout
     *
     * @param inflater inflates layout
     * @param container viewgroup container
     * @param savedInstanceState bundle saved instance
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate and return the layout
        View v = inflater.inflate(R.layout.fragment_location_info, container,
                false);
        setRetainInstance(true);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        imageButton = (FloatingActionButton) v.findViewById(R.id.fab);

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

    /**
     * sets up the listener for visted tab
     */
    private void setUpVisitedListener() {

        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (googleMap.getMyLocation() != null) {
                    for (int i = 0; i < artArrayList.size(); i++) {

                        double tolerance=0.000250;
                        //double tolerance = 1000; Testing purposes only

                        //checks all locations
                        LatLng artLoc = artArrayList.get(i).getLoc();
                        if ((googleMap.getMyLocation().getLatitude() <= artLoc.latitude + tolerance) && (googleMap.getMyLocation().getLatitude() >= artLoc.latitude - tolerance)) {
                            if ((googleMap.getMyLocation().getLongitude() <= artLoc.longitude + tolerance) && (googleMap.getMyLocation().getLongitude() >= artLoc.longitude - tolerance)) {
                                //if the user is at the street art
                                int date = currentDate.get(Calendar.DATE);
                                int month = currentDate.get(Calendar.MONTH) + 1;
                                if (month == 13) {
                                    month = 1;
                                }
                                int year = currentDate.get(Calendar.YEAR);
                                String fullDate = date + "/" + month + "/" + year;
                                //Haven't visited OR Have visited but the date is different
                                if ((!SplashActivity.artArrayList.get(i).getVisited()) || (SplashActivity.artArrayList.get(i).getVisited() && !fullDate.equals(SplashActivity.artArrayList.get(i).getDateVisited()))) {
                                    SplashActivity.artArrayList.get(i).setDateVisited(fullDate);
                                    SplashActivity.artArrayList.get(i).setVisited(true);
                                    updateVisited(i, fullDate);
                                }

                            }
                        }
                    }
                }


            }

        });


    }

    /**
     * Checks if google maps is installed on the phone
     * @return true if it is installed false if not
     */
    public boolean isGoogleMapsInstalled() {
        try {
            ApplicationInfo info = getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);


            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Handles onResume fragment
     */
    @Override
    public void onResume() {
        super.onResume();
            filter = GallerySwipeSingleFragment.filt;
            name = GallerySwipeSingleFragment.ind;
          //  System.out.println("on resume filter: " + GoogleMapFragmentSmall.filter + " index: " + name);

            if (isGoogleMapsInstalled()) {

                setUpMap();

            }
            mMapView.onResume();

            GoogleMapFragmentSmall.filter = false;

    }

    /**
     * Handles onPause fragment
     */
    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    /**
     * Handles onDestroy fragment
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        mMapView.onDestroy();
    }

    /**
     * Handles onLowMemory fragment
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    /**
     * Sets up the map and markers
     */
    public void setUpMap() {


        artArrayList = GalleryData.get().getArtworkList();
        googleMap = mMapView.getMap();
        zoom();
        googleMap.setMyLocationEnabled(true);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom();
            }
        });
        googleMap.clear();

        for (int i = 0; i < artArrayList.size(); i++) {
            int pos = i + 1;
            String title = pos + ". ";
            System.out.println("filter is " + GoogleMapFragmentSmall.filter);
            // System.out.println(GoogleMapFragmentSmall.index);

            if (filter == true && name == artArrayList.get(i).getName()) {
                filterMarker = googleMap.addMarker(new MarkerOptions().position(artArrayList.get(i).getLoc()).title(title));
            } else {
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


                if (latLng.equals(new LatLng(51.445988, -0.0863601))) {

                    ImageView picView = (ImageView) v.findViewById(R.id.pic);

                    try {
                        picView.setImageDrawable(getAssetImage(getActivity(), "dulwichpicturegallery"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    TextView txtView = (TextView) v.findViewById(R.id.markerName);
                    txtView.setText("The Inspiration Dulwich Picture Gallery 1811");

                } else {

                    for (int i = 0; i < artArrayList.size(); i++) {
                        if (latLng.equals(artArrayList.get(i).getLoc())) {

                            ImageView picView = (ImageView) v.findViewById(R.id.pic);

                            picView.setImageDrawable(artArrayList.get(i).getDrawableStreet());


                            TextView txtView = (TextView) v.findViewById(R.id.markerName);
                            txtView.setText(artArrayList.get(i).getName());


                        }


                    }

                }
                return v;


            }

        });


        if (filter) {

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(filterMarker.getPosition(), 13);
            googleMap.animateCamera(update);
            filterMarker.showInfoWindow();
            filter = false;
            GallerySwipeSingleFragment.filt = false;
        }


    }

    /**
     * Zooms into the map
     */
    public void zoom() {

        int screenOrientation = this.getResources().getConfiguration().orientation;

        if (screenOrientation == Surface.ROTATION_0 + 1) {
            //For portrait mode
            locStart = new LatLng(51.454013, -0.080496);

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locStart, 13);

            googleMap.animateCamera(update);

        } else if (screenOrientation == Surface.ROTATION_90 + 1) {
            //For landscape mode
            locStart = new LatLng(51.454013, -0.080496);

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locStart, 12);

            googleMap.animateCamera(update);

        }



    }


    /**
     * @param context Context of Activity
     * @param filename filename of image
     * @return bitmap drawable
     * @throws IOException if filenames not found
     */
    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("" + filename + ".jpg")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * Updates the visited list
     * @param index index of artwork
     * @param fullDate date visited
     */
    public void updateVisited(int index, String fullDate) {
        artArrayList.get(index).setVisited(true);
        SharedPreferences visitedPref = getActivity().getSharedPreferences("VisitedList", Context.MODE_PRIVATE);
        SharedPreferences datePref = getActivity().getSharedPreferences("VisitedDate", Context.MODE_PRIVATE);
        visitedPref.edit().putBoolean(SplashActivity.artArrayList.get(index).getName(), true).apply();
        datePref.edit().putString(SplashActivity.artArrayList.get(index).getName(), fullDate).apply();
        Toast.makeText(this.getActivity(), "Updated", Toast.LENGTH_SHORT).show();
        VisitedTabFragment.getInstance(4).updateList(); //Update the screen
    }

    @Override
    public void onDestroyView() {
        System.gc();
        super.onDestroyView();
    }

}