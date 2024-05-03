// CryptoListFrame.java
package forex;

import javax.swing.*;
import java.awt.*;
import javax.swing.JScrollPane;

public class CryptoListFrame extends JFrame {

    public CryptoListFrame(String cryptoList) {
        setTitle("Cryptocurrency List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setResizable(true);

        JTextArea cryptoListArea = new JTextArea();
        cryptoListArea.setEditable(false);

        // Display the fetched cryptocurrency list in the JTextArea
        cryptoListArea.setText(cryptoList);

        JScrollPane scrollPane = new JScrollPane(cryptoListArea);
        add(scrollPane);
    }
}
