import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    String genre;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + genre + ")";
    }
}

public class LibraryManagementSystem extends JFrame {
    private ArrayList<Book> books;
    private DefaultListModel<Book> bookListModel;
    private JList<Book> bookList;
    private JTextField titleField, authorField, genreField;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        bookListModel = new DefaultListModel<>();

        setTitle("Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre:");
        genreField = new JTextField(20);

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");

        bookList = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        add(titleLabel);
        add(titleField);
        add(authorLabel);
        add(authorField);
        add(genreLabel);
        add(genreField);
        add(addButton);
        add(viewButton);
        add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });

        setVisible(true);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();

        if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
            Book book = new Book(title, author, genre);
            books.add(book);
            bookListModel.addElement(book);
            titleField.setText("");
            authorField.setText("");
            genreField.setText("");
        }
    }

    private void displayBooks() {
        StringBuilder list = new StringBuilder();
        for (Book book : books) {
            list.append(book).append("\n");
        }
        JOptionPane.showMessageDialog(this, list.toString(), "Book List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}
