package com.example.intentdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
					new ASyncPut().execute();
				} else {
					bTurnOn.setText("Turn it on");
					new ASyncPut().execute();
				}
			}
		});

	}
	

	class ASyncPut extends AsyncTask<String, Void, String> {
		private String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	 
	        inputStream.close();
	        return result;
	 
	    }  
		public String POSTPerson(){
	        InputStream inputStream = null;
	        String result = "";
	        
	        try {

	            SharedPreferences prefs = getSharedPreferences(
	  			      "com.example.intentdemo", Context.MODE_PRIVATE);
	            String id = prefs.getString("id", "");
	            
	            String url = "http://sockless.herokuapp.com/"+id;
	        	
	            // 1. create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	 
	            // 2. make POST request to the given URL
	            HttpPut httpPut = new HttpPut(url);
	 
	            

	            // 8. Execute POST request to the given URL
	            HttpResponse httpResponse = httpclient.execute(httpPut);

	 
	            // 9. receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	 
	            
	            // 10. convert inputstream to string
	            if(inputStream != null)
	                result = convertInputStreamToString(inputStream);
	            else
	                result = "Did not work!";
	 
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        } 
	 
	        // 11. return result
	        System.out.println(result);
	        return result;
	    }
		@Override
		protected String doInBackground(String... params) {
			POSTPerson();
			return null;
		}
	}
	
}
