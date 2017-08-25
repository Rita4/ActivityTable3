package com.rita.activitytable3.ui.fragments;

//import android.support.v4.app.Fragment;

import com.rita.activitytable3.ui.fragments.MySelfEventsFragment;
import com.rita.activitytable3.ui.fragments.MySelfProjectsFragment;
import com.rita.activitytable3.ui.fragments.UserStarProjectFragment;
import com.rita.activitytable3.ui.fragments.UserWatchProjectsFragment;
import com.rita.activitytable3.ui.basefragment.BaseViewPagerFragment;
import com.rita.activitytable3.adapter.ViewPageFragmentAdapter;
import com.rita.activitytable3.AppContext;
import com.rita.activitytable3.common.Contanst;
import com.rita.activitytable3.R;

import android.os.Bundle;

/**
 * 用户主界面
 * 
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-04-29
 */
public class MySelfViewPagerFragment extends BaseViewPagerFragment {
	
    public static MySelfViewPagerFragment newInstance() {
        return new MySelfViewPagerFragment();
    }
    
	@Override
	protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		String[] title = getResources().getStringArray(R.array.myself_title_array);
		adapter.addTab(title[0], "event", MySelfEventsFragment.class, null);
		adapter.addTab(title[1], "project", MySelfProjectsFragment.class, null);
		Bundle bundle = new Bundle();
		bundle.putSerializable(Contanst.USER, AppContext.getInstance().getLoginInfo());
		adapter.addTab(title[2], "star_projects", UserStarProjectFragment.class, bundle);
		adapter.addTab(title[3], "watch_projects", UserWatchProjectsFragment.class, bundle);
	}
	
}
