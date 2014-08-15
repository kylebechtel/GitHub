package connectingtophp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class Main {
//http://hc.apache.org/downloads.cgi download binarys from here.
	public static void main(String[] args) throws IOException {
		/*
		try{
            URL myURL = new URL("http://kylesserver.noip.me/post.php&hash=hello");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            System.out.println("successful connection");
        }
        catch (Exception e){
            System.out.println("something went wrong...");
        }
        try {
            URL url = new URL("http://kylesserver.noip.me/post.php&hash=hello");
            URLConnection con = url.openConnection();
            java.io.InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            System.out.println(body);
        }
        catch (Exception e){
            System.out.println("couldnt parse webpage... probably");
        }
        
        //this one doesnt break... not sure why.
        try{
	        HttpClient httpclient = HttpClients.createDefault();
	        HttpPost httppost = new HttpPost("http://kylesserver.noip.me/post.php&hash=hello");
	
	        // Request parameters and other properties.
	        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	        params.add(new BasicNameValuePair("param-1", "12345"));
	        params.add(new BasicNameValuePair("param-2", "Hello!"));
	        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	
	        //Execute and get the response.
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	
	        if (entity != null) {
	            InputStream instream = entity.getContent();
	            try {
	                //do something usefull
	            } finally {
	                instream.close();
	            }
	        }
        }
        catch (Exception e){
        	System.out.println("attempted http thing failed... ");
        }
        
        
        */
        //This stuff prints the entire web doc.  interesting, but not useful in its current state. 
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String urlstring = "http://kylesserver.noip.me/post.php\\&hash=stuff";
        System.out.println(urlstring);
        HttpGet httpget = new HttpGet(urlstring);
        System.out.println(httpget);
        try (CloseableHttpResponse response = httpclient.execute(httpget)) { 
        	System.out.println(response);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	
            	//enitity appears to be the full web page
            	//TODO:: writing a parser for entity
            	String page = EntityUtils.toString(entity), dbstuff;
            	
            	//heres the parser. dbstuff stores the php returned values
            	String [] tokens = page.split("<body>");
            	String [] tok2 = tokens[1].split("</body>");
            	dbstuff = tok2[0];
            	System.out.println(dbstuff);
               //System.out.println(EntityUtils.toString(entity));
            }
            response.close();
        } catch (IOException ex) {
            Logger.getLogger(HttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        
        try {
        	
            String result = null, urlString = "http://kylesserver.noip.me/post.php&hash=stuff";
			// Create a URL for the desired page
            URL url = new URL(urlString );
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                // str is one line of text; readLine() strips the newline character(s)
                 result += str;
            }
        	System.out.println(result);
            in.close();             
           }  catch (IOException e) {
        	   System.out.println("well this failed to.");
           } 
           */         
        
	}

}
