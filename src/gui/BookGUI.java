package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entities.Book;
import service.BookDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookGUI extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField titleField;
    private JTextField auteurField;
    private JTextField ISBNField;

    private JTextArea outputArea;
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;

    public BookDAO bookDAO;

    public BookGUI() {
        super("Book CRUD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        inputPanel.add(new JLabel("Auteur:"));
        auteurField = new JTextField(30);
        inputPanel.add(auteurField);

        inputPanel.add(new JLabel("Titre:"));
        titleField = new JTextField(30);
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("ISBN:"));
        ISBNField = new JTextField(30);
        inputPanel.add(ISBNField);

        createButton = new JButton("Create");
        createButton.addActionListener(this);
        inputPanel.add(createButton);

        readButton = new JButton("Read");
        readButton.addActionListener(this);
        inputPanel.add(readButton);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        inputPanel.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        inputPanel.add(deleteButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        bookDAO = new BookDAO();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == createButton) {
            //int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String auteur = auteurField.getText();
            String ISBN = ISBNField.getText();
            Book book = new Book(auteur,ISBN,title);
           
           BookDAO bookDAO = new BookDAO();
           bookDAO.createBook(book);
            outputArea.setText("Book created: " + book);
        } else if (event.getSource() == readButton) {
            int id = Integer.parseInt(idField.getText());
            Book book = bookDAO.readBook(id);
            if (book != null) {
                outputArea.setText("Book found: " + book);
                titleField.setText(book.getTitre());
                auteurField.setText(book.getAuteur());
                ISBNField.setText(book.getISBN());
            } else {
                outputArea.setText("Book not found");
            }
        } else if (event.getSource() == updateButton) {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String auteur = auteurField.getText();
            String ISBN = ISBNField.getText();
            Book book = new Book(auteur,ISBN,title);
            bookDAO.updateBook(book, id);
            outputArea.setText("Book updated: " + book);
        } else if (event.getSource() == deleteButton) {
            int id = Integer.parseInt(idField.getText());
            bookDAO.deleteBook(id);
            outputArea.setText("Book deleted");
            idField.setText("");
            titleField.setText("");
            auteurField.setText("");
            ISBNField.setText("");
        }
    }

    public static void main(String[] args) {
        BookGUI gui = new BookGUI();
        gui.setVisible(true);
    }
}