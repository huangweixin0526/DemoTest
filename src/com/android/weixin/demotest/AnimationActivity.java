package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * 补间动画定义时，是以相对自身View元素的左上角为原点坐为动画坐标点，动画是以相对该点来执行的。而且该元素动画只在该元素的父元素的范围内运动，
 * 如超过时将会被遮挡
 * 
 * 
 * Animation: 1、startNow方法：立刻启动动画,该方法用于启动执行一个动画。该方法是启动执行动画的主要方法.
 * 2、start方法：启动动画,该方法用于启动执行一个动画 。该方法是启动执行动画的另一个主要方法.
 * start方法区别于startNow方法的地方在于，start方法可以用于在getTransformation方法被调用时启动动画。
 * 3、cancel方法：取消动画，该方法用于取消一个动画的执行。该方法是取得一个正在执行中的动画的主要方法。
 * cancel方法和startNow方法结合可以实现对动画执行过程的控制
 * 。需要注意的是，通过cancel方法取消的动画，必须使用reset方法或者setAnimation方法重新设置，才可以再次执行动画。
 * 
 * @author weixin
 * 
 */
public class AnimationActivity extends Activity implements OnClickListener {

	private Button mTranslateBtn, mTranslateTwoBtn;
	private TextView mTranslateTv;

	private Animation mTranslateAnim;
	private Animation mRotateAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_layout);
		mTranslateBtn = (Button) findViewById(R.id.translate_anim_btn);
		mTranslateBtn.setOnClickListener(this);
		mTranslateTwoBtn = (Button) findViewById(R.id.translate_anim_two_btn);
		mTranslateTwoBtn.setOnClickListener(this);
		mTranslateTv = (TextView) findViewById(R.id.translate_anim_tv);

		mTranslateAnim = AnimationUtils.loadAnimation(mTranslateBtn.getContext(), R.anim.translate_anim);

		mRotateAnim = AnimationUtils.loadAnimation(mTranslateBtn.getContext(), R.anim.rotate_anim);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.translate_anim_btn:
			// Animation translateAnimation = new TranslateAnimation(0, 200, 0,
			// 200);
			// translateAnimation.setDuration(3000); // 设置动画持续时间
			// 设置动画效果
			// mTranslateTwoBtn.setAnimation(mTranslateAnim);
			// mTranslateAnim.startNow();
			// translateAnimation.startNow(); // 启动动画

			 mTranslateTv.startAnimation(mTranslateAnim);
			// mTranslateMove = 0;
			// mTranslateBtn.postDelayed(runnable, 100);
			break;
		case R.id.translate_anim_two_btn:
			mTranslateTwoBtn.setTranslationY(100f);
			mTranslateTwoBtn.startAnimation(mRotateAnim);
			break;
		default:
			break;
		}
	}

	private int mTranslateMove = 0;
	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			if (mTranslateMove >= 800) {
				return;
			}
			mTranslateBtn.postDelayed(runnable, 100);
			mTranslateTv.setTranslationY(mTranslateMove);
			mTranslateMove += 100;
		}
	};

	private void threadStartAnim() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				mTranslateAnim.start();
			}
		});
		thread.start();
	}
}
