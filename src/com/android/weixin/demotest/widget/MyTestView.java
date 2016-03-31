package com.android.weixin.demotest.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.os.Looper;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.TextView;

import com.android.weixin.demotest.R;

/**
 * 自定义视图，onDraw(Canvas canvas)canvas无法调用setBitmap设置Bitmap会抛异常
 * ,此时该Canvas中的mBitmap为null，绘制时将直接绘制到屏幕上<br/>
 * 自已创建的Canvas是无法将图像绘制到屏幕上的，但是可以设置Bitmap将其绘制到该位图中。 <br/>
 * Canvas是一个很虚幻的概念，相当于一个透明图层（用过PS的同学应该都知道），
 * 每次Canvas画图时（即调用Draw系列函数），都会产生一个透明图层，然后在这个图层上画图，画完之后覆盖在屏幕上显示。
 * 
 * 
 * @author weixin
 * 
 */
public class MyTestView extends View {

	private boolean isDraw = false;
	private Bitmap bitmap;
	private Bitmap cacheBitmap;
	private Bitmap cacheBitmap2;
	private Canvas mCanvas = null;
	private Canvas cacheCanvas = null;

	public MyTestView(Context context) {
		super(context);
	}

	public MyTestView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public MyTestView(Context context, AttributeSet attrs, int defStyle) {
		super(context);
		// 设置键盘模式是否能获得焦点
		setFocusable(true);
		// 设置触摸模式是否能获得焦点
		setFocusableInTouchMode(true);
	}

