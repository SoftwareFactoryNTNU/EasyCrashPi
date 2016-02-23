package DigitalUnit.Database;

import java.io.File;
import java.sql.*;

/**
 * Database client class for the derby database
 */

public class DBClient {

    private Connection conn;

    /**
     * Establises a connection to the database
     */

    public void connect() {
        try {
            File dbfolder = new File(System.getProperty("user.home") + "/.blackbox");
            if (!dbfolder.exists()) {
                conn = DriverManager.getConnection("jdbc:derby:" + System.getProperty("user.home") + "/.blackbox;create = true");
            } else {
                conn = DriverManager.getConnection("jdbc:derby:" + System.getProperty("user.home") + "/.blackbox");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String tableString = "CREATE TABLE MEASUREMENTS (" +
                "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY" +
                "(START WITH 1, INCREMENT BY 1)," +
                "LATITUDE DOUBLE," +
                "LONGITUDE DOUBLE," +
                "VEHICLE_SPEED DOUBLE," +
                "ENGINE_SPEED INTEGER," +
                "ACCELERATOR_PEDAL DOUBLE," +
                "BREAKING_PEDAL BOOLEAN," +
                "TIMESTMP TIMESTAMP)";

        try {
            Statement statement = conn.createStatement();
            statement.execute(tableString);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
