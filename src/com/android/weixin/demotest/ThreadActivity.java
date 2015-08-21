package com.android.weixin.demotest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 线程及网络请求测试
 * 
 * @author weixin
 * 
 */
public class ThreadActivity extends Activity implements OnClickListener {

	private static String tag = "HttpConnectApi";
	private static String CHARSET_UTF8 = HTTP.UTF_8;
	// private static final int MAXLOGSIZE = 1000;
	private static int TIMEOUT_CONNECTION = 40 * 1000; // 连接超时
	private static int TIMEOUT_SOCKET = 35 * 1000; // 返回超时
	private static final int BUFFER_SIZE = 8 * 1024;

	private String[] paths = { "http://www.baidu.com/", "http://www.qq.com/",
			"http://www.163.com/", "http://www.ifeng.com/",
			"http://www.sina.com.cn/" };
	private FrameLayout mCollectRoot;
	private Button mExecuteBtn, mPost;
	private Button mWaitVar;
	private TextView mResult;
	private Thread mThread;
	private HttpClient mHttpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threader_lay);

		mExecuteBtn = (Button) findViewById(R.id.execute_thread_btn);
		mExecuteBtn.setOnClickListener(this);
		mPost = (Button) findViewById(R.id.post_thread_btn);
		mPost.setOnClickListener(this);
		mWaitVar = (Button) findViewById(R.id.var_wait_btn);
		mWaitVar.setOnClickListener(this);
		mResult = (TextView) findViewById(R.id.result_tv);
		mCollectRoot = (FrameLayout) findViewById(R.id.collect_root);
		initInstance();
	}

	private void initInstance() {
		if (mHttpClient == null) {
			mHttpClient = getNewHttpClient();
		}
	}

	public static DefaultHttpClient getNewHttpClient() {
		try {
			HttpParams params = new BasicHttpParams();
			// 设置一些基本参数
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET_UTF8);
			HttpProtocolParams.setUseExpectContinue(params, true);
			String userAgent = getUserAgent();
			HttpProtocolParams.setUserAgent(params, userAgent);
			Log.i(tag, "setUserAgent : " + userAgent);
			/* 连接超时 */
			HttpConnectionParams.setConnectionTimeout(params,
					TIMEOUT_CONNECTION);
			/* 读取超时 */
			HttpConnectionParams.setSoTimeout(params, TIMEOUT_SOCKET);
			/* Socket 缓存大小 */
			HttpConnectionParams.setSocketBufferSize(params, 8192);
			// 设置重定向，缺省为 true
			HttpClientParams.setRedirecting(params, true);
			// 设置我们的HttpClient支持HTTP和HTTPS两种模式
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));
			// 使用线程安全的连接管理来创建HttpClient
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
					params, schReg);
			return new DefaultHttpClient(conMgr, params);

		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	/**
	 * 设置用户代理信息
	 * 
	 * @param mContext
	 * @return
	 */
	private static String getUserAgent() {
		StringBuilder ua = new StringBuilder("MyAndroidFrame");
		// ua.append('/' + info.versionName + '_' + info.versionCode);// App版本
		ua.append("/Android");// 手机系统平台
		ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
		ua.append("/" + android.os.Build.MODEL); // 手机型号
		return ua.toString();
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
		case R.id.execute_thread_btn:
			Thread mThread = new Thread(mRun);

			// 判断当前线程是否已启动
			if (!mThread.isAlive()) {
				mThread.start();
			} else {
				Toast.makeText(ThreadActivity.this, "当前线程已启动",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.post_thread_btn:
			if (mCollectRoot.getChildCount() == 0) {
				Thread createView = new Thread(threadCreateView);
				createView.start();
			} else {
				final LinearLayout postll = (LinearLayout) mCollectRoot
						.getChildAt(0);
				postll.post(new Runnable() {

					@Override
					public void run() {
						if (Thread.currentThread() == Looper.getMainLooper()
								.getThread()) {
							TextView text = (TextView) postll.getChildAt(0);
							text.setText("321");
						}
					}
				});
			}
			break;
		case R.id.thread_post_btn:
			if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
				mResult.setText("post thread");
				Thread thre = new Thread(new Runnable() {

					@Override
					public void run() {
						Button btn = (Button) v;
						btn.setText("...");
					}
				});
				thre.start();
			}
			break;
		case R.id.var_wait_btn:
			for (int i = 0; i < 10; i++) {
				finalTest(i);
			}
			break;
		default:
			break;
		}

	}

	Runnable mRun = new Runnable() {

		@Override
		public void run() {

			for (int i = 0; i < paths.length; i++) {
				try {
					Thread.sleep(500);
					final int index = i;
					new Thread(new Runnable() {

						@Override
						public void run() {

							Log.i("-->", "当前执行项：" + (index + 1));
							try {
								HttpGet httpGet = new HttpGet(paths[index]);
								HttpResponse response = mHttpClient
										.execute(httpGet);
								HttpEntity httpEntity = response.getEntity();
								InputStream is = httpEntity.getContent();

								byte[] responseByteArray = new byte[BUFFER_SIZE];
								ByteArrayBuffer bab = new ByteArrayBuffer(
										BUFFER_SIZE);
								int line = -1;
								while ((line = is.read(responseByteArray)) != -1) {
									bab.append(responseByteArray, 0, line);
									responseByteArray = new byte[BUFFER_SIZE];
								}
								String res = new String(bab.toByteArray(),
										"UTF-8");
								Log.i("-->", "当前执行项：" + (index + 1) + " 结果："
										+ res);
							} catch (ClientProtocolException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									Toast.makeText(ThreadActivity.this,
											"当前线程已执行完成", Toast.LENGTH_SHORT)
											.show();
								}
							});

						}
					}).start();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private Runnable threadCreateView = new Runnable() {

		@Override
		public void run() {
			threadInitView();
		}
	};

	/**
	 * Java和C#一样，局部变量是线程所私有的
	 * @param params
	 */
	private void finalTest(final int params) {
		Log.v("-->", "params index：" + params);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				long threadId = Thread.currentThread().getId();
				Log.v("-->", "thread id：" + threadId + " params：" + params);
				try {
					int sleepTime = 1000 * ((params % 3) + 1);
					Log.v("-->", "sleep time：" + sleepTime);
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Log.v("-->", "wait thread id：" + threadId + " params index："
						+ params);
			}
		});
		thread.start();
	}

	/**
	 * <p>
	 * View可以由子线程来创建，并可由子线程来操作. 但是由主线程创建的View，子线程并不能来操作该View.
	 * 子线程创建的View，主线程可以操作. 子线程创建的View可以添加至界面中. <br/>
	 * 
	 * View.post如果该View没有添加至界面时，是无法执行的。只有添加至界面才可以执行。<br/>
	 * 
	 * 由子线程创建的View，View.post也是post至主线程中<br/>
	 * 
	 * 由子线程创建的View，在没有加进界面时，可以由子线程修改。 但是如果已经加入界面后，子线程将不能在进行修改，点击的事件回调，也是由主线程来执行。
	 * </P>
	 */
	private void threadInitView() {
		if (mCollectRoot.getChildCount() > 0)
			return;
		final LinearLayout li = new LinearLayout(ThreadActivity.this);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		li.setLayoutParams(params);
		final Button btn = new Button(ThreadActivity.this);
		LinearLayout.LayoutParams liParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btn.setLayoutParams(liParams);
		btn.setText("123");
		btn.setId(R.id.thread_post_btn);
		btn.setOnClickListener(this);
		li.addView(btn);
		// 未加入界面中，所以不会被执行。
		li.post(new Runnable() {

			@Override
			public void run() {
				btn.setText("123123");
			}
		});
		mExecuteBtn.post(new Runnable() {

			@Override
			public void run() {
				if (Thread.currentThread() == Looper.getMainLooper()
						.getThread()) {
					mCollectRoot.addView(li);
				}
			}
		});

	}

}
