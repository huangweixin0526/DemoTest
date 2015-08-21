package com.android.weixin.demotest.app;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{
	
	@Override
	public void onTerminate() {
		Log.e("-->", "Application exit");
		super.onTerminate();
	}

}