	View appendView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.append_view_to_view_lay, null, false);
	{
		measureView(appendView);
	}

	/**
	 * 如何绘制这个View
	 */
	@Override
	protected void onDraw(final Canvas canvas) {
		Log.e("--->", "onDraw start");
		Log.i("--->", "default sava count：" + canvas.getSaveCount());
		Log.v("MyTestView", "当前线程ID：" + String.valueOf(Thread.currentThread().getId()));
		if (Looper.myLooper() == Looper.getMainLooper()) {
			Log.v("MyTestView", "主线程，线程ID：" + String.valueOf(Thread.currentThread().getId()));
		} else {
			Log.v("MyTestView", "子线程，线程ID：" + String.valueOf(Thread.currentThread().getId()));
		}

		if (isDraw) {
			// Drawable dra =
			// getResources().getDrawable(R.drawable.ic_launcher);
			// // 必须设置范围，才可以绘制出来
			// dra.setBounds(300, 150, 600, 800);
			// // 将该Drawable绘制到屏上
			// dra.draw(canvas);
			// BitmapDrawable bitDrawable = (BitmapDrawable) dra;
			// if(bitDrawable.getBitmap() !=null &&
			// !bitDrawable.getBitmap().isRecycled()){
			// bitDrawable.getBitmap().recycle();
			// }
			// 绘制视图的Canvas无法设置Bitmap，会抛出异常
			// canvas.setBitmap(cacheBitmap);
			// Thread thread = new Thread(new Runnable() {
			//
			// @Override
			// public void run() {
			// post(new Runnable() {
			//
			// @Override
			// public void run() {

			// if (mCanvas == null) {
			// mCanvas = canvas;
			// canvas.drawBitmap(bitmap, 0, 0, null);
			// }else{
			// mCanvas.drawBitmap(bitmap, 100, 200, null);
			// }
			// }
			// });
			// }
			// });
			// thread.start();
			// measureText(canvas);
			Log.i("-->", "Draw");
			// canvas.drawBitmap(cacheBitmap2, 0, 0, null);
			// savaCount(canvas);
			if (!cacheBitmap2.isRecycled()) {
				cacheBitmap2.recycle();
			}
			Drawable bgDrawable1 = new ColorDrawable(0xff36373a);
			bgDrawable1.setBounds(0, 100, 500, 500);
			// bgDrawable1.draw(canvas);

			Drawable bgDrawable2 = new ColorDrawable(0xff282a2e);
			bgDrawable2.setBounds(0, 100, 400, 400);
			// bgDrawable2.draw(canvas);

			appendView.draw(canvas);
		}
		Log.e("--->", "onDraw end");
	}

	private void savaCount(Canvas canvas) {
		if (mCanvas == null) {
			mCanvas = canvas;
		}
		mCanvas.clipRect(new Rect(100, 100, 800, 800));
		mCanvas.drawColor(Color.GREEN);
		mCanvas.save();
		Log.i("-->", "Save Count：" + canvas.getSaveCount());

	}

	private void measureText(Canvas canvas) {
		String text = "测";
		String TEXT = "测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测测";

		TextView textView = new TextView(getContext());
		textView.setTextSize(50);
		textView.setTextColor(Color.RED);
		textView.setText(text);
		TextPaint paint = new TextPaint();

		paint.setTextSize(50);
		paint.setColor(Color.RED);

		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		int width1 = bounds.width();
		int height1 = bounds.height();

		int width2 = (int) paint.measureText(text);
		// ascent + descent
		int height2 = (int) paint.getFontSpacing();

		FontMetrics fm = paint.getFontMetrics();

		float top = fm.top;

		float ascent = fm.ascent;

		float descent = fm.descent;

		float bottom = fm.bottom;

		float leading = fm.leading;

		float textSize = paint.getTextSize();

		float spacingExtra = textView.getLineSpacingExtra();

		textView.setText(TEXT);
		TextPaint multpaint = textView.getPaint();

		int width3 = (int) multpaint.measureText(text);
		int height3 = (int) multpaint.getFontSpacing();

		FontMetrics fm3 = multpaint.getFontMetrics();

		float top3 = fm3.top;

		float ascent3 = fm3.ascent;

		float descent3 = fm3.descent;

		float bottom3 = fm3.bottom;

		float leading3 = fm3.leading;

		float textSize3 = multpaint.getTextSize();

		float spacingExtra4 = textView.getLineSpacingExtra();

		StaticLayout staticLayout = new StaticLayout(TEXT, paint, canvas.getWidth(), Alignment.ALIGN_NORMAL, 1.0F, 50.0F, false);
		staticLayout.draw(canvas);
		canvas.restore();
		int lineCount = staticLayout.getLineCount();
		int height4 = (int) paint.getFontSpacing();

		Paint staticPaint = staticLayout.getPaint();
		// 获取行间距的倍数
		float spacing = staticLayout.getSpacingMultiplier();
		// 获取行间距
		float spacingAdd = staticLayout.getSpacingAdd();
		float lineLeft = staticLayout.getLineLeft(1);
		float lineAscent = staticLayout.getLineAscent(1);
		float lineBaseline = staticLayout.getLineBaseline(1);
		float lineBottom = staticLayout.getLineBottom(1);
		float lineDescent = staticLayout.getLineDescent(1);
		float lineMax = staticLayout.getLineMax(1);
		float lineRight = staticLayout.getLineRight(1);
		float lineStart = staticLayout.getLineStart(1);
		float lineEnd = staticLayout.getLineEnd(1);
		float lineTop = staticLayout.getLineTop(1);
		float staticHei = staticLayout.getHeight();
		float staticWid = staticLayout.getWidth();

		Rect staticbounds = new Rect();
		staticLayout.getLineBounds(1, staticbounds);

		int staticWidth = staticbounds.width();
		int staticHeight = staticbounds.height();

		float staticHeight1 = staticPaint.getFontSpacing();

		FontMetrics fm1 = staticPaint.getFontMetrics();

		float top2 = fm1.top;

		float ascent2 = fm1.ascent;

		float descent2 = fm1.descent;

		float bottom2 = fm1.bottom;

		float leading2 = fm1.leading;

		float textSize2 = paint.getTextSize();
	}

	/**
	 * 通知绘制时，必须得等当前次绘制完成才可以继续绘制
	 * 
	 * @param canvas
	 */
	private void postInvalidateTask(Canvas canvas) {
		postInvalidate();
		for (int i = 0; i < 5; i++) {
			try {
				Log.i("-->", "当前循环次数：" + i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Log.i("-->", "当前循环结束");
	}

	public void setDraw(boolean isDrawBit) {
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		cacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
		cacheBitmap2 = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
		cacheCanvas = new Canvas(cacheBitmap2);
		cacheCanvas.drawBitmap(bitmap, 0, 0, null);
		this.isDraw = isDrawBit;
	}

	/**
	 * 
	 * 决定View的大小。 父元素需要调整子元素的大小，所以有时会执行多次 measure时父View会让各个子View Measure
	 * 自己的大小，然后如果父View觉得太大或太小即不合适的话会要求子View重新Measure <br/>
	 * 如果高度或宽度为0，则onDraw将不进行调用.
	 * 
	 * <br/>
	 * 
	 * 一个MeasureSpec封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求。
	 * 一个MeasureSpec由大小和模式组成 。 它有三种模式：
	 * UNSPECIFIED(未指定),父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小 ；
	 * EXACTLY(完全)，父元素决定自元素的确切大小 ，子元素将被限定在给定的边界里而忽略它本身大小；
	 * AT_MOST(至多)，子元素至多达到指定大小的值。
	 * 
	 * 它常用的三个函数：<br/>
	 * 1.static int getMode(int measureSpec):根据提供的测量值(格式)提取模式(上述三个模式之一) <br/>
	 * 2.static int getSize(int measureSpec):根据提供的测量值(格式)提取大小值(这个大小也就是我们通常所说的大小)
	 * <br/>
	 * 3.static int makeMeasureSpec(int size,int mode):根据提供的大小值和模式创建一个测量值(格式)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 决定View在ViewGroup中的位置 如果父视图的子试图的个数为0，就会执行一次。否则就会执行多次。因为开始时父试图中是没有子试图的。
	 * 但是当你从xml文件中加载子试图或者在java代码中添加子试图时，父试图的状态会发生变化，这个变化会引起onlayout
	 * 上下左右参数都是相对于parent
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	public class AppendView extends TextView {

		public AppendView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
			super.onMeasure(200, 200);
		}
	}

	/**
	 * 测量view的宽高
	 * 
	 * @param child
	 */
	private void measureView(View child) {

		ViewGroup.LayoutParams lp = child.getLayoutParams();

		if (lp == null) {
			lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childMeasureWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
		int childMeasureHeight;

		if (lp.height > 0) {
			childMeasureHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
		} else {
			childMeasureHeight = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}

		child.measure(childMeasureWidth, childMeasureHeight);
	}
}
