package com.example.orm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.sqlite.Author;
import com.example.sqlite.Book;
import com.example.sqlite.DBManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class SqliteActivity extends Activity implements OnClickListener{
	
	Button btnSearch;
	Button btnInsert;
	TextView tvInsertTime;
	TextView tvSearchTime;
	int authorsCount = 20;
	
	DBManager dbManager;
	
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
	
		dbManager = new DBManager(this);
		if(dbManager.queryAuthor().getCount() == 0){
			ArrayList<Author> authors; 	
			authors = new ArrayList<Author>();
			Author author = null;
			for(int i=0; i< authorsCount; i++){
				author = new Author();
				author.mName = MainActivity.AUTHOR + i;
				author.mAge = 34 + i;
				author.mSex = i%2==1?MainActivity.SEX_FEMALE: MainActivity.SEX_MALE;
				authors.add(author);
			}
			dbManager.addAuthors(authors);
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
				book.mDate = (new Date()).toString();
				book.mPrice = 23.5 + i/100;
				book.mTitle = MainActivity.BOOK + i;
				book.mAuthorId = i%20;
				books.add(book);
			}
			dbManager.addBooks(books);
			n = n+MainActivity.COUNT;
			tvInsertTime.setText("insert " + MainActivity.COUNT + " datas used "  + (System.currentTimeMillis()-startTime) + "milliseconds");
			break;
		case R.id.btn_search:
			List<Book> result = dbManager.query();
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
		dbManager.deleteAllDatas();
		dbManager.closeDB();
	}
}
