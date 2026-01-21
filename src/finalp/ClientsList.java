
package finalp;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ClientsList extends JPanel {
    Color NORMAL = new Color(0xAAC3DD);

    JTable ClientTable;

    public ClientsList() {
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
        JLabel header = new JLabel("Clients List");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        contentPanel.add(header);

        JLabel sub = new JLabel("View current clients");
        sub.setBounds(23, 55, 400, 20);
        contentPanel.add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        contentPanel.add(sep);

        ClientTable = new JTable();
        ClientTable.setRowHeight(30);
        ClientTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane loanScroll = new JScrollPane(ClientTable);
        loanScroll.setBounds(45, 100, 1000, 700);
        contentPanel.add(loanScroll);

        String query = """
                SELECT
                        Client.client_reference_number AS "Client Reference No.",
                        CONCAT(Client.first_name, ' ', Client.last_name) AS "Client Name",
                        Client.employment_status AS "Employment Status",
                        Client.monthly_income AS "Monthly Income",
                        Loan_Application.status AS "Application Status"
                FROM Loan_Application
                JOIN Client ON Loan_Application.client_id = Client.client_id
                        """;

        String clientID, clientName, employmentStatus, monthlyIncome, status;

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel tblModel = (DefaultTableModel) ClientTable.getModel();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnLabel(i + 1);
                tblModel.setColumnIdentifiers(colName);
            }
            while (rs.next()) {
                clientID = rs.getString(1);
                clientName = rs.getString(2);
                employmentStatus = rs.getString(3);
                monthlyIncome = rs.getString(4);
                status = rs.getString(5);
                String[] row = { clientID, clientName, employmentStatus, monthlyIncome, status };
                tblModel.addRow(row);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
