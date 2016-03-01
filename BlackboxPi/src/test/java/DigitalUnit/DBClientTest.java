package DigitalUnit;

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

    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
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
