package com.android.weixin.demotest;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * 带Fragment标签的布局，无法生成View,经实验并不能将一个Fragment添加至ListView Item中，
 * 一个View或ViewGroup并不能来操作Fragment也就是，不能将Fragment添加或替换一个通过LayoutInflater生成的View中。
 * Fragment的操作只能是在一整个Activity的布局里进行操作
 * @author weixin
 *
 */
public class ListViewContentTypeActivity extends FragmentActivity {

	private List<String> mDataArray;
	private ListView mListView;
	private FragmentManager fm;
	private FragmentTransaction ft;

	{
		mDataArray = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			String content = String.format("Item %1$s", i);
			mDataArray.add(content);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_content_type_lay);
		// setContentView(R.layout.list_view_content_type_item_kay);
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		 ListViewAdapter adapter = new ListViewAdapter();
		 mListView = (ListView) findViewById(R.id.list_content_type_lv);
		 mListView.setAdapter(adapter);
	}

	public class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mDataArray.size();
		}

		@Override
		public String getItem(int position) {
			return mDataArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(
					R.layout.list_view_content_type_item_kay, parent, false);
			

			return convertView;
		}
	}

}
