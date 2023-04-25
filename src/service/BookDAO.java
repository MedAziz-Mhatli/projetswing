package service;

import java.sql.*;
import java.util.ArrayList;

import entities.Book;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        try {
            //Création de la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libb", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Méthode pour créer un livre dans la base de données
    public void createBook(Book book) {
        try {
            //Préparation de la requête SQL
            PreparedStatement statement = connection.prepareStatement("INSERT INTO book VALUES (?, ?, ?)");

            //Définition des paramètres de la requête
            //statement.setInt(1, book.getId());
            statement.setString(1, book.getAuteur());
            statement.setString(2, book.getISBN());
            statement.setString(3, book.getTitre());

            //Exécution de la requête
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Méthode pour lire un livre dans la base de données
    public Book readBook(int id) {
        Book book = null;
        try {
            //Préparation de la requête SQL
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE id = ?");

            //Définition des paramètres de la requête
            statement.setInt(1, id);

            //Exécution de la requête
            ResultSet result = statement.executeQuery();

            //Traitement des résultats
            if (result.next()) {
                book = new Book( result.getString("auteur"), result.getString("ISBN"),result.getString("titre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //Méthode pour mettre à jour un livre dans la base de données
    public void updateBook(Book book,int id) {
        try {
            //Préparation de la requête SQL
            PreparedStatement statement = connection.prepareStatement("UPDATE book SET  auteur = ?,ISBN = ?,titre = ? WHERE id ="+id);

            //Définition des paramètres de la requête
            statement.setString(1, book.getAuteur());
            statement.setString(2, book.getISBN());
            statement.setString(3, book.getTitre());
            
            //Exécution de la requête
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Méthode pour supprimer un livre de la base de données
    public void deleteBook(int id) {
        try {
            //Préparation de la requête SQL
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book WHERE id = ?");

            //Définition des paramètres de la requête
            statement.setInt(1, id);

            //Exécution de la requête
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Méthode pour récupérer tous les livres de la base de données
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            //Préparation de la requête SQL
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM book");

            //Exécution de la requête
            ResultSet result = statement.executeQuery();

            //Traitement des résultats
            while (result.next()) {
            Book book = new Book( result.getString("auteur"), result.getString("ISBN"),result.getString("titre"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}