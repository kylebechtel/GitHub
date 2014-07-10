package us.the.mac.pharma;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Develop {

	private static Map<String,FileInputStream> fileIn = new HashMap<String,FileInputStream>();
	private static Map<String,FileOutputStream> fileOut = new HashMap<String,FileOutputStream>();

	private static FileInputStream currentFileIn = null;
	private static Scanner currentInput = null;
	private static FileOutputStream currentFileOut = null;

	
	
	public static void createFile(String name) {
		
		try { fileOut.put(name, new FileOutputStream(new File(name))); }
		catch (Exception e) { System.err.println(e); }
	}
	
	public static void appendToFile(String name, Object content) {
		
		try { fileOut.get(name).write((""+content).getBytes()); }
		catch (Exception e) {
			System.err.println(e); 
			createFile(name);
			appendToFile(name, content);
		}
	}

	
	public static void openFile(String name) {

		if(fileIn.containsKey(name))
		{
			currentFileIn = fileIn.get(name);
		}
		else
		{
			try {
				currentFileIn = new FileInputStream(new File(name));
				fileIn.put(name, currentFileIn); 
			}
			catch (Exception e) { 
				if(name.contains("ver"))
				{
					createFile(name);
					appendToFile(name, ""+0);
					openFile(name);
					
					closeCurrentOutputFile();
				}
				else
					System.err.println(e); 
			}			
		}
		
		currentInput = new Scanner(currentFileIn);
	}

	public static void closeCurrentOutputFile() {
		try { currentFileOut.close(); }
		catch (Exception ex) { System.err.println(ex); }
	}

	public static void closeOutputFile(String name) {
		try { fileOut.get(name).close(); }
		catch (Exception ex) { System.err.println(ex); }
	}

	public static void closeInputFile(String name) {
		try { fileIn.get(name).close(); }
		catch (Exception ex) { System.err.println(ex); }
	}
	
	public static String readLine()
	{
		if(currentInput == null) return null;
		
		return currentInput.nextLine();
	}
	
	public static int readInt() {

		if(currentInput == null) return -1;
		
		return currentInput.nextInt();
	}


	public static void showAllFiles() {
		System.out.println("\nInput files:");

		Iterator<Entry<String, FileInputStream>> itIn = fileIn.entrySet().iterator();
		while (itIn.hasNext()) {
			Map.Entry<String, FileInputStream> pairs = (Map.Entry<String, FileInputStream>)itIn.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}

		System.out.println("\nOutput files:");
		Iterator<Entry<String, FileOutputStream>> itOut = fileOut.entrySet().iterator();
		while (itOut.hasNext()) {
			Map.Entry<String, FileOutputStream> pairs = (Map.Entry<String, FileOutputStream>)itOut.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}
	}
	
	public static void closeAllFiles() {
		try { for (FileInputStream f : fileIn.values()) { f.close(); } }
		catch (Exception e) { System.err.println(e); }

		try { for (FileOutputStream f : fileOut.values()) { f.close(); } }
		catch (Exception e) { System.err.println(e); }

	}

	
	public static void print(Object info) {
	
		System.out.print(""+info);
	}
	
	public static void println(Object info) {
		
		System.out.println(""+info);
	}

	public static String findContent(String URL, String startContent, String endContent, String[][] replace) {
		String value = saveUrl(URL);
		
		String nameValues = "";
		String[] values;
		
		
		try {
			
			
			int sec_1 = value.indexOf(startContent);
			sec_1 = sec_1 + startContent.length();
			
			int sec_2 = value.indexOf(endContent, sec_1+1);
			
			value = value.substring(sec_1,sec_2);

			for (int i = 0; replace != null &&i < replace.length; i++) {
				value = value.replaceAll(replace[0][i], replace[1][i]);
			}
			
			
			String array[] = value.split("\n");
			values = new String[array.length];
			
			for (int i = 0; i < array.length; i++) {
				String content[] = array[i].split(":");
				nameValues += content[0] + "\n";	
				values[i] = content[0] + "\n";				
			}

		}
		catch(Exception e) {  }

		return nameValues;
	}
	
	public static String saveUrl(String urlString)
	{
		BufferedInputStream in = null;
		StringBuffer out = new StringBuffer();

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

	public static String readFile(String fileName)
	{
		BufferedInputStream in = null;
		StringBuffer out = new StringBuffer();

		try
		{
			in = new BufferedInputStream(new FileInputStream(fileName));

			byte data[] = new byte[1024];
			while ((in.read(data, 0, 1024)) != -1)
			{
				out.append(new String(data));// + "\n"
				System.gc();
			}
		}
		catch(Exception e)
		{
			try { if (in != null) in.close(); }
			catch(Exception ex) { }
		}

		return out.toString();
	}
	
	public static String readAssetsFile(Context c, String fileName)
	{
		BufferedInputStream in = null;
		StringBuffer out = new StringBuffer();

		try
		{
			in = new BufferedInputStream(c.getAssets().open(fileName));

			byte data[] = new byte[1024];
			while ((in.read(data, 0, 1024)) != -1)
			{
				out.append(new String(data));// + "\n"
				System.gc();
			}
		}
		catch(Exception e)
		{
			try { if (in != null) in.close(); }
			catch(Exception ex) { }
		}

		return out.toString();
	}
	
//	public JSONObject getJSONFromUrl(String url) {
//		 
//        // Making HTTP request
//        try {
//            // defaultHttpClient
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
// 
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();          
// 
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//         
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            is.close();
//            json = sb.toString();
//        } catch (Exception e) {
//            Log.e("Buffer Error", "Error converting result " + e.toString());
//        }
// 
//        // try parse the string to a JSON object
//        try {
//            jObj = new JSONObject(json);
//        } catch (JSONException e) {
//            Log.e("JSON Parser", "Error parsing data " + e.toString());
//        }
// 
//        // return JSON String
//        return jObj;
// 
//    }

	private static InputStream getResponseStream(String urlString) {
		try { return new BufferedInputStream(new URL(urlString).openStream()); }
		catch (Exception e) {  return null; }
	}
	
	public static JSONObject getJSONFromUrl(String url) {

      try {
    	  return getJSONFromStream(getResponseStream(url)); 
    	  
          // defaultHttpClient
//          DefaultHttpClient httpClient = new DefaultHttpClient();
//          HttpPost httpPost = new HttpPost(url);
//
//          HttpResponse httpResponse = httpClient.execute(httpPost);
//          HttpEntity httpEntity = httpResponse.getEntity();
//          
//          return getJSONFromStream(httpEntity.getContent());

//      } catch (UnsupportedEncodingException e) {
//          e.printStackTrace();
//      } catch (ClientProtocolException e) {
//          e.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      }
      
		return null;
	}
	
	public static JSONObject getJSONFromFile(Context c, String file) {

		try {
			return new JSONObject(readAssetsFile(c, file));
		} catch (Exception e) {
			Toast.makeText(c, String.format("Failure in getJSONFromFile: "+e, Locale.US), Toast.LENGTH_LONG).show();
		}
		
		return null;
	}
	
	public static JSONObject getJSONFromStream(InputStream is) {
		String json = "";
		JSONObject jObj = null;
		
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
}
