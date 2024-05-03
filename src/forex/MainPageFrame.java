package forex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPageFrame extends JFrame {
     //LoginFrame loginFrame = new LoginFrame();
     static String username ;

    public static void nameOfLogin(String name){
        username = name;
    }

    public MainPageFrame() {
        setTitle("Main Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setResizable(true);

        // Create buttons
        JButton cryptoListButton = new JButton("Crypto List");
        JButton settingsButton = new JButton("Settings");

        // Add action listeners to the buttons
        cryptoListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCryptoListFrame();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                   SettingsFrame settingsFrame = new SettingsFrame(username);
                    settingsFrame.setVisible(true);

            }
        });

        // Create panel to hold the buttons
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(cryptoListButton);
        panel.add(settingsButton);

        // Add panel to the frame
        add(panel);
    }

    private void openCryptoListFrame() {
        // Fetch cryptocurrency list using TraderMadeAPIClient
        String cryptoList = TraderMadeAPIClient.fetchData();

        // Create and display the cryptocurrency list frame
        new CryptoListFrame(cryptoList).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPageFrame().setVisible(true);
            }
        });
    }
}
