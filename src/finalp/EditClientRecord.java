package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EditClientRecord extends JPanel {
    JTextField firstName, middleName, lastName, dateOfBirth, email, contactNum;
    JTextField street, brgy, city, province;
    JTextField employerName, validIDNo;
    JRadioButton male, female;
    JComboBox<String> civilStatus, employmentStatus, monthlyIncome, validIDType;

    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    public EditClientRecord() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(new Color(0xFFFFFF));

        JLabel header = new JLabel("Edit Client Record");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("Modify existing client's details");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        setVisible(true);

        JPanel p = new JPanel(null);
        p.setBounds(60, 110, 965, 400);
        p.setBackground(NORMAL);

        // ===== FIRST NAME =====
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(60, 28, 100, 30);
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblFirstName);

        firstName = new JTextField();
        firstName.setBounds(160, 28, 150, 30);
        p.add(firstName);

        // ===== MIDDLE NAME =====
        JLabel lblMn = new JLabel("Middle Name");
        lblMn.setBounds(340, 28, 100, 30);
        lblMn.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblMn);

        middleName = new JTextField();
        middleName.setBounds(460, 28, 150, 30);
        p.add(middleName);

        // ===== LAST NAME =====
        JLabel lblLn = new JLabel("Last Name");
        lblLn.setBounds(640, 28, 100, 30);
        lblLn.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblLn);

        lastName = new JTextField();
        lastName.setBounds(740, 28, 150, 30);
        p.add(lastName);

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

        dateOfBirth = new JTextField();
        dateOfBirth.setBounds(460, 75, 150, 30);
        p.add(dateOfBirth);

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

        contactNum = new JTextField();
        contactNum.setBounds(160, 122, 150, 30);
        p.add(contactNum);

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

        civilStatus = new JComboBox<>(civilStatuses);
        civilStatus.setBounds(740, 169, 150, 30);
        civilStatus.setBackground(NORMAL);
        p.add(civilStatus);

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

        employmentStatus = new JComboBox<>(empStatuses);
        employmentStatus.setBounds(220, 216, 150, 30);
        employmentStatus.setBackground(NORMAL);
        p.add(employmentStatus);

        // ===== EMPLOYER NAME =====
        JLabel lblEmployer = new JLabel("Employer Name");
        lblEmployer.setBounds(400, 216, 160, 30);
        lblEmployer.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblEmployer);

        employerName = new JTextField();
        employerName.setBounds(530, 216, 150, 30);
        p.add(employerName);

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

        monthlyIncome = new JComboBox<>(incomeRanges);
        monthlyIncome.setBounds(200, 263, 150, 30);
        monthlyIncome.setBackground(NORMAL);
        p.add(monthlyIncome);

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

        validIDType = new JComboBox<>(validIds);
        validIDType.setBounds(460, 263, 150, 30);
        validIDType.setBackground(NORMAL);
        p.add(validIDType);

        // ===== VALID ID NUMBER =====
        JLabel lblValidIdNo = new JLabel("Valid ID No.");
        lblValidIdNo.setBounds(640, 263, 150, 30);
        lblValidIdNo.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblValidIdNo);

        validIDNo = new JTextField();
        validIDNo.setBounds(740, 263, 150, 30);
        p.add(validIDNo);

        JButton next = new JButton("Update Record");
        next.setBounds(840, 350, 100, 35);
        next.setBackground(NORMAL);
        p.add(next);

        add(p);
    }
}
