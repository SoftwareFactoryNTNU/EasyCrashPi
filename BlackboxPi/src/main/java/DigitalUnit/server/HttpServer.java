package DigitalUnit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

public class HttpServer{

	private URL url;
	private HttpConnection server= null;
	
	public HttpServer() throws MalformedURLException
	{
		this.url = new URL("http://blackboxpi.com");
		try {
			server = new HttpConnection("http://localhost");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void getPage(String urlString) throws IOException
	{
		URL url = new URL(urlString);
		HttpURLConnection connection= (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
	}
	
	public boolean sendData(/*Gson gson*/) throws IOException
	{
		String jsonstr= "thisisnojson";
		
		
		if(server!= null)
		{
			server.post(jsonstr);
			return true;
			
		}

		
		
				
		return false;
		
	}

}
