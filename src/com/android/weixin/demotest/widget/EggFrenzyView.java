package com.android.weixin.demotest.widget;

import com.android.weixin.demotest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class EggFrenzyView extends View {

	private static final long ANIM_TIME = 600;
	private int mImgRawWidth;
	private int mImgRawHeight;
	private int mEggWidth;
	private int mEggHeight;
	private int mCurAnimIndex;
	private boolean isClickEgg;
	private EggIndex mCurEggIndex = EggIndex.NONE;
	private Bitmap mRawEggBitmap;
	private Bitmap mBgBitmap;
	private Bitmap[] mEggAnimBitmaps = new Bitmap[3];
	private Rect mEggOne;
	private Rect mEggTwo;
	private Rect mEggThree;

	private Paint mEggPaint;
	private GestureDetector mGestureDetector;

	public EggFrenzyView(Context context) {
		this(context, null);
	}

	public EggFrenzyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusable(true);
		setFocusableInTouchMode(true);
		initVar();
		initPaint();
	}

	private void initVar() {
		isClickEgg = false;
		mCurEggIndex = EggIndex.NONE;

		mRawEggBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.egg_normal);
		mEggAnimBitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.egg_anim_one);
		mEggAnimBitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.egg_anim_two);
		mEggAnimBitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.egg_anim_three);
	}

	private void initPaint() {
		mEggPaint = new Paint();
		mEggPaint.setAntiAlias(true);
		mEggPaint.setDither(true);
		mGestureDetector = new GestureDetector(getContext(), mSimpleGestureListener);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int viewSize = Math.min(getMeasuredWidth(), getMeasuredHeight());

		if (viewSize <= 0) {
			return;
		}

		setMeasuredDimension(viewSize, 2 * viewSize / 3);
		Log.v("--->", "onMeasure");
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.v("--->", "onSizeChanged");
		mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_tanchuang);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.drawable.egg_normal, options);
		mImgRawWidth = options.outWidth;
		mImgRawHeight = options.outHeight;

		if (w <= 4 * mImgRawWidth) {
			mEggWidth = (int) Math.floor(w / 3);
			mEggHeight = (int) (mImgRawHeight * ((float) mEggWidth / mImgRawWidth));
		} else {

		}

		mEggOne = new Rect(0, h - (4 * mEggHeight / 3), mEggWidth, h - (mEggHeight / 3));
		mEggTwo = new Rect(mEggWidth, h - (4 * mEggHeight / 3), mEggWidth * 2, h - (mEggHeight / 3));
		mEggThree = new Rect(mEggWidth * 2, h - (4 * mEggHeight / 3), mEggWidth * 3, h - (mEggHeight / 3));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(mBgBitmap, null, new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), mEggPaint);
		// canvas.drawBitmap(mEggBitmap, 50, 50, mEggPaint);
		//
		// canvas.drawBitmap(mEggBitmap, 50, 250, mEggPaint);

		// mEggPaint.setColor(0xFFFF7836);
		// canvas.drawRect(mEggOne, mEggPaint);
		// canvas.drawRect(mEggTwo, mEggPaint);
		// canvas.drawRect(mEggThree, mEggPaint);

		drawEggOne(canvas);
		drawEggTwo(canvas);
		drawEggThree(canvas);
		// canvas.drawBitmap(mRawEggBitmap, mEggWidth + 200, 50, mEggPaint);
		if (isClickEgg && mCurAnimIndex < 3) {
			postInvalidateDelayed(ANIM_TIME);
		}
		Log.v("--->", "onDraw");
	}

	private void drawEggOne(Canvas canvas) {
		if (!isClickEgg || mCurEggIndex != EggIndex.ONE) {
			canvas.drawBitmap(mRawEggBitmap, null, mEggOne, mEggPaint);
		} else if (mCurEggIndex == EggIndex.ONE) {
			drawAnim(canvas, mEggOne);
		}
	}

	private void drawEggTwo(Canvas canvas) {
		if (!isClickEgg || mCurEggIndex != EggIndex.TWO) {
			canvas.drawBitmap(mRawEggBitmap, null, mEggTwo, mEggPaint);
		} else {
			drawAnim(canvas, mEggTwo);
		}
	}

	private void drawEggThree(Canvas canvas) {
		if (!isClickEgg || mCurEggIndex != EggIndex.THREE) {
			canvas.drawBitmap(mRawEggBitmap, null, mEggThree, mEggPaint);
		} else {
			drawAnim(canvas, mEggThree);
		}
	}

	private void drawAnim(Canvas canvas, Rect rect) {
		if (mCurAnimIndex >= 0 && mCurAnimIndex < 3) {
			canvas.drawBitmap(mEggAnimBitmaps[mCurAnimIndex++], null, rect, mEggPaint);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (mGestureDetector != null) {
			mGestureDetector.onTouchEvent(event);
		}
		return super.dispatchTouchEvent(event);
	}

	GestureDetector.SimpleOnGestureListener mSimpleGestureListener = new GestureDetector.SimpleOnGestureListener() {

		@Override
		public void onShowPress(MotionEvent e) {
			if (isClickEgg) {
				return;
			}
			Rect tempEggOne = new Rect(mEggOne);
			tempEggOne.inset(mEggOne.width() / 4, mEggOne.height() / 4);
			if (tempEggOne.left <= e.getX() && tempEggOne.right >= e.getX() && tempEggOne.top <= e.getY()
					&& tempEggOne.bottom >= e.getY()) {
				isClickEgg = true;
				Log.v("--->", "on Show Press Egg One");
				mCurEggIndex = EggIndex.ONE;
				postInvalidateDelayed(ANIM_TIME);
				return;
			}
			Rect tempEggTwo = new Rect(mEggTwo);
			tempEggTwo.inset(mEggTwo.width() / 4, mEggTwo.height() / 4);
			if (tempEggTwo.left <= e.getX() && tempEggTwo.right >= e.getX() && tempEggTwo.top <= e.getY()
					&& tempEggTwo.bottom >= e.getY()) {
				isClickEgg = true;
				Log.v("--->", "on Show Press Egg Two");
				mCurEggIndex = EggIndex.TWO;
				postInvalidateDelayed(ANIM_TIME);
				return;
			}
			Rect tempEggThree = new Rect(mEggThree);
			tempEggThree.inset(mEggThree.width() / 4, mEggThree.height() / 4);
			if (tempEggThree.left <= e.getX() && tempEggThree.right >= e.getX() && tempEggThree.top <= e.getY()
					&& tempEggThree.bottom >= e.getY()) {
				isClickEgg = true;
				Log.v("--->", "on Show Press Egg Three");
				mCurEggIndex = EggIndex.THREE;
				postInvalidateDelayed(ANIM_TIME);
				return;
			}

		};
	};

	private enum EggIndex {
		NONE, ONE, TWO, THREE
	}

}
