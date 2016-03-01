package DigitalUnit;

import java.io.File;

import DigitalUnit.database.DBClient;

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
