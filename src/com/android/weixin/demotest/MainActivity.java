package com.android.weixin.demotest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.weixin.demotest.iface.IRead;
import com.android.weixin.demotest.utils.TestClass;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.MemoryFile;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener {

	private final String TAG = getClass().getSimpleName();
	private Button clickBtn, mDisplayBtn, mListBtn, mMyViewBtn;
	private Button mSingleLineBtn, mTrafficStats, mOverflow;
	private Button mThread, mSpinner, mItemType, mLifecycleFra, mLifecycleAct;
	private Button mGcBtn, mViewStateBtn, mTouchPoint, mDBBtn, mFileBtn;
	private Button mViewPagerBtn, mOperatorBtn, mAnimationBtn, mSurfaceViewBtn;
	private Button mClipBtn,mEggFrenzyBtn;

	private Map<Integer, String> mMap = new HashMap<Integer, String>();

	public MainActivity() {
		Log.v(TAG, "new instance mainActivity");
		// ActivityManager
		// WindowManager
		// ContentProvider
		// PackageManager
		// TelephonyManager
		// LocationManager
		// ResourceManager
		// NotificationManager
		// AudioManager
		// FragmentManager
		// InputMethodManager
		// PowerManager
		// LoaderManager 提供异步加载数据
		// DownloadManager
		// PreferenceManager
		
		// ActivityGroup.getLocalActivityManager
		MemoryFile memoryFile;

		HandlerThread thread;

		LruCache lruCache;

		FragmentPagerAdapter f;
		FragmentStatePagerAdapter s;
		ViewPager vp;

		TabActivity tab;
		ActivityGroup group;
		ActionBar bar;
		IntentService intentService;
		FragmentActivity frament;
		ActionBarActivity barActivity;

		String str = Integer.toBinaryString(-10);

		String ss = " ";

		
		if (TextUtils.isEmpty(ss)) {
			Log.v(TAG, "is empty");
		}
		// AsyncTask<Params, Progress, Result>
		// DrawerLayout

	}

	private void getActivityManager(){
		ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
		
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1) ;
		
		(runningTaskInfos.get(0).topActivity).toString();
	}

	Map<String, Object> maps = new TreeMap<>();
	Map<String, Object> mapsTable = new Hashtable<String, Object>();

	List<String> ss = new ArrayList<String>() {

		// 非静态块，在构造函数前执行
		{
			// add("adas");
		}
	};

	private void testJSON() {
		String[] array = new String[] { "（形）重要：～点｜～事｜主～｜紧～。", "（名）重要的内容：纲～｜摘～｜提～。", "希望得到；希望保持：他～了个口琴" };
		JSONObject jsonObj = new JSONObject();
		try {
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < array.length; i++) {
				jsonArray.put(array[i]);
				String ss = jsonArray.getString(i);
				int iii = 0;
			}
			jsonObj.put("means", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String sss = jsonObj.toString();
		if (TextUtils.isEmpty(sss)) {

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		testJSON();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		clickBtn = (Button) findViewById(R.id.click_btn);
		clickBtn.setOnClickListener(this);
		clickBtn.setOnLongClickListener(this);
		mDisplayBtn = (Button) findViewById(R.id.display_click_btn);
		mDisplayBtn.setOnClickListener(this);
		mListBtn = (Button) findViewById(R.id.list_click_btn);
		mListBtn.setOnClickListener(this);
		mMyViewBtn = (Button) findViewById(R.id.my_view_click_btn);
		mMyViewBtn.setOnClickListener(this);
		mSingleLineBtn = (Button) findViewById(R.id.single_line_click_btn);
		mSingleLineBtn.setOnClickListener(this);
		mTrafficStats = (Button) findViewById(R.id.traffic_stats_click_btn);
		mTrafficStats.setOnClickListener(this);
		mOverflow = (Button) findViewById(R.id.overflow_click_btn);
		mOverflow.setOnClickListener(this);
		mThread = (Button) findViewById(R.id.thread_click_btn);
		mThread.setOnClickListener(this);
		mSpinner = (Button) findViewById(R.id.spinner_click_btn);
		mSpinner.setOnClickListener(this);
		mItemType = (Button) findViewById(R.id.list_item_type_btn);
		mItemType.setOnClickListener(this);
		mLifecycleFra = (Button) findViewById(R.id.lifecycle_fragment_btn);
		mLifecycleFra.setOnClickListener(this);
		mLifecycleAct = (Button) findViewById(R.id.lifecycle_Activity_btn);
		mLifecycleAct.setOnClickListener(this);
		mGcBtn = (Button) findViewById(R.id.gc_click_btn);
		mGcBtn.setOnClickListener(this);
		mViewStateBtn = (Button) findViewById(R.id.view_state_click_btn);
		mViewStateBtn.setOnClickListener(this);
		mTouchPoint = (Button) findViewById(R.id.touch_point_click_btn);
		mTouchPoint.setOnClickListener(this);
		mDBBtn = (Button) findViewById(R.id.db_click_btn);
		mDBBtn.setOnClickListener(this);
		mFileBtn = (Button) findViewById(R.id.file_click_btn);
		mFileBtn.setOnClickListener(this);
		mViewPagerBtn = (Button) findViewById(R.id.view_pager_btn);
		mViewPagerBtn.setOnClickListener(this);
		mOperatorBtn = (Button) findViewById(R.id.operator_btn);
		mOperatorBtn.setOnClickListener(this);
		mAnimationBtn = (Button) findViewById(R.id.animation_btn);
		mAnimationBtn.setOnClickListener(this);
		mSurfaceViewBtn = (Button) findViewById(R.id.surface_view_btn);
		mSurfaceViewBtn.setOnClickListener(this);
		mClipBtn = (Button) findViewById(R.id.clip_view_btn);
		mClipBtn.setOnClickListener(this);
		mEggFrenzyBtn = (Button) findViewById(R.id.egg_frenzy_btn);
		mEggFrenzyBtn.setOnClickListener(this);

		Canvas canvas = new Canvas();

		BitmapDrawable bit = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher);

		if (savedInstanceState != null) {
			Log.v(TAG, "savedInstanceState not null");
		}
		Log.v("--->", "Main Activity onCreate");
		
		String str1 = "Epub";
		if(str1 == "Epub".intern()){
			
		}
		
		String str2 = "Epub";
		
		
		final float level;
		level = .111f;
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {		
		super.onRestoreInstanceState(savedInstanceState);
		Log.v("--->", "Main Activity onRestoreInstanceState");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {		
		super.onSaveInstanceState(outState);
		Log.v("--->", "Main Activity onSaveInstanceState");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.click_btn:
			// Intent intent = new Intent(this, WBShareMainActivity.class);
			// startActivityForResult(intent, 100);
			// storageDir();

			String str1 = null;
			Object obj1 = str1;
			String str2 = (String) obj1;

			Object obj = getParams();
			Object[] objs = (Object[]) obj;
			Log.v("-->", "onClick");
			//testHideOverride();
			getActivityManager();
			break;
		case R.id.display_click_btn:
			Intent disIntent = new Intent(this, DisplayActivity.class);
			startActivity(disIntent);
			break;
		case R.id.list_click_btn:
			Intent listIntent = new Intent(this, ListViewActivity.class);
			startActivity(listIntent);
			break;
		case R.id.my_view_click_btn:
			Intent viewIntent = new Intent(this, MyViewActivity.class);
			startActivity(viewIntent);
			break;
		case R.id.single_line_click_btn:
			Intent singleIntent = new Intent(this, SlipListActivity.class);
			startActivity(singleIntent);
			break;
		case R.id.traffic_stats_click_btn:
			Intent trafficIntent = new Intent(this, TrafficStatsActivity.class);
			startActivity(trafficIntent);
			break;
		case R.id.overflow_click_btn:
			Intent overflowIntent = new Intent(this, OverflowActivity.class);
			startActivity(overflowIntent);
			break;
		case R.id.thread_click_btn:
			Intent threadIntent = new Intent(this, ThreadActivity.class);
			startActivity(threadIntent);
			break;
		case R.id.spinner_click_btn:
			Intent spinnerIntent = new Intent(this, SpinnerActivity.class);
			startActivity(spinnerIntent);
			break;
		case R.id.list_item_type_btn:
			Intent itemTypeIntent = new Intent(this, ListViewContentTypeActivity.class);
			startActivity(itemTypeIntent);
			break;
		case R.id.lifecycle_fragment_btn:
			Intent lifecycleFraIntent = new Intent(this, FramentLifecycleActivity.class);
			startActivity(lifecycleFraIntent);
			break;
		case R.id.lifecycle_Activity_btn:
			Intent lifecycleActIntent = new Intent(this, LifecycleActivity.class);
			startActivity(lifecycleActIntent);
			break;
		case R.id.gc_click_btn:
			Intent gcIntent = new Intent(this, GCActivity.class);
			startActivity(gcIntent);
			break;
		case R.id.view_state_click_btn:
			Intent stateIntent = new Intent(this, ViewStateActivity.class);
			startActivity(stateIntent);
			break;
		case R.id.touch_point_click_btn:
			Intent touchIntent = new Intent(this, TouchPointActivity.class);
			startActivity(touchIntent);
			break;
		case R.id.db_click_btn:
			Intent dbIntent = new Intent(this, DBActivity.class);
			startActivity(dbIntent);
			break;
		case R.id.file_click_btn:
			Intent fileIntent = new Intent(this, FileActivity.class);
			startActivity(fileIntent);
			break;
		case R.id.view_pager_btn:
			Intent viewPagerIntent = new Intent(this, ViewPagerActivity.class);
			startActivity(viewPagerIntent);
			break;
		case R.id.operator_btn:
			Intent operatorIntent = new Intent(this, OperatorActivity.class);
			startActivity(operatorIntent);
			break;
		case R.id.animation_btn:
			Intent animationIntent = new Intent(this, AnimationActivity.class);
			startActivity(animationIntent);
			break;
		case R.id.surface_view_btn:
			Intent surfaceViewIntent = new Intent(this, SurfaceViewActivity.class);
			startActivity(surfaceViewIntent);
			break;
		case R.id.clip_view_btn:
			Intent clipIntent = new Intent(this, ClipActivity.class);
			startActivity(clipIntent);
			break;
		case R.id.egg_frenzy_btn:
			Intent eggFrenzyIntent = new Intent(this, EggFrenzyActivity.class);
			startActivity(eggFrenzyIntent);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("TAG", "onActivityResult " + resultCode);
	}

	Object[] objs = getParams();

	private Object[] getParams(Object... params) {
		Object obj = params;
		return (Object[]) obj;
	}

	private void testHideOverride() {
		IRead test = new TestClass();
		test.read();
		File cache = getCacheDir();
		if (cache.exists()) {
			String path = cache.getPath() + File.separator + "aq";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
				File tempFile = new File(path);
				if (tempFile.exists()) {
					boolean bbb = true;
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void sendNotify() {
		NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification mNotification = new Notification();
		mNotification.icon = 0;
		mNotification.tickerText = "抱歉支付失败，请重新支付";
		mNotification.defaults = Notification.DEFAULT_SOUND;
		mNotification.flags = Notification.FLAG_AUTO_CANCEL;
		mNotification.when = System.currentTimeMillis();
		Intent clickIntent = new Intent();
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, clickIntent, PendingIntent.FLAG_ONE_SHOT);
		mNotification.setLatestEventInfo(this, "支付结果通知", "抱歉支付失败，请重新支付", pendingIntent);
		mManager.notify(1, mNotification);
	}

	private void selectImageCrop() {
		// 选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
		// 有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("crop", "true");// 才能出剪辑的小方框，不然没有剪辑功能，只能选取图片
		intent.putExtra("aspectX", 1); // 出现放大和缩小

		startActivityForResult(intent, 2);
	}

	@Override
	public boolean onLongClick(View v) {
		Log.v("-->", "onLongClick");
		return true;
	}

	private void method() {
		// this.recreate();
		// this.startLockTask();
		// this.startManagingCursor(c);
	}
}
