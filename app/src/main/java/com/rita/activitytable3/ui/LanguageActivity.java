package com.rita.activitytable3.ui;

import com.rita.activitytable3.adapter.CommonAdapter;
import com.rita.activitytable3.adapter.ProjectAdapter;
import com.rita.activitytable3.adapter.ViewHolder;
import com.rita.activitytable3.api.GitOSCApi;
import com.rita.activitytable3.bean.Language;
import com.rita.activitytable3.bean.Project;
import com.rita.activitytable3.common.UIHelper;
import com.rita.activitytable3.ui.baseactivity.BaseActivity;
import com.rita.activitytable3.utils.JsonUtils;
import com.rita.activitytable3.widget.EnhanceListView;
import com.rita.activitytable3.widget.TipInfoLayout;
import com.rita.activitytable3.R;

import com.kymjs.rxvolley.client.HttpCallback;
import butterknife.ButterKnife;
import butterknife.InjectView;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;
import java.util.Map;

public class LanguageActivity extends BaseActivity implements
        ActionBar.OnNavigationListener, OnItemClickListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
	 
	private CommonAdapter<Language> mLanguageAdapter;
    private ProjectAdapter mProjectAdapter;
    private String mLanguageId;
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	@InjectView(R.id.tip_info)
    TipInfoLayout tipInfo;
    @InjectView(R.id.listView)
    EnhanceListView listView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.inject(this);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        mProjectAdapter = new ProjectAdapter(this, R.layout.list_item_project);
        mLanguageAdapter = new CommonAdapter<Language>(getApplicationContext(), R.layout
                .languages) {
            @Override
            public void convert(ViewHolder vh, Language item) {
                vh.setText(R.id.language_name, item.getName());
            }
        };
        mActionBar.setListNavigationCallbacks(mLanguageAdapter, this);
        listView.setAdapter(mProjectAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnLoadMoreListener(new EnhanceListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int pageNum, int pageSize) {
                loadProjects(mLanguageId, pageNum);
            }
        });
        tipInfo.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLanguageAdapter.getCount() == 0) {
                    loadLanguagesList();
                } else {
                    loadProjects(mLanguageId, 1);
                }
            }
        });
        loadLanguagesList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
	
    private void loadProjects(final String languageId, final int page) {
        GitOSCApi.getLanguageProjectList(languageId, page, new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                tipInfo.setHiden();
                List<Project> projects = JsonUtils.getList(Project[].class, t);
                if (projects != null && !projects.isEmpty()) {
                    listView.setVisibility(View.VISIBLE);
                    mProjectAdapter.addItem(projects);
                } else {
                    if (page == 1) {
                        tipInfo.setEmptyData("暂无该语言相关的项目");
                    }
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                tipInfo.setLoadError();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                if (page == 1) {
                    tipInfo.setLoading();
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
	
    void setFooterNotLanguages() {
        listView.setVisibility(View.GONE);
        tipInfo.setEmptyData("加载语言列表失败");
    }

    // 加载语言列表
    private void loadLanguagesList() {
        GitOSCApi.getLanguageList(new HttpCallback() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                List<Language> languageList = JsonUtils.getList(Language[].class, t);
                if (languageList != null && !languageList.isEmpty()) {
                    mLanguageAdapter.addItem(languageList);
                } else {
                    setFooterNotLanguages();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                setFooterNotLanguages();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                tipInfo.setLoading();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(int arg0, long arg1) {
        Language language = mLanguageAdapter.getItem(arg0);
        if (language != null) {
            mProjectAdapter.clear();
            mLanguageId = language.getId();
            loadProjects(mLanguageId, 1);
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Project p = mProjectAdapter.getItem(position);
        if (p != null) {
            UIHelper.showProjectDetail(this, p, p.getId());
        }
    }
	
	
}
