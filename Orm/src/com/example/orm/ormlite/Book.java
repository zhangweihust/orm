package com.example.orm.ormlite;

import android.R.fraction;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ���ݿ��Ӧ��book�࣬ע��һ������
 * 1����д������� @DatabaseTable
 * 2����д���г־û���� @DatabaseField ����ʹ˳������������
 * 3������һ���޲εĹ��캯��
 */
//������
@DatabaseTable(tableName = "book")
public class Book {
	    // ���� id ������
	    @DatabaseField(generatedId = true)
	    public int mId;
	    // ӳ��
	    @DatabaseField(canBeNull = false)
	    public double mPrice;
	    // ��Ϊ��
	    @DatabaseField(canBeNull = false)
	    public String mDate;
	    
	    @DatabaseField(defaultValue = "")
	    public int mAuthorId ;
	    
	    @DatabaseField(canBeNull = false)
	    public String mTitle;
	    
	    public Book() {
			// TODO Auto-generated constructor stub
		}

}
