package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.weixin.demotest.utils.DisplayUtil;

/**
 * 单位转换
 * @author weixin
 *
 */
public class DisplayActivity extends Activity implements OnClickListener {

	private TextView mSpValue, mDpValue;
	private EditText mSpPxValue, mDpPxValue;
	private Button mSpConvertBtn, mDpConvertBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_lay);
		mSpValue = (TextView) findViewById(R.id.sp_convert_value_tv);
		mDpValue = (TextView) findViewById(R.id.dip_convert_value_tv);
		mSpPxValue = (EditText) findViewById(R.id.sp_px_value_et);
		mDpPxValue = (EditText) findViewById(R.id.dip_px_value_et);
		mSpConvertBtn = (Button) findViewById(R.id.sp_convert_btn);
		mSpConvertBtn.setOnClickListener(this);
		mDpConvertBtn = (Button) findViewById(R.id.dip_convert_btn);
		mDpConvertBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sp_convert_btn:
			pxToSpConvert();
			break;
		case R.id.dip_convert_btn:
			pxToDpConvert();
			break;
		}

	}

	private void pxToSpConvert() {
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		float pxValue = Float.parseFloat(mSpPxValue.getText().toString());
		int spValue = DisplayUtil.px2sp(pxValue, dm.scaledDensity);
		mSpValue.setText(String.valueOf(spValue));
	}

	private void pxToDpConvert() {
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		float pxValue = Float.parseFloat(mDpPxValue.getText().toString());
		int dpValue = DisplayUtil.px2dip(pxValue, dm.density);
		mDpValue.setText(String.valueOf(dpValue));
	}

}
