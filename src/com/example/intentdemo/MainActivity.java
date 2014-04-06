package com.example.intentdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		SharedPreferences prefs = this.getSharedPreferences(
			      "com.example.intentdemo", Context.MODE_PRIVATE);
		String restoredText = prefs.getString("myNumber", null);
		if (restoredText != null) {
			Intent i = new Intent(MainActivity.this, ActivityTwo.class);
			startActivity(i);
			finish();
		} else {
			final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
			
			Button bOkay = (Button) findViewById(R.id.bOkay);			
			
			bOkay.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(MainActivity.this, List.class);
					SharedPreferences.Editor editor = getSharedPreferences(
						      "com.example.intentdemo", Context.MODE_PRIVATE).edit();
					editor.putString("myNumber", etPhoneNumber.getText().toString());
					editor.commit();
					startActivity(i);
				}
			});
			
		}
		

		
		/*try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			File file = new File("test.txt");
			Scanner sc = new Scanner(file);
			Intent i = new Intent(MainActivity.this, ActivityTwo.class);
			startActivity(i);
			//done
		} catch(Exception e) {		
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
	
			final EditText etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
	
			Button bOkay = (Button) findViewById(R.id.bOkay);			
			
			bOkay.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(MainActivity.this, ActivityTwo.class);
					String text = etPhoneNumber.getText().toString();
					String revText = "";
					for(int j = 0; j < 10; j++) {
						revText += text.charAt(-1 * j + 9);						
					}
					int revPhNumber = Integer.parseInt(revText);
					byte[] digits = new byte[10];
					
					for(int j = 0; j < 10; j++) {
						digits[j] = (byte) (revPhNumber % 10);
						revPhNumber /= 10;
					}
					try {
					FileOutputStream outputStream = getApplicationContext().openFileOutput("test.txt", Context.MODE_PRIVATE);
					outputStream.write(digits);
					outputStream.close();
					} catch(Exception e) {
						throw new IllegalArgumentException();
					}
					startActivity(i);
				}
			});
		}
		*/
	}
}
 