package org.example;

import org.jgroups.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleChat implements Receiver {
    JChannel channel;
    String username = System.getProperty("user.name", "n/a");
//        String username = "John";

    public static void main(String[] args) throws Exception {
        System.setProperty("jgroups.bind_addr", "127.0.0.1");
        new SimpleChat().start();
    }

    private void start() throws Exception {
        channel = new JChannel().setReceiver(this).connect("ChatCluster");
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Message msg;
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();
                line = reader.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }
                line = "[" + username + "] " + line;
                msg = new ObjectMessage(null, line);
                channel.send(msg);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

    public void viewAccepted(View view) {
        System.out.println("** View: " + view);
    }

    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }
}
