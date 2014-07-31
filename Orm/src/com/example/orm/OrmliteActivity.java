package com.example.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.orm.ormlite.Author;
import com.example.orm.ormlite.Book;
import com.example.orm.ormlite.DBHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.SelectArg;


public class OrmliteActivity extends OrmLiteBaseActivity<DBHelper> implements OnClickListener{
	
	Button btnSearch;
	Button btnInsert;
	TextView tvInsertTime;
	TextView tvSearchTime;
	int authorsCount = 20;
	
	private RuntimeExceptionDao<Book, Integer> mBookDAO;

	private RuntimeExceptionDao<Author, Integer> mAuthorDao;
	int n = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_androrm);
		
		btnSearch = (Button) findViewById(R.id.btn_search);
		btnInsert = (Button) findViewById(R.id.btn_insert);
		tvInsertTime = (TextView) findViewById(R.id.tv_inserttime);
		tvSearchTime = (TextView) findViewById(R.id.tv_searchtime);
		
		btnSearch.setOnClickListener(this);
		btnInsert.setOnClickListener(this);
		
		mBookDAO = getHelper().getBookDataDao();
		
		mAuthorDao = getHelper().getAuthorDataDao();
		if(mAuthorDao.queryForAll().size() == 0){
			Author author = null;
			for(int i=0; i< authorsCount; i++){
				author = new Author();
				author.mName = MainActivity.AUTHOR + i;
				author.mAge = 34 + i;
				author.mSex = i%2==1?MainActivity.SEX_FEMALE: MainActivity.SEX_MALE;
				mAuthorDao.createIfNotExists(author);
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Long startTime = System.currentTimeMillis();
		switch (v.getId()) {
		case R.id.btn_insert:
			Book book = null;
			for(int i=n; i< n+MainActivity.COUNT; i++){
				book = new Book();
				book.mDate = (new Date()).toString();
				book.mPrice = 23.5 + i/100;
				book.mTitle = MainActivity.BOOK + i;
				book.mAuthorId = i%20;
				mBookDAO.createIfNotExists(book);
			}
			n = n+MainActivity.COUNT;
			tvInsertTime.setText("insert " + MainActivity.COUNT + " datas used "  + (System.currentTimeMillis()-startTime) + "milliseconds");
			break;
		case R.id.btn_search:
			/*mAuthorDao.queryBuilder().where().in("mName", "author1", "author2");
			SelectArg userSelectArg = new SelectArg();
			mAuthorDao.queryBuilder().selectColumns(arg0)
			List<Book> result =  mBookDAO.queryBuilder().where().eq("mAuthorId", ).query();

			System.out.println(result.size() + "");
			tvSearchTime.setText("search in " + n + " datas used "  + (System.currentTimeMillis()-startTime) + "milliseconds");
			*/break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		deleteAll();
	}
	
	/**
     * 删除全部
     */
    private void deleteAll()
    {
        mBookDAO.delete(queryAll());
    }
    
    /**
     * 查询所有的
     */
    private List<Book> queryAll()
    {
        List<Book> users = mBookDAO.queryForAll();
        return users;
    }


}
