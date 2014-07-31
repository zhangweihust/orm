package com.example.orm.androrm;

import android.R.integer;

import com.orm.androrm.Model;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.IntegerField;
import com.orm.androrm.field.OneToManyField;

public class Author extends Model{
	 protected CharField mName;
	 protected IntegerField mAge;
	 protected CharField mSex;
	 // implicit field, that uses the ForeignKey  on the Book model   
	 public Author() {
		 super();
		 mName = new CharField(80);
		 mAge = new IntegerField();
		 mSex = new CharField(1);
	}
	 
	 public void setmName(String mName) {
		this.mName.set(mName);
	 }
	 
	 public void setmAge(Integer mAge) {
		this.mAge.set(mAge);
	}
	 
	 public void setmSex(String mSex) {
		this.mSex.set(mSex);
	}
}
