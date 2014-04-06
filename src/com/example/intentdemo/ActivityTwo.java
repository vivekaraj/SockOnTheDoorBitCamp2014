package com.example.intentdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends Activity {
	String friend1, friend2, friend3, friend4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_two);
		TextView textView = (TextView) findViewById(R.id.myNumber);
		SharedPreferences prefs = getSharedPreferences(
			      "com.example.intentdemo", Context.MODE_PRIVATE);
		String defaultValue = prefs.getString("myNumber", "You have not entered your number") + "&&";
		defaultValue += prefs.getString("Number1", "You have not entered your number") + "&&";
		defaultValue += prefs.getString("Number2", "You have not entered your number") + "&&";
		defaultValue += prefs.getString("Number3", "You have not entered your number") + "&&";
		defaultValue += prefs.getString("Number4", "You have not entered your number");
		textView.setText(defaultValue);
		
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
