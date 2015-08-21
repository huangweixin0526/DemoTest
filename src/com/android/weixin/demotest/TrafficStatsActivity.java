package com.android.weixin.demotest;

import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 手机使用流量的测试用例
 * @author weixin
 *
 */
public class TrafficStatsActivity extends Activity {
	/** Called when the activity is first created. */
	private Button showBtn = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.traffic_stats_lay);
		showBtn = (Button) findViewById(R.id.showList);
		showBtn.setOnClickListener(new ButtonListener());
	}

	public void getAppTrafficList() {
		// 获取所有的安装在手机上的应用软件的信息，并且获取这些软件里面的权限信息
		PackageManager pm = getPackageManager();// 获取系统应用包管理
		// 获取每个包内的androidmanifest.xml信息，它的权限等等
		List<PackageInfo> pinfos = pm
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES
						| PackageManager.GET_PERMISSIONS);
		// 遍历每个应用包信息
		for (PackageInfo info : pinfos) {
			// 请求每个程序包对应的androidManifest.xml里面的权限
			String[] premissions = info.requestedPermissions;
			if (premissions != null && premissions.length > 0) {
				// 找出需要网络服务的应用程序
				for (String premission : premissions) {
					if ("android.permission.INTERNET".equals(premission)) {
						// 获取每个应用程序在操作系统内的进程id
						int uId = info.applicationInfo.uid;
						// 如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
						long rx = TrafficStats.getUidRxBytes(uId);
						// 如果返回-1，代表不支持使用该方法，注意必须是2.2以上的
						long tx = TrafficStats.getUidTxBytes(uId);
						if (rx < 0 || tx < 0) {
							continue;
						} else {
							Toast.makeText(
									this,
									info.applicationInfo.loadLabel(pm)
											+ "消耗的流量--"
											+ Formatter.formatFileSize(this, rx
													+ tx), Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		}
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			getAppTrafficList();
		}

	}
}
