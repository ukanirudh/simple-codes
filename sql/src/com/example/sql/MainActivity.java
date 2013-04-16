package com.example.sql;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	EditText e;
	EditText e1;
	Button update;
	Button view;
	Button getin,delete,edit;
	EditText row;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e= (EditText) findViewById(R.id.et1);
		e1 =(EditText) findViewById(R.id.et2);
		update= (Button) findViewById(R.id.b1);
		view= (Button) findViewById(R.id.b2);
		row = (EditText) findViewById(R.id.row);
		getin=(Button) findViewById(R.id.getinfo);
		delete=(Button) findViewById(R.id.deleteent);
		edit=(Button) findViewById(R.id.editent);
		
		update.setOnClickListener(this);
		view.setOnClickListener(this);
		e1.setOnClickListener(this);
		e.setOnClickListener(this);
		row.setOnClickListener(this);
		getin.setOnClickListener(this);
		delete.setOnClickListener(this);
		edit.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.b1:
			boolean works = true;
			try{
			String name =e.getEditableText().toString();
			String hot = e1.getText().toString();
			hot entry = new hot(MainActivity.this);
			entry.open();
			entry.createEntry(name,hot);
			entry.close();
			}catch(Exception e)
			{
				works=false;
				String error = e.toString();
				Dialog	d =new Dialog(this);
				d.setTitle("HECK noo");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
				
				
			}finally{
				if(works)
				{
				Dialog	d =new Dialog(this);
				d.setTitle("HECK YYAA");
				TextView tv = new TextView(this);
				tv.setText("success");
				d.setContentView(tv);
				d.show();
				}
				
			}
			break;
		case R.id.b2:
			Intent i = new Intent("com.example.sql.sqlview");
			startActivity(i);
			break;
		
			
		case R.id.getinfo:	
			String s=row.getText().toString();
			long l = Long.parseLong(s);
			hot hon = new hot(this);
			hon.open();
			String rename= hon.getName(l);
			String rehot = hon.getHot(l);
			hon.close();
			
			e.setText(rename);
			e1.setText(rehot);
			
			break;
			
			
		case R.id.editent:
			String sname =e.getEditableText().toString();
			String shot = e1.getText().toString();
			String srow=row.getText().toString(); 
			long lrow=Long.parseLong(srow);
			
			hot ex= new hot(this);
			ex.open();
			
			ex.update(lrow, sname, shot);
			ex.close();
			break;
			
		case R.id.deleteent:	
			String srow1=row.getText().toString(); 
			long lrow1=Long.parseLong(srow1);
			hot delete=new hot(this);
			delete.open();
			delete.deleteentry(lrow1);
			delete.close();
			
			break;
			
			
			
		}
	}

}
