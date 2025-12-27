package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class ViewLoans extends JPanel{
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);
    
    JTable viewLoan;

    CardLayout card;
    JPanel container;
    
    public ViewLoans() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("View Loans");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("View active loans");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        // table
        card = new CardLayout();
        container = new JPanel(card);
        container.setBounds(40, 100, 1010, 300);
        container.setBackground(new Color(210,210,210));
        container.setLayout(card);
        
        // view loan table
        String[] col2 = {"Client Name", "Loan Amount", "Loan Date", "Status", "Balance"};
        Object[][] data2 = {
            {"John Doe dela Cruz", "₱25,000", "2024-05-01", "Active", "₱18,000"},
            {"Jane Doe", "₱40,000", "2024-03-15", "Active", "₱30,500"},
            {"Mark Reyes", "₱15,000", "2024-01-10", "Overdue", "₱12,000"},
            {"Ana Cruz", "₱50,000", "2023-12-20", "Completed", "₱0"},
            {"Luis Santos", "₱30,000", "2024-02-05", "Active", "₱22,000"}
        };
        viewLoan = new JTable(data2, col2);
        viewLoan.setRowHeight(30);
        viewLoan.setEnabled(true);
        viewLoan.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));


        container.add(new JScrollPane(viewLoan), "payments");

        add(container);
    }
}