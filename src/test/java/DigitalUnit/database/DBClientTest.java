package DigitalUnit.database;

import DigitalUnit.database.DBClient;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.sql.SQLException;

public class DBClientTest extends TestCase {

    private String location = "/tmp/blackboxtests";

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DBClientTest( String testName ) {
        super(testName);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        File f = new File(location);
        if (f.exists()) {
            Runtime.getRuntime().exec("rm -r " + location);
        }
    }

    public void testCreateDatabase() {
        try {
            DBClient.connect(location);
        } catch (SQLException e) {
            fail("Failed to connect to " + location);
        }

        File f = new File(location);
        assertTrue(f.exists());
    }

    public void testCreateTable() {
        try {
            DBClient.connect(location);
            DBClient.createTable();
        } catch (SQLException e) {
            fail("Failed to create table or connect to location " + location);
        }
    }


}
