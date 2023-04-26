package com.fpt.assignment.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

 

/**
 *
 * @author User
 */
public class DBUtils {

    private DBUtils() {
    }
    
    private static boolean USE_LOCAL = true;
    private static final String IP = "localhost";
    private static final String instanceName = "SANG-DANG\\SQLEXPRESS";
    private static final String port = "1433";
    private static final String uid = "sa";
    private static final String pwd = "12345";
    private static final String db = "QuizDatabase";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
            + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;

    public static Connection getConnection() {
        try {
            if (USE_LOCAL) {
                Connection cn = null;
                Class.forName(driver);
                cn = DriverManager.getConnection(url);
                return cn;
            } else {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                return DriverManager.getConnection("jdbc:sqlserver://myassignment.database.windows.net:1433;"
                        + "database=QuizDatabase;"
                        + "user=admins@myassignment;"
                        + "password=projectAssignment123;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "hostNameInCertificate=*.database.windows.net;"
                        + "loginTimeout=30;"
                );
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
