package team3j.dulwichstreetart;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


/**
 * @author Team 3-J
 * This is the fragment of the Homepage to be displayed in the tab
 */


public class HomePageFragment extends Fragment {

    private TextView textView;
    private CardView cardView;
    private CardView cardView2;
    private TextView facebookCardText;
    private LinearLayout linearLayout;
    private ViewFlipper viewFlipper;
    private Timer timer;
    private com.etsy.android.grid.util.DynamicHeightImageView aboutDulwich;

    private TextView name;
    private SliderLayout mDemoSlider;

    Animation slide_in_left, slide_out_right;
    private boolean isLoggedIn;
    private DynamicHeightImageView mapButton;

    static ArrayList<String> todaysTweets = new ArrayList<>();
    private TextView twitView1;
    private TextView twitView2;
    private View layout;

    /**
     * return an instance of this Fragment with a bundle into the tab adapter
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

    /**
     *
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
        if (isOnline()) {
            getTweets();

            Log.d("tweets","online");

        }
        else{
            getOfflineTweets();
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

    private void getOfflineTweets() {
        Log.d("tweets","offline");

    }


    /**
     *
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
     *
     * @param layout
     */
    private void setupOnScreenElements(View layout) {
        cardView = (CardView) layout.findViewById(R.id.card_view_1_welcome1);
        cardView2 = (CardView) layout.findViewById(R.id.car_view_22);
        linearLayout = (LinearLayout) layout.findViewById(R.id.welcomeView);
        //name = (TextView) layout.findViewById(R.id.atsymbol);
        //name.setText("    @DulwichGallery      14h");
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
     *
     * @param layout
     */
    public void setupTweetsAnimations(View layout) {

        twitView1 = (TextView) layout.findViewById(R.id.DisplayTweet1);
        twitView2 = (TextView) layout.findViewById(R.id.DisplayTweet2);



//        try {
//        //    writeToFile("dulwichTweet.txt");
//      //      ReadBtn();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


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

        if (todaysTweets.size()!=0) {
            viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                int i = 0;
                boolean t1 = false;
                boolean welcomed = false;

                public void onAnimationStart(Animation animation) {
                    if (i == todaysTweets.size()) i = 0;

                    if (t1 == false) {
                        twitView1.setText(todaysTweets.get(i));
                        t1 = true;
                        i++;
                    } else {
                        twitView2.setText(todaysTweets.get(i));
                        t1 = false;
                        i++;
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
        }

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewFlipper.showNext();


            }
        });

    }

    private void writeToFile(String fileName) throws IOException {
        File file = getActivity().getFileStreamPath(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream writer = getActivity().openFileOutput(file.getName(), Context.MODE_PRIVATE);

        for (String string: todaysTweets){
            writer.write(string.getBytes());
            writer.flush();
        }

        writer.close();


    }
    public void ReadBtn() {
        //reading text from file
        try {
            FileInputStream fileIn=getActivity().openFileInput("dulwichTweet.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[100];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Toast.makeText(getActivity().getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void WriteBtn(View v) {
//        // add-write text into file
//        try {
//             FileOutputStream fileout=openFileOutput("mytextfile.txt", getActivity().MODE_PRIVATE);
//            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
//            outputWriter.write(textmsg.getText().toString());
//            outputWriter.close();
//
//            //display file saved message
//            Toast.makeText(getActivity().getBaseContext(), "File saved successfully!",
//                    Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private String readFromFile(Context context, String fileName) {
        if (context == null) {
            return null;
        }

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int size = inputStream.available();
                char[] buffer = new char[size];

                inputStreamReader.read(buffer);

                inputStream.close();
                ret = new String(buffer);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
    /**
     *
     * @param layout
     */
    private void setupLibraryAnimations(View layout) {

        mDemoSlider = (SliderLayout) layout.findViewById(R.id.slider);


        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

        //file_maps - used to display images in the top homepage slider
        file_maps.put("Conor Harrington", R.drawable.lowresconorharrington);
        file_maps.put("Walter Kershaw", R.drawable.lowreswalterlandscape);


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
     *ShowMessage() - this method shows the about us information for the outdoor gallery in an
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
     *showMessagePictureGallery - this method shows the about information about the picture gallery
     */
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

    /**
     *
     */
    public void getTweets() {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                //Date currentDate = new Date();
                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true) //authentication:
                        .setOAuthConsumerKey("5ZSHBFnviFgB1IVwBwO0ownXl")
                        .setOAuthConsumerSecret("WxwEzdsEhj9zH2b7OlYmPgcEZrFjt7DetEWeTRroc5cDJFpSFJ")
                        .setOAuthAccessToken("52824349-KOrBaGvCDvOZKcfFCeizpBsaeTINR7EmVJImGMmWN")
                        .setOAuthAccessTokenSecret("bgEDzmYOaeVZIImuEjhHilJEezHl6Fz1xSqitsmvxE0Hs");
                TwitterFactory tf = new TwitterFactory(cb.build());
                Twitter twitter = tf.getInstance();
                try {
                    List<Status> statuses = twitter.getUserTimeline("DulwichGallery");
                    Log.i("Status Count", statuses.size() + " Feeds");

                    String status;
                    int i = 0;
                    int c = 0;

                    do {
                        status = statuses.get(i).getText();
                        if(!status.substring(0,2).equals("RT") && !status.substring(0, 1).equals("@")) {
                            todaysTweets.add(status);
                            c++;
                        }
                        i++;
                    } while (i<20 && c<5); //statuses.get(i).getCreatedAt().equals(currentDate)

                } catch (TwitterException e) {
                    e.printStackTrace();
                }

                setupTweetsAnimations(layout);

            }
        });

        thread.start();

    }

    /**
     *
     * @return
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}




