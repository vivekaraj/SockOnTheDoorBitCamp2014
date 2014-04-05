package com.example.intentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_two);
		
		TextView tvText = (TextView) findViewById(R.id.tvText);
		
		String text = getIntent().getStringExtra("phoneNumber");
		
		tvText.setText(text);
	}
}
