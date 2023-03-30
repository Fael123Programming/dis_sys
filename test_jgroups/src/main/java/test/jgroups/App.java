package test.jgroups;

import org.jgroups.JChannel;

public class App {
    JChannel channel;
    String username = System.getProperty("user.name", "n/a");

    public static void main( String[] args ) throws Exception {
        new App().start();
    }

    private void start() throws Exception {
        channel = new JChannel();
        channel.connect("ChatCluster");
    }
}
