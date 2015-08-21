package com.android.weixin.demotest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * TextView类中
 * 
 * 有个 getLayout（）方法：the Layout that is currently being used to display the text.
 * This can be null if the text or width has recently changes.
 * 
 * 其返回类型是Layout ，也就是返回textView的布局。
 * 
 * 然后重要的是通过这个 layout调用一个方法：
 * 
 * getLineForVertical(int verticalPointPosition) //得到某点在垂直方向上的行数值
 * 
 * 于是综上所述,在实际的触摸事件中可以这样使用:
 * 
 * Layout layout=textView.getLayout（）;
 * 
 * int line = layout.getLineForVertical（textView.getScrollY() + (int)
 * event.getY());
 * 
 * //得到触摸点在textView中垂直方向上的行数值。参数是触摸点在Y轴上的偏移量
 * 
 * 接下来继续介绍一个方法，要用到上边的 layout 和 line：
 * 
 * layout.getOffsetForHorizontal( line , (int) event.getX() );
 * 
 * //得到触摸点在某一行水平方向上的偏移量。
 * 
 * @author weixin
 * 
 */
public class MyTextView extends TextView {

	public MyTextView(Context context) {
		super(context);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// 触摸点相对于其所在组件原点的坐标
		Log.w("-->", "MyTextView dispatchTouchEvent Touch Event：X = " + event.getX() + " Y = " + event.getY());
		// 触摸点相对于屏幕原点的坐标
		Log.w("-->", "MyTextView dispatchTouchEvent Touch Event：RawX = " + event.getRawX() + " RawY = " + event.getRawY());
		Log.w("-->", "MyTextView dispatchTouchEvent Touch Event：getLeft = " + getLeft() + " getTop = " + getTop() + " getRight = " + getRight()
				+ " getBottom = " + getBottom());
		// getX和getY获取到的值为相对于父视图而言的两个左边缘和上边缘的距离。
		Log.w("-->", "MyTextView dispatchTouchEvent Touch Event：getX = " + getX() + " getY = " + getY());
		return super.dispatchTouchEvent(event);
	}
}
