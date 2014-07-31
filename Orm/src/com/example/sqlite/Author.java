package com.example.sqlite;

//db.execSQL("CREATE TABLE IF NOT EXISTS author" +
//"(mId integer PRIMARY KEY autoincrement,mSex varchar(1)	,mAge integer,mName varchar(80))");

public class Author {
	public int mId;
	public String mSex;
	public int mAge;
	public String mName;
	
	
	public Author(){
		
	}
	
	public Author(String sex, int age, String name){
		mSex = sex;
		mAge = age;
		mName = name;
	}
}
