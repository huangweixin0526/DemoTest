package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 测试触摸点位置，每个View的dispatchTouchEvent,onTouch触摸点是相对于该View的位置
 * 
 * @author weixin
 * 
 */
public class TouchPointActivity extends Activity {

	private ScrollView mSv;
	private TextView mTouchContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_point_layout);
		mSv = (ScrollView) findViewById(R.id.touch_point_sv);
		// mSv.setOnTouchListener(svTouchListener);
		mTouchContent = (TextView) findViewById(R.id.touch_content_tv);
		// mTouchContent.setOnTouchListener(touchListener);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.i("-->", "Activity Dispatch Touch Event：X = " + ev.getX() + " Y = " + ev.getY());
		return super.dispatchTouchEvent(ev);
	}

	private OnTouchListener touchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// Log.v("-->", "TextView onTouch Touch Event：X = " + event.getX() +
			// " Y = " + event.getY());
			getViewAbsolutePoint();
			return false;
		}

	};

	private OnTouchListener svTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Log.e("-->", "ScrollView onTouch Touch Event：X = " + event.getX() + " Y = " + event.getY());
			return false;
		}

	};

	/**
	 * getLocalVisibleRect ， 返回一个填充的Rect对象， 感觉是这个View的Rect大小，left，top取到的都是0
	 * 
	 * getGlobalVisibleRect ， 获取全局坐标系的一个视图区域， 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
	 * 
	 * View getLeft , getTop, getBottom, getRight, 这一组是获取相对在它父亲里的坐标
	 */
	private void getViewAbsolutePoint() {
		int[] windowPoint = new int[2];
		int[] ScreenPoint = new int[2];
		// 获取在当前窗口内的绝对坐标
		mTouchContent.getLocationInWindow(windowPoint);
		// 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）
		// 获取在当前屏幕内的绝对坐标
		mTouchContent.getLocationOnScreen(ScreenPoint);
		Log.v("-->", "view--->x窗口坐标 = " + windowPoint[0] + " view--->y窗口坐标 = " + windowPoint[1] + " view--->x屏幕坐标 = " + ScreenPoint[0] + " view--->y屏幕坐标 = "
				+ ScreenPoint[1]);
	}

}
