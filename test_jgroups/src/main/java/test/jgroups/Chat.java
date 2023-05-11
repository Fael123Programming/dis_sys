package test.jgroups;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.View;
// import java.util.Scanner;
import javax.swing.JOptionPane;

public class Chat implements Receiver {
    private JChannel channel;
    private String username;

    public Chat(String username, String channel) {
        this.username = username;
        try {
            this.channel = new JChannel().setReceiver(this).connect(channel);
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getMessage());
            System.exit(-1);
        }
    }

    public void start() {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userMessage;
        // Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                // System.out.print("> ");
                // System.out.flush();
                userMessage = JOptionPane.showInputDialog(
                        null, "Text box (quit to finish)", this.username,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (userMessage == null || userMessage.startsWith("quit") || userMessage.startsWith("exit")) {
                    break;
                }
                userMessage = "[" + this.username + "] " + userMessage;
                this.channel.send(new ObjectMessage(null, userMessage));
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                this.channel.close();
                // scanner.close();
                System.exit(-1);
            }
        }
        this.channel.close();
        // scanner.close();
    }

    @Override
    public void receive(Message msg) {
        System.out.println("[" + msg.getSrc() + "] " + msg.getObject());
        System.out.flush();
    }

    @Override
    public void viewAccepted(View view) {
        System.out.println("Group cluster status: " + view);
    }
}
