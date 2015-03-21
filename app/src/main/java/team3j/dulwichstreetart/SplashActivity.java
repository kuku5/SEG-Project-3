package team3j.dulwichstreetart;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

/**
 * @author Team 3-J
 * This Activity is the main Activity and is called when the app first launched creates displays splash
 *
 */


public class SplashActivity extends Activity {


    private long TIMER=500;
    public static ArrayList<Art> artArrayList = GalleryData.create().GetGalleryData();

    /**
     * onCreate setups up the onscreen elements showing the splash logo.
     * also sets up static instance of GalleryData to be used through out the app.
     * Calls methods to check if the application has been run for the first time.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create Gallery Data instance
        GalleryData.create();

        //call to method to check first-time run of application
        if(initialRun())
        {
            initiateVisited();


        }else
        {
            loadVisited();

        }

        setContentView(R.layout.activity_splash);


        //display splash screen for half a second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
               overridePendingTransition(R.anim.swipeback_slide_right_in,
                        R.anim.swipeback_stack_to_back);
                finish();
            }
        }, TIMER);


    }

    /**
     * Checks if the user has opened/started the app for the first time.
     * @return boolean value defining if the app has been run before. If it returns false then it has been run.
     */
    private boolean initialRun()
    {
        SharedPreferences firstPref = getPreferences(MODE_PRIVATE);
        boolean hasRun = firstPref.getBoolean("hasRun", false);
        if (!hasRun) {
            SharedPreferences.Editor editor = firstPref.edit();
            editor.putBoolean("hasRun", true);
            editor.commit();
        }
        return !hasRun;
    }


    /**
     * Initialises the two shared preferences needed in the functionality of the visited tab-
     * visitedPref and datePref
     * This only runs the first time the app is opened
     */
    public void initiateVisited()
    {
        SharedPreferences visitedPref = this.getSharedPreferences("VisitedList", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = visitedPref.edit();

        SharedPreferences datePref = this.getSharedPreferences("VisitedDate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editDate = datePref.edit();

        for(int i=0; i<artArrayList.size(); i++)
        {
            edit.putBoolean(artArrayList.get(i).getName(), false);
            editDate.putString(artArrayList.get(i).getName(),"--/--/----");
        }

        edit.apply();
        editDate.apply();
        System.out.println("First time");


    }

    /**
     * On reopening the app the values stored in the shared preferences are reloaded into the artArrayList
     */
    public void loadVisited()
    {
        SharedPreferences visitedPref = this.getSharedPreferences("VisitedList", Context.MODE_PRIVATE);
        SharedPreferences datePref = this.getSharedPreferences("VisitedDate", Context.MODE_PRIVATE);
        String date;
        boolean hasVisit;
        for(int i = 0; i<artArrayList.size(); i++)
        {
            hasVisit = visitedPref.getBoolean(artArrayList.get(i).getName(),false);
            date = datePref.getString(artArrayList.get(i).getName(), "--/--/----");
            if(hasVisit == true)
            {
                artArrayList.get(i).setVisited(true);
                artArrayList.get(i).setDateVisited(date);
            }


            System.out.println("Not First time");

        }
    }


}
