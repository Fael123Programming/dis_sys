package pt.ipb.dsys.com.proverb;

import javax.swing.JOptionPane;

public class TestProverbProtocol {
    public static void main(String[] args) {
        ProverbProtocol pp = new ProverbProtocol();
        String clientAnswer = JOptionPane.showInputDialog("Are you ready player 1?");
        System.out.println(clientAnswer);
        // System.out.println(pp.processInput(null));
    }
}
