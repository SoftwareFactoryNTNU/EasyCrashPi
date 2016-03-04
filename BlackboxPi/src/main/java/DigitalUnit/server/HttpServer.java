package DigitalUnit.server;

import java.io.IOException;

import DigitalUnit.utils.CarData;
import DigitalUnit.utils.GsonCollection;


/*
 * Class that handles to connections to all server endpoints, 
 * and lets other services send json over http.
 */
public class HttpServer{

	private HttpConnection single_line_connection= null;
	private HttpConnection many_lines_connection = null;
	
	
	/*
	 * Constructor tries to connect to server api endpoints
	 */
	public HttpServer()
	{
		try {
			//single_line_connection = new HttpConnection("http://localhost:3000/test");
			single_line_connection = new HttpConnection("http://178.62.220.37/api/add_data");
			many_lines_connection = new HttpConnection("http://178.62.220.37/api/add_bulk_data");
			//many_lines_connection = new HttpConnection("http://localhost:3000/test");
		} catch (IOException e) {
			System.out.println("Couldn't connect to host..");
		}
		
		
	}
	
	/*
	 * Method that lets a service send a single line of JSON to the server
	 * 
	 * Used to send data recorded after crash, as sending data quickly is essential
	 * 
	 * @return response from server or error message
	 */
	public String sendLine(CarData line)
	{
		if(single_line_connection!= null)
		{
			try {
				return single_line_connection.post(line.toString());
			} catch (IOException e) {
				return e.getMessage();
				//return "Could not read from/write to stream";
			}
		}
		return "Service not found";
	}
	
	/*
	 * Method that lets a service send large chunks of JSON objects to the server
	 * 
	 * Used to send data recorded before the accident.
	 * 
	 * @return response from server or error message
	 */
	public String sendLines(GsonCollection col)
	{
		if(many_lines_connection!= null)
		{
			try {
				return many_lines_connection.post(col.toString());
			} catch (IOException e) {
				return e.getMessage();
				
			}
		}
		return "Service not found";
	}
	
	

}
