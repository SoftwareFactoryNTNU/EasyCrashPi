package digitalUnit;

import java.io.File;

import digitalUnit.database.DBClient;

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
