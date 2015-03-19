package team3j.dulwichstreetart;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.facebook.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * @author Team 3-J
 *         This is the fragment of the Homepage to be displayed in the tab
 */


public class HomePageFragment extends Fragment {

    Animation slide_in_left, slide_out_right;
    private CardView cardView;
    private CardView cardView2;
    private LinearLayout linearLayout;
    private ViewFlipper viewFlipper;
    private com.etsy.android.grid.util.DynamicHeightImageView aboutDulwich;
    private SliderLayout mDemoSlider;
    private DynamicHeightImageView mapButton;

    private ArrayList<Status> todaysTweets;
    private String twitterUser = "DulwichGallery";
    private TextView twitView1, twitView2, twitTime1, twitTime2;

    private View layout;


    /**
     * return an instance of this Fragment with a bundle into the tab adapter
     *
     * @param position
     * @return myFragmentTab
     */

    public static HomePageFragment getInstance(int position) {
        HomePageFragment myFragmentTab = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", 1);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //non facebook setup
        layout = inflater.inflate(R.layout.fragment_home_page, container, false);

        setRetainInstance(true);

        setupOnScreenElements(layout);
        //Get Today's Tweets

        if (GalleryData.get().getTodaysTweets().isEmpty()) {
            // Restore last state for checked position.
            if (isOnline()) {

                getTweets();
                Log.d("tweets", "online");

            }
        } else {
            todaysTweets = GalleryData.get().getTodaysTweets();
            Log.d("tweets", "no tweets call");

            setupTweetsAnimations(layout);
        }


        setupLibraryAnimations(layout);

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


    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    //Handles the web log in
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);

    }

    /**
     * @param layout
     */
    private void setupOnScreenElements(View layout) {
        cardView = (CardView) layout.findViewById(R.id.card_view_1_welcome1);
        cardView2 = (CardView) layout.findViewById(R.id.car_view_22);
        linearLayout = (LinearLayout) layout.findViewById(R.id.welcomeView);
        mapButton = (DynamicHeightImageView) layout.findViewById(R.id.map_image);


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewPager.setCurrentItem(3, true);
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


    /**
     * @param layout
     */
    public void setupTweetsAnimations(View layout) {

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

        twitView1 = (TextView) layout.findViewById(R.id.DisplayTweet1);
        twitView2 = (TextView) layout.findViewById(R.id.DisplayTweet2);
        twitTime1 = (TextView) layout.findViewById(R.id.statusTime1);
        twitTime2 = (TextView) layout.findViewById(R.id.statusTime2);

        viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            private final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            int i = 0;
            boolean t1 = false; // true if currently displaying twitView1
            boolean welcomed = false;

            public void onAnimationStart(Animation animation) {
                if (!todaysTweets.isEmpty()) {
                    if (i == todaysTweets.size()) i = 0;

                    if (t1 == false) {
                        twitView1.setText(todaysTweets.get(i).getText());
                        if (todaysTweets.get(i) != null) {
                            twitTime1.setText(df.format(todaysTweets.get(i).getCreatedAt()).toString());
                            t1 = true;

                        }
                        i++;
                    } else {
                        twitView2.setText(todaysTweets.get(i).getText());
                        twitTime2.setText(df.format(todaysTweets.get(i).getCreatedAt()).toString());
                        t1 = false;
                        i++;
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                if (!welcomed) {
                    twitView2.setTextSize(12);
                    welcomed = true;
                }
            }
        });


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewFlipper.showNext();


            }
        });

    }


    /**
     * @param layout
     */
    private void setupLibraryAnimations(View layout) {

        mDemoSlider = (SliderLayout) layout.findViewById(R.id.slider);


        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

        //file_maps - used to display images in the top homepage slider
        file_maps.put("Conor Harrington", R.drawable.lowresconorharrington);
        file_maps.put("Walter Kershaw", R.drawable.lowreswalterlandscape);
        file_maps.put("Stik", R.drawable.lowresstikthreeboys);
        file_maps.put("RUN", R.drawable.lowresrunstrita);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout

            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
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


    /**
     * ShowMessage() - this method shows the about us information for the outdoor gallery in an
     * AlertDialog
     */
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

    /**
     * showMessagePictureGallery() - this method shows the about information about the picture gallery
     */
    private void showMessagePictureGallery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About Dulwich Gallery");
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

    /**
     * getTweets() - this method retrieves the latest 5 tweets from a twitterUser
     */
    public void getTweets() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true) //authentication:
                        .setOAuthConsumerKey("5ZSHBFnviFgB1IVwBwO0ownXl")
                        .setOAuthConsumerSecret("WxwEzdsEhj9zH2b7OlYmPgcEZrFjt7DetEWeTRroc5cDJFpSFJ")
                        .setOAuthAccessToken("52824349-KOrBaGvCDvOZKcfFCeizpBsaeTINR7EmVJImGMmWN")
                        .setOAuthAccessTokenSecret("bgEDzmYOaeVZIImuEjhHilJEezHl6Fz1xSqitsmvxE0Hs");
                TwitterFactory tf = new TwitterFactory(cb.build());
                Twitter twitter = tf.getInstance();
                try {
                    List<Status> statuses = twitter.getUserTimeline(twitterUser);
                    Log.i("Status Count", statuses.size() + " Feeds");

                    String status;
                    int i = 0;
                    int c = 0;

                    while (i < 20 && c < 6) {
                        status = statuses.get(i).getText();
                        if (!status.substring(0, 2).equals("RT") && !status.substring(0, 1).equals("@")) {
                            GalleryData.get().getTodaysTweets().add(statuses.get(i));
                            c++;
                        }
                        i++;
                    }

                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                todaysTweets = GalleryData.get().getTodaysTweets();

                setupTweetsAnimations(layout);

            }
        });

        thread.start();

    }

    /**
     * @return boolean value for whether or not devise is online.
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}




