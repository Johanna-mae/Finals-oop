package finalp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

class PendingLoanApplication extends JPanel {

        Color NORMAL = new Color(0xAAC3DD);

        JTextField tfClient, tfType, tfAmount, tfEstimate, tfTerm, tfAppRefNo, tfDate,
                        tfRemarks;
        JTextArea taPurpose;
        JScrollPane loanScroll;
        JTable pendingLoanApplicationTable;
        JButton btnCancel, btnApproveLoan;

        public PendingLoanApplication() {
                setLayout(null);
                setBounds(280, 0, 1090, 800);
                setBackground(Color.WHITE);

                JLabel header = new JLabel("Pending Loan Application");
                header.setFont(new Font("Arial", Font.BOLD, 25));
                header.setBounds(20, 25, 500, 40);
                add(header);

                JLabel sub = new JLabel("View details to avail loan");
                sub.setBounds(23, 55, 400, 20);
                add(sub);

                JSeparator sep = new JSeparator();
                sep.setBounds(23, 80, 1040, 2);
                add(sep);

                // ===PANEL===
                JPanel box = new JPanel(null);
                box.setBackground(NORMAL);
                box.setBounds(20, 110, 1000, 385);
                add(box);

                tfAppRefNo = addField(box, "Application ID", 20, 20);
                tfClient = addField(box, "Client Name", 20, 60);
                tfType = addField(box, "Loan Type", 20, 100);
                tfAmount = addField(box, "Requested Amount", 20, 140);
                tfTerm = addField(box, "Term (Months)", 20, 180);
                tfEstimate = addField(box, "Est. / Month", 20, 220);
                tfDate = addField(box, "Date", 20, 260);

                JLabel lblPurpose = new JLabel("Purpose");
                lblPurpose.setBounds(20, 300, 150, 25);
                box.add(lblPurpose);

                taPurpose = new JTextArea();
                taPurpose.setEditable(false);
                JScrollPane sp = new JScrollPane(taPurpose);
                sp.setBounds(180, 300, 250, 60);
                box.add(sp);

                JLabel lblStatus = new JLabel("Status");
                lblStatus.setBounds(500, 20, 150, 25);
                box.add(lblStatus);

                JComboBox<String> cbStatus = new JComboBox<>(
                                new String[] { "For Approval", "Under Review", "Approved", "Rejected", "Cancelled" });
                cbStatus.setBounds(660, 20, 250, 30);
                box.add(cbStatus);

                JLabel lblDate = new JLabel("Date Reviewed");
                lblDate.setBounds(500, 60, 150, 30);
                box.add(lblDate);

                JTextField tfDateReviewed = new JTextField("yyyy-MM-dd");
                tfDateReviewed.setBounds(660, 60, 250, 25);
                box.add(tfDateReviewed);

                JLabel lblRejectReason = new JLabel("Rejection Reason");
                lblRejectReason.setBounds(500, 100, 150, 30);
                box.add(lblRejectReason);

                JTextArea taRejectReason = new JTextArea();
                taRejectReason.setLineWrap(true);
                JScrollPane spRejectReason = new JScrollPane(taRejectReason);
                spRejectReason.setBounds(660, 100, 250, 60);
                box.add(spRejectReason);

                JLabel lblApproveReason = new JLabel("Approval Reason");
                lblApproveReason.setBounds(500, 170, 250, 30);
                box.add(lblApproveReason);

                JTextArea taApproveReason = new JTextArea();
                taRejectReason.setLineWrap(true);
                JScrollPane spApproveReason = new JScrollPane(taApproveReason);
                spApproveReason.setBounds(660, 170, 250, 60);
                box.add(spApproveReason);

                JLabel lblreviewedByEmployee = new JLabel("Reviewed By");
                lblreviewedByEmployee.setBounds(500, 240, 150, 30);
                box.add(lblreviewedByEmployee);

                JComboBox<EmployeeItem> cbEmployeeName = new JComboBox<EmployeeItem>();
                retreiveEmployeeList(cbEmployeeName);
                cbEmployeeName.setSelectedIndex(-1);
                cbEmployeeName.setBounds(660, 240, 250, 30);
                box.add(cbEmployeeName);

                btnCancel = new JButton("Cancel");
                btnCancel.setBounds(630, 300, 100, 30);
                box.add(btnCancel);

                btnApproveLoan = new JButton("Approve Loan");
                btnApproveLoan.setBounds(760, 300, 150, 30);
                box.add(btnApproveLoan);

                pendingLoanApplicationTable = new JTable();
                pendingLoanApplicationTable.setRowHeight(30);
                pendingLoanApplicationTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));

                String query = """
                                SELECT
                                        Loan_Application.loan_application_reference_number AS "Application Ref. No.",
                                        CONCAT(Client.first_name, ' ', Client.last_name) AS "Client Name",
                                        Loan_Type.type_name AS "Loan Type",
                                        Loan_Application.requested_amount AS "Requested Amount",
                                        Loan_Application.requested_term_months AS "Requested Term",
                                        Loan_Application.application_date AS "Application Date",
                                        Loan_Application.status AS "Application Status",
                                        Loan_Application.purpose,
                                        Loan_Type.annual_interest_rate,
                                        Loan_Application.client_id
                                FROM Loan_Application
                                JOIN Client ON Loan_Application.client_id = Client.client_id
                                JOIN Loan_Type ON Loan_Application.loan_type_id = Loan_Type.loan_type_id
                                WHERE status = "For Approval"
                                        """;


                String no, name, loanType, requestedAmount, requestedTerm, applicationDate, status, purpose, annualInterestRate, clientID;

                try {
                        Connection conn = DatabaseConnection.getConnection();
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        DefaultTableModel tblModel = (DefaultTableModel) pendingLoanApplicationTable.getModel();

                        int cols = rsmd.getColumnCount();
                        String[] colName = new String[cols];

                        for (int i = 0; i < cols; i++) {
                                colName[i] = rsmd.getColumnLabel(i + 1);
                                tblModel.setColumnIdentifiers(colName);
                        }
                        while (rs.next()) {
                                no = rs.getString(1);
                                name = rs.getString(2);
                                loanType = rs.getString(3);
                                requestedAmount = rs.getString(4);
                                requestedTerm = rs.getString(5);
                                applicationDate = rs.getString(6);
                                status = rs.getString(7);
                                purpose = rs.getString(8);
                                annualInterestRate = rs.getString(9);
                                clientID = rs.getString(10);
                                String[] row = { no, name, loanType, requestedAmount, requestedTerm, applicationDate,
                                                status, purpose, annualInterestRate, clientID};
                                tblModel.addRow(row);
                        }

                        st.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }

                pendingLoanApplicationTable.getColumnModel().getColumn(9).setMinWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(9).setMaxWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(9).setWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(8).setMinWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(8).setMaxWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(8).setWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(7).setMinWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(7).setMaxWidth(0);
                pendingLoanApplicationTable.getColumnModel().getColumn(7).setWidth(0);


                pendingLoanApplicationTable.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                                int selectedRow = pendingLoanApplicationTable.getSelectedRow();
                                if (selectedRow != -1) {
                                        String appId = pendingLoanApplicationTable.getValueAt(selectedRow, 0).toString();
                                        String client = pendingLoanApplicationTable.getValueAt(selectedRow, 1).toString();
                                        String type = pendingLoanApplicationTable.getValueAt(selectedRow, 2).toString();
                                        String requestedAmount = pendingLoanApplicationTable.getValueAt(selectedRow, 3).toString();
                                        String requestedTerm = pendingLoanApplicationTable.getValueAt(selectedRow, 4).toString();
                                        String date = pendingLoanApplicationTable.getValueAt(selectedRow, 5).toString();
                                        String status = pendingLoanApplicationTable.getValueAt(selectedRow, 6).toString();
                                        String purpose = pendingLoanApplicationTable.getValueAt(selectedRow, 7).toString();
                                        double annualInterestRate = Double.parseDouble(pendingLoanApplicationTable.getValueAt(selectedRow, 8).toString());
                                        int clientID = Integer.parseInt(pendingLoanApplicationTable.getValueAt(selectedRow, 9).toString());
                                        

                                        // Push to your existing setter method
                                        setApplicationData(client, type, requestedAmount, requestedTerm, appId, date, status, purpose, annualInterestRate);
                                }
                        }
                });

                loanScroll = new JScrollPane(pendingLoanApplicationTable);
                loanScroll.setBounds(20, 500, 1000, 220);
                add(loanScroll);

                btnApproveLoan.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){          
                                int selectedRow = pendingLoanApplicationTable.getSelectedRow();
                                if (selectedRow == -1) {
                                        JOptionPane.showMessageDialog(null, "Select an application first!");
                                        return;
                                }
                                
                                String status = cbStatus.getSelectedItem().toString();
                                String applicationID = tfAppRefNo.getText();
                                String loanType = tfType.getText();
                                int loanTypeID = 0;

                                String dateReviewed = tfDate.getText();
                                String rejectReason = taRejectReason.getText();
                                String approveNotes = taApproveReason.getText();

                                int clientID = Integer.parseInt(pendingLoanApplicationTable.getValueAt(selectedRow, 9).toString());
                                double annualRate = Double.parseDouble(pendingLoanApplicationTable.getValueAt(selectedRow, 8).toString());

                                double principal = Double.parseDouble(tfAmount.getText().replace(",", ""));
                                int months = Integer.parseInt(tfTerm.getText());
                                double totalInterest = principal * (annualRate/100) * (months/12.0);
                                double totalPayable = principal + totalInterest;
                                double monthlyPayment = totalPayable / months;
                                double outstandingBalance = totalPayable;

                                switch (loanType) {
                                        case "Personal":
                                                loanTypeID = 1;
                                                break;
                                        case "Auto":
                                                loanTypeID = 2;
                                                break;
                                        case "Housing":
                                                loanTypeID = 3;
                                                break;
                                        case "Business":
                                                loanTypeID = 4;
                                                break;
                                        case "Education":
                                                loanTypeID = 5;
                                                break;
                                        default:
                                                break;
                                }

                                if ("Approved".equals(status)){
                                        try {
                                                Connection conn = DatabaseConnection.getConnection();
                                                conn.setAutoCommit(false);

                                                String updateApplication = """
                                                        UPDATE Loan_Application 
                                                        SET status = "Approved", review_date = ?, rejection_reason = ?, approval_notes = ?
                                                        WHERE loan_application_reference_number = ?
                                                                """;

                                                PreparedStatement ps1 = conn.prepareStatement(updateApplication);
                                                ps1.setString(1, dateReviewed);
                                                ps1.setString(2, rejectReason);
                                                ps1.setString(3, approveNotes);
                                                ps1.setString(4, applicationID);
                                                ps1.executeUpdate();

                                                Object timestamp = LocalDateTime.now(); 
                                                String loanRefNo = ReferenceNumberGenerator.generateLoanRefNo(5, loanTypeID);

                                                String createLoan = """
                                                        INSERT INTO Loan (
                                                                loan_reference_number, 
                                                                principal_amount, 
                                                                interest_rate, 
                                                                terms_months, 
                                                                monthly_payment, 
                                                                total_interest, 
                                                                total_amount_payable, 
                                                                outstanding_balance, 
                                                                loan_start_date, 
                                                                loan_end_date, 
                                                                status, 
                                                                loan_type_id, 
                                                                client_id,
                                                                created_date
                                                        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL ? MONTH), 'Active', ?, ?, ?)
                                                                """;

                                                PreparedStatement ps2 = conn.prepareStatement(createLoan);
                                                ps2.setString(1, loanRefNo);
                                                ps2.setDouble(2, principal);
                                                ps2.setDouble(3, annualRate);
                                                ps2.setInt(4, months);
                                                ps2.setDouble(5, monthlyPayment);
                                                ps2.setDouble(6, totalInterest);
                                                ps2.setDouble(7, totalPayable);
                                                ps2.setDouble(8, outstandingBalance);
                                                ps2.setInt(9, months);
                                                ps2.setInt(10, loanTypeID);
                                                ps2.setInt(11, clientID);
                                                ps2.setObject(12, timestamp);
                                                ps2.executeUpdate();

                                                conn.commit();

                                                JOptionPane.showMessageDialog(
                                                PendingLoanApplication.this,
                                                "Loan Application Updated",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE);

                                                JOptionPane.showMessageDialog(
                                                PendingLoanApplication.this,
                                                "Loan Record Created",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE);

                                        } catch (SQLException ez) {
                                                ez.printStackTrace();
                                        }
                                } else if ("Rejected".equals(status)) {
                                        String status0 = cbStatus.getSelectedItem().toString();
                                        String applicationID0 = tfAppRefNo.getText();

                                        try {
                                                Connection conn = DatabaseConnection.getConnection();
                                                conn.setAutoCommit(false);

                                                String updateApplication_Rejected = """
                                                        UPDATE Loan_Application
                                                        SET status = ?
                                                        WHERE loan_application_reference_number = ?
                                                                """;
                                                PreparedStatement ps0 = conn.prepareStatement(updateApplication_Rejected);
                                                ps0.setString(1, status0);
                                                ps0.setString(2, applicationID0);
                                                ps0.executeUpdate();
                                                
                                                conn.commit();

                                        } catch (SQLException te){
                                                te.printStackTrace();
                                        }

                                } else if ("Under Review".equals(status)){
                                        String status1 = cbStatus.getSelectedItem().toString();
                                        String applicationID1 = tfAppRefNo.getText();

                                        try {
                                                Connection conn = DatabaseConnection.getConnection();
                                                conn.setAutoCommit(false);

                                                String updateApplication_UnderReview = """
                                                        UPDATE Loan_Application
                                                        SET status = ?
                                                        WHERE loan_application_reference_number = ?
                                                                """;
                                                
                                                PreparedStatement ps3 = conn.prepareStatement(updateApplication_UnderReview);
                                                ps3.setString(1, status1);
                                                ps3.setString(2, applicationID1);
                                                ps3.executeUpdate();

                                                conn.commit();

                                        } catch (SQLException b) {
                                                b.printStackTrace();
                                        }

                                } else if ("Cancelled".equals(status) && !"For Approval".equals(status)){
                                        String status4 = cbStatus.getSelectedItem().toString();
                                        String applicationID4 = tfAppRefNo.getText();

                                        try {
                                                Connection conn = DatabaseConnection.getConnection();
                                                conn.setAutoCommit(false);

                                                String updateApplication_UnderReview = """
                                                        UPDATE Loan_Application
                                                        SET status = ?
                                                        WHERE loan_application_reference_number = ?
                                                                """;
                                                
                                                PreparedStatement ps3 = conn.prepareStatement(updateApplication_UnderReview);
                                                ps3.setString(1, status4);
                                                ps3.setString(2, applicationID4);
                                                ps3.executeUpdate();

                                                conn.commit();

                                        } catch (SQLException b) {
                                                b.printStackTrace();
                                        }

                                } else if ("For Approval".equals(status)){
                                        JOptionPane.showMessageDialog(
                                                PendingLoanApplication.this,
                                                "Update Selection for this Application's Status",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                }
                        }
                });


        JButton btnRefresh = new JButton("Refresh Table");
        btnRefresh.setBounds(850, 730, 150, 30);
        btnRefresh.setVisible(true);
        btnRefresh.setEnabled(true);
        add(btnRefresh);
        btnRefresh.addActionListener(e -> loadTableData());
        }

        private JTextField addField(JPanel box, String label, int x, int y) {
                JLabel lbl = new JLabel(label);
                lbl.setBounds(x, y, 150, 25);
                box.add(lbl);

                JTextField tf = new JTextField();
                tf.setBounds(x + 160, y, 250, 25);
                tf.setEditable(false);
                box.add(tf);

                return tf;
        }

        // 🔑 SETTER METHOD
        public void setApplicationData(String client, String type, String amount, String term, String appId, String date, String status, String purpose, double annualInterestRate) {
                // calculate the estimate here
                double monthlyEstimate = 0;
                try {
                        double principal = Double.parseDouble(amount);
                        int months = Integer.parseInt(term);

                        // Simple Interest Calculation: (Principal + (Principal * Rate * Time)) / Time
                        // Time in years = months / 12.0
                        double rate = annualInterestRate / 100.0;
                        double totalInterest = principal * rate * (months / 12.0);
                        double totalRepayment = principal + totalInterest;
                        monthlyEstimate = totalRepayment / months;

                        tfEstimate.setText(String.format("%,.2f", monthlyEstimate));
                        tfAmount.setText(String.format("%,.2f", principal));

                } catch (Exception e) {
                        tfEstimate.setText("Error");
                }

                tfClient.setText(client);
                tfType.setText(type);

                tfTerm.setText(term);
                taPurpose.setText(purpose);
                tfAppRefNo.setText(appId);
                tfDate.setText(date);
        }

        public void retreiveEmployeeList(JComboBox<EmployeeItem> cbEmployee) {
                String query = """
                        SELECT employee_id,
                        CONCAT(last_name, ', ', first_name) AS full_name
                        FROM Employee
                        WHERE last_name IS NOT NULL AND first_name IS NOT NULL 
                                """;
                cbEmployee.removeAllItems();

                try {
                        Connection conn = DatabaseConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement(query);
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                                int employeeID = rs.getInt("employee_id");
                                String full_name = rs.getString("full_name");
                                cbEmployee.addItem(new EmployeeItem(employeeID, full_name));
                        }

                        rs.close();
                        ps.close();
                        
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public void loadTableData() {
                DefaultTableModel tblModel = (DefaultTableModel) pendingLoanApplicationTable.getModel();
                tblModel.setRowCount(0); 

                String query = """
                                SELECT
                                        Loan_Application.loan_application_reference_number AS "Application Ref. No.",
                                        CONCAT(Client.first_name, ' ', Client.last_name) AS "Client Name",
                                        Loan_Type.type_name AS "Loan Type",
                                        Loan_Application.requested_amount AS "Requested Amount",
                                        Loan_Application.requested_term_months AS "Requested Term",
                                        Loan_Application.application_date AS "Application Date",
                                        Loan_Application.status AS "Application Status",
                                        Loan_Application.purpose,
                                        Loan_Type.annual_interest_rate,
                                        Loan_Application.client_id
                                FROM Loan_Application
                                JOIN Client ON Loan_Application.client_id = Client.client_id
                                JOIN Loan_Type ON Loan_Application.loan_type_id = Loan_Type.loan_type_id
                                WHERE status = "For Approval"
                                        """;

                try {
                        Connection conn = DatabaseConnection.getConnection();
                        if (conn == null || conn.isClosed()) {
                        // Re-establish connection kung sakaling closed na
                        System.out.println("Connection was closed, re-opening...");
                        }
                        
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        while (rs.next()) {
                        Object[] row = new Object[10];
                        for (int i = 0; i < 10; i++) {
                                row[i] = rs.getObject(i + 1);
                        }
                        tblModel.addRow(row);
                        }
                        
                        rs.close();
                        st.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
                }
}
}
