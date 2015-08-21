package com.android.weixin.demotest.utils;

import java.util.ArrayList;
import java.util.List;

import com.android.weixin.demotest.iface.IRead;

import android.util.Log;

public class TestClass extends Read implements IRead{

	public List<String> list = new ArrayList<String>();

	public static List<String> staticList = new ArrayList<String>();

	public TestClass() {
		Log.v("TAG", "invoke TestClass 构造函数");
		if (list != null) {
			Log.v("TAG", "list不为空，所以全局变量在构造函数前Invoke");
		} else {
			Log.v("TAG", "list等于空");
		}
	}

	{
		Log.v("ATG", "非静态块执行");
		if (list != null) {
			Log.v("TAG", "非静态块list不等空");
		} else {
			Log.v("TAG", "非静态块list等于空");
		}

		if (staticList != null) {
			Log.v("TAG", "staticList不等于空");
		} else {
			Log.v("TAG", "staticList等于空");
		}
	}

	static {
		Log.v("ATG", "静态块1执行");
		if (staticList != null) {
			Log.v("TAG", "静态变量比静态块早执行");
		} else {
			Log.v("TAG", "静态变量比静态块晚执行");
		}
	}

	static {
		Log.v("ATG", "静态块2执行");
	}

	{
		Log.v("TAG", "非静态块2");
	}

	
	public void read() {
		Log.i("-->", "Test Class Method read");
	}
	
}
