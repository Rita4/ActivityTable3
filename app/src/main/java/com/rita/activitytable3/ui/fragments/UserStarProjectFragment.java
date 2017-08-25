package com.rita.activitytable3.ui.fragments;

import com.rita.activitytable3.adapter.CommonAdapter;
import com.rita.activitytable3.adapter.ProjectAdapter;
import com.rita.activitytable3.api.GitOSCApi;
import com.rita.activitytable3.bean.Project;
import com.rita.activitytable3.bean.User;
import com.rita.activitytable3.common.Contanst;
import com.rita.activitytable3.common.UIHelper;
import com.rita.activitytable3.ui.basefragment.BaseSwipeRefreshFragment;
import com.rita.activitytable3.utils.JsonUtils;
import com.rita.activitytable3.R;

import android.os.Bundle;

import java.util.List;

/**
 * 用户star项目列表
 *
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *         <p/>
 *         最后更新
 *         更新者
 * @created 2014-08-27
 */
public class UserStarProjectFragment extends 
BaseSwipeRefreshFragment<Project> {
	
    private User mUser;

    public static UserStarProjectFragment newInstance(User user) {
        UserStarProjectFragment fragment = new UserStarProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Contanst.USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mUser = (User) args.getSerializable(Contanst.USER);
        }
    }

    @Override
    public CommonAdapter<Project> getAdapter() {
        return new ProjectAdapter(getActivity(), R.layout.list_item_project);
    }

    @Override
    public List<Project> getDatas(byte[] responeString) {
        return JsonUtils.getList(Project[].class, responeString);
    }

    @Override
    public void requestData() {
        GitOSCApi.getStarProjects(mUser.getId(), mCurrentPage, mHandler);
    }

    @Override
    public void onItemClick(int position, Project project) {
        UIHelper.showProjectDetail(getActivity(), null, project.getId());
    }

	/*
    @Override
    protected String getEmptyTip() {
        return "暂无star";
    }
	*/
	
}
