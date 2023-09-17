import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JournalApp {
    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JFrame entryFrame; // journal entry page
    private JTextArea journalTextArea; // Text area to display journal entries
    private List<JournalEntry> journalEntries; // List to store journal entries

    private JPanel panel; //  panel at the class level
    private JTextField titleField; //  titleField at the class level
    private JTextArea contentArea; // contentArea at the class level, 3 wotai declaration

    public JournalApp() {
        
        createLoginFrame();
        journalEntries = new ArrayList<>();
    }

    private void createLoginFrame() {
        loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        // Username text field
        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, gbc);

        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        // Password text field
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span the button across two columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (isValidLogin(username, password)) {
                    loginFrame.dispose();
                    openJournalEntryPage(username); // username lai journal entry page ma pass garni
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Login failed. Please try again.");
                }
            }
        });

        loginFrame.add(panel);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private boolean isValidLogin(String username, String password) {
        // Check if the username and password matches 
        return username.equals("test") && password.equals("test");
    }

    private void openJournalEntryPage(String username) {
        entryFrame = new JFrame("Journal Entry Page");
        entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("Add New Entry");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel);

        // Title label and text field
        JLabel titleLabelForm = new JLabel("Title:");
        panel.add(titleLabelForm);
        titleField = new JTextField(20); // Adjust the size of the title text box
        panel.add(titleField);

        // Content label and text area
        JLabel contentLabel = new JLabel("Content:");
        panel.add(contentLabel);
        contentArea = new JTextArea(10, 40); // Adjust the size of the content text area
        contentArea.setWrapStyleWord(true);
        contentArea.setLineWrap(true);
        panel.add(new JScrollPane(contentArea));

        // Add Entry button
        JButton addButton = new JButton("Add Entry");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the "Add Entry" button click
                String title = titleField.getText();
                String content = contentArea.getText();

                // Create a new journal entry
                JournalEntry newEntry = new JournalEntry(title, content);
                journalEntries.add(newEntry);

                // Refresh the display
                refreshJournalTextArea();
            }
        });

        // Journal text area to display entries
        journalTextArea = new JTextArea(20, 60);
        journalTextArea.setEditable(false); // read garna
        panel.add(new JScrollPane(journalTextArea));

        entryFrame.add(panel);
        entryFrame.setSize(800, 600);
        entryFrame.setLocationRelativeTo(null);
        entryFrame.setVisible(true);
    }

    private void refreshJournalTextArea() {
        // Clear and refresh the journalTextArea with updated entries
        journalTextArea.setText("");
        for (JournalEntry entry : journalEntries) {
            String entryText = "Title: " + entry.getTitle() + "\nContent:\n" + entry.getContent() + "\n\n";
            journalTextArea.append(entryText);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JournalApp();
            }
        });
    }
}

class JournalEntry {
    private String title;
    private String content;

    public JournalEntry(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
