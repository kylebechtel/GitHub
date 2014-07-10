package us.the.mac.pharma;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/** The activity that displays prescription order dates from sample JSON file (prescriptions.json), and 
the following is the link and sample output for prescriptions:<br><br>http://the-mac.us/prescription/index.php?session=a31321396f0a3fbb8edb75e3b1ef914b
 * <pre>
 {
  	"success":true,
  	"prescription":
  	[{
	  	"ID":"1",
		"patientID":"1",
		"drugID":"1",
		"Dosage":"300mg",
		"OrderDate":"2014-01-21 00:00:00",
		"Amount":"90",
		"RefillAmount":"2",
		"OrderFulfillDate":"2014-01-21 00:00:00",
		"PatientPickupDate":"2014-01-22 00:00:00",
		"doctorID":"1",
		"pharmacistID":"1"
	}]
}</pre>
 **/
public class Prescriptions extends Activity {
	
	private final class JSONAdapter extends ArrayAdapter<JSONObject>
	{
		private JSONObject jsonObject = new JSONObject();
		private JSONArray jsonArray = new JSONArray();
		
		public JSONAdapter(Context context, int resource, JSONObject json) {
			super(context, resource);

			if(json == null)  return;
			
			jsonObject = json;

			try {

				JSONArray names = jsonObject.names();
				
				String success = names.getString(1);
				String name = names.getString(0);
				
				if(jsonObject.getBoolean(success))
				{
					jsonArray = json.getJSONArray(name);
				}
				
			} catch (Exception e) { }
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = (TextView)  super.getView(position, convertView, parent);
			
			JSONObject object = getItem(position);
			if(object == null)  return view;			
			String text = null;
			
			try { text = object.getString("Dosage") +", ordered on "+ object.getString("OrderDate").substring(0, 11); }
			catch (Exception e) { e.printStackTrace(); }

			view.setText(text);

			return view;
		}
		
		@Override
		public JSONObject getItem(int position) {
			try { return jsonArray.getJSONObject(position); }
			catch (Exception e) { return null; }
		}
		
		@Override
		public int getCount() {
			return jsonArray.length();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prescriptions);

		ListView lv = (ListView) findViewById(android.R.id.list);
		lv.setAdapter(new JSONAdapter(this, android.R.layout.simple_list_item_1, getJson("prescriptions.json")));
	}

	private JSONObject getJson(String string) {
		if("prescriptions.json".equals(string))
			return Develop.getJSONFromFile(this, string);

		return Develop.getJSONFromUrl(string);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.prescriptions, menu);
		return true;
	}

}
