package com.xoom.codechallenge.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.xoom.codechallenge.R;
import com.xoom.codechallenge.databinding.FragmentCountryDisplayBinding;
import com.xoom.codechallenge.view.fragments.CodeChallengeBaseFragment;

public class CodeChallengeBaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void pushFragment(CodeChallengeBaseFragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add( R.id.container,fragment)
                .addToBackStack(tag)
                .commit();
    }
    public void replaceFragment(CodeChallengeBaseFragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,fragment)
                .commit();
    }

    protected void popFragment(String tag) {
        if(getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack(tag, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            this.finish();
        }
    }
}
