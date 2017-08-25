package com.rita.activitytable3.ui.fragments;

import com.rita.activitytable3.ui.basefragment.BaseSwipeRefreshFragment;
import com.rita.activitytable3.adapter.CommonAdapter;
import com.rita.activitytable3.adapter.EventAdapter;
import com.rita.activitytable3.api.GitOSCApi;
import com.rita.activitytable3.bean.Event;
import com.rita.activitytable3.common.UIHelper;
import com.rita.activitytable3.utils.JsonUtils;
import com.rita.activitytable3.R;

import java.util.List;

/**
 * 我的最新动态列表
 * @created 2014-05-20 下午15:47
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新
 * 更新者
 */
public class MySelfEventsFragment extends BaseSwipeRefreshFragment<Event> {

	public static MySelfEventsFragment newInstance() {
		return new MySelfEventsFragment();
	}

    @Override
    public CommonAdapter<Event> getAdapter() {
        return new EventAdapter(getActivity(), R.layout.list_item_event);
    }

    @Override
    public List<Event> getDatas(byte[] responeString) {
        return JsonUtils.getList(Event[].class, responeString);
    }

    @Override
    public void requestData() {
        GitOSCApi.getMyEvents(mCurrentPage, mHandler);
    }
	
	@Override
	public void onItemClick(int position, Event event) {
		UIHelper.showEventDetail(getActivity(), event);
	}
	
}
