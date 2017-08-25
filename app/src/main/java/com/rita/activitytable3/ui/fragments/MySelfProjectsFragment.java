package com.rita.activitytable3.ui.fragments;

import com.rita.activitytable3.adapter.CommonAdapter;
import com.rita.activitytable3.adapter.MySelfProjectsAdapter;
import com.rita.activitytable3.api.GitOSCApi;
import com.rita.activitytable3.bean.Project;
import com.rita.activitytable3.common.UIHelper;
import com.rita.activitytable3.ui.basefragment.BaseSwipeRefreshFragment;
import com.rita.activitytable3.utils.JsonUtils;
import com.rita.activitytable3.R;

import java.util.List;

/**
 * 我的项目列表Fragment
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @created 2014-05-12 下午14：24
 */
public class MySelfProjectsFragment extends BaseSwipeRefreshFragment<Project> {
	
    public static MySelfProjectsFragment newInstance() {
        return new MySelfProjectsFragment();
    }

    @Override
    public CommonAdapter<Project> getAdapter() {
        return new MySelfProjectsAdapter(getActivity(), R.layout.list_item_myproject);
    }

    @Override
    public List<Project> getDatas(byte[] responeString) {
        return JsonUtils.getList(Project[].class, responeString);
    }

    @Override
    public void requestData() {
        GitOSCApi.getMyProjects(mCurrentPage, mHandler);
    }
	
    @Override
    public void onItemClick(int position, Project project) {
        UIHelper.showProjectDetail(getActivity(), null, project.getId());
    }
	
}
