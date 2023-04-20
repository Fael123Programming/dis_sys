package pt.ipb.dsys.com.workspace;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import pt.ipb.dsys.com.proverb.ProverbProtocol;

public class ProverbClientMain {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(ProverbServerMain.HOSTNAME, ProverbServerMain.PORT)) {
            try (
                BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {
                String serverMessage, clientAnswer;
                while ((serverMessage = serverReader.readLine()) != null) {
                    clientAnswer = JOptionPane.showInputDialog(serverMessage);
                    if (serverMessage.equals(ProverbProtocol.EXIT_MESSAGE)) {
                        break;
                    }
                    writer.println(clientAnswer);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        System.exit(0);
    }
}
