package com.android.weixin.demotest;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FileActivity extends Activity implements OnClickListener {

	private static final int ERROR = -1;

	private Button mRootDir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_layout);
		mRootDir = (Button) findViewById(R.id.root_path_btn);
		mRootDir.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		getInRootPath();
		getExRootPath();
		getRootPath();
	}

	/**
	 * 获取内部存储根目录
	 */
	private void getInRootPath() {
		File inRootFile = Environment.getDataDirectory();
		File cachefile = getCacheDir();
		Log.v("-->", "internal store root dir：" + inRootFile.getPath());
		Log.v("-->", "internal store cache dir：" + cachefile.getPath());
	}

	/**
	 * 获取外部存储根目录
	 */
	private void getExRootPath() {
		if (externalMemoryAvailable()) {
			File exRootFile = Environment.getExternalStorageDirectory();
			File cacheFile = getExternalCacheDir(); 
			Log.i("-->", "external store root dir：" + exRootFile.getPath());
			Log.i("-->", "external store cache dir：" + cacheFile.getPath());
		}else{
			Log.i("-->", "un external store");
		}
	}
	
	private void getRootPath(){
		File rootFile = Environment.getRootDirectory();
		String dirPath = rootFile.getPath();
		Log.i("-->", "Root Directory PATH " + dirPath);
	}

	public void storageDir() {
		try {
			File rootFile = Environment.getDataDirectory();// 获取手机根目录
			File file1 = new File(rootFile.getAbsoluteFile() + "/INTERNAL");
			boolean issucceed1 = false;
			issucceed1 = file1.mkdirs();
			File external = Environment.getExternalStorageDirectory();// 获取SD卡根目录
			File file2 = new File(external, "external.txt");
			boolean issucceed2 = file2.createNewFile();

			File file3 = this.getFilesDir();
			String path3 = file3.getAbsolutePath();

			File file4 = this.getExternalCacheDir();
			String path4 = file4.getAbsolutePath();

			Log.v("TAG", file1.getAbsolutePath() + " " + issucceed1);
			Log.v("TAG", file2.getAbsolutePath() + " " + issucceed2);
			Log.v("TAG", path3);
			Log.v("TAG", path4);

			Log.v("TAG", "获取手机内部剩余存储空间" + " " + getAvailableInternalMemorySize());
			Log.v("TAG", "获取手机内部总的存储空间" + " " + getTotalInternalMemorySize());
			Log.v("TAG", "获取SDCARD剩余存储空间" + " " + getAvailableExternalMemorySize());
			Log.v("TAG", "获取SDCARD总的存储空间" + " " + getTotalExternalMemorySize());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * SDCARD是否存在
	 */
	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取手机内部剩余存储空间
	 * 
	 * @return
	 */
	public static long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 获取手机内部总的存储空间
	 * 
	 * @return
	 */
	public static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * 获取SDCARD剩余存储空间
	 * 
	 * @return
	 */
	public static long getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return ERROR;
		}
	}

	/**
	 * 获取SDCARD总的存储空间
	 * 
	 * @return
	 */
	public static long getTotalExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return ERROR;
		}
	}
}
