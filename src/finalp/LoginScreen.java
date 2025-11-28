package finalp;

import java.awt.*;
import javax.swing.*;


class LoginScreen extends JFrame{
    public LoginScreen(){
        add(createLoginPanel());
        //add(backgroundImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel backgroundImage() {
        JLabel bgImg = new JLabel();
        ImageIcon bgImageIcon = new ImageIcon("scott-graham-OQMZwNd3ThU-unsplash.jpg");
        bgImg.setIcon(bgImageIcon);
        bgImg.setLayout(new BorderLayout());

        return bgImg;
    }
    

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
    
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 10, 150, 30);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(10, 45, 150, 30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 75, 150, 30);
        JTextField passwordField = new JTextField();
        passwordField.setBounds(10, 105, 150, 30);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 140, 75, 30);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        panel.setBackground(Color.red);

        return panel;
    }
}
