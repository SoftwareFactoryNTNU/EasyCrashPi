package DigitalUnit.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection 
{
	private URL url;
	private HttpURLConnection connection;
	
	
	/**
	 * Constructor creates connection to servers api endpoint
	 */
	public HttpConnection(String url) throws IOException
	{
		this.url = new URL(url);
		
		this.connection = (HttpURLConnection) this.url.openConnection();
		connection.setRequestProperty("Content-Type","application/json");
		connection.setRequestProperty("Accept","application/json");
		connection.setRequestMethod("POST");
		
		connection.setDoOutput(true);
	}
	
	/**
	 * Posts json data to server api endpoint associated with this object.
	 * 
	 * @param JSON object on string form
	 * @return response from server
	 */
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
