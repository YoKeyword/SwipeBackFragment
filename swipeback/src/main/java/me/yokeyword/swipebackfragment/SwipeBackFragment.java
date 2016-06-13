package me.yokeyword.swipebackfragment;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/**
 * SwipeBackFragment
 * Created by YoKeyword on 16/4/19.
 */
public class SwipeBackFragment extends Fragment {
    private static final String SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN = "SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN";
    private SwipeBackLayout mSwipeBackLayout;
    private Animation mNoAnim;
    boolean mLocking = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

        mNoAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.no_anim);
        onFragmentCreate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SWIPEBACKFRAGMENT_STATE_SAVE_IS_HIDDEN, isHidden());
    }

    private void onFragmentCreate() {
        mSwipeBackLayout = new SwipeBackLayout(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSwipeBackLayout.setLayoutParams(params);
        mSwipeBackLayout.setBackgroundColor(Color.TRANSPARENT);
    }

    protected View attachToSwipeBack(View view) {
        mSwipeBackLayout.attachToFragment(this, view);
        return mSwipeBackLayout;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden && mSwipeBackLayout != null) {
            mSwipeBackLayout.hiddenFragment();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        initFragmentLayoutBg(view);
        if (view != null) {
            view.setClickable(true);
        }
    }

    private void initFragmentLayoutBg(View view) {
        if (view != null && !(view instanceof SwipeBackLayout) && view.getBackground() == null) {
            int background = getWindowBackground();
            view.setBackgroundResource(background);
        } else {
            if (view instanceof SwipeBackLayout) {
                View childView = ((SwipeBackLayout) view).getChildAt(0);
                if (childView != null && childView.getBackground() == null) {
                    int background = getWindowBackground();
                    childView.setBackgroundResource(background);
                }
            }
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (mLocking) {
            return mNoAnim;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    protected int getWindowBackground() {
        TypedArray a = getActivity().getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.windowBackground
        });
        int background = a.getResourceId(0, 0);
        a.recycle();
        return background;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackLayout;
    }

    public void setSwipeBackEnable(boolean enable) {
        mSwipeBackLayout.setEnableGesture(enable);
    }
}
