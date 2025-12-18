package finalp;

import java.awt.*;
import javax.swing.*;

class PaymentHistory extends JPanel{
    JTable tpay;

    CardLayout card;
    JPanel tcon;
    
    public PaymentHistory() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Payment History");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);
        
        JLabel subtitle = new JLabel("View all recorded payments");
        subtitle.setBounds(23, 55, 400, 20);
        add(subtitle);

        JSeparator separator = new JSeparator();
        separator.setBounds(23, 80, 1040, 2);
        add(separator);

        // table
        card = new CardLayout();
        tcon = new JPanel(card);
        tcon.setBounds(40, 100, 1010, 300);
        tcon.setBackground(new Color(210,210,210));
        tcon.setLayout(card);

        // pay table
        String[] col2 = {"Transaction No.", "Account No.", "Amount Paid", "Date Paid"};
        Object[][] data2 = {
            {"4567", "10", "₱ 3000", "2025-02-02"},
            {"3489", "56", "₱ 10000", "2025-02-05"}
        };
        tpay = new JTable(data2, col2);

        tcon.add(new JScrollPane(tpay), "payments");

        add(tcon);
        
       
    }
}