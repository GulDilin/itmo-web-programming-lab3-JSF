import com.jcraft.jsch.*;
import netscape.javascript.JSException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

import static java.lang.System.exit;

public class Tunnel {
    private String host;
    private String user;
    private String password;
    private int port;
    private String tunnelRemoteHost;
    private int tunnelLocalPort;
    private int tunnelRemotePort;

    /**
     * SSH Tunnel constructor
     *
     * @param host             Target SSH host, which you will be connected     (example: "helios.se.ifmo.ru")
     * @param user             User on target SSH host                          (example: s244444)
     * @param password         Password on target SSH host
     * @param port             Port on target SSH host                          (example: 2222)
     * @param tunnelRemoteHost The host from server, which you want to connect  (example: "pg")
     * @param tunnelLocalPort  The port on your machine                         (example: 5454)
     * @param tunnelRemotePort The port on SSH server                           (example: 5432)
     */
    public Tunnel(String host,
                  String user,
                  String password,
                  int port,
                  String tunnelRemoteHost,
                  int tunnelLocalPort,
                  int tunnelRemotePort) {


        this.user = user;
        this.password = password;
        this.port = port;
        this.host = host;
        try {
            InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("Uncorrect SSH host");
            e.printStackTrace();
        }

        this.tunnelRemotePort = tunnelRemotePort;
        this.tunnelRemoteHost = tunnelRemoteHost;
        this.tunnelLocalPort = tunnelLocalPort;
    }

    /**
     * Establish SSH connection and set port forwarding L
     *
     * @return assigned port from connection
     */
    public int connect() throws JSchException{
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);

            localUserInfo lui = new localUserInfo();
            session.setUserInfo(lui);

            //CONNECT SSH
            System.out.println("Establishing SSH connection to " +
                    host + " : " + port);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect(3000);

            //DO PORT FORWARDING
            int assingedPort = session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
            System.out.println("Connected");
            System.out.println("localhost:" + assingedPort + " -> " + host + ":" + port + "/"
                    + tunnelRemoteHost + ":" + tunnelRemotePort);
            return assingedPort;

        } catch (JSchException e) {
            System.out.println("SSH connection error");
//            e.printStackTrace();
            throw e;
        }
//        exit(-1);
    }

    class localUserInfo implements UserInfo {
        String passwd;

        public String getPassword() {
            return passwd;
        }

        public boolean promptYesNo(String str) {
            return true;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return true;
        }

        public boolean promptPassword(String message) {
            return true;
        }

        public void showMessage(String message) {
        }
    }
}

