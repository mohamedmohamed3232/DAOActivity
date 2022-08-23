package DAO;

import java.sql.*;

public class AbstractDAO {
    private final String URL = "jdbc:mysql://localhost:3306/DAOActivity";
    private final String USER = "root";
    private final String PASS = "password";
    protected Connection conn = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    public void connectToMySQL() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS );
    }

    public void closeConnections() {
        try {
            if(!conn.equals(null)) {
                if(!conn.isClosed()) {
                    conn.close();
                }
            }
            if(!rs.equals(null)) {
                if(!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
