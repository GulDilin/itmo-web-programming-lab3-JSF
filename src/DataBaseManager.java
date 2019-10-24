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
                tunnel = new Tunnel(host, "s264449", "cfv571", 2222, rhost, this.lPort, 5432);
                tunnel.connect();
                isConnect = true;

            } catch (JSchException e) {
                this.lPort += 15;
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
        String result = "";
        String x;
        String y;
        String r;
        int x_dot;
        int y_dot;
        int r_dot;
        String res;
        try {
            if (statement != null) {
                result = "";
                ResultSet rs = statement.executeQuery(BDQuerys.GET_ALL.getTextQuery());
                while (rs.next()) {
                    try {
                        res = rs.getString("result");
                        x = rs.getString("x");
                        y = rs.getString("y");
                        r = rs.getString("r");
                        System.out.println("r = " + r);
                        r_dot = Integer.parseInt(r);
                        x_dot = (int) Math.round(Double.parseDouble(x) * 120 / r_dot + 150);
                        y_dot = (int) Math.round(-Double.parseDouble(y) * 120 / r_dot + 150);

                        if (res.equals("true") || res.equals("false")) {
                            result += "<circle r=\"3\" cx=\""; //37\" cy=\"61\" id=\"dot\" stroke=\"#AD2D2D\" fill=\"#AD2D2D\" class=\"4\"></circle>"
                            result += x_dot + "\" cy=\"";
                            result += y_dot + "\" class=\"";
                            result += r + "\" ";
                            result += res.equals("false") ? "stroke=\"green\" fill=\"green\"" : "stroke=\"#AD2D2D\" fill=\"#AD2D2D\"";
                            result += "></circle>\n";
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void deleteAll() {
        try {
            if (statement != null) {
                statement.execute(BDQuerys.DELETE_DOTS.getTextQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addDot(double x, double y, int r, String result) {
        try {
            if (statement != null) {
                statement.execute("INSERT into dots (x, y, r, result) VALUES (" + x + ", " + y + ", " + r + ", \''" + result + "\''");
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
