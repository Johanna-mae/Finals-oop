package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;

class Register extends JPanel{
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);
    
    JButton selected = null;
    
    public Register() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("User Registration");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);
        
        JLabel subtitle = new JLabel("Create a new user account");
        subtitle.setBounds(23, 55, 400, 20);
        add(subtitle);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        //for the form
        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(NORMAL);
        box.setBounds(170, 135, 750, 200);
        add(box);

        JLabel lbl1 = new JLabel("Username");
        lbl1.setBounds(20, 30, 150, 30);
        lbl1.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl1);

        JTextField tf1 = new JTextField();
        tf1.setBounds(180, 30, 550, 30);
        box.add(tf1);

        JLabel lbl2 = new JLabel("Password");
        lbl2.setBounds(20, 80, 150, 30);
        lbl2.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl2);

        JPasswordField tf2 = new JPasswordField();
        tf2.setBounds(180, 80, 550, 30);
        box.add(tf2);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(180, 130, 120, 40);
        registerBtn.setBackground(NORMAL);
        box.add(registerBtn);
        
        setVisible(true);
        
        //functions ng button mouselistener
        registerBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != registerBtn) {
                    registerBtn.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != registerBtn) {
                    registerBtn.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = registerBtn;
                registerBtn.setBackground(ACTIVE);
                
                // Get user input
                String username = tf1.getText().trim();
                String password = new String(tf2.getPassword());
                
                // Validate input
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "Please fill in all fields!", 
                        "Validation Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(null, 
                        "Password must be at least 6 characters!", 
                        "Validation Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    // Hash the password using BCrypt (includes automatic salting)
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
                    
                    // Save to database
                    
                    String query = "INSERT INTO employee (username, password_hash) VALUES (?, ?)";
                     Connection conn = DatabaseConnection.getConnection();
                    System.out.println(conn);

                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, username);
                    ps.setString(2, hashedPassword);
                    
                    int result = ps.executeUpdate();
                    
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, 
                            "User registered successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                        
                        // Clear fields
                        tf1.setText("");
                        tf2.setText("");
                    }
                    
                    ps.close();
                    
                } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                    JOptionPane.showMessageDialog(null, 
                        "Username already exists!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, 
                        "Registration failed: " + e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
    }
}
