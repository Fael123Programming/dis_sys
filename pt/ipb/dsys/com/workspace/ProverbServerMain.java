package pt.ipb.dsys.com.workspace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import pt.ipb.dsys.com.proverb.ProverbProtocol;

public class ProverbServerMain {
    protected static String HOSTNAME = "127.0.0.1";
    protected static int PORT = 4444;

    public static void main(String[] args) throws IOException {
        try {
            InetAddress address = InetAddress.getByName(HOSTNAME);
            try (ServerSocket serverSocket = new ServerSocket(PORT, 50, address)) {
                System.out.printf("Waiting for connection on %s:%d\n", HOSTNAME, PORT);
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.printf("[%s:%d] connected!\n", clientSocket.getInetAddress().getHostName(), clientSocket.getPort());
                    try (
                        BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                    ) {
                        String inputLine = null, proverbAnswer;
                        ProverbProtocol proverb = new ProverbProtocol();
                        do {
                            proverbAnswer = proverb.processInput(inputLine);
                            output.println(proverbAnswer);
                        } while((inputLine = clientInput.readLine()) != null);
                    }
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
