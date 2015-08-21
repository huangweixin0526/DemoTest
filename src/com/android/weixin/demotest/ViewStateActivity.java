package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * selector XML状态具有优先级，当第一个状态被满足时就使用第一个，当第一个状态的条件不满足以下的状态也将不可用，
 * 比如state_focused改状态放第一个时，View.setFocusable(false);该状态将不可用,及使后面还有state_selected状态也将不触发(state_focused放在中间后面的状态也可能被失效)
 * 
 * 只有状态被满足才可以逐个被检测，看是否被满足.
 * @author weixin
 *
 */
public class ViewStateActivity extends Activity implements OnClickListener{

	private TextView mStateTv1,mStateTv2;
	private TextView mStateTv3,mStateTv4;
	private TextView mStateTv5,mStateTv6;
	private ImageView mPraiseIv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_state_layout);
		
		mStateTv1 = (TextView) findViewById(R.id.text_state_tv1);
		mStateTv1.setOnClickListener(this);
		mStateTv1.setSelected(true);
		mStateTv2 = (TextView) findViewById(R.id.text_state_tv2);
		mStateTv2.setOnClickListener(this);
		
		mStateTv3 = (TextView) findViewById(R.id.text_state_tv3);
		mStateTv3.setOnClickListener(this);
		mStateTv4 = (TextView) findViewById(R.id.text_state_tv4);
		mStateTv4.setOnClickListener(this);
		
		mStateTv5 = (TextView) findViewById(R.id.text_state_tv5);
		mStateTv5.setOnClickListener(this);
		mStateTv6 = (TextView) findViewById(R.id.text_state_tv6);
		mStateTv6.setOnClickListener(this);
		
		mPraiseIv = (ImageView) findViewById(R.id.comment_item_praise_iv);
		mPraiseIv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_state_tv5:
			mStateTv5.setSelected(true);
			mStateTv6.setSelected(false);
			break;
		case R.id.text_state_tv6:
			mStateTv5.setSelected(false);
			mStateTv6.setSelected(true);
			break;
		case R.id.comment_item_praise_iv:
			mPraiseIv.setSelected(true);
			break;
		default:
			break;
		}
	}
}
