package finalp;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

class ViewLoans extends JPanel {

    Color NORMAL = new Color(0xAAC3DD);

    JTable loanTable;
    JTable breakdownTable;

    DefaultTableModel breakdownModel;

    public ViewLoans() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        // ===== CONTENT PANEL 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(1090, 990));

        // ===== SCROLL PANE =====
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(0, 0, 1090, 800);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(scrollPane);
        
        // ===== HEADER =====
        JLabel header = new JLabel("View Loans");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        contentPanel.add(header);

        JLabel sub = new JLabel("View active loans and breakdown");
        sub.setBounds(23, 55, 400, 20);
        contentPanel.add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        contentPanel.add(sep);

        loanTable = new JTable();
        loanTable.setRowHeight(30);
        loanTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    
        JScrollPane loanScroll = new JScrollPane(loanTable);
        loanScroll.setBounds(45, 100, 1000, 220);
        contentPanel.add(loanScroll);

        String query = """
                        SELECT
                                Loan.loan_reference_number AS "Loan Reference No.",
                                CONCAT(Client.first_name, ' ', Client.last_name) AS "Client Name",
                                Loan.principal_amount AS "Loan Amount",
                                Loan.terms_months AS "Term",
                                Loan.status AS "Status"
                        FROM Loan
                        JOIN Client ON Loan.client_id = Client.client_id
                            """;

                String loanRefNo, clientName, Term, status;
                double loanAmount;

                try {
                        Connection conn = DatabaseConnection.getConnection();
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        DefaultTableModel tblModel = new DefaultTableModel(); 
                        loanTable.setModel(tblModel);

                        int cols = rsmd.getColumnCount();
                        String[] colName = new String[cols];

                        for (int i = 0; i < cols; i++) {
                                colName[i] = rsmd.getColumnLabel(i + 1);
                                
                        }
                        tblModel.setColumnIdentifiers(colName);
                        while (rs.next()) {
                                Object[] row = {
                                    rs.getString(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getInt(4),
                                    rs.getString(5)
                                };
                                tblModel.addRow(row);
                        }
                        st.close();

                } catch (SQLException e) {
                        e.printStackTrace();
                }

        // ================= BREAKDOWN PANEL =================
        JPanel breakdownPanel = new JPanel(null);
        breakdownPanel.setBounds(45, 340, 1000, 390);
        breakdownPanel.setBackground(NORMAL);
        contentPanel.add(breakdownPanel);

        JLabel breakdownLbl = new JLabel("Loan Breakdown");
        breakdownLbl.setFont(new Font("Arial", Font.BOLD, 16));
        breakdownLbl.setBounds(20, 10, 400, 30);
        breakdownPanel.add(breakdownLbl);

        String[] breakdownCols = {
            "Payment #", "Principal Paid", "Interest Paid", "Total Paid", "Remaining Balance"
        };

        breakdownModel = new DefaultTableModel(breakdownCols, 0);
        breakdownTable = new JTable(breakdownModel);
        breakdownTable.setRowHeight(26);

        JScrollPane breakdownScroll = new JScrollPane(breakdownTable);
        breakdownScroll.setBounds(20, 45, 960, 320);
        breakdownScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        breakdownPanel.add(breakdownScroll);

        
        loanTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = loanTable.getSelectedRow();
                if (row == -1) return;

                breakdownModel.setRowCount(0); // clear table

                double loanAmount = (double) loanTable.getValueAt(row, 2);
                int term = (int) loanTable.getValueAt(row, 3);

                double principalPerPayment = loanAmount / term;
                double remaining = loanAmount;

                for (int i = 1; i <= term; i++) {
                    double interest = remaining * 0.02; // sample interest
                    remaining -= principalPerPayment;

                    breakdownModel.addRow(new Object[]{
                        i,
                        String.format("₱%.2f", principalPerPayment),
                        String.format("₱%.2f", interest),
                        String.format("₱%.2f", principalPerPayment + interest),
                        String.format("₱%.2f", Math.max(remaining, 0))
                    });
                }
            }
        });
    }
}


