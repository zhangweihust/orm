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
@DatabaseTable(tableName = "author")
public class Author {
	    // ���� id ������
	    @DatabaseField(generatedId = true)
	    public int mId;
	    // ӳ��
	    @DatabaseField(canBeNull = false)
	    public String mSex;
	    // ��Ϊ��
	    @DatabaseField(canBeNull = false)
	    public int mAge;
	    
	    @DatabaseField(defaultValue = "")
	    public String mName;
	    
	    public Author() {
	    	
		}
	    
	    public Author(String mSex,int mAge,String mName) {
	    	this.mSex=mSex;
	    	this.mAge=mAge;
	    	this.mName=mName;
		}

}
