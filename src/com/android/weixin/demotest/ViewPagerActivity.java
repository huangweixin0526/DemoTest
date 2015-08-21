package com.android.weixin.demotest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.weixin.demotest.fragment.LifecycleFragment;

public class ViewPagerActivity extends FragmentActivity implements OnClickListener {

	private Button mPrePageBtn, mNextPageBtn;
	private ViewPager mViewPager;
	private MyFragmentPagerAdapter mFragmentAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager_layout);
		mFragmentAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.fragment_view_pager);
		mViewPager.setAdapter(mFragmentAdapter);
		mPrePageBtn = (Button) findViewById(R.id.pre_page_btn);
		mPrePageBtn.setOnClickListener(this);
		mNextPageBtn = (Button) findViewById(R.id.next_page_btn);
		mNextPageBtn.setOnClickListener(this);
	}

	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return createFragment("Fragment position = " + position);
		}

		private Fragment createFragment(String params) {
			LifecycleFragment lifecycle = new LifecycleFragment();
			Bundle args = new Bundle();
			args.putString(LifecycleFragment.PARAMS, params);
			lifecycle.setArguments(args);
			return lifecycle;
		}

		@Override
		public int getCount() {
			return 5;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pre_page_btn:
			if (mViewPager.getCurrentItem() != 0) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1,true);
			}
			break;
		case R.id.next_page_btn:
			if (mViewPager.getCurrentItem() != 4) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1,false);
			}
			break;
		default:
			break;
		}

	}
}
