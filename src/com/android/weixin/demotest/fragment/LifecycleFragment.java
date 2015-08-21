package com.android.weixin.demotest.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.weixin.demotest.R;

/**
 * Fragment生命周期试验
 * 
 * @author weixin
 * 
 */
public class LifecycleFragment extends Fragment implements OnClickListener {

	public static final String PARAMS = "PARAMS";

	// Fragment和Activity建立关联的时候调用
	@Override
	public void onAttach(Activity activity) {
		Log.v("-->", "onAttach：" + getArguments().getString(PARAMS));
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("-->", "onCreate：" + getArguments().getString(PARAMS));
	}

	// 为Fragment加载布局时调用
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.v("-->", "onCreateView：" + getArguments().getString(PARAMS));

		return inflater.inflate(R.layout.fragment_item_lay, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		TextView textView = (TextView) view.findViewById(R.id.list_item_tv);
		textView.setText(getArguments().getString(PARAMS));
		textView.setOnClickListener(this);
		Log.v("-->", "onViewCreated：" + getArguments().getString(PARAMS));
	}

	// 当Activity中的onCreate方法执行完后调用
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v("-->", "onActivityCreated：" + getArguments().getString(PARAMS));
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.v("-->", "onStart：" + getArguments().getString(PARAMS));
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.v("-->", "onResume：" + getArguments().getString(PARAMS));
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.v("-->", "onPause：" + getArguments().getString(PARAMS));
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.v("-->", "onStop：" + getArguments().getString(PARAMS));
	}

	// Fragment中的布局被移除时调用。
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.v("-->", "onDestroyView：" + getArguments().getString(PARAMS));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v("-->", "onDestroy：" + getArguments().getString(PARAMS));
	}

	// Fragment和Activity解除关联的时候调用。
	@Override
	public void onDetach() {
		super.onDetach();
		Log.v("-->", "onDetach：" + getArguments().getString(PARAMS));
	}

	@Override
	public void onClick(View v) {

	}
}
