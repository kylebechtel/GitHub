package com.example.test;

import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


//  http://the-mac.us/prescription/index.php?session=a31321396f0a3fbb8edb75e3b1ef914b&token=

public class MainActivity extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public final static String serverJson = "com.example.myfirstapp.MESSAGE";

	private String jsonObjects;

	//generates md5 hash for passing userinfo
	public String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i=0; i<messageDigest.length; i++)
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}



	public static String saveUrl(final String urlString)
	{
		final StringBuffer out = new StringBuffer();
		
		BufferedInputStream in = null;
		try
		{
			URL url = new URL(urlString);
			in = new BufferedInputStream(url.openStream());

			byte data[] = new byte[1024];
			while ((in.read(data, 0, 1024)) != -1)
			{
				out.append(new String(data));
			}
		}
		catch(Exception e)
		{
			try { if (in != null) in.close(); }
			catch(Exception ex) { }
		}
		
		return out.toString();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment())
			.commit();
		}
	}

	//Button click action aka. login
	public void submit(View view) {

	
		//getting text input from view
		EditText Password = (EditText) findViewById(R.id.editText1);
		String Key = Password.getText().toString();
		EditText Username = (EditText) findViewById(R.id.editText2);
		String User = Username.getText().toString();


		//hash's user info and creates a url to hit the server. Gets back JSON as a string
		String userInfo = User + "~" + Key;
		String hash, jsonstring;
		hash = md5(userInfo);

		final String urlString = "http://the-mac.us/prescription/index.php?session=a31321396f0a3fbb8edb75e3b1ef914b&token=" + hash;

		Toast.makeText(this, urlString, Toast.LENGTH_LONG).show();

		//putting JSON string into a JSONObject
		new Thread(new Runnable()
		{
			@Override
			public void run() {
				String jsonstring = saveUrl(urlString);
				jsonObjects = jsonstring;
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(MainActivity.this, jsonObjects, Toast.LENGTH_LONG).show();						
					}
				});
				Log.d("hey", jsonstring);

		JSONObject JSON = new JSONObject();
		try {
			JSONObject JSONattempt = new JSONObject(jsonObjects);
			JSON = JSONattempt;
			Intent intent = new Intent(MainActivity.this, DisplayPharmacist.class);

			intent.putExtra(serverJson, JSON.toString());
			startActivity(intent);
		} catch (final Exception e) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
				}
			});
			//Log.d(tag, msg).out.println()
			e.printStackTrace();
		}   	

			}
		}).start();

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
