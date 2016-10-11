package com.zksn.jilinjiaotong.city.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 城市管理的数据库
 * 
 * @author admin
 * 
 */
public class SQLiteCityManager extends SQLiteOpenHelper {

	public SQLiteCityManager(Context context) {
		super(context, "guanoweather", null, 1);
	}

	public SQLiteCityManager(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table guanoweather(_id integer primary key autoincrement, cityname varchar(20), "
				+ "imageurl varchar(20), weather varchar(20), temp varchar(20),shikuangcode varchar(20),cityCode varchar(20),ishave varchar(20));";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists person;");
	}

}
