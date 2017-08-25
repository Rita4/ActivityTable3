package com.rita.activitytable3.ui.fragments;

import com.rita.activitytable3.ui.fragments.NotificationFragment;
import com.rita.activitytable3.R;
import com.rita.activitytable3.adapter.ViewPageFragmentAdapter;
import com.rita.activitytable3.ui.basefragment.BaseViewPagerFragment;

import android.os.Bundle;

/**
 * 通知
 * 
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-04-29
 */
public class NotificaiontViewPagerFragment extends BaseViewPagerFragment {
	
    public static NotificaiontViewPagerFragment newInstance() {
        return new NotificaiontViewPagerFragment();
    }

	@Override
	protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
		String[] title = getResources().getStringArray(R.array.notification_title_array);
		Bundle unRead = new Bundle();
		unRead.putInt(NotificationFragment.NOTIFICATION_ACTION_KEY, NotificationFragment.ACTION_UNREAD);
		adapter.addTab(title[0], "unread", NotificationFragment.class, unRead);
		Bundle readed = new Bundle();
		readed.putInt(NotificationFragment.NOTIFICATION_ACTION_KEY, NotificationFragment.ACTION_READED);
		adapter.addTab(title[1], "readed", NotificationFragment.class, readed);
	}
}
