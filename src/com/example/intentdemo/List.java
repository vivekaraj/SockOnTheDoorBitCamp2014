package com.example.intentdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
					new ASyncPost().execute();
				}
			});


	}
	}


		class ASyncPost extends AsyncTask<String, Void, String> {
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
		        	
		            // 1. create HttpClient
		            HttpClient httpclient = new DefaultHttpClient();
		 
		            // 2. make POST request to the given URL
		            HttpPost httpPost = new HttpPost("http://sockless.herokuapp.com/");
		 
		            String json = "";

		            
		            SharedPreferences prefs = getSharedPreferences(
		  			      "com.example.intentdemo", Context.MODE_PRIVATE);
		            String person = prefs.getString("myNumber", "");
		            
		            
		            
		            String[] people;
		            if (prefs.getString("Number2", "").length() != 10){
		            	people = new String[1];
		            	people[0] = prefs.getString("Number1", "");;
		            }
		            else if (prefs.getString("Number3", "").length() != 10){
		            	people = new String[2];
		            	people[0] = prefs.getString("Number1", "");
		            	people[1] = prefs.getString("Number2", "");
		            } else if (prefs.getString("Number4", "").length() != 10){
		            	people = new String[3];
		            	people[0] = prefs.getString("Number1", "");
		            	people[1] = prefs.getString("Number2", "");
		            	people[2] = prefs.getString("Number3", "");
		            } else {
		            	people = new String[4];
		            	people[0] = prefs.getString("Number1", "");
		            	people[1] = prefs.getString("Number2", "");
		            	people[2] = prefs.getString("Number3", "");
		            	people[3] = prefs.getString("Number4", "");
		            }
		            JSONArray parr = new JSONArray(people);
		            
		            
		            // 3. build jsonObject
		            JSONObject jsonObject = new JSONObject();
		            jsonObject.accumulate("person", person);
		            jsonObject.accumulate("outbound", parr);
		            jsonObject.accumulate("inbound", new JSONArray());
		            jsonObject.accumulate("active", false);
		 
		            // 4. convert JSONObject to JSON to String
		            json = jsonObject.toString();
		 
		            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
		            // ObjectMapper mapper = new ObjectMapper();
		            // json = mapper.writeValueAsString(person); 
		 
		            // 5. set json to StringEntity
		            StringEntity se = new StringEntity(json.toString());
		 
		            // 6. set httpPost Entity
		            httpPost.setEntity(se);
		            
		 
		            // 7. Set some headers to inform server about the type of the content   
		            httpPost.setHeader("Accept", "application/json");
		            httpPost.setHeader("Content-type", "application/json");


		            // 8. Execute POST request to the given URL
		            HttpResponse httpResponse = httpclient.execute(httpPost);

		 
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
		        SharedPreferences.Editor editor = getSharedPreferences(
					      "com.example.intentdemo", Context.MODE_PRIVATE).edit();
		        editor.putString("id", result.toString());
		        editor.commit();
		        return result;
		    }
			@Override
			protected String doInBackground(String... params) {
				POSTPerson();
				return null;
			}
		}

}




