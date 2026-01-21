package finalp;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditClientRecord extends JPanel {

    // ===== FORM FIELDS =====
    JTextField firstName, middleName, lastName, dateOfBirth, email, contactNum;
    JTextField street, brgy, city, province;
    JTextField employerName, validIDNo;
    JRadioButton male, female;
    JComboBox<String> civilStatus, employmentStatus, monthlyIncome, validIDType;

    // ===== COLORS =====
    Color NORMAL = new Color(0xAAC3DD);

    // ===== TABLE =====
    JTable clientListTable;
    DefaultTableModel tableModel;

    public EditClientRecord() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        // ===== MAIN CONTENT PANEL (for scrolling) =====
        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(1090, 1200));
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
        JPanel p = new JPanel(null);
        p.setBounds(20, 110, 965, 400);
        p.setBackground(NORMAL);
        contentPanel.add(p);

        // ===== FORM FIELDS =====
        firstName = addLabelAndTextField(p, "First Name", 60, 28, 160, 28);
        middleName = addLabelAndTextField(p, "Middle Name", 340, 28, 460, 28);
        lastName = addLabelAndTextField(p, "Last Name", 640, 28, 740, 28);

        // Sex
        JLabel lblSex = new JLabel("Sex");
        lblSex.setBounds(60, 75, 150, 30);
        lblSex.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lblSex);

        male = new JRadioButton("Male"); male.setBackground(NORMAL); male.setBounds(160, 75, 80, 30);
        female = new JRadioButton("Female"); female.setBackground(NORMAL); female.setBounds(260, 75, 80, 30);
        ButtonGroup bg = new ButtonGroup(); bg.add(male); bg.add(female);
        p.add(male); p.add(female);

        dateOfBirth = addLabelAndTextField(p, "Date of Birth", 340, 75, 460, 75);
        email = addLabelAndTextField(p, "Email", 640, 75, 740, 75);
        contactNum = addLabelAndTextField(p, "Contact No.", 60, 122, 160, 122);
        street = addLabelAndTextField(p, "Street", 340, 122, 460, 122);
        brgy = addLabelAndTextField(p, "Barangay", 640, 122, 740, 122);
        city = addLabelAndTextField(p, "City", 60, 174, 160, 169);
        province = addLabelAndTextField(p, "Province", 340, 169, 460, 169);

        // Civil Status
        JLabel lblCivil = new JLabel("Civil Status");
        lblCivil.setBounds(640, 169, 100, 30); lblCivil.setFont(new Font("Arial", Font.BOLD, 14)); p.add(lblCivil);
        String[] civilStatuses = {"Single","Married","Separated","Widowed"};
        civilStatus = new JComboBox<>(civilStatuses); civilStatus.setBounds(740, 169, 150, 30); civilStatus.setBackground(NORMAL);
        p.add(civilStatus);

        // Employment Status & Employer Name
        JLabel lblEmpStatus = new JLabel("Employment Status"); lblEmpStatus.setBounds(60, 216, 160, 30); lblEmpStatus.setFont(new Font("Arial", Font.BOLD, 14)); p.add(lblEmpStatus);
        String[] empStatuses = {"Employed","Self-Employed","Unemployed","Student","Retired"};
        employmentStatus = new JComboBox<>(empStatuses); employmentStatus.setBounds(220, 216, 150, 30); employmentStatus.setBackground(NORMAL); p.add(employmentStatus);

        employerName = addLabelAndTextField(p, "Employer Name", 400, 216, 530, 216);
        monthlyIncome = new JComboBox<>(new String[]{"Below ₱10,000","₱10,000 – ₱20,000","₱20,001 – ₱30,000","₱30,001 – ₱50,000","₱50,001 – ₱100,000","Above ₱100,000"}); 
        monthlyIncome.setBounds(200, 263, 150, 30); monthlyIncome.setBackground(NORMAL); p.add(monthlyIncome);

        JLabel lblValidId = new JLabel("Valid ID"); lblValidId.setBounds(380, 263, 150, 30); lblValidId.setFont(new Font("Arial", Font.BOLD, 14)); p.add(lblValidId);
        validIDType = new JComboBox<>(new String[]{"Passport","Driver’s License","UMID","PhilSys National ID","SSS ID","GSIS ID","Voter’s ID","Postal ID","PRC ID"});
        validIDType.setBounds(460, 263, 150, 30); validIDType.setBackground(NORMAL); p.add(validIDType);

        validIDNo = addLabelAndTextField(p, "Valid ID No.", 640, 263, 740, 263);

        // ===== UPDATE BUTTON =====
        JButton updateRecord = new JButton("Update Record");
        updateRecord.setBounds(780, 350, 170, 35); updateRecord.setBackground(NORMAL); p.add(updateRecord);

        // ===== CLIENT TABLE =====
        String[] columns = {"Client Ref No.","Client Name","Employment Status","Monthly Income","Application Status"};
        tableModel = new DefaultTableModel(columns,0){
            @Override public boolean isCellEditable(int row,int col){return false;}
        };
        clientListTable = new JTable(tableModel);
        clientListTable.setRowHeight(30); clientListTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
        JScrollPane sp = new JScrollPane(clientListTable); sp.setBounds(20, 550, 995, 400); contentPanel.add(sp);

        // ===== ROW CLICK TO FILL FORM =====
        clientListTable.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row = clientListTable.getSelectedRow();
                if(row>=0){
                    String name = tableModel.getValueAt(row,1).toString();
                    firstName.setText(name.split(" ")[0]);
                    lastName.setText(name.split(" ")[1]);
                    employmentStatus.setSelectedItem(tableModel.getValueAt(row,2));
                    monthlyIncome.setSelectedItem(tableModel.getValueAt(row,3));
                }
            }
        });

        // ===== UPDATE RECORD ACTION =====
        updateRecord.addActionListener(e -> updateClientRecord());

        // ===== MAIN SCROLL PANE =====
        JScrollPane mainScroll = new JScrollPane(contentPanel);
        mainScroll.setBounds(0,0,1090,800);
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);
        add(mainScroll);

        // ===== LOAD DATA =====
        loadClientData();
    }

    // Helper for creating labels + text fields
    private JTextField addLabelAndTextField(JPanel p, String label, int lx, int ly, int tx, int ty) {
        JLabel l = new JLabel(label); l.setBounds(lx, ly, 100, 30); l.setFont(new Font("Arial", Font.BOLD, 14)); p.add(l);
        JTextField t = new JTextField(); t.setBounds(tx, ty, 150, 30); p.add(t);
        return t;
    }

    private void loadClientData() {
        String query = """
            SELECT
                Client.client_reference_number AS "Client Ref No.",
                CONCAT(Client.first_name, ' ', Client.last_name) AS "Client Name",
                Client.employment_status AS "Employment Status",
                Client.monthly_income AS "Monthly Income",
                Loan_Application.status AS "Application Status"
            FROM Loan_Application
            JOIN Client ON Loan_Application.client_id = Client.client_id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            tableModel.setRowCount(0);

            while(rs.next()){
                tableModel.addRow(new Object[]{
                        rs.getString("Client Ref No."),
                        rs.getString("Client Name"),
                        rs.getString("Employment Status"),
                        rs.getString("Monthly Income"),
                        rs.getString("Application Status")
                });
            }

        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading client data: " + e.getMessage());
        }
    }

    private void updateClientRecord() {
        int row = clientListTable.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Please select a client to update.");
            return;
        }

        String clientRef = tableModel.getValueAt(row, 0).toString();
        String fName = firstName.getText();
        String lName = lastName.getText();
        String empStatus = employmentStatus.getSelectedItem().toString();
        String income = monthlyIncome.getSelectedItem().toString();

        String sql = """
            UPDATE Client
            SET first_name = ?, last_name = ?, employment_status = ?, monthly_income = ?
            WHERE client_reference_number = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, empStatus);
            ps.setString(4, income);
            ps.setString(5, clientRef);

            int updated = ps.executeUpdate();

            if(updated > 0){
                JOptionPane.showMessageDialog(this, "Client record updated successfully!");
                loadClientData(); // refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Please try again.");
            }

        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating client: " + e.getMessage());
        }
    }
}
