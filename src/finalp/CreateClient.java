package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;
import java.sql.Connection;

class CreateClient extends JPanel{
    CardLayout card;
    JPanel container;

    // STEP 1 FIELDS
    JTextField fn, mn, ln, dob, email, contact;
    JTextField street, brgy, city, province;
    JTextField employer, validIdNo;
    JRadioButton male, female;
    JComboBox<String> civil, empStatus, income, validId;

    JTextArea verifyArea;
    
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);
    
    JButton selected = null;
    
    public CreateClient(){
        setLayout(null); //outside the form
        setBounds(280, 0, 1090, 800);
        setBackground(new Color(0xFFFFFF));

        JLabel header = new JLabel("Create Client Record");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("Enter details to avail loan");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        
        card = new CardLayout();
        container = new JPanel(card);
        container.setBounds(60, 110, 965, 400);
        container.setBackground(NORMAL);
        add(container);

        container.add(step1(), "step1");
        container.add(step2(), "step2");
        container.add(step3(), "step3");

        card.show(container, "step1");
 
        setVisible(true);
    }
    
    /* ===== STEP 1 ===== */
    JPanel step1() {
        JPanel p = new JPanel(null);
        p.setBackground(NORMAL);
        
        
        // ===== FIRST NAME =====
        JLabel firstName = new JLabel("First Name");
        firstName.setBounds(60, 28, 100, 30);
        firstName.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(firstName);

        fn = new JTextField();
        fn.setBounds(160, 28, 150, 30);
        p.add(fn);

        // ===== MIDDLE NAME =====
        JLabel lblMn = new JLabel("Middle Name");
        lblMn.setBounds(340, 28, 100, 30);
        lblMn.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblMn);

        mn = new JTextField();
        mn.setBounds(460, 28, 150, 30);
        p.add(mn);

        // ===== LAST NAME =====
        JLabel lblLn = new JLabel("Last Name");
        lblLn.setBounds(640, 28, 100, 30);
        lblLn.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblLn);

        ln = new JTextField();
        ln.setBounds(740, 28, 150, 30);
        p.add(ln);

        
        
        // ===== SEX =====
        JLabel lblSex = new JLabel("Sex");
        lblSex.setBounds(60, 75, 150, 30);
        lblSex.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblSex);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(160, 75, 80, 30);
        male.setBackground(NORMAL);
        female.setBounds(260, 75, 80, 30);
        female.setBackground(NORMAL);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        p.add(male);
        p.add(female);

        // ===== DATE OF BIRTH =====
        JLabel lblDob = new JLabel("Date of Birth");
        lblDob.setBounds(340, 75, 150, 30);
        lblDob.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblDob);

        dob = new JTextField(); 
        dob.setBounds(460, 75, 150, 30);
        p.add(dob);

        // ===== EMAIL =====
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(640, 75, 100, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblEmail);

        email = new JTextField();
        email.setBounds(740, 75, 150, 30);
        p.add(email);

        
        
        // ===== CONTACT =====
        JLabel lblContact = new JLabel("Contact No.");
        lblContact.setBounds(60, 122, 150, 30);
        lblContact.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblContact);

        contact = new JTextField();
        contact.setBounds(160, 122, 150, 30);
        p.add(contact);

        // ===== STREET =====
        JLabel lblStreet = new JLabel("Street");
        lblStreet.setBounds(340, 122, 150, 30);
        lblStreet.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblStreet);

        street = new JTextField();
        street.setBounds(460, 122, 150, 30);
        p.add(street);

        // ===== BARANGAY =====
        JLabel lblBrgy = new JLabel("Barangay");
        lblBrgy.setBounds(640, 122, 150, 30);
        lblBrgy.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblBrgy);

        brgy = new JTextField();
        brgy.setBounds(740, 122, 150, 30);
        p.add(brgy);

        // ===== CITY =====
        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(60, 174, 100, 30);
        lblCity.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblCity);

        city = new JTextField();
        city.setBounds(160, 169, 150, 30);
        p.add(city);

        // ===== PROVINCE =====
        JLabel lblProvince = new JLabel("Province");
        lblProvince.setBounds(340, 169, 100, 30);
        lblProvince.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblProvince);

        province = new JTextField();
        province.setBounds(460, 169, 150, 30);
        p.add(province);

        // ===== CIVIL STATUS =====
        JLabel lblCivil = new JLabel("Civil Status");
        lblCivil.setBounds(640, 169, 100, 30);
        lblCivil.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblCivil);

        String[] civilStatuses = {
            "Single",
            "Married",
            "Separated",
            "Widowed"
        };

        civil = new JComboBox<>(civilStatuses);
        civil.setBounds(740, 169, 150, 30);
        civil.setBackground(NORMAL);
        p.add(civil);

        
        
        // ===== EMPLOYMENT STATUS =====
        JLabel lblEmpStatus = new JLabel("Employment Status");
        lblEmpStatus.setBounds(60, 216, 160, 30);
        lblEmpStatus.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblEmpStatus);
        
        String[] empStatuses = {
            "Employed",
            "Self-Employed",
            "Unemployed",
            "Student",
            "Retired"
        };

        empStatus = new JComboBox<>(empStatuses);
        empStatus.setBounds(220, 216, 150, 30);
        empStatus.setBackground(NORMAL);
        p.add(empStatus);

        // ===== EMPLOYER NAME =====
        JLabel lblEmployer = new JLabel("Employer Name");
        lblEmployer.setBounds(400, 216, 160, 30);
        lblEmployer.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblEmployer);

        employer = new JTextField();
        employer.setBounds(530, 216, 150, 30);
        p.add(employer);

        
        
        // ===== MONTHLY INCOME =====
        JLabel lblIncome = new JLabel("Monthly Income");
        lblIncome.setBounds(60, 263, 160, 30);
        lblIncome.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblIncome);

        String[] incomeRanges = {
            "Below ₱10,000",
            "₱10,000 – ₱20,000",
            "₱20,001 – ₱30,000",
            "₱30,001 – ₱50,000",
            "₱50,001 – ₱100,000",
            "Above ₱100,000"
        };

        income = new JComboBox<>(incomeRanges);
        income.setBounds(200, 263, 150, 30);
        income.setBackground(NORMAL);
        p.add(income);

        // ===== VALID ID =====
        JLabel lblValidId = new JLabel("Valid ID");
        lblValidId.setBounds(380, 263, 150, 30);
        lblValidId.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblValidId);

        String[] validIds = {
            "Passport",
            "Driver’s License",
            "UMID",
            "PhilSys National ID",
            "SSS ID",
            "GSIS ID",
            "Voter’s ID",
            "Postal ID",
            "PRC ID"
        };

        validId = new JComboBox<>(validIds);
        validId.setBounds(460, 263, 150, 30);
        validId.setBackground(NORMAL);
        p.add(validId);
        
        // ===== VALID ID NUMBER =====
        JLabel lblValidIdNo = new JLabel("Valid ID No.");
        lblValidIdNo.setBounds(640, 263, 150, 30);
        lblValidIdNo.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblValidIdNo);

        validIdNo = new JTextField();
        validIdNo.setBounds(740, 263, 150, 30);
        p.add(validIdNo);
        
        

        // ===== 1ST NEXT BUTTON =====
        JButton next = new JButton("Next");
        next.setBounds(840, 350, 100, 35);
        next.setBackground(NORMAL);
        p.add(next);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent z) {
                boolean isFNNull = CheckIfNull.isTextFieldNull(fn);
                boolean isMNNull = CheckIfNull.isTextFieldNull(mn);
                boolean isLNNull = CheckIfNull.isTextFieldNull(ln);
                boolean isDOBNull = CheckIfNull.isTextFieldNull(dob);
                boolean isEmailNull = CheckIfNull.isTextFieldNull(email);
                boolean isContactNull = CheckIfNull.isTextFieldNull(contact);
                boolean isStreetNull = CheckIfNull.isTextFieldNull(street);
                boolean isBrgyNull = CheckIfNull.isTextFieldNull(brgy);
                boolean isProvinceNull = CheckIfNull.isTextFieldNull(province);
                boolean isValidIDNumberNull = CheckIfNull.isTextFieldNull(validIdNo);

                if (Stream.of(isFNNull, isMNNull, isLNNull, isDOBNull, isEmailNull, isContactNull, isStreetNull, isBrgyNull, isProvinceNull, isValidIDNumberNull).allMatch(b -> b == true) && (male.isSelected() || female.isSelected())) {
                    card.show(container, "step2");
                } else {
                    JOptionPane.showMessageDialog(
                    CreateClient.this,
                    "There is an empty field. Make sure there are no empty fields",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE
                );
                }
            }
        });
        
        next.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                next.setBackground(ACTIVE);
            }

            public void mouseExited(MouseEvent e) {
                next.setBackground(NORMAL);
            }

            public void mousePressed(MouseEvent e) {
                next.setBackground(ACTIVE);
            }
        });
        
        return p;
    }
    
    /* ===== STEP 2 ===== */
    JPanel step2() {
        JPanel p = new JPanel(null);
        p.setBackground(NORMAL);

        JTextArea text = new JTextArea(
                "DATA PRIVACY ACT\n\n" +
                "We collect and process your personal information in accordance with the Data Privacy Act of 2012. \n" +
                "The information you provide will be used solely for loan processing, credit evaluation, identity verification, \n" +
                "and legal compliance. Reasonable security measures are implemented to protect your data.\n\n" +
                "TERMS AND CONDITIONS\n\n" +
                "By proceeding, you confirm that all information provided is true and correct. \n" +
                "Loan approval is subject to evaluation. Interest rates, fees, and repayment terms \n" +
                "will be disclosed prior to approval. Late payments may incur penalties. \n" +
                "This agreement is governed by Philippine laws."
        );
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(new Font("Arial", Font.PLAIN, 15));


        JScrollPane sp = new JScrollPane(text);
        sp.setBounds(20, 20, 925, 290);
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        p.add(sp);

        JCheckBox dp = new JCheckBox("I agree to the Data Privacy Act");
        JCheckBox tc = new JCheckBox("I agree to the Terms and Conditions");

        dp.setBounds(20, 320, 300, 25);
        tc.setBounds(20, 345, 350, 25);
        dp.setBackground(NORMAL);
        tc.setBackground(NORMAL);

        JButton next = new JButton("Next");
        next.setBounds(840, 350, 100, 35);
        next.setBackground(NORMAL);
        next.setEnabled(false);

        ItemListener chk = e -> next.setEnabled(dp.isSelected() && tc.isSelected());
        dp.addItemListener(chk);
        tc.addItemListener(chk);

        p.add(dp); p.add(tc); p.add(next);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent y) {
                verifyArea.setText(getSummary());
                card.show(container, "step3");
            }
        });
        
        next.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                next.setBackground(ACTIVE);
            }

            public void mouseExited(MouseEvent e) {
                next.setBackground(NORMAL);
            }

            public void mousePressed(MouseEvent e) {
                next.setBackground(ACTIVE);
            }
        });
        
        return p;
    }
    
    /* ===== STEP 3 ===== */
    JPanel step3() {
        JPanel p = new JPanel(null);
        p.setBackground(NORMAL);

        verifyArea = new JTextArea();
        verifyArea.setEditable(false);
        verifyArea.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        JScrollPane sp = new JScrollPane(verifyArea);
        sp.setBounds(20, 20, 925, 290);
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        p.add(sp);

        JButton edit = new JButton("Edit Information");
        JButton create = new JButton("Create");

        edit.setBounds(670, 350, 150, 35);
        edit.setBackground(NORMAL);
        create.setBounds(840, 350, 100, 35);
        create.setBackground(NORMAL);

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent x){
                card.show(container, "step1");
            } 
        });
        
        edit.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                edit.setBackground(ACTIVE);
            }

            public void mouseExited(MouseEvent e) {
                edit.setBackground(NORMAL);
            }

            public void mousePressed(MouseEvent e) {
                edit.setBackground(ACTIVE);
            }
        });
        
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveClientRecord();
            }
        });
        
        create.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                create.setBackground(ACTIVE);
            }

            public void mouseExited(MouseEvent e) {
                create.setBackground(NORMAL);
            }

            public void mousePressed(MouseEvent e) {
                create.setBackground(ACTIVE);
            }
        });

        p.add(edit); p.add(create);
        return p;
    }

    /* =====================================================
       HELPERS
    ====================================================== */
    JLabel label(String t, int x, int y){
        JLabel l = new JLabel(t);
        l.setBounds(x,y,300,25);
        return l;
    }

    JTextField field(JPanel p, String lbl, int x, int y){
        JLabel l = new JLabel(lbl);
        JTextField f = new JTextField();
        l.setBounds(x,y,150,25);
        f.setBounds(180,y,350,25);
        p.add(l); p.add(f);
        return f;
    }

    String getSummary(){
        return
            "Name: " + fn.getText()+" "+mn.getText()+" "+ln.getText()+"\n"+
            "Sex: " + (male.isSelected() ? "Male":"Female") + "\n"+
            "DOB: " + dob.getText()+"\n"+
            "Email: " + email.getText()+"\n"+
            "Contact: " + contact.getText()+"\n"+
            "Address: " + street.getText()+", "+brgy.getText()+", "+city.getText()+", "+province.getText()+"\n"+
            "Civil Status: " + civil.getSelectedItem().toString()+"\n"+
            "Employment Status: " + empStatus.getSelectedItem().toString()+"\n"+
            "Employer: " + employer.getText()+"\n"+
            "Monthly Income: " + income.getSelectedItem().toString()+"\n"+
            "Valid ID: " + validId.getSelectedItem().toString()+"\n"+
            "Valid ID No.: " + validIdNo.getText();
    }

    public void saveClientRecord() {
        String firstNameLine = fn.getText();
        String middleNameLine = mn.getText();
        String lastNameLine = ln.getText();
        //sex part
        
        String emailLine = email.getText();
        String contactNoLine = contact.getText();
        String streetLine = street.getText();
        String brgyLine = brgy.getText();
        String cityLine = city.getText();
        String provinceLine = province.getText();
        String civilStatusLine = civil.getSelectedItem().toString();
        String employmentStatusLine = empStatus.getSelectedItem().toString();
        String employerNameLine = employer.getText();
        String incomeLine = income.getSelectedItem().toString();
        String validIDLine = validId.getSelectedItem().toString();
        String validIDNumberLine = validIdNo.getText();
        LocalDateTime timestamp = LocalDateTime.now();

        String query = """
            INSERT INTO Client (first_name, middle_name, last_name, date_of_birth, email, phone_number, address_line, barangay, city, province, 
                civil_status, employment_status, employer_name, monthly_income, valid_id_type, valid_ID_number, date_registered) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            String dateofBirthLine = dob.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateofBirthLine, formatter);

            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, firstNameLine);
            ps.setString(2, middleNameLine);
            ps.setString(3, lastNameLine);
            ps.setObject(4, localDate);
            ps.setString(5, emailLine);
            ps.setString(6, contactNoLine);
            ps.setString(7, streetLine);
            ps.setString(8, brgyLine);
            ps.setString(9, cityLine);
            ps.setString(10, provinceLine);
            ps.setString(11, civilStatusLine);
            ps.setString(12, employmentStatusLine);
            ps.setString(13, employerNameLine);
            ps.setString(14, incomeLine);
            ps.setString(15, validIDLine);
            ps.setString(16, validIDNumberLine);
            ps.setObject(17, timestamp);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(
                    CreateClient.this,
                    "Created Successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            ps.close();
            conn.close();
            card.show(container, "step1");
            fn.setText("");
            mn.setText("");
            ln.setText("");
            //sex part
            dob.setText("");
            email.setText("");
            contact.setText("");
            street.setText("");
            brgy.setText("");
            city.setText("");
            province.setText("");
            civil.setSelectedIndex(0);
            empStatus.setSelectedIndex(0);
            employer.setText("");
            income.setSelectedIndex(0);
            validId.setSelectedIndex(0);
            validIdNo.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
            "Database Error: Could not save client.\n" + e.getMessage(), 
            "Database Error", 
        JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException d) {
            JOptionPane.showMessageDialog(null, 
            "The date you entered is invalid. Please use the format: YYYY-MM-DD", 
            "Input Error", 
            JOptionPane.WARNING_MESSAGE);
        }

    }
}

class CheckIfNull {
    public static boolean isTextFieldNull(JTextField component) {
        boolean isTextFieldNull;

        String sample = component.getText();
        
        if (sample.trim().isEmpty()) {
            return isTextFieldNull = false;
        } else {
            return isTextFieldNull = true;
        }
    }
}