package DigitalUnit;

import java.io.IOException;

import server.HttpServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        HttpServer serv = new HttpServer();
        try {
			serv.getPage("http://www.google.no");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        serv.printTest();
    }
}
