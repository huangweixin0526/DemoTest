package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.webkit.WebView;

public class SurfaceViewActivity extends Activity {

	private SurfaceView mSurfaceView;
	WebView web;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.surface_view_layout);
	}
}
