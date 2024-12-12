package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.classes.Books;
import model.classes.ConnectionManager;

public class BookListControllers {
    public static ArrayList<Books> getBuku() {
        ArrayList<Books> books = new ArrayList<>();
        Books book = null;
        String query = "SELECT * FROM books";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            try (ResultSet rs = st.executeQuery()) {
                while(rs.next()) {
                    book = new Books();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setGenre(rs.getString("genre"));
                    book.setPrice(rs.getInt("price"));
                    books.add(book);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat mengambil data: " + ex.getMessage());
        }
        return books;
    }
}
