package me.yokeyword.swipebackfragment.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import me.yokeyword.swipebackfragment.SwipeBackActivity;
import me.yokeyword.swipebackfragment.sample.fragment.FirstSwipeBackFragment;

public class MainActivity extends SwipeBackActivity implements BaseSwipeBackFragment.OnAddFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FirstSwipeBackFragment firstFragment = FirstSwipeBackFragment.newInstance();
            addFragment(firstFragment);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_pop_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_exit)
                .add(R.id.fl_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onAddFragment(Fragment fragment) {
        addFragment(fragment);
    }
}
