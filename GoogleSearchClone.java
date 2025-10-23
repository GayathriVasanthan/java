import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GoogleSearchClone extends JFrame {
    private JTextField searchInput;
    private JButton searchButton;
    private JTextArea searchResults;

    public GoogleSearchClone() {
        setTitle("Google Search Clone");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the input field and search button
        searchInput = new JTextField();
        searchButton = new JButton("Search");
        searchResults = new JTextArea();
        searchResults.setEditable(false);

        // Style the components
        searchInput.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 16));
        searchResults.setFont(new Font("Arial", Font.PLAIN, 14));
        searchResults.setBackground(new Color(245, 245, 245));

        // Action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String query = searchInput.getText().trim();
                if (!query.isEmpty()) {
                    performSearch(query);
                }
            }
        });

        // Layout the components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(searchInput, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(searchResults);
        scrollPane.setPreferredSize(new Dimension(450, 400));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void performSearch(String query) {
        // Clear previous search results
        searchResults.setText("");

        // Simulate search results (fake results)
        for (int i = 1; i <= 5; i++) {
            String result = String.format("Result %d: %s - This is a fake result.%n", i, query);
            searchResults.append(result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GoogleSearchClone().setVisible(true);
            }
        });
    }
}
