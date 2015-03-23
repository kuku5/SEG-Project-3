package team3j.dulwichstreetart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.hannesdorfmann.swipeback.Position;
import com.hannesdorfmann.swipeback.SwipeBack;

/**
 * @author Team 3-J
 * This class is the Holder for the Swipe Gallery
 */

public class GallerySwipeHolder extends FragmentActivity {

    private ViewPager viewPager;
    private int mPagerPosition;
    private int mPagerOffsetPixels;
    private int indexOfArtWork;

    /**
     * onBackPressed overrides the default back button press for when on the gallery
     * and adds a custom animation to swipe to the left
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipeback_stack_to_front,
                R.anim.swipeback_stack_right_out);
        viewPager.removeAllViews();
        viewPager.destroyDrawingCache();
        viewPager = null;

        GalleryFragment.recyclerView.getAdapter().notifyDataSetChanged();

        System.gc();

    }

    /**
     * onCreate set up the gallery view pager which displays all the artwork
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent myIntent = getIntent(); // gets the previously created intent
        indexOfArtWork = myIntent.getIntExtra("indexOfArtWork", 0);


        // uses library for swiping to create swipe effect
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.view_pager)
                .setSwipeBackView(R.layout.custom_swipeback)
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
        viewPager.setOffscreenPageLimit(1);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                viewPager.getCurrentItem();
                mPagerPosition = position;
                mPagerOffsetPixels = 90;
                if (position == indexOfArtWork) {
                    mPagerPosition = 0;
                    mPagerOffsetPixels = 0;

                }

            }


        });


        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        viewPager.setCurrentItem(indexOfArtWork, true);

    }

    /**
     * this class is used as a custom page ViewPager.PageTransformer animation for when swiping from
     * artwork to artwork
     */
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.9f;
        private static final float MIN_ALPHA = 0.85f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}