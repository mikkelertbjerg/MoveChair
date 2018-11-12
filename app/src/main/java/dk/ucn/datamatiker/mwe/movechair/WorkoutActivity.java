package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

//TODO Test if this is an optimal implementation of sliding between fagments, might just have to delete it.

public class WorkoutActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager workoutPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter workoutPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_workout_view);

        // Instantiate a ViewPager and a PagerAdapter.
        workoutPager = (ViewPager) findViewById(R.id.workout_pager);
        workoutPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        workoutPager.setAdapter(workoutPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (workoutPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            workoutPager.setCurrentItem(workoutPager.getCurrentItem() - 1);
        }
    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ExerciseViewModel();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
