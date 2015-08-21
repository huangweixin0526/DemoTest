/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.weixin.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * 该类是分享功能的入口。
 * 
 * @author SINA
 * @since 2013-09-29
 */
public class WBShareMainActivity extends Activity implements
		IWeiboHandler.Response {

	/** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
	public static final String APP_KEY = "2045436852";

	public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";

	/** 微博分享的接口实例 */
	private IWeiboShareAPI mWeiboShareAPI;

	/** 微博 ALL IN ONE 分享按钮 */
	private Button mShareAllInOneButton;

	/**
	 * @see {@link Activity#onCreate}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_share_layout);
		initialize();
	}

	/**
	 * 初始化 UI 和微博接口实例 。
	 */
	private void initialize() {

		// 创建微博 SDK 接口实例
		mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, APP_KEY);

		// 获取微博客户端相关信息，如是否安装、支持 SDK 的版本
		boolean isInstalledWeibo = mWeiboShareAPI.isWeiboAppInstalled();
		int supportApiLevel = mWeiboShareAPI.getWeiboAppSupportAPI();

		mWeiboShareAPI.registerApp();

		// 设置ALL IN ONE分享按钮对应回调
		mShareAllInOneButton = (Button) findViewById(R.id.share_to_weibo_all_in_one);
		mShareAllInOneButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// shareWeiboClient();
				// shareWeibo();
				setResult(101);
				Log.v("TAG", "FINISH");
				finish();
			}
		});
	}

	private void shareWeiboClient() {
		WeiboMultiMessage weiboMessage = new WeiboMultiMessage();// 初始化微博的分享消息

		weiboMessage.textObject = getTextObj();
		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		request.transaction = String.valueOf(System.currentTimeMillis());
		request.multiMessage = weiboMessage;
		mWeiboShareAPI.sendRequest(this, request);
	}

	private void shareWeibo() {

		// 2. 初始化从第三方到微博的消息请求
		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		// 用transaction唯一标识一个请求
		request.transaction = String.valueOf(System.currentTimeMillis());
		request.multiMessage = getWeiboMessage();

		AuthInfo authInfo = new AuthInfo(this, APP_KEY, REDIRECT_URL, SCOPE);
		// Oauth2AccessToken accessToken =
		// AccessTokenKeeper.readAccessToken(getApplicationContext());
		String token = "";
		// if (accessToken != null) {
		// token = accessToken.getToken();
		// }
		mWeiboShareAPI.sendRequest(this, request, authInfo, token,
				new WeiboAuthListener() {

					@Override
					public void onWeiboException(WeiboException arg0) {
						Toast.makeText(getApplicationContext(),
								"onWeiboException", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete(Bundle bundle) {
						Toast.makeText(getApplicationContext(), "onComplete",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onCancel() {
						Toast.makeText(getApplicationContext(), "onCancel",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	private WeiboMultiMessage getWeiboMessage() {
		WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
		weiboMessage.textObject = getTextObj();
		return weiboMessage;
	}

	/**
	 * 创建文本消息对象。
	 * 
	 * @return 文本消息对象。
	 */
	private TextObject getTextObj() {

		TextObject textObject = new TextObject();
		textObject.text = "测试";
		return textObject;
	}

	@Override
	public void onResponse(BaseResponse arg0) {

	}
}
