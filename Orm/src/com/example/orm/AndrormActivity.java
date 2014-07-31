package com.example.orm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.orm.androrm.Author;
import com.example.orm.androrm.Book;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;

public class AndrormActivity extends Activity implements OnClickListener{
	
	Button btnSearch;
	Button btnInsert;
	TextView tvInsertTime;
	TextView tvSearchTime;
	Author[] authors; 
	int authorsCount = 20;
	
	DatabaseAdapter adapter;
	
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
		
		//����Androrm��ݿ�		
		List<Class<? extends Model>> models = new ArrayList<Class<? extends Model>>();
		models.add(Author.class);
		models.add(Book.class);                                                     
		DatabaseAdapter.setDatabaseName("androrm_database");
		adapter = DatabaseAdapter.getInstance(getApplicationContext());
		adapter.setModels(models);
		authors = new Author[authorsCount];
		for(int i=0; i< authorsCount; i++){
			authors[i] = new Author();
			authors[i].setmName(MainActivity.AUTHOR + i);
			authors[i].setmAge(34+i);
			authors[i].setmSex(i%2==1?MainActivity.SEX_FEMALE: MainActivity.SEX_MALE);
			authors[i].save(this);
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Long startTime = System.currentTimeMillis();
		switch (v.getId()) {
		case R.id.btn_insert:
			Book book = null;
			adapter.beginTransaction();
			for(int i=n; i< n+MainActivity.COUNT; i++){
				book = new Book();
				book.setmDate(new Date());
				book.setmPrice(23.5 + i/100);
				book.setmTitle(MainActivity.BOOK + i);
				book.setmAuthor(authors[i%20]);
				book.save(this);
			}
			adapter.commitTransaction();
			n = n+MainActivity.COUNT;
			tvInsertTime.setText("insert " + MainActivity.COUNT + " datas used "  + (System.currentTimeMillis()-startTime) + "milliseconds");
			break;
		case R.id.btn_search:
			List<String> values = Arrays.asList(new String[] {
				    "author1",
				    "author2"
				});

				Filter filter = new Filter();
				filter.in("mAuthor__mName", values);
				QuerySet<Book> books = Book.objects(getApplicationContext()).filter(filter);
				
				System.out.println(books.count() + "");
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
		DatabaseAdapter adapter = DatabaseAdapter.getInstance(getApplicationContext());
		adapter.drop(MainActivity.AUTHOR);
		adapter.drop(MainActivity.BOOK);
		adapter.close();
	}
}
