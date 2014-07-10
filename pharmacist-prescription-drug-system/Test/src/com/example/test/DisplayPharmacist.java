package com.example.test;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.test.MainActivity.PlaceholderFragment;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;


// get JSON from server
public class DisplayPharmacist extends ActionBarActivity {

	public static String name = "john";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_display_pharmacist);
	    
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
	    
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    //Get JSON object from intent
	    JSONObject JSON;
		try {
			JSON = new JSONObject(intent.getStringExtra(MainActivity.serverJson));
			name = (String) JSON.getString("UserName");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    
	    
	    // Edit the text view in XML to include the name found in JSON
	    TextView textView = (TextView) findViewById(R.id.name);
	    textView.setTextSize(40);
	    textView.setText(name);
	    
	    
	}
	

	//Hides the prescription items until button press.
	public void prescription(View view){
		LinearLayout c = (LinearLayout) findViewById(R.id.prescriptionlayout);
		if(c.getVisibility() == View.VISIBLE) c.setVisibility(View.GONE);
		if(c.getVisibility() == View.GONE) c.setVisibility(View.VISIBLE);
	}
	
	public void patient(View view){
		LinearLayout c = (LinearLayout) findViewById(R.id.patientlayout);
		if(c.getVisibility() == View.VISIBLE) c.setVisibility(View.GONE);
		if(c.getVisibility() == View.GONE) c.setVisibility(View.VISIBLE);
	}
	
	public void drug(View view){
		LinearLayout c = (LinearLayout) findViewById(R.id.druglayout);
		if(c.getVisibility() == View.VISIBLE) c.setVisibility(View.GONE);
		if(c.getVisibility() == View.GONE) c.setVisibility(View.VISIBLE);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_pharmacist, menu);
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
			View rootView = inflater.inflate(
					R.layout.fragment_display_pharmacist, container, false);
			return rootView;
		}
	}

}
