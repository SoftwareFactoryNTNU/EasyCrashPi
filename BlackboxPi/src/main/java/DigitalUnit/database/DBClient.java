package DigitalUnit.database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DigitalUnit.Utils.CarData;

/**
 * Database client class for the derby database
 */

public final class DBClient {

    private static final String INSERT_DATA_STRING = "INSERT INTO MEASUREMENTS (LATITUDE, LONGITUDE, VEHICLE_SPEED, ENGINE_SPEED, ACCELERATOR_PEDAL, BREAKING_PEDAL, TIMESTMP) " +
                                                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static Connection conn;

    private DBClient() {
    }

    /**
     * Establises a connection to the database
     */

    public static void connect(String location) throws SQLException {
        File dbfolder = new File(location + "/.blackbox");
        if (!dbfolder.exists()) {
            conn = DriverManager.getConnection("jdbc:derby:" + location + "/.blackbox;create = true");
        } else {
            conn = DriverManager.getConnection("jdbc:derby:" + location + "/.blackbox");
        }
    }

    public static void createTable() throws SQLException {
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


        Statement statement = conn.createStatement();
        statement.execute(tableString);
        statement.close();
    }

    public static List<CarData> getAll() {
        List<CarData> result = new ArrayList<>();
        String sql = "SELECT * FROM MEASUREMENTS";

        try {
            Statement statement = conn.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    double latitude = resultSet.getDouble("LATITUDE");
                    double longitude = resultSet.getDouble("LONGITUDE");
                    double vehicleSpeed = resultSet.getDouble("VEHICLE_SPEED");
                    int engineSpeed = resultSet.getInt("ENGINE_SPEED");
                    double acceleratorPedal = resultSet.getDouble("ACCELERATOR_PEDAL");
                    boolean breakingPedal = resultSet.getBoolean("BREAKING_PEDAL");
                    double timestamp = ((double) (resultSet.getTimestamp("TIMESTAMP").getTime())) / 1000;
                    result.add(new CarData(latitude, longitude, vehicleSpeed, engineSpeed, acceleratorPedal, breakingPedal, timestamp));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void insert(CarData carData) {
        try {
            PreparedStatement insertCarData = conn.prepareStatement(INSERT_DATA_STRING);
            insertCarData.setDouble(1, carData.getLatitude());
            insertCarData.setDouble(2, carData.getLongitude());
            insertCarData.setDouble(3, carData.getVehicleSpeed());
            insertCarData.setInt(4, carData.getEngineSpeed());
            insertCarData.setDouble(5, carData.getAcceleratorPedal());
            insertCarData.setBoolean(6, carData.isBreakingPedal());
            long millis = (long) (carData.getTimestamp() * 1000);
            insertCarData.setTimestamp(7, new Timestamp(millis));
            insertCarData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
