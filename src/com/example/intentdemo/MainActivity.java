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

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		
		final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button bOkay = (Button) findViewById(R.id.bOkay);
		
		bOkay.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ActivityTwo.class);
				String text = etPhoneNumber.getText().toString();
				try {
					int phNumber = Integer.parseInt(text);
				} catch(Exception e) {
					
				}
				i.putExtra("phoneNumber", text);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt(getString(R.string.saved_high_score), newHighScore);
				editor.commit();
				startActivity(i);
			}
		});
		
		
	}
}
 