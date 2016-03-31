package com.android.weixin.demotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity生命周期
 * 
 * 跳转下一个页面时页面会调用onSaveInstanceState，当屏幕方向转换Activity重建时处于后台的页面并不会重建，而且返回下一级页面onRestoreInstanceState并不会触发调用。
 * 
 * 如果结束进程，会重建上一级页面，这时如果在返回上一级页面，上一级页面也会重建。如果页面被系统Kill则会调用onSaveInstanceState
 * 
 * @author weixin
 * 
 */
public class LifecycleActivity extends Activity implements OnClickListener {

	private Button mNavigation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lifecycle_activity_lay);
		mNavigation = (Button) findViewById(R.id.navigation_click_btn);
		mNavigation.setOnClickListener(this);
		Intent intent = getIntent();
		if (intent != null) {
			Log.i("-->", "Intent Is Not Null");
		} else {
			Log.i("-->", "Intent Is Null");
		}
		Log.i("--->", "Lifecycle Activity onCreate");
		//finish();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("-->", "onRestart");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("-->", "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("-->", "onResume");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i("-->", "onNewIntent");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("-->", "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("-->", "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("-->", "onDestroy");
	}

	@Override
	public void onClick(View v) {
		Intent lifecycleFraIntent = new Intent(this, FramentLifecycleActivity.class);
		startActivity(lifecycleFraIntent);
	}

	@Override
	protected void onUserLeaveHint() {
		// 当一个Activity将要进入后台(background)时被回调
		super.onUserLeaveHint();
		Log.i("-->", "onUserLeaveHint");
	}

	/**
	 * OnLowMemory和OnTrimMemory的比较
	 * 
	 * 1，OnLowMemory被回调时，已经没有后台进程；而onTrimMemory被回调时，还有后台进程。
	 * 2，OnLowMemory是在最后一个后台进程被杀时调用，一般情况是low memory killer
	 * 杀进程后触发；而OnTrimMemory的触发更频繁，每次计算进程优先级时，只要满足条件，都会触发。
	 * 3，通过一键清理后，OnLowMemory不会被触发，而OnTrimMemory会被触发一次。
	 */

	/**
	 * onLowMemory
	 * 当后台程序已经终止资源还匮乏时会调用这个方法。好的应用程序一般会在这个方法里面释放一些不必要的资源来应付当后台程序已经终止，
	 * 前台应用程序内存还不够时的情况。
	 */
	@Override
	public void onLowMemory() {
		// TODO 当整个系统运行一个低内存状态时，这个方法会被调用
		// OnLowMemory是Android提供的API，在系统内存不足，所有后台程序（优先级为background的进程，不是指后台运行的进程）都被杀死时，系统会调用OnLowMemory
		super.onLowMemory();
		Log.i("-->", "onLowMemory");
	}

	/**
	 * OnTrimMemory的参数是一个int数值，代表不同的内存状态：
	 * 
	 * TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
	 * 
	 * 
	 * TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。
	 * 
	 * 
	 * TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
	 * 
	 * 
	 * TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。
	 * 
	 * 
	 * 以上4个是4.0增加
	 * 
	 * 
	 * TRIM_MEMORY_RUNNING_CRITICAL：内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
	 * 
	 * 
	 * TRIM_MEMORY_RUNNING_LOW：内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
	 * 
	 * 
	 * TRIM_MEMORY_RUNNING_MODERATE：内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存
	 * 
	 * 
	 * 以上3个是4.1增加
	 */
	@Override
	public void onTrimMemory(int level) {
		// TODO OnTrimMemory是Android 4.0之后提供的API，系统会根据不同的内存状态来回调
		super.onTrimMemory(level);
	}

	@Override
	public int getTaskId() {
		// TODO 此Activity所在的Task的ID
		return super.getTaskId();
	}

	@Override
	public boolean isTaskRoot() {
		// TODO 如果此Activity是所在Task中的第一个Activity返回true.
		return super.isTaskRoot();
	}

	@Override
	public boolean moveTaskToBack(boolean nonRoot) {
		// TODO 把这个Activity所在的Task移到后台(background,此时将回到Home或者之前的Task)
		return super.moveTaskToBack(nonRoot);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {		
		super.onRestoreInstanceState(savedInstanceState);
		Log.v("--->", "Lifecycle Activity onRestoreInstanceState");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {		
		super.onSaveInstanceState(outState);
		Log.v("--->", "Lifecycle Activity onSaveInstanceState");
	}
}
