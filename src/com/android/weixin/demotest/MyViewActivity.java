package com.android.weixin.demotest;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.weixin.demotest.widget.MyTestView;

/**
 * 自定义View
 * @author weixin
 *
 */
public class MyViewActivity extends Activity implements OnClickListener {

	private MyTestView mMyView;
	private Button mDrawBtn, mPostDraw;
	private Button mCanvasBtn;
	private LinearLayout mRoot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_view_lay);
		mDrawBtn = (Button) findViewById(R.id.my_view_draw_btn);
		mDrawBtn.setOnClickListener(this);
		mPostDraw = (Button) findViewById(R.id.my_view_post_draw_btn);
		mPostDraw.setOnClickListener(this);
		mMyView = (MyTestView) findViewById(R.id.my_test_view);
		mRoot = (LinearLayout) findViewById(R.id.my_view_root_ll);
		mCanvasBtn = (Button) findViewById(R.id.request_layout_draw_btn);
		mCanvasBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_view_draw_btn:
			Log.v("invalidate",
					"当前线程ID：" + String.valueOf(Thread.currentThread().getId()));
			//mMyView.setDraw(true);
			// 必须在主线程中通知View进行绘制
			for (int i = 0; i < 5; i++) {
				mMyView.setDraw(true);
				mMyView.invalidate();
				Log.i("--->", "invalidate");
			}
			mRoot.setBackground(new ColorDrawable(0xff282a2e));
			break;
		case R.id.my_view_post_draw_btn:
			new Thread(new Runnable() {

				@Override
				public void run() {
					Log.v("postInvalidate",
							"当前线程ID："
									+ String.valueOf(Thread.currentThread()
											.getId()));
					mMyView.setDraw(true);
					// 可以在子线程中通知View进行绘制
					mMyView.postInvalidate(100, 100, 200, 300);
				}
			}).start();

			break;
		case R.id.request_layout_draw_btn:
			// 重新请求布局
			mMyView.requestLayout();
			break;
		default:
			break;
		}
	}
}
