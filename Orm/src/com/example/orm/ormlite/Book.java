package com.example.orm.ormlite;

import android.R.fraction;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 数据库对应的book类，注意一下三点
 * 1、填写表的名称 @DatabaseTable
 * 2、填写表中持久化项的 @DatabaseField 还可使顺便设置其属性
 * 3、保留一个无参的构造函数
 */
//表名称
@DatabaseTable(tableName = "book")
public class Book {
	    // 主键 id 自增长
	    @DatabaseField(generatedId = true)
	    public int mId;
	    // 映射
	    @DatabaseField(canBeNull = false)
	    public double mPrice;
	    // 不为空
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
