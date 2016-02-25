package DigitalUnit.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection 
{
	private URL url;
	private HttpURLConnection connection;
	
	public HttpConnection(String url) throws IOException
	{
		this.url = new URL(url);
		
		this.connection = (HttpURLConnection) this.url.openConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.addRequestProperty("Accept-Language", "en-US,en;q=0.5");
		connection.setDoOutput(true);
	}
	public String post(String urlParameters) throws IOException
	{
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
	}
	
	
	
}
