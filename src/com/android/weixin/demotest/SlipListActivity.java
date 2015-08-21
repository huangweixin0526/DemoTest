package com.android.weixin.demotest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.weixin.demotest.widget.ReaderListView;

public class SlipListActivity extends Activity{
	
	private static final int COUNT = 12;
	
	private String[] mSourceData = new String[COUNT];
	
	{
		for(int i = 0;i<COUNT;i++){
			mSourceData[i] = String.format("书名%1$s", i);
		}
	}
	
	private ReaderListView mSingleGrdiView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_line_grid_lay);
		
		this.mSingleGrdiView = (ReaderListView) findViewById(R.id.dishtype);
		SingleLineAdapter adapter = new SingleLineAdapter();
		mSingleGrdiView.setAdapter(adapter);
	}
	
	
	public class SingleLineAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mSourceData.length;
		}

		@Override
		public String getItem(int position) {
			return mSourceData[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				Log.v("TAG", "getView convertView null");
				convertView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.single_line_item_lay, parent,false);
				TextView nameTextView = (TextView) convertView.findViewById(R.id.item_name_tv);
				nameTextView.setText(getItem(position));
				convertView.setTag(nameTextView);
			}else{
				Log.v("TAG", "getView convertView not null");
				TextView nameTextView = (TextView) convertView.getTag();
				nameTextView.setText(getItem(position));
			}			
			return convertView;
		}
		
	}
}
