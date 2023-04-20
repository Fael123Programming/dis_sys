package pt.ipb.dsys.com.intro;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientMain {

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(SocketServerMain.HOSTNAME, SocketServerMain.PORT)) {
            try (PrintWriter output = new PrintWriter(clientSocket.getOutputStream())) {
                String message = JOptionPane.showInputDialog(null, "Message to server?", "CLIENT", JOptionPane.QUESTION_MESSAGE);
                output.println(message);
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }
    }
}
