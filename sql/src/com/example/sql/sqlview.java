package com.example.sql;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class sqlview extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql1);
		TextView tv=(TextView) findViewById(R.id.tv3);
		hot info=new hot(this);
		info.open();
		String data=info.getData();
		info.close();
		tv.setText(data);
	}

	
}
