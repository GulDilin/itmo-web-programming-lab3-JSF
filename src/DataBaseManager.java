import com.jcraft.jsch.JSchException;

import java.sql.*;

public class DataBaseManager {
    private Tunnel tunnel;
    private Connection connection;
    private Statement statement;
    private int lPort;
    private int rPort;
    private String host = "helios.se.ifmo.ru";
    private String rhost = "pg";
    private String user;
    private String password;
    private boolean isConnect = false;

    public DataBaseManager(String dbName, int lPort) {
        statement = null;
        user = "s264449";
        password = "cfv571";
        this.lPort = lPort;
        int i = 1;
        while (!isConnect) {
            try {
                tunnel = new Tunnel(host, "user", "password", 2222, rhost, this.lPort, 5432);
                tunnel.connect();
                isConnect = true;

            } catch (JSchException e) {
                this.lPort+= 15;
                System.out.println("SSH tunneling error. Trying new local port: " + this.lPort);
                System.out.println(i);
                isConnect = false;
                i--;
                if (i == 0) {
                    System.out.println("Can't connect SSH tunnel.");
                    break;
                }
            }
        }
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:" + lPort + "/" + dbName, user, password);
            statement = connection.createStatement();
            System.out.println("Database connected");
        } catch (ClassNotFoundException e) {
            System.out.println("Cant load Driver class");
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getAll() {
        String result = "Nothing";
        try {
            if (statement != null) {
                result = "";
                ResultSet rs = statement.executeQuery(BDQuerys.GET_ALL.getTextQuery());
                while (rs.next()) {
                    result += rs.getString("color") + " ";
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }
}
