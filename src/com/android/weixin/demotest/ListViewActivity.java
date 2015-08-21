package com.android.weixin.demotest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * 测试ListView Item 子视图点击事件的传递及状态改变问题， 要进行各个组件点击事件状态改变，一般是各个组件自己注册事件
 * 
 * beforeDescendants：viewgroup会优先其子类控件而获取到焦点
 * 
 * afterDescendants：viewgroup只有当其子类控件不需要获取焦点时才获取焦点
 * 
 * blocksDescendants：viewgroup会覆盖子类控件而直接获得焦点
 * 
 * 设置beforeDescendants然后给各个组件设置事件，就可以达到各个组件自己的点击状态，而不会出现点击Item结果子视图跟着改变状态
 * 
 * @author weixin
 * 
 */
public class ListViewActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	private List<String> mListData;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_data_lay);
		initData();
		initView();
	}

	private void initView() {
		ListViewAdapter adapter = new ListViewAdapter();
		mListView = (ListView) findViewById(R.id.list_lv);
		mListView.setOnItemClickListener(this);
		mListView.setAdapter(adapter);
	}

	private void initData() {
		mListData = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			String data = "List View Item Data Index " + i;
			mListData.add(data);
		}
	}

	public class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mListData.size();
		}

		@Override
		public String getItem(int position) {
			return mListData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				convertView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
						.inflate(R.layout.list_item_lay, parent, false);
				// convertView.setOnClickListener(ListViewActivity.this);
				holder = new Holder();
				holder.mItemText = (TextView) convertView
						.findViewById(R.id.note_title_tv);
				holder.mItemImage = (ImageView) convertView
						.findViewById(R.id.note_list_share_iv);
				// holder.mItemImage.setOnClickListener(ListViewActivity.this);
				holder.mItemButton = (Button) convertView
						.findViewById(R.id.bt_listitem_recommend_headshow_add);
				// holder.mItemButton.setOnClickListener(ListViewActivity.this);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.mItemText.setText(getItem(position));
			return convertView;
		}

	}

	public class Holder {
		public TextView mItemText;
		public ImageView mItemImage;
		public Button mItemButton;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.note_list_share_iv:
			Log.v("TAG", "note_list_share_iv");
			break;
		case R.id.bt_listitem_recommend_headshow_add:
			Log.v("TAG", "bt_listitem_recommend_headshow_add");
			break;
		default:
			Log.v("TAG", "list item");
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.v("TAG", "onItemClick");

	}
}
