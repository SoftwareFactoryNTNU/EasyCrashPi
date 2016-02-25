package DigitalUnit;

import java.io.IOException;

import DigitalUnit.Database.DBClient;
import DigitalUnit.server.HttpServer;

import java.io.File;
import java.net.MalformedURLException;

/**
 * BlackboxPI main class
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        HttpServer serv;
		try {
			serv = new HttpServer();
			serv.sendData();
			//serv.getPage("http://www.google.no");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

}
