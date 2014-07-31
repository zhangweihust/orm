package com.example.orm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.orm.androrm.Author;
import com.example.orm.androrm.Book;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;

public class MainActivity extends Activity implements OnClickListener{

	Button btnAndrorm;
	Button btnOrmlite;
	Button btnAndroid;
	Button btnGreenDao;
	
	public static String BOOK = "book";
	public static String AUTHOR = "author";
	public static String SEX_MALE = "M";
	public static String SEX_FEMALE = "F";

	public static int COUNT = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAndroid = (Button) findViewById(R.id.btn_android);
		btnAndrorm = (Button) findViewById(R.id.btn_androrm);
		btnOrmlite = (Button) findViewById(R.id.btn_ormlite);
		btnGreenDao = (Button) findViewById(R.id.btn_greendao);
		btnAndrorm.setOnClickListener(this);
		btnAndroid.setOnClickListener(this);
		btnOrmlite.setOnClickListener(this);
		btnGreenDao.setOnClickListener(this);
		
		
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent ;
		switch (v.getId()) {
		case R.id.btn_androrm:
			intent = new Intent(MainActivity.this, AndrormActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_android:
			intent = new Intent(MainActivity.this, SqliteActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_ormlite:
			intent = new Intent(MainActivity.this, OrmliteActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_greendao:
			intent = new Intent(MainActivity.this, GreenDaoActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	
}
