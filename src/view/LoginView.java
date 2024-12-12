package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.classes.Users;

public class LoginView extends JFrame {
    JFrame frame;
    JTextField emailField;
    JPasswordField passwordField;
    JButton login;

    public LoginView() {
        viewLogin();
    }

    private void viewLogin() {
        
        frame = new JFrame("Login");
        frame.setLayout(new BorderLayout());

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

        JLabel emailTxt = new JLabel("Email");
        emailTxt.setBounds(20, 100, 400, 25);
        emailField = new JTextField();
        emailField.setBounds(120, 100, 400, 25);
        mainPanel.add(emailTxt);
        mainPanel.add(emailField);
        
        JLabel passwordTxt = new JLabel("Password");
        passwordField = new JPasswordField();
        passwordTxt.setBounds(20, 125, 400, 25);
        passwordField.setBounds(120, 125, 400, 25);
        mainPanel.add(passwordTxt);
        mainPanel.add(passwordField);

        login = new JButton("Login");
        login.setBounds(20, 150, 100, 25);
        mainPanel.add(login);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println(password);
                int hasilLogin = Users.login(email, password);
                Users user = Users.getData(email, password);

                switch (hasilLogin) {
                    case 0:
                        JOptionPane.showMessageDialog(mainPanel, "Login Gagal");
                        frame.dispose();
                        break;
                    case 1:
                        new BookListView(user);
                        frame.dispose();
                        break;
                    default:
                        break;
                }
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

}
