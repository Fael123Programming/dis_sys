package test.jgroups;

public class App {
    public static void main(String[] args) {
        Chat chat = new Chat("Jesus", "ChatCluster");
        chat.start();
    }
}
