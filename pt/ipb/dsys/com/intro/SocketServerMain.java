package pt.ipb.dsys.com.intro;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketServerMain {

    public static final int PORT = 4444;

    public static final String HOSTNAME = "127.0.0.1";

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(HOSTNAME);
            try (ServerSocket serverSocket = new ServerSocket(PORT, 50, address)) {
                System.out.printf("Waiting for connection on %s:%d\n", HOSTNAME, PORT);
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.printf("[%s:%d] connected!\n", clientSocket.getInetAddress().getHostName(), clientSocket.getPort());
                    try (BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        String inputLine;
                        while ((inputLine = clientInput.readLine()) != null) {
                            JOptionPane.showMessageDialog(null, String.format("Message from client: %s\n", inputLine), "SERVER", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.out.printf("Invalid Address: %s\n", HOSTNAME);
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }
    }
}
