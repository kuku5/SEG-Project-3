package team3j.dulwichstreetart;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author Team 3-J
 * This Activity is the main Activity and is called when the app first launched creates displays splash
 *
 */


public class SplashActivity extends Activity {


    private long TIMER=500;

    /**
     * onCreate setups up the onscreen elements showing the splash logo
     * also sets up static instance of GalleryData to be used through out the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create Gallery Data instance
        GalleryData.create();

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


}
