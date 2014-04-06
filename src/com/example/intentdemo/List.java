package com.example.intentdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class List extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_list);

		SharedPreferences prefs = this.getSharedPreferences(
			      "com.example.intentdemo", Context.MODE_PRIVATE);
		String restoredText = prefs.getString("Number1", null);
		if (restoredText != null) {
			Intent i = new Intent(List.this, ActivityTwo.class);
			startActivity(i);
			finish();
		} else {
			final EditText friend1 = (EditText) findViewById(R.id.friend1);
	        final EditText friend2 = (EditText) findViewById(R.id.friend2);
	        final EditText friend3 = (EditText) findViewById(R.id.friend3);
	        final EditText friend4 = (EditText) findViewById(R.id.friend4);
	        
			Button bDone = (Button) findViewById(R.id.bDone);			
			
			bDone.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(List.this, ActivityTwo.class);
					SharedPreferences.Editor editor = getSharedPreferences(
						      "com.example.intentdemo", Context.MODE_PRIVATE).edit();
					if (friend1.getText().toString().length() == 10)
						editor.putString("Number1", friend1.getText().toString());
					if (editor.putString("Number2", friend2.getText().toString())!=null && 
							friend2.getText().toString().length() == 10)
						editor.putString("Number2", friend2.getText().toString());
					if (editor.putString("Number3", friend2.getText().toString())!=null && 
							friend3.getText().toString().length() == 10)
						editor.putString("Number3", friend3.getText().toString());
					if (editor.putString("Number4", friend2.getText().toString())!=null && 
							friend4.getText().toString().length() == 10)
						editor.putString("Number4", friend4.getText().toString());
					editor.commit();
					startActivity(i);
				}
			});


	}



}}




