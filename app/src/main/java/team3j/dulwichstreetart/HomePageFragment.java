package team3j.dulwichstreetart;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import com.facebook.model.GraphUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private TextView facebookCardText;
    private Button button;
    private LinearLayout linearLayout;
    private ViewFlipper viewFlipper;
    private Timer timer;
    private com.etsy.android.grid.util.DynamicHeightImageView aboutDulwich;

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
//        Toast.makeText(getActivity(), GalleryData.toVisit.size() +
//                        "this is my Toast message!!! =)",
//                Toast.LENGTH_LONG).show();
        //facebook setup
        //setup xml elements
        button = (Button) layout.findViewById(R.id.button_facebook);
        facebookCardText = (TextView) layout.findViewById(R.id.facebookCardText);
        facebookCardText.setText("Log in via\nFacebook");
        //facebook work
        isLoggedIn = false;

        checkIfActiveSession();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });
        //button.setText("Log In");


        //  ---------- KEYHASH GENERATOR -----------//
        /*
       try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "team3j.dulwichstreetart",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        */

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
            Session.openActiveSession(getActivity(), this, true, statusCallback);
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
        checkIfActiveSession();
    }

    //Display different things depending on if the user is logged in
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            //If logged in, show this
            Log.i("MainActivity", "Logged in...");
            //test.setText("");
            //retrieveInfo(session);
            isLoggedIn = true;
            Request.newMeRequest(session, new Request.GraphUserCallback() {
                // callback after Graph API response with user object
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    if (user != null) {
                        facebookCardText.setText(user.getFirstName() + "\nLog Out.");
                    }
                }
            }).executeAsync();

        } else if (state.isClosed()) {
            //If logged out, show this
            Log.i("MainActivity", "Logged out...");
            //test.setText("");
            facebookCardText.setText("Log In via\nFacebook");
            isLoggedIn = false;

        }
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
        name = (TextView) layout.findViewById(R.id.atsymbol);
        name.setText("    @DulwichGallery      14h");
        mapButton = (DynamicHeightImageView) layout.findViewById(R.id.map_image);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewPager.setCurrentItem(3, true);
                // onClickInsideFragment.onCardViewTa p();
            }
        });
        aboutDulwich = (DynamicHeightImageView) layout.findViewById(R.id.aboutDulwich);
        aboutDulwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
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

        file_maps.put("Agent Provocateur", R.drawable.lowresagentprovocateur);

        file_maps.put("Ben Wilson", R.drawable.judgementofparis);
        file_maps.put("Ben Wilson", R.drawable.lowrespharaohsring);
        file_maps.put("Ben Wilson", R.drawable.stcatherine);

        file_maps.put("Conor Harrington", R.drawable.lowresconorharrington);
        file_maps.put("David Shillinglaw", R.drawable.lowresdavidshillinglaw);
        file_maps.put("Faith47", R.drawable.lowreseuropaandthebull);

        file_maps.put("Pablo Delgado", R.drawable.lowrespablodelgadoone);

        file_maps.put("REKA", R.drawable.lowresreka);
        file_maps.put("Remi Rough & System", R.drawable.lowresremiroughandsystem);
        file_maps.put("ROA", R.drawable.lowresdoginlandscape);

        file_maps.put("RUN - St Rita", R.drawable.lowresrunstrita);
        file_maps.put("RUN - Triumph Of David - Triumph of David Lordship Lane", R.drawable.runtriumphofdavid);

        file_maps.put("STIK - Eliza and Mary Davidson", R.drawable.lowresstikelizaandmarydavidsontilly);
        file_maps.put("STIK - Three Boys", R.drawable.lowresstikthreeboys);

        file_maps.put("Walter Kershaw", R.drawable.lowreswalterlandscape);


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


    public void setupClickInsideFragment(OnClickInsideFragment onClickInsideFragment) {
        this.onClickInsideFragment = onClickInsideFragment;
    }

    public interface OnClickInsideFragment {
        public void onCardViewTap();
    }

    private void showMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About Dulwich Outdoor Gallery");
        builder.setMessage(R.string.art_outdoor_gallery_about);
        builder.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessagePictureGallery();
            }
        });
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setLayout(400, 400);
        dialog.show();
    }

    private void showMessagePictureGallery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About Dulwich Picture Gallery");
        builder.setMessage(R.string.art_picture_gallery_about);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage();
            }
        });

        AlertDialog box = builder.create();
        box.getWindow().setLayout(400, 400);
        box.show();
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
class OnSliderClickListener implements BaseSliderView.OnSliderClickListener {


    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {


    }
}

