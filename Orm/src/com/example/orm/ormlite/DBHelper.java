package com.example.orm.ormlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper
{
    private static final String TAG = "DatabaseHelper";
    // 数据库名称
    private static final String DATABASE_NAME = "Ormlite.db";
    // 数据库version
    private static final int DATABASE_VERSION = 1;

    private Dao<Book, Integer> bookDao = null;
    private RuntimeExceptionDao<Book, Integer> bookRuntimeDao = null;

    private Dao<Author, Integer> authorDao = null;
    private RuntimeExceptionDao<Author, Integer> authorRuntimeDao = null;
    
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // 可以用配置文件来生成 数据表，有点繁琐，不喜欢用
       // super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * @param context
     * @param databaseName
     * @param factory
     * @param databaseVersion
     */
    public DBHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion)
    {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource)
    {
        try
        {
            //建立User表
            TableUtils.createTable(connectionSource, Book.class);
            //初始化DAO
            bookDao = getBookDao();
            bookRuntimeDao = getBookDataDao();
           	authorDao = getAuthorDao();
            authorRuntimeDao = getAuthorDataDao();
        }
        catch (SQLException e)
        {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, Book.class, true);
            TableUtils.dropTable(connectionSource, Author.class, true);
        }
        catch (SQLException e)
        {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @throws SQLException
     * @throws java.sql.SQLException 
     */
    private Dao<Author, Integer> getAuthorDao() throws SQLException
    {
        if (authorDao == null)
        	authorDao = getDao(Author.class);
        return authorDao;
    }

    public RuntimeExceptionDao<Author, Integer> getAuthorDataDao()
    {
        if (authorRuntimeDao == null)
        {
        	authorRuntimeDao = getRuntimeExceptionDao(Author.class);
        }
        return authorRuntimeDao;
    }
    
    /**
     * @return
     * @throws SQLException
     * @throws java.sql.SQLException 
     */
    private Dao<Book, Integer> getBookDao() throws SQLException
    {
        if (bookDao == null)
        	bookDao = getDao(Book.class);
        return bookDao;
    }

    public RuntimeExceptionDao<Book, Integer> getBookDataDao()
    {
        if (bookRuntimeDao == null)
        {
        	bookRuntimeDao = getRuntimeExceptionDao(Book.class);
        }
        return bookRuntimeDao;
    }
    
    /**
     * 释放 DAO
     */
    @Override
    public void close() {
        super.close();
        bookRuntimeDao = null;
        authorRuntimeDao = null;
    }


}
