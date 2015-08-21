package com.android.weixin.demotest;

import java.io.File;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DBActivity extends Activity implements OnClickListener {

	private final String DB_NAME = "DEMO.db";
	private final String DB_EX_NAME = "DEMO_EX.DB";
	private final String DB_EX_PATH = "/mnt/sdcard/Android/data/com.example.demotest/databases/";

	private Button mCreateDB;
	private Button mFindDBPath;
	private SQLiteDatabase mSqlIte;
	private SQLiteOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_layout);
		mCreateDB = (Button) findViewById(R.id.create_db_btn);
		mCreateDB.setOnClickListener(this);
		mFindDBPath = (Button) findViewById(R.id.find_db_path_btn);
		mFindDBPath.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.create_db_btn:
			createDB();
			break;
		case R.id.find_db_path_btn:
			findDBPath();
			break;
		default:
			break;
		}
	}

	private void createDB() {
		openOrCreateDatabase(DB_NAME, 0, null);
		openOrCreateDatabase(DB_EX_PATH + DB_EX_NAME, 0, null);
	}

	private void findDBPath() {
		File dbFile = getDatabasePath(DB_NAME);
		String dbPath = dbFile.getPath();
		Log.i("-->", "db path：" + dbPath);
		File exDBFile = getDatabasePath(DB_EX_PATH + DB_EX_NAME);
		String exDBPath = exDBFile.getPath();
		Log.i("-->", "external db path：" + exDBPath);
	}
}
