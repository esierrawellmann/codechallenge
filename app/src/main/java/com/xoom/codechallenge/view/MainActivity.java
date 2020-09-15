package com.xoom.codechallenge.view;

import android.os.Bundle;

import com.xoom.codechallenge.R;
import com.xoom.codechallenge.view.activities.CodeChallengeBaseActivity;
import com.xoom.codechallenge.view.fragments.CountryDisplayFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends CodeChallengeBaseActivity {

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().addOnBackStackChangedListener(backStackChangedListener);
        setContentView(R.layout.activity_main);
        replaceFragment(new CountryDisplayFragment());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    FragmentManager.OnBackStackChangedListener backStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {

        }
    };
}