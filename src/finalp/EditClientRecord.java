package finalp;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditClientRecord extends JPanel {

    // ===== FORM FIELDS =====
    JTextField firstName, middleName, lastName, dateOfBirth, email, contactNum;
    JTextField street, brgy, city, province;
    JTextField employerName, validIDNo;
    JRadioButton male, female;
    JComboBox<String> civilStatus, employmentStatus, monthlyIncome, validIDType;
    JPanel p;

    // ===== COLORS =====
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    // ===== TABLE =====
    JTable clientListTable;
    DefaultTableModel tableModel;

    public EditClientRecord() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        // ===== MAIN CONTENT PANEL (for scrolling) =====
        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1090, 1200)); // adjust height as needed
        contentPanel.setBackground(Color.WHITE);

        // ===== HEADER =====
        JLabel header = new JLabel("Edit Client Record");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        contentPanel.add(header);

        JLabel sub = new JLabel("Modify existing client's details");
        sub.setBounds(23, 55, 400, 20);
        contentPanel.add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        contentPanel.add(sep);

        // ===== FORM PANEL =====
        p = new JPanel(null);
        p.setBounds(60, 110, 965, 400);
        p.setBackground(NORMAL);
        contentPanel.add(p);

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

        // ===== ADDRESS =====
        JLabel lblStreet = new JLabel("Street");
        lblStreet.setBounds(340, 122, 150, 30);
        lblStreet.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblStreet);
        street = new JTextField();
        street.setBounds(460, 122, 150, 30);
        p.add(street);

        JLabel lblBrgy = new JLabel("Barangay");
        lblBrgy.setBounds(640, 122, 150, 30);
        lblBrgy.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblBrgy);
        brgy = new JTextField();
        brgy.setBounds(740, 122, 150, 30);
        p.add(brgy);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(60, 174, 100, 30);
        lblCity.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblCity);
        city = new JTextField();
        city.setBounds(160, 169, 150, 30);
        p.add(city);

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
        String[] civilStatuses = { "Single", "Married", "Separated", "Widowed" };
        civilStatus = new JComboBox<>(civilStatuses);
        civilStatus.setBounds(740, 169, 150, 30);
        civilStatus.setBackground(NORMAL);
        civilStatus.setSelectedIndex(-1);
        p.add(civilStatus);

        // ===== EMPLOYMENT STATUS =====
        JLabel lblEmpStatus = new JLabel("Employment Status");
        lblEmpStatus.setBounds(60, 216, 160, 30);
        lblEmpStatus.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblEmpStatus);
        String[] empStatuses = { "Employed", "Self-Employed", "Unemployed", "Student", "Retired" };
        employmentStatus = new JComboBox<>(empStatuses);
        employmentStatus.setBounds(220, 216, 150, 30);
        employmentStatus.setBackground(NORMAL);
        employmentStatus.setSelectedIndex(-1);
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
        String[] incomeRanges = { "Below ₱10,000", "₱10,000-₱20,000", "₱20,001-₱30,000", "₱30,001-₱40,000", "₱40,001-₱50,000",
                "₱50,001-₱100,000", "Above ₱100,000" };
        monthlyIncome = new JComboBox<>(incomeRanges);
        monthlyIncome.setBounds(200, 263, 150, 30);
        monthlyIncome.setBackground(NORMAL);
        monthlyIncome.setSelectedIndex(-1);
        p.add(monthlyIncome);

        // ===== VALID ID =====
        JLabel lblValidId = new JLabel("Valid ID");
        lblValidId.setBounds(380, 263, 150, 30);
        lblValidId.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblValidId);
        String[] validIds = { "Passport", "Driver's License", "UMID", "PhilSys National ID", "SSS ID", "GSIS ID",
                "Voter's ID", "Postal ID", "PRC ID" };
        validIDType = new JComboBox<>(validIds);
        validIDType.setBounds(460, 263, 150, 30);
        validIDType.setBackground(NORMAL);
        p.add(validIDType);

        JLabel lblValidIdNo = new JLabel("Valid ID No.");
        lblValidIdNo.setBounds(640, 263, 150, 30);
        lblValidIdNo.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblValidIdNo);
        validIDNo = new JTextField();
        validIDNo.setBounds(740, 263, 150, 30);
        p.add(validIDNo);

        JButton updateRecord = new JButton("Update Record");
        updateRecord.setBounds(780, 350, 170, 35);
        updateRecord.setBackground(NORMAL);
        updateRecord.addActionListener(e -> updateClientRecord());
        p.add(updateRecord);

        JButton updateClientListTable = new JButton("Update List");
        updateClientListTable.setBounds(595, 350, 170, 35);
        updateClientListTable.setBackground(NORMAL);
        updateClientListTable.addActionListener(y -> loadClientData());
        p.add(updateClientListTable);

        // ===== CLIENT TABLE =====
        String[] columns = { "Client Ref No.", "Client Name", "Employment Status", "Monthly Income", "Client Status" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        clientListTable = new JTable(tableModel);
        clientListTable.setRowHeight(30);
        clientListTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane sp = new JScrollPane(clientListTable);
        sp.setBounds(60, 550, 965, 400);
        contentPanel.add(sp);

        // ===== ROW CLICK TO FILL FORM =====
        clientListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = clientListTable.getSelectedRow();
                if (row >= 0) {
                    String clientRef = tableModel.getValueAt(row, 0).toString(); // get Ref No.

                    String sql = "SELECT * FROM Client WHERE client_reference_number = ?";

                    // Fetch full client data from DB
                    try (Connection conn = DatabaseConnection.getConnection();
                            PreparedStatement ps = conn.prepareStatement(sql);) {
                        ps.setString(1, clientRef);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            firstName.setText(rs.getString("first_name"));
                            middleName.setText(rs.getString("middle_name"));
                            lastName.setText(rs.getString("last_name"));
                            if (rs.getString("sex").equalsIgnoreCase("Male"))
                                male.setSelected(true);
                            else
                                female.setSelected(true);
                            dateOfBirth.setText(rs.getString("date_of_birth"));
                            email.setText(rs.getString("email"));
                            contactNum.setText(rs.getString("phone_number"));
                            street.setText(rs.getString("address_line"));
                            brgy.setText(rs.getString("barangay"));
                            city.setText(rs.getString("city"));
                            province.setText(rs.getString("province"));
                            civilStatus.setSelectedItem(rs.getString("civil_status"));
                            employmentStatus.setSelectedItem(rs.getString("employment_status"));
                            employerName.setText(rs.getString("employer_name"));
                            monthlyIncome.setSelectedItem(rs.getString("monthly_income"));
                            validIDType.setSelectedItem(rs.getString("valid_id_type"));
                            validIDNo.setText(rs.getString("valid_id_number"));
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(EditClientRecord.this,
                                "Error fetching client data: " + ex.getMessage());
                    }
                }
            }
        });

        // ===== MAIN SCROLL PANE =====
        JScrollPane mainScroll = new JScrollPane(contentPanel);
        mainScroll.setBounds(0, 0, 1090, 800);
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);
        add(mainScroll);

        /*
         * add(contentPanel);
         * contentPanel.setBounds(0, 0, 1090, 800);
         */

        // ===== LOAD DATA =====
        loadClientData();
    }

    private void loadClientData() {
        String query = """
                    SELECT
                        Client.client_reference_number AS "Client Ref. No.",
                        Client.first_name AS "First Name",
                        Client.middle_name AS "Middle Name",
                        Client.last_name AS "Last Name",
                        Client.sex AS "Sex",
                        Client.date_of_birth AS "Date of Birth",
                        Client.email AS "Email",
                        Client.phone_number AS "Contact No.",
                        Client.address_line AS "Street",
                        Client.barangay AS "Barangay",
                        Client.city AS "City",
                        Client.province AS "Province",
                        Client.civil_status AS "Civil Status",
                        Client.employment_status AS "Employment Status",
                        Client.employer_name AS "Employer Name",
                        Client.monthly_income AS "Monthly Income",
                        Client.valid_id_type AS "Valid ID Type",
                        Client.valid_id_number AS "Valid ID No.",
                        Client.account_status AS "Status"
                    FROM Client
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query);) {

            tableModel.setRowCount(0);

            while (rs.next()) {
                String fullName = rs.getString("First Name") + " " +
                        rs.getString("Middle Name") + " " +
                        rs.getString("Last Name");

                tableModel.addRow(new Object[] {
                        rs.getString("Client Ref. No."),
                        fullName,
                        rs.getString("Employment Status"),
                        rs.getString("Monthly Income"),
                        rs.getString("Status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading client data: " + e.getMessage());
        }
    }

    private void updateClientRecord() {
        int row = clientListTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a client to update.");
            return;
        }

        String clientRef = tableModel.getValueAt(row, 0).toString();

        // ===== GET FORM DATA =====
        String fName = firstName.getText();
        String mName = middleName.getText();
        String lName = lastName.getText();
        String sex = male.isSelected() ? "Male" : "Female";
        String dob = dateOfBirth.getText();
        String mail = email.getText();
        String contact = contactNum.getText();
        String addrStreet = street.getText();
        String addrBrgy = brgy.getText();
        String addrCity = city.getText();
        String addrProvince = province.getText();
        String civil = civilStatus.getSelectedItem().toString();
        String empStatus = employmentStatus.getSelectedItem().toString();
        String employer = employerName.getText();
        String income = monthlyIncome.getSelectedItem().toString();
        String idType = validIDType.getSelectedItem().toString();
        String idNo = validIDNo.getText();

        String sql = """
                    UPDATE Client
                    SET
                        first_name = ?,
                        middle_name = ?,
                        last_name = ?,
                        sex = ?,
                        date_of_birth = ?,
                        email = ?,
                        phone_number = ?,
                        address_line = ?,
                        barangay = ?,
                        city = ?,
                        province = ?,
                        civil_status = ?,
                        employment_status = ?,
                        employer_name = ?,
                        monthly_income = ?,
                        valid_id_type = ?,
                        valid_id_number = ?
                    WHERE client_reference_number = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fName);
            ps.setString(2, mName);
            ps.setString(3, lName);
            ps.setString(4, sex);
            ps.setString(5, dob);
            ps.setString(6, mail);
            ps.setString(7, contact);
            ps.setString(8, addrStreet);
            ps.setString(9, addrBrgy);
            ps.setString(10, addrCity);
            ps.setString(11, addrProvince);
            ps.setString(12, civil);
            ps.setString(13, empStatus);
            ps.setString(14, employer);
            ps.setString(15, income);
            ps.setString(16, idType.replace('’', '\'').replace('—', '‐').replace('–', '‐'));
            ps.setString(17, idNo);
            ps.setString(18, clientRef);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Client record updated successfully!");
                loadClientData(); // refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating client: " + e.getMessage());
        }
    }
}
