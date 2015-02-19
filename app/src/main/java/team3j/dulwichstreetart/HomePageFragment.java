package team3j.dulwichstreetart;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;

import java.util.HashMap;
import java.util.Timer;


/**
 * Created by JGill on 25/01/15.
 * This is the fragment of the Homepage to be displayed in the tab
 */

//TODO incomplete homepage needs a clear idea of design

public class HomePageFragment extends Fragment {
    private TextView textView;
    private CardView cardView;
    private CardView cardView2;
    private Button button;
    private LinearLayout linearLayout;
    private ViewFlipper viewFlipper;
    private Timer timer;

    private TextView name;
    private SliderLayout mDemoSlider;

    Animation slide_in_left, slide_out_right;
    private boolean isLoggedIn;
    private DynamicHeightImageView mapButton;
    private OnClickInsideFragment onClickInsideFragment;

    //return an instance of this Fragment with a bundle into the tab adapter
    public static HomePageFragment getInstance(int position) {
        HomePageFragment myFragmentTab = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //non facebook setup
        View layout = inflater.inflate(R.layout.fragment_home_page, container, false);
        setupOnScreenElements(layout);
        setupAnimations(layout);
        setupGoogleMapsCard(layout);
        setupLibraryAnimations(layout);

        //facebook setup
        //setup xml elements
        button = (Button) layout.findViewById(R.id.button_facebook);

        //facebook work
        isLoggedIn = false;

        checkIfActiveSession();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });
        button.setText("Log In");


        // ---------- KEYHASH GENERATOR -----------//
//
//       try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
//                    "team3j.dulwichstreetart",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

      //slideDown();
        return layout;
    }


    //Acts like the an Observer who looks for Session changes and invokes onSessionStateChanged
    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    //handler for the log in button
    public void onClickLogin() {
        Session session = Session.getActiveSession();

        if (!isLoggedIn) {
            System.out.println("SKEENNNNNNN" + Session.openActiveSession(getActivity(), this, true, statusCallback));
            checkIfActiveSession();
        } else if (isLoggedIn) {
            session.close();
        }
    }

    //checks to see if there is already a session open.
    public void checkIfActiveSession() {
        Session session = Session.getActiveSession();
        if (session != null && (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
            System.out.println("There is already a open session");
        }
    }

    public void onResume() {
        super.onResume();
        //checkIfActiveSession();
    }

    //Display different things depending on if the user is logged in
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            //If logged in, show this
            Log.i("MainActivity", "Logged in...");
            //test.setText("");
            retrieveInfo(session);
            button.setText("Log Out");
            isLoggedIn = true;


        } else if (state.isClosed()) {
            //If logged out, show this
            Log.i("MainActivity", "Logged out...");
            //test.setText("");
            button.setText("Log In");
            isLoggedIn = false;

        }
    }

    //Method used to retrieve fb data
    public void retrieveInfo(Session session) {
        //test.setText("Logged in as ");
        //Get the profile of the person logged in
        Bundle b1 = new Bundle();
        b1.putBoolean("summary", true);     //includes a summary in the request
        b1.putString("filter", "stream");   //gets the chronological order of comments
        b1.putString("limit", "100");        //gets max of 100
        new Request(session, "726958990741991/comments", b1, HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        if (response != null) {
                            try {
                                System.out.println(response.getGraphObject().toString());
                                System.out.println(response.getGraphObject().getInnerJSONObject().getJSONObject("summary").toString());
                                int x = response.getGraphObject().getInnerJSONObject().getJSONArray("data").length();
                                System.out.println(x);
                                for (int i = 0; i < 61; i++) {
                                    System.out.println(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message"));
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                }).executeAsync();

        // Get total number of likes on a post
        Bundle b = new Bundle();
        b.putBoolean("summary", true);
        new Request(session, "798332966914164/likes", b, HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        try {
                            //test.append(" \n Total like count on 798332966914164 is "+response.getGraphObject().getInnerJSONObject().getJSONObject("summary").get("total_count").toString());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }).executeAsync();
    }

    //Handles the web log in
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);

    }

    private void setupOnScreenElements(View layout) {
        cardView = (CardView) layout.findViewById(R.id.card_view_1_welcome1);
        cardView2 = (CardView) layout.findViewById(R.id.car_view_22);
        linearLayout = (LinearLayout) layout.findViewById(R.id.welcomeView);
        name=(TextView) layout.findViewById(R.id.atsymbol);
        name.setText("    @DulwichGallery      14h");
        mapButton=(DynamicHeightImageView) layout.findViewById(R.id.map_image);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onClickInsideFragment.onCardViewTap();
            }
        });
    }

    public void setupAnimations(View layout) {



        Bundle bundle = getArguments();

        if (bundle != null) {

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setVisibility(View.GONE);

                }
            });
        }


        viewFlipper = (ViewFlipper) layout.findViewById(R.id.view_animator);
        viewFlipper.setFlipInterval(3500);

        slide_in_left = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewFlipper.showNext();


            }
        });

    }


    private void setupGoogleMapsCard(View layout) {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        GoogleMapFragmentSmall fragmentSmall = GoogleMapFragmentSmall.getInstance(0);
//        ft.replace(R.id.small_map, fragmentSmall);
//        ft.commit();

    }

    private void setupLibraryAnimations(View layout) {

        mDemoSlider = (SliderLayout) layout.findViewById(R.id.slider);

        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

        file_maps.put("Conor Harrington", R.drawable.lowresconorharrington);
        file_maps.put("Walter Landscape", R.drawable.lowreswalterlandscape);
        file_maps.put("Conor Harrington", R.drawable.lowresconorharrington);
        file_maps.put("Walter Landscape", R.drawable.lowreswalterlandscape);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout

            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new OnSliderClickListener());

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        //mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
        //mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);

        // mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());


    }

    public void slideDown() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // your code here
                linearLayout.setVisibility(View.VISIBLE);

            }
        }, 1500/* 1sec delay */);

    }

    public void setupClickInsideFragment(OnClickInsideFragment onClickInsideFragment){
        this.onClickInsideFragment=onClickInsideFragment;
    }
    public interface OnClickInsideFragment {
        public void onCardViewTap();
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
class OnSliderClickListener implements BaseSliderView.OnSliderClickListener{


    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {


    }
}

