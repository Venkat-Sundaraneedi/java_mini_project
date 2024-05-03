package forex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SettingsFrame extends JFrame {

    //private final JTextField nameField;
    JCheckBox indianCheckBox;
    private final JRadioButton maleRadioButton;
    private final JLabel selectedDateLabel;

    public SettingsFrame(String username) {
        setTitle("Settings");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Initialize components
        JPanel panel = new JPanel(new GridLayout(7, 2));
        //
        JLabel nameLabel = new JLabel(username);
       // JLabel nameLabel = new JLabel("Name:");


        JLabel genderLabel = new JLabel("Gender:");
        maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        JRadioButton otherRadioButton = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);


         indianCheckBox = new JCheckBox("Indian");
        JCheckBox internationalCheckBox = new JCheckBox("International");
        JLabel nationalityLabel = new JLabel("Nationality:");
        JMenuBar menuBar = new JMenuBar();
        JMenu dateMenu = new JMenu("Date");
        JMenuItem pickDateMenuItem = new JMenuItem("Pick Date");
        selectedDateLabel = new JLabel("Selected Date: ");

        // Add components to panel
        panel.add(nameLabel);
        //panel.add(nameField);
        panel.add(genderLabel);
        panel.add(maleRadioButton);
        panel.add(new JLabel()); // Placeholder for alignment
        panel.add(femaleRadioButton);
        panel.add(new JLabel()); // Placeholder for alignment
        panel.add(otherRadioButton);
        panel.add(nationalityLabel);
        panel.add(indianCheckBox);
        panel.add(new JLabel()); // Placeholder for alignment
        panel.add(internationalCheckBox);
        panel.add(selectedDateLabel);

        // Add menu items to menu
        dateMenu.add(pickDateMenuItem);
        menuBar.add(dateMenu);
        setJMenuBar(menuBar);

        // Add action listener to pick date menu item
        pickDateMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate selectedDate = showDatePicker();
                if (selectedDate != null) {
                    selectedDateLabel.setText(STR."Selected Date: \{selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}");
                }
            }
        });

        // Load user details
        loadUserDetails(username);

        // Add panel to frame
        add(panel);
    }

    private LocalDate showDatePicker() {
        String input = JOptionPane.showInputDialog(SettingsFrame.this, "Enter date (yyyy-MM-dd):");
        if (input != null) {
            try {
                return LocalDate.parse(input);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(SettingsFrame.this, "Invalid date format. Please enter date in yyyy-MM-dd format.");
            }
        }
        return null;
    }

    private void loadUserDetails(String username) {
        // Load user details from database or any other source
        // For demonstration, let's assume we have fetched the user details
        // and set them in the UI components
        //nameField.setText(username);
        maleRadioButton.setSelected(true); // Assume default selection is male
        indianCheckBox.setSelected(true); // Assume default nationality is Indian
    }

}
