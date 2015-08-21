package com.android.weixin.demotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.weixin.demotest.fragment.LifecycleFragment;

/**
 * Fragment生命周期测试
 * @author weixin
 *
 */
public class FramentLifecycleActivity extends FragmentActivity implements
		OnClickListener {

	private static final String FRAGMENT_VALUE_1 = "framgnet_1";
	private static final String FRAGMENT_VALUE_2 = "fragment_2";

	private Fragment fragment;
	private FragmentManager fm;

	private Button mAdd, mReplace, mAttach, mShow;
	private Button mHide, mDetach, mRemove;
	private Button mNaviagtion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifecycle_layout);
		mAdd = (Button) findViewById(R.id.fragment_add_btn);
		mAdd.setOnClickListener(this);
		mReplace = (Button) findViewById(R.id.fragment_replace_btn);
		mReplace.setOnClickListener(this);
		mAttach = (Button) findViewById(R.id.fragment_attach_btn);
		mAttach.setOnClickListener(this);
		mShow = (Button) findViewById(R.id.fragment_show_btn);
		mShow.setOnClickListener(this);
		mHide = (Button) findViewById(R.id.fragment_hide_btn);
		mHide.setOnClickListener(this);
		mDetach = (Button) findViewById(R.id.fragment_detach_btn);
		mDetach.setOnClickListener(this);
		mRemove = (Button) findViewById(R.id.fragment_remove_btn);
		mRemove.setOnClickListener(this);
		mNaviagtion = (Button) findViewById(R.id.naviagtion_btn);
		mNaviagtion.setOnClickListener(this);

		fm = getSupportFragmentManager();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_add_btn:
			addFragment();
			break;
		case R.id.fragment_replace_btn:
			replaceFragment();
			break;
		case R.id.fragment_attach_btn:
			attachFragment();
			break;
		case R.id.fragment_show_btn:
			showFragment();
			break;
		case R.id.fragment_hide_btn:
			hideFragment();
			break;
		case R.id.fragment_detach_btn:
			detachFragment();
			break;
		case R.id.fragment_remove_btn:
			removeFragment();
			break;
		case R.id.naviagtion_btn:
			navigationActivity();
			break;
		}
	}

	/**
	 * 添加Fragment
	 */
	private void addFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		fragment = createFragment(FRAGMENT_VALUE_1);
		ft.add(R.id.fragment_root_fl, fragment, FRAGMENT_VALUE_1);
		ft.commit();
	}

	/**
	 * 替换Fragment
	 */
	private void replaceFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.fragment_root_fl, createFragment(FRAGMENT_VALUE_2));
		ft.commit();
	}

	/**
	 * 附加Fragment
	 */
	private void attachFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		ft.attach(fragment);
		ft.commit();
	}

	/**
	 * 显示Fragment
	 */
	private void showFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		ft.show(fragment);
		ft.commit();
	}

	/**
	 * 隐藏Fragment
	 */
	private void hideFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		ft.hide(fragment);
		ft.commit();
	}

	/**
	 * 分离Fragment
	 */
	private void detachFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		ft.detach(fragment);
		ft.commit();
	}

	/**
	 * 移除Fragment
	 */
	private void removeFragment() {
		FragmentTransaction ft = fm.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}
	
	private void navigationActivity(){
		Intent lifecycleActIntent = new Intent(this, LifecycleActivity.class);
		startActivity(lifecycleActIntent);
	}

	private Fragment createFragment(String params) {
		LifecycleFragment lifecycle = new LifecycleFragment();
		Bundle args = new Bundle();
		args.putString(LifecycleFragment.PARAMS, params);
		lifecycle.setArguments(args);
		return lifecycle;
	}
}
