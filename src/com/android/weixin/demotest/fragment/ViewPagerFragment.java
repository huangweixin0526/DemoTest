package com.android.weixin.demotest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.weixin.demotest.R;

public class ViewPagerFragment extends Fragment implements OnClickListener {

	private Button mPrePageBtn, mNextPageBtn;
	private ViewPager mViewPager;
	private MyFragmentPagerAdapter mFragmentAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.view_pager_fragment_layout, container, false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mFragmentAdapter = new MyFragmentPagerAdapter(this.getChildFragmentManager());
		mViewPager = (ViewPager) view.findViewById(R.id.fragment_view_pager);
		mViewPager.setAdapter(mFragmentAdapter);
		mPrePageBtn = (Button) view.findViewById(R.id.pre_page_btn);
		mPrePageBtn.setOnClickListener(this);
		mNextPageBtn = (Button) view.findViewById(R.id.next_page_btn);
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
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
			}
			break;
		case R.id.next_page_btn:
			if (mViewPager.getCurrentItem() != 4) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, false);
			}
			break;
		default:
			break;

		}
	}
}
