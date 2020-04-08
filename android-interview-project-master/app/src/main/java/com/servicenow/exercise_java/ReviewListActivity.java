package com.servicenow.exercise_java;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.servicenow.exercise.R;

public class ReviewListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment rlFragment = new ReviewListFragment();
        this.getFragmentManager().beginTransaction()
                .add(R.id.activity_frame, (Fragment)rlFragment, "ReviewList Fragment")
                //.addToBackStack(null)
                .commit();

    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
