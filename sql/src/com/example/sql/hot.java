package com.example.sql;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class hot extends Activity {
	public static final String key_rowid="_id";
	public static final String key_name="persons_name";
	public static final String key_hotness="persons_hotness";
	
	
	private static final String database_name= "hot";
	private static final String database_table= "peopletable";
	private static final int database_version= 1;
	
	private DbHelper ourhelper;
	private final Context ourcontext;
	private SQLiteDatabase ourdatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context,database_name , null, database_version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + database_table + "(" +
						key_rowid +" INTEGER PRIMARY KEY AUTOINCREMENT , " +
					   key_name + " TEXT NOT NULL ," + 
						key_hotness + " TEXT NOT NULL"+ ")" );
			}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS" + database_table );
		onCreate(db);
			
		}
		
	
		}
	public hot(Context c){
		ourcontext = c;	
	}
	
	public hot open() throws SQLException{
	ourhelper = new DbHelper(ourcontext);
	ourdatabase = ourhelper.getWritableDatabase();
	return this;
	}
	
	public void close(){
		ourhelper.close();
	}

	public long createEntry(String name, String hot) {
		ContentValues cv= new ContentValues();
		cv.put(key_name, name);
		cv.put(key_hotness, hot);
		return(ourdatabase.insert(database_table, null, cv));
	}

	public String getData() {
		// TODO Auto-generated method stub
		
		String columns[] =new String[]{ key_rowid, key_name, key_hotness};
		
		Cursor c = ourdatabase.query(database_table, columns, null, null, null , null ,  null);
		String result = "";
		int irow = c.getColumnIndex(key_rowid);
		int iname = c.getColumnIndex(key_name);
		int ihot = c.getColumnIndex(key_hotness);
		
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext()){
			result = result + c.getString(irow) + " " + c.getString(iname) + " " + c.getString(ihot) + "\n";
		}
		return result;
	}



	public String getHot(long l) {
		String columns[] =new String[]{ key_rowid, key_name, key_hotness};
		Cursor c =ourdatabase.query(database_table, columns, key_rowid + "=" + l, null, null, null, null);
		if(c!=null)
		{
			c.moveToFirst();
			String hotness= c.getString(2);
			return hotness;
		}
	
		return null;
	}

	public String getName(long l) {
		String columns[] =new String[]{ key_rowid, key_name, key_hotness};
		Cursor c =ourdatabase.query(database_table, columns, key_rowid + "=" + l, null, null, null, null);
		if(c!=null)
		{
			c.moveToFirst();
			String name= c.getString(1);
			return name;
		}
		
		return null;
	}

	public void update(long srow, String sname, String shot) {
		ContentValues cvup= new ContentValues();
		cvup.put(key_name, sname);
		cvup.put(key_hotness, shot);
		ourdatabase.update(database_table, cvup, key_rowid + "=" + srow, null);
	}

	public void deleteentry(long lrow1) {
		ourdatabase.delete(database_table, key_rowid + "=" + lrow1, null);
		
	}
}
