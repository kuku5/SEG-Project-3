package team3j.dulwichstreetart;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
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
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
    ViewFlipper viewFlipper;

    Animation slide_in_left, slide_out_right;
    private boolean isLoggedIn;

    //return an instance of this Fragment with a bundle into the tab adapter
    public static HomePageFragment getInstance(int position) {
        HomePageFragment myFragmentTab = new HomePageFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        myFragmentTab.setArguments(args);
        return myFragmentTab;
    }

    //this creates a view
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       //creates view that setups what is displayed
        View layout = inflater.inflate(R.layout.fragment_home_page, container, false);
        //textView = (TextView) layout.findViewById(R.id.position);
        isLoggedIn = false;
        cardView = (CardView) layout.findViewById(R.id.card_view_1_welcome1);
        cardView2 = (CardView) layout.findViewById(R.id.car_view_22);
        linearLayout=(LinearLayout) layout.findViewById(R.id.welcomeView);
        button=(Button) layout.findViewById(R.id.button_facebook);
        Bundle bundle = getArguments();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });


        //retrieves the bundle
        if (bundle != null) {

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setVisibility(View.GONE);

                }
            });
        }

        //animate the slider

        viewFlipper = (ViewFlipper)layout.findViewById(R.id.view_animator);

        slide_in_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left_no_fade);
        slide_out_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right_no_fade);

        viewFlipper.setInAnimation(slide_in_left);
        viewFlipper.setOutAnimation(slide_out_right);
        viewFlipper.setFlipInterval(3000);

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();

            }
        });
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

            System.out.println("SKEENNNNNNN" + Session.openActiveSession(getActivity(),this, true, statusCallback));
            checkIfActiveSession();
        } else if(isLoggedIn){
            session.close();
        }
    }

    //checks to see if there is already a session open.
    public void checkIfActiveSession(){
        Session session = Session.getActiveSession();
        if (session != null && (session.isOpened() || session.isClosed()) ) {
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
            Log.i("MainActivity","Logged out...");
            //test.setText("");
            button.setText("Log In");
            isLoggedIn = false;

        }
    }
    //Method used to retrieve fb data
    public void retrieveInfo(Session session){
        //test.setText("Logged in as ");
        //Get the profile of the person logged in
        Bundle b1 = new Bundle();
        b1.putBoolean("summary", true);     //includes a summary in the request
        b1.putString("filter", "stream");   //gets the chronological order of comments
        b1.putString("limit","100");        //gets max of 100
        new Request(session, "726958990741991/comments", b1, HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response)  {
                        if(response !=null) {
                            try {
                                System.out.println(response.getGraphObject().toString());
                                System.out.println(response.getGraphObject().getInnerJSONObject().getJSONObject("summary").toString());
                                int x = response.getGraphObject().getInnerJSONObject().getJSONArray("data").length();
                                System.out.println(x);
                                for(int i = 0; i<61; i++){
                                    System.out.println(response.getGraphObject().getInnerJSONObject().getJSONArray("data").getJSONObject(i).get("message"));
                                }
                            } catch (Exception e){

                            }
                        }
                    }
                }).executeAsync();

        // Get total number of likes on a post
        Bundle b = new Bundle();
        b.putBoolean("summary", true);
        new Request(session,"798332966914164/likes",b,HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(Response response) {
                        try {
                            //test.append(" \n Total like count on 798332966914164 is "+response.getGraphObject().getInnerJSONObject().getJSONObject("summary").get("total_count").toString());
                        } catch (Exception e){
                            System.out.println(e);
                        }
                    }
                }).executeAsync();
    }

    //Handles the web log in
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);

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

