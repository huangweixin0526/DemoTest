package com.android.weixin.demotest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 下拉框 控件的使用
 * 
 * android:spinnerMode：列表显示的模式，有两个选择，为弹出列表（dialog）以及下拉列表（dropdown），如果不特别设置，为下拉列表。。
 * 
 * android:entries：使用<string-array.../>资源配置数据源。
 * 
 * android:prompt：对当前下拉列表设置标题，仅在dialog模式下有效。
 * 
 * @author weixin
 * 
 */
public class SpinnerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_layout);
		List<String> list = new ArrayList<String>();
		list.add("苹果");
		list.add("香蕉");
		list.add("橘子");
		list.add("香蕉");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		Spinner sp = (Spinner) findViewById(R.id.spinner1);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			// parent： 为控件Spinner view：显示文字的TextView position：下拉选项的位置从0开始
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tvResult = (TextView) findViewById(R.id.tvResult);
				// 获取Spinner控件的适配器
				ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent
						.getAdapter();
				tvResult.setText(adapter.getItem(position));
			}

			// 没有选中时的处理
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}

}
