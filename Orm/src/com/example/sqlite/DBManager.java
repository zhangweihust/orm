package com.example.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private DBHelper helper;
	private SQLiteDatabase db;
	
	public DBManager(Context context) {
		helper = new DBHelper(context);
		//因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
		//所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}
	
	/**
	 * add authors
	 * @param authors
	 */
	public void addAuthors(List<Author> authors) {
        db.beginTransaction();	//开始事务
        try {
        	for (Author author : authors) {
        		db.execSQL("INSERT INTO author VALUES(null, ?, ?, ?)", new Object[]{author.mSex, author.mAge, author.mName});
        	}
        	db.setTransactionSuccessful();	//设置事务成功完成
        } finally {
        	db.endTransaction();	//结束事务
        }
	}
	
	/**
	 * add books
	 * @param books
	 */
	public void addBooks(List<Book> books) {
        db.beginTransaction();	//开始事务
        try {
        	for (Book book : books) {
        		db.execSQL("INSERT INTO book VALUES(null, ?, ?, ?,?)", new Object[]{book.mPrice, book.mDate, book.mAuthorId, book.mTitle});
        	}
        	db.setTransactionSuccessful();	//设置事务成功完成
        } finally {
        	db.endTransaction();	//结束事务
        }
	}
	
	/**
	 * query all persons, return list
	 * @return List<Person>
	 */
	public List<Book> query() {
		ArrayList<Book> books = new ArrayList<Book>();
		Cursor c = queryTheCursor();
        while (c.moveToNext()) {
        	Book book = new Book();
        	book.mId = c.getInt(c.getColumnIndex("mId"));
        	book.mPrice = c.getFloat(c.getColumnIndex("mPrice"));
        	book.mAuthorId = c.getInt(c.getColumnIndex("mAuthor"));
        	book.mDate = c.getString(c.getColumnIndex("mDate"));
        	book.mTitle = c.getString(c.getColumnIndex("mTitle"));
        	books.add(book);
        }
        c.close();
        return books;
	}
	
	/**
	 * query all persons, return cursor
	 * @return	Cursor
	 */
	public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT book.mId, mTitle, book.mAuthor, mPrice, mDate FROM book, author where book.mAuthor = author.mId AND ( author.mName = \"author1\" OR author.mName = \"author2\")", null);
        return c;
	}
	
	/**
	 * query all persons, return cursor
	 * @return	Cursor
	 */
	public Cursor queryAuthor() {
        Cursor c = db.rawQuery("SELECT * FROM author", null);
        return c;
	}
	
	public void deleteAllDatas(){
		db.delete("book", null, null);
	}
	
	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}
}

