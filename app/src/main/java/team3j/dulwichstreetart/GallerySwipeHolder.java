package team3j.dulwichstreetart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

/**
 * Created by JGill on 25/01/15.
 * This class is the Holder for the Swipe Gallery
 */

public class GallerySwipeHolder extends FragmentActivity {

    private ViewPager viewPager;
    private int mPagerPosition;
    private int mPagerOffsetPixels;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_stack_to_front,
                R.anim.swipeback_stack_right_out);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent myIntent = getIntent(); // gets the previously created intent
        int indexOfArtWork = myIntent.getIntExtra("indexOfArtWork", 0);


        //uses library for swiping to create swipe effect
         SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.view_pager)
                .setSwipeBackView(R.layout.swipeback_default)
                .setDividerAsSolidColor(Color.WHITE)
                .setDividerSize(2)
                .setOnInterceptMoveEventListener(
                        new SwipeBack.OnInterceptMoveEventListener() {
                            @Override
                            public boolean isViewDraggable(View v, int dx,
                                                           int x, int y) {
                                if (v == viewPager) {
                                    return !(mPagerPosition == 0 && mPagerOffsetPixels == 0)
                                            || dx < 0;
                                }

                                return false;
                            }
                        });

        //creates fragment adapter to display all images
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),indexOfArtWork));
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPagerPosition = position;
                mPagerOffsetPixels = positionOffsetPixels;
            }

        });
    }


}