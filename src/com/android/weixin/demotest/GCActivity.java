package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.weixin.demotest.other.GCThread;

/**
 * GC垃圾回收测试
 * @author weixin
 *
 */
public class GCActivity extends Activity implements OnClickListener {

	private Button mGcBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gc_layout);

		mGcBtn = (Button) findViewById(R.id.create_gc_btn);
		mGcBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		threadGcFee();
	}

	/**
	 * 对象没有被引用，但对象内部有线程还在运行时，该对象并不会被释放。
	 * 但是Activity返回时，可以被释放，因为Activity并没有被该对象所引用。
	 */
	private void threadGcFee() {
		GCThread gc = new GCThread();
		gc.start();
	}
}
