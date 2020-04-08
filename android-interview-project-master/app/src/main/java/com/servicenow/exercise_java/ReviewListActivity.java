/**
 * ReviewListActivity - the launcher activity of the application.
 * ReviewListActivity starts the ReviewListFragment; puts it in the backstack such that
 * when a user click on "back" button in the detailed review page, it goes back the review list page.
 *
 * @author Henry Song
 */
package com.servicenow.exercise_java;

import android.app.Activity;
import android.os.Bundle;

import android.app.Fragment;

import com.servicenow.exercise.R;

public class ReviewListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment rlFragment = new ReviewListFragment();
        // display the ReviewListFragment, put it in the backstack in fragment manager
        this.getFragmentManager().beginTransaction()
                .add(R.id.activity_frame, (Fragment)rlFragment, "ReviewList Fragment")
                .commit();

    }

    public void onBackPressed() {
        // user pressed back button
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
