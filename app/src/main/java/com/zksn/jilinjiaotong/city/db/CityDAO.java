package com.zksn.jilinjiaotong.city.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zksn.jilinjiaotong.city.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityDAO {

	private SQLiteCityManager mHelper;

	public CityDAO(Context context) {
		// 创建数据库
		mHelper = new SQLiteCityManager(context);

		// 获取可读或可写数据库时, 数据库才真正会被创建
		// mHelper.getWritableDatabase();
	}


	public void add(String name, String cityCode, String isHave, String shikuang) {

		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("cityname", name);
		values.put("cityCode", cityCode);
		db.insert("guanoweather", null, values);

	}


	public void delete(String cityname) {
		// 获取可写数据库
		SQLiteDatabase db = mHelper.getWritableDatabase();

		db.execSQL("delete from guanoweather where cityname = '" + cityname
				+ "'");

	}

	public void update(String cityname, String ishave) {
		SQLiteDatabase db = mHelper.getWritableDatabase();

		db.execSQL("update guanoweather set ishave = " + ishave
				+ " where cityname = '" + cityname + "'");
	}


	public City querySingleRecord(String queryName) {
		// select * from person where name = 'zhangsan';

		SQLiteDatabase db = mHelper.getReadableDatabase();
		// 获取一个可读数据库

		Cursor cursor = db.rawQuery(
				"select * from guanoweather where cityname = '" + queryName
						+ "'", null);

		City city = new City();
		// 如果可以移动到第一条数据, 说明查询到了至少一个结果
		if (cursor.moveToFirst()) {
			// getColumnIndex获取指定列名的索引号

			String isHave = cursor.getString(cursor.getColumnIndex("ishave"));

		}
		return city;

	}

	/**
	 * 查询所有数据
	 */
	public List<City> queryAll() {
		// select * from person;
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from guanoweather", null);

		try {
			List<City> cities = new ArrayList<City>();

			while (cursor.moveToNext()) { // 直到移动到最后一条数据后,不能再往下移动了, 循环停止
				// 获取数据
				City p = new City();
				String cityname = cursor.getString(cursor
						.getColumnIndex("cityname"));
				String cityCode = cursor.getString(cursor
						.getColumnIndex("cityCode"));
				String isHave = cursor.getString(cursor
						.getColumnIndex("ishave"));
				String shikuangcode = cursor.getString(cursor
						.getColumnIndex("shikuangcode"));
				p.setCityCode(shikuangcode);
				p.setCityName(cityname);
				cities.add(p);
			}
			return cities;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return null;

	}
}
