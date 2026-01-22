package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

class Register extends JPanel{
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);
    
    JButton selected = null;
    
    public Register() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Employee Registration");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);
        
        JLabel subtitle = new JLabel("Create a new employee account");
        subtitle.setBounds(23, 55, 400, 20);
        add(subtitle);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        //for the form
        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(NORMAL);
        box.setBounds(170, 135, 750, 400);
        add(box);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(20, 30, 150, 30);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lblUsername);

        JTextField tfUsername = new JTextField();
        tfUsername.setBounds(180, 30, 550, 30);
        box.add(tfUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(20, 80, 150, 30);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lblPassword);

        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setBounds(180, 80, 550, 30);
        box.add(tfPassword);

        JLabel lblFirstName = new JLabel("First name");
        lblFirstName.setBounds(20, 130, 150, 30);
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lblFirstName);

        JTextField tfFirstName = new JTextField();
        tfFirstName.setBounds(180, 130, 550, 30);
        box.add(tfFirstName);

        JLabel lblLastName = new JLabel("Last name");
        lblLastName.setBounds(20, 180, 150, 30);
        lblLastName.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lblLastName);

        JTextField tfLastName = new JTextField();
        tfLastName.setBounds(180, 180, 550, 30);
        box.add(tfLastName);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(20, 230, 150, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lblEmail);

        JTextField tfEmail = new JTextField();
        tfEmail.setBounds(180, 230, 550, 30);
        box.add(tfEmail);

        JLabel lblEmployeeRoles = new JLabel("Employee's Role");
        lblEmployeeRoles.setBounds(20, 280, 150, 30);
        box.add(lblEmployeeRoles);

        JComboBox<String> cbEmployeeRoles = new JComboBox<String>(new String[]{"Admin", "Manager", "Loan Officer", "Cashier"});
        cbEmployeeRoles.setBounds(180, 280, 550, 30);
        box.add(cbEmployeeRoles);

        JLabel lblIsActive = new JLabel("Is Active");
        lblIsActive.setBounds(20, 330, 150, 30);
        box.add(lblIsActive);

        JCheckBox ckbIsActive = new JCheckBox();
        ckbIsActive.setBounds(180, 335, 20, 20); 
        box.add(ckbIsActive);
        
        LocalDate dateHired = LocalDate.now();

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(600, 330, 120, 40);
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
                String username = tfUsername.getText().trim();
                String password = new String(tfPassword.getPassword());
                String firstName = tfFirstName.getText().trim();
                String lastname = tfLastName.getText().trim();
                String email = tfEmail.getText().trim();
                String employeeRole = (String) cbEmployeeRoles.getSelectedItem();
                String isActive = String.valueOf(ckbIsActive.isSelected());
                int isActiveNum = 0;

                switch (isActive) {
                    case "true":
                        isActiveNum = 1;
                        break;
                    case "false":
                        isActiveNum = 0;
                        break;
                    default:
                        break;
                }

                String employeeRefNo = ReferenceNumberGenerator.generateEmployeeRefNo(5);
                
                // Validate input
                if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastname.isEmpty() || email.isEmpty() /*|| ckbIsActive.isSelected()*/) {
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
                
                String query = "INSERT INTO Employee (username, password_hash, first_name, last_name, email, date_hired, employee_reference_number, role, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(query)) {

                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); 
                    
                    // Save to database
                    ps.setString(1, username);
                    ps.setString(2, hashedPassword);
                    ps.setString(3, firstName);
                    ps.setString(4, lastname);
                    ps.setString(5, email);
                    ps.setObject(6, dateHired);
                    ps.setString(7, employeeRefNo);
                    ps.setString(8, employeeRole);
                    ps.setInt(9, isActiveNum);
                    
                    int result = ps.executeUpdate();
                    
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, 
                            "User registered successfully!", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                        
                        // Clear fields
                        tfUsername.setText("");
                        tfPassword.setText("");
                        tfFirstName.setText("");
                        tfLastName.setText("");
                        tfEmail.setText("");
                        cbEmployeeRoles.setSelectedIndex(-1);
                        ckbIsActive.setSelected(false);
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
