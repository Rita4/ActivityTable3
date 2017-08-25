package com.rita.activitytable3.ui;

import com.rita.activitytable3.ui.baseactivity.BaseActivity;
import com.rita.activitytable3.R;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 关于我们
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        setTitle("关于码云");


        //获取客户端版本信息
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            TextView mVersion = (TextView) findViewById(R.id.about_version);
            mVersion.setText(info.versionName);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }
}
