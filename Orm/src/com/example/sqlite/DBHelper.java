package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sqlite_database.db";
	private static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		//CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//���ݿ��һ�α�����ʱonCreate�ᱻ����
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS book" +
				"(mId integer PRIMARY KEY autoincrement,mPrice double,mDate varchar(19),mAuthor integer,mTitle varchar(80),FOREIGN KEY (mAuthor) REFERENCES author (mId) ON DELETE CASCADE)");
		db.execSQL("CREATE TABLE IF NOT EXISTS author" +
				"(mId integer PRIMARY KEY autoincrement,mSex varchar(1)	,mAge integer,mName varchar(80))");
		
		
	}

	//���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
	}
}

