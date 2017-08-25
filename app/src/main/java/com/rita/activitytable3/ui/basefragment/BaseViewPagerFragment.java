package com.rita.activitytable3.ui.basefragment;

import com.rita.activitytable3.ui.basefragment.BaseFragment;
import com.rita.activitytable3.adapter.ViewPageFragmentAdapter;
import com.rita.activitytable3.widget.PagerSlidingTabStrip;
import com.rita.activitytable3.R;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 带有导航条的基类
 * 海棠： fragment的导航条三在这里加入
 */
public abstract class BaseViewPagerFragment extends BaseFragment 
{

	protected PagerSlidingTabStrip mTabStrip;
    protected ViewPageFragmentAdapter mTabsAdapter;
	protected ViewPager mViewPager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpage_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(), mTabStrip,
                mViewPager);

		// for child class
        onSetupTabAdapter(mTabsAdapter);
        mTabsAdapter.notifyDataSetChanged();
		mViewPager.setOffscreenPageLimit(3);
		if (savedInstanceState != null) {
			int pos = savedInstanceState.getInt("position");
			mViewPager.setCurrentItem(pos, true);
		}
	}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", mViewPager.getCurrentItem());
    }
	
    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
	
	
}