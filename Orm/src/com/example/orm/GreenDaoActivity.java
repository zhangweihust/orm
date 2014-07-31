package com.example.orm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.orm.greendao.Author;
import com.example.orm.greendao.AuthorDao;
import com.example.orm.greendao.Book;
import com.example.orm.greendao.BookDao;
import com.example.orm.greendao.DaoMaster;
import com.example.orm.greendao.DaoSession;
import com.example.orm.greendao.DaoMaster.DevOpenHelper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class GreenDaoActivity extends Activity implements OnClickListener{
	
	Button btnSearch;
	Button btnInsert;
	TextView tvInsertTime;
	TextView tvSearchTime;
	int authorsCount = 20;
	
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private BookDao bookDao;
    private AuthorDao authorDao;
	
	
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
		
        DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        DaoMaster.createAllTables(db, true);
        
        daoSession = daoMaster.newSession();
        bookDao = daoSession.getBookDao();
        authorDao = daoSession.getAuthorDao();
		
		if(authorDao.queryBuilder().buildCount().count()==0){
			ArrayList<Author> authors; 	
			authors = new ArrayList<Author>();
			Author author = null;
			for(int i=0; i< authorsCount; i++){
				author = new Author();
				author.setName(MainActivity.AUTHOR + i);
				author.setAge(34 + i);
				author.setSex(i%2==1?Boolean.TRUE: Boolean.FALSE);
				authors.add(author);
				authorDao.insert(author);
			}
			
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Long startTime = System.currentTimeMillis();
		switch (v.getId()) {
		case R.id.btn_insert:
			ArrayList<Book> books = new ArrayList<Book>();
			Book book = null;
			for(int i=n; i< n+MainActivity.COUNT; i++){
				book = new Book();
				book.setDate(new Date());
				book.setPrice(23.5 + i/100);
				book.setTitle(MainActivity.BOOK + i);
				book.setAuthorId(i);
				books.add(book);
//				bookDao.insert(book);
			}
			bookDao.insertInTx(books);
			n = n+MainActivity.COUNT;
			tvInsertTime.setText("insert " + MainActivity.COUNT + " datas used "  + (System.currentTimeMillis()-startTime) + "milliseconds");
			break;
		case R.id.btn_search:
			List<Book> result = bookDao.queryBuilder().list();
			System.out.println(result.size() + "");
			tvSearchTime.setText("search in " + n + " datas used "  + (System.currentTimeMillis()-startTime) + "milliseconds");
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
