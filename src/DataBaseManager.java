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

    public DataBaseManager(String dbName, int lPort) {
        statement = null;
        user = "s264449";
        password = "cfv571";
        this.lPort = lPort;
        tunnel = new Tunnel(host, "user", "password", 2222, rhost, lPort, 5432);
        tunnel.connect();
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
                while (rs.next()){
                    result += rs.getString("color") + " ";
                }
            }
        } catch (SQLException e) {

        }
        return result;
    }
}
