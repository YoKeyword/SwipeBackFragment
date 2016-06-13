package me.yokeyword.swipebackfragment.sample;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.yokeyword.swipebackfragment.SwipeBackFragment;


/**
 * Created by YoKeyword on 16/4/21.
 */
public class BaseSwipeBackFragment extends SwipeBackFragment {
    protected OnAddFragmentListener mAddFragmentListener;

    protected void _initToolbar(Toolbar toolbar) {
        toolbar.setTitle("SwipeBackActivity的Fragment");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddFragmentListener) {
            mAddFragmentListener = (OnAddFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAddFragmentListener = null;
    }

    public interface OnAddFragmentListener {
        void onAddFragment(Fragment fromFragment, Fragment toFragment);
    }
}
