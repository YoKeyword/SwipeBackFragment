package me.yokeyword.swipebackfragment.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import me.yokeyword.swipebackfragment.SwipeBackActivity;
import me.yokeyword.swipebackfragment.sample.fragment.FirstSwipeBackFragment;

public class MainActivity extends SwipeBackActivity implements BaseSwipeBackFragment.OnAddFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FirstSwipeBackFragment firstFragment = FirstSwipeBackFragment.newInstance();
            loadFragment(firstFragment);
        } else {
            Toast.makeText(MainActivity.this, "啊哦,app被强杀喽~", Toast.LENGTH_LONG).show();
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

    private void addFragment(Fragment fromFragment, Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit)
                .add(R.id.fl_container, toFragment, toFragment.getClass().getSimpleName())
                .hide(fromFragment)
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }

    private void loadFragment(Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, toFragment, toFragment.getClass().getSimpleName())
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onAddFragment(Fragment fromFragment, Fragment toFragment) {
        addFragment(fromFragment, toFragment);
    }
}
