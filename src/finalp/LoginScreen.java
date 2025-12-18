package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



class LoginScreen extends JFrame{
    private Frame mainFrame;
    
    public void setMainFrame(Frame frame) {
        this.mainFrame = frame;
    }
    
    public LoginScreen(){
        add(createLoginPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon image = new ImageIcon(Finalp.class.getResource("/finalp/images/logo-icon.png"));
        setIconImage(image.getImage());
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
         
        ImageIcon originalImage = new ImageIcon(getClass().getResource("images/bg-loan-image.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(960, 720, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundImageJLabel = new JLabel(scaledIcon);
        backgroundImageJLabel.setBounds(0, 0, 960, 720);
        backgroundImageJLabel.setLayout(null);

        JPanel boundingBox = new JPanel();
        boundingBox.setBounds(130, 110, 700, 500);
        boundingBox.setBackground(new Color(255, 255, 255, 215));
        boundingBox.setLayout(null);

        JLabel title = new JLabel("PaLoan System");
        title.setBounds(170, 65, 500, 56);
        title.setFont(new Font("Sans Serif", Font.BOLD, 50));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(190, 200, 165, 40);
        usernameLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));       
        
        JTextField usernameField = new JTextField();
        usernameField.setBounds(360, 205, 150, 30);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(190, 250, 165, 40);
        passwordLabel.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(360, 255, 150, 30);
        
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(220, 380, 275, 64);
        loginButton.setFont(new Font("Sans Serif", Font.BOLD, 24));
        loginButton.setBackground(new Color(0xAAC3DD));
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainFrame.setVisible(true);
                
                dispose();
            }
        });
        
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                    loginButton.setBackground(new Color(0x8AA1B9));
            }
            public void mouseExited(MouseEvent e) {
                    loginButton.setBackground(new Color(0xAAC3DD));
            }
        });
        
        //panel.add(backgroundImageJLabel);
        boundingBox.add(title);
        boundingBox.add(usernameLabel);
        boundingBox.add(usernameField);
        boundingBox.add(passwordLabel);
        boundingBox.add(passwordField);
        boundingBox.add(new JLabel());
        boundingBox.add(loginButton);

        backgroundImageJLabel.add(boundingBox);

        panel.add(backgroundImageJLabel);

        return panel;
    }
}