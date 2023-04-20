package pt.ipb.dsys.com.multiuser;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import pt.ipb.dsys.com.proverb.ProverbProtocol;

public class HandleSocket implements Runnable {
    private Socket toHandle;

    public HandleSocket(Socket toHandle) {
        this.toHandle = toHandle;
    }

    @Override
    public void run() {
        System.out.printf("[%s:%d] connected!\n", this.toHandle.getInetAddress().getHostName(), this.toHandle.getPort());
        try (
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(this.toHandle.getInputStream()));
            PrintWriter output = new PrintWriter(this.toHandle.getOutputStream(), true);
        ) {
            String inputLine = null, proverbAnswer;
            ProverbProtocol proverb = new ProverbProtocol();
            do {
                proverbAnswer = proverb.processInput(inputLine);
                output.println(proverbAnswer);
            } while((inputLine = clientInput.readLine()) != null);
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
    }
}
