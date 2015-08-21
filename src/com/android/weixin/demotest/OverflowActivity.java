package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class OverflowActivity extends Activity implements OnClickListener{

	private TextView tv, mtv;
	private TextView mTvClick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overflow_lay);
		
		mtv = (TextView) findViewById(R.id.textView1);
		tv = (TextView) findViewById(R.id.streamOverflow);
		
		mTvClick = (TextView) findViewById(R.id.click_tv);
		mTvClick.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.click_tv:
			tv.setVisibility(View.GONE);
			mtv.setVisibility(View.GONE);
			break;
		}		
	}
}
