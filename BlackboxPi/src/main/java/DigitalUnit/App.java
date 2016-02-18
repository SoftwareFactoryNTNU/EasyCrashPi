package DigitalUnit;

import DigitalUnit.Database.DBClient;

import java.io.File;

/**
 * BlackboxPI main class
 *
 */
public class App {

    /**
     * BlackboxPI main entry point
     * @param args command line arguments
     */

    public static void main( String[] args ) {
        DBClient db = new DBClient();
        db.connect();
    }
}
