package DigitalUnit.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vegard on 18.02.2016.
 */
public class DBClient {

    private Connection conn;

    public void connect() {
        try {
            File dbfolder = new File(System.getProperty("user.home") + ".blackbox");
            if (!dbfolder.exists()) {
                conn = DriverManager.getConnection("jdbc:derby:" + System.getProperty("user.home") + "/.blackbox;create = true");
            } else {
                conn = DriverManager.getConnection("jdbc:derby:" + System.getProperty("user.home") + "/.blackbox");
            }
            System.out.println(conn.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
