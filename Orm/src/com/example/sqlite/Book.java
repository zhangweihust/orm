package com.example.sqlite;


//"(mPrice numeric,mId integer PRIMARY KEY autoincrement,mDate varchar(19),mAuthor integer,mTitle varchar(80),FOREIGN KEY (mAuthor) REFERENCES author (mId) ON DELETE CASCADE)");

public class Book {
	public int mId;
	public double mPrice;
	public String mDate;
	public int mAuthorId;
	public String mTitle;
	
	public Book(){
		
	}
	
	public Book(double price, String date, int authorid, String title){
		mPrice = price;
		mDate = date;
		mAuthorId = authorid;
		mTitle = title;
	}
}
