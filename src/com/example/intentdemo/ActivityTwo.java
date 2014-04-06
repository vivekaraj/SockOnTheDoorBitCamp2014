package com.example.intentdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_two);
		TextView textView = (TextView) findViewById(R.id.myNumber);
		SharedPreferences prefs = getSharedPreferences(
			      "com.example.intentdemo", Context.MODE_PRIVATE);
		String defaultValue = prefs.getString("myNumber", "vivek");
		textView.setText(defaultValue);

		
		
		String numberholder = getIntent().getStringExtra("phoneNumber");
		
		final Button bTurnOn = (Button) findViewById(R.id.bTurnOn);
		
		bTurnOn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				if(bTurnOn.getText().equals("Turn it on")) {
					bTurnOn.setText("Turn it off");
				} else {
					bTurnOn.setText("Turn it on");
				}
			}
		});
		
		

	}
}
