package pt.ipb.dsys.com.proverb;

import javax.swing.*;

public class StandaloneProv {

  public static void main(String[] args) {

    ProverbProtocol protocol = new ProverbProtocol();
    String userInput = null;

    while (true) {
      String protocolResponse = protocol.processInput(userInput);
      if (ProverbProtocol.isEndGame(protocolResponse)) {
        JOptionPane.showMessageDialog(null, protocolResponse);
        break;
      } else {
        userInput = JOptionPane.showInputDialog(protocolResponse);
      }
    }

    System.exit(0);
  }
}
