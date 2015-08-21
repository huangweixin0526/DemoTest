package com.android.weixin.demotest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 当ScrollView嵌套ListView时，ListView高度无法算出来，通常只能显示ListView的其中一行。
 * 但此时滚动的时候就有两种情况，ScrollView滚动和ListView滚动。
 * 如果ScrollView滚动则ListView不滚动，将只显示第一项，ScrollView滚动时ListView的Adapter中getView不会执行.
 * 如果ScrollView中的内容未能使ScrollView滚动时，则ListView将可以滚动，ListView滚动时Adapter中的getView依然可以执行。
 * 
 * 解决方案，将ListView加载所有项的高度计算出来，此时ListView中所有项都将加载到界面中，ListView将不提供滚动
 * 所以滚动时ListView中Adapter的getView不会执行
 * @author weixin
 *
 */
public class ReaderListView extends ListView {

	public ReaderListView(Context context) {
		super(context);
		setFocusable(false);
	}

	public ReaderListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(false);
	}

	public ReaderListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
