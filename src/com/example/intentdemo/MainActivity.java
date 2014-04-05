package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		Button bOkay = (Button) findViewById(R.id.bOkay);
		
		bOkay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ActivityTwo.class);
				String text = etPhoneNumber.getText().toString();
				i.putExtra("phoneNumber", text);
				startActivity(i);
			}
		});
	}
}
