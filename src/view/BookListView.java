package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import controller.BookListControllers;
import model.classes.Books;
import model.classes.Users;

public class BookListView extends JFrame{
    JFrame frame;
    JButton transactions;

    
    public BookListView(Users user){
        viewBookList(user);
    }

    private void viewBookList(Users user){
        frame = new JFrame("Book Menu");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        final int frameWidth = 800;
        final int frameHeight = 500;

        int frameX = (screenWidth / 2) - (frameWidth / 2);
        int frameY = (screenHeight / 2) - (frameHeight / 2);

        frame.setBounds(frameX, frameY, frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, frameWidth, frameHeight);

        transactions = new JButton("Transactions");
        transactions.setBounds((frameWidth/2)-50, 25, 100, 25);
        mainPanel.add(transactions);

        ArrayList<Books> books = BookListControllers.getBuku();

        for (Books buku : books) {
            JTextField titleTxt = new JTextField(buku.getTitle());
            JTextField authorTxt = new JTextField(buku.getAuthor());
            JTextField genreTxt = new JTextField(buku.getGenre());
            JTextField priceTxt = new JTextField(buku.getPrice());
            mainPanel.add(titleTxt);
            mainPanel.add(authorTxt);
            mainPanel.add(genreTxt);
            mainPanel.add(priceTxt);
        }

        transactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionsView();
                frame.dispose();
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
}   
