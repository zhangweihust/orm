package com.example.orm.androrm;

import java.util.Date;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.DateField;
import com.orm.androrm.field.DoubleField;
import com.orm.androrm.field.ForeignKeyField;
import com.orm.androrm.field.OneToManyField;

public class Book extends Model {

    // fields visibility is set to protected,
    // so the Model class can access it
    protected CharField mTitle;
    protected DateField mDate;
    protected DoubleField mPrice;
    
    // Link the Author model to the Book model.
    protected ForeignKeyField<Author> mAuthor;
    
    // zero-argument constructor, that can be called 
    // by androrm, to gather field information
    public Book() {
    	super();
    	// set up field with maxLength parameter
    	mTitle = new CharField(80);
    	mDate = new DateField();
    	mPrice = new DoubleField();
    	// initialize the foreign key relation
    	mAuthor = new ForeignKeyField<Author>(Author.class);
   	}
    
    public void setmAuthor(Author mAuthor) {
		this.mAuthor.set(mAuthor);
	}
    
    public void setmTitle(String mTitle) {
		this.mTitle.set(mTitle);
	}
    
    public void setmDate(Date mDate) {
		this.mDate.set(mDate);
	}
  
    public void setmPrice(Double mPrice) {
		this.mPrice.set(mPrice);
	}
    
    public static final QuerySet<Book> objects(Context context) {
    	return objects(context, Book.class);
    }
}