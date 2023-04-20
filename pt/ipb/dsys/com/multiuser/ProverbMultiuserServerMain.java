package pt.ipb.dsys.com.multiuser;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ProverbMultiuserServerMain {
    protected static String HOSTNAME = "127.0.0.1";
    protected static int PORT = 4444;

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(HOSTNAME);
            try (ServerSocket serverSocket = new ServerSocket(PORT, 50, address)) {
                System.out.printf("Waiting for connection on %s:%d\n", HOSTNAME, PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new HandleSocket(clientSocket)).start();
                }
            }
        } catch (UnknownHostException e) {
            System.out.printf("Invalid Address: %s\n", HOSTNAME);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        System.exit(0);
    }
}
