package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Home extends JPanel{
    JButton recloan;
    JButton recpay;

    JTable tloans;
    JTable tpay;

    CardLayout card;
    JPanel tcon;
    
    public Home() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Home/Dashboard");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JPanel content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.WHITE);
        
        
        //the three panels
        JPanel totalLoans = otherpanel("TOTAL LOANS", "0");
        totalLoans.setBounds(40, 80, 330, 140);
        add(totalLoans);

        JPanel activeLoans = otherpanel("ACTIVE LOANS", "0");
        activeLoans.setBounds(380, 80, 330, 140);
        add(activeLoans);

        JPanel settledLoans = otherpanel("SETTLED LOANS", "0");
        settledLoans.setBounds(720, 80, 330, 140);
        add(settledLoans);

        // mga button sa taas ng table (tabs)
        recloan = new JButton("Recent loans");
        recloan.setBounds(40, 245, 130, 35);
        recloan.setBackground(new Color(200, 200, 200));
        add(recloan);

        recpay = new JButton("Recent payments");
        recpay.setBounds(170, 245, 150, 35);
        recpay.setBackground(new Color(230, 230, 230));
        add(recpay);

        // table
        card = new CardLayout();
        tcon = new JPanel(card);
        tcon.setBounds(40, 280, 1010, 300);
        tcon.setBackground(new Color(210,210,210));
        tcon.setLayout(card);

        // loan table
        String[] col = {"Client's Name", "Amount", "Status", "Date"};
        Object[][] data1 = {
            {"Juan Dela Cruz", "₱5,000", "Approved", "2025-02-01"},
            {"Maria Santos", "₱3,500", "Pending", "2025-02-03"}
        };
        tloans = new JTable(data1, col);

        // pay table
        String[] col2 = {"Client's Name", "Amount Paid", "Status", "Date"};
        Object[][] data2 = {
            {"Pedro Ruiz", "₱1,000", "Completed", "2025-02-02"},
            {"Ana Reyes", "₱800", "Completed", "2025-02-05"}
        };
        tpay = new JTable(data2, col2);

        tcon.add(new JScrollPane(tloans), "loans");
        tcon.add(new JScrollPane(tpay), "payments");

        add(tcon);

        // events ng recloan at recpay
        recloan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
                card.show(tcon, "loans");
                recloan.setBackground(new Color(200,200,200));
                recpay.setBackground(new Color(230,230,230));
            }
        });
            

        recpay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent b){
                card.show(tcon, "payments");
                recloan.setBackground(new Color(230,230,230));
                recpay.setBackground(new Color(200,200,200));
            }
        });

        setVisible(true);
    }

    // laman nung tatlong boxes
    private JPanel otherpanel(String title, String number){
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(180, 180, 180));

        JLabel ltitle = new JLabel(title);
        ltitle.setFont(new Font("Arial", Font.BOLD, 14));
        ltitle.setBounds(20, 10, 200, 20);
        p.add(ltitle);

        JLabel lnum = new JLabel(number);
        lnum.setFont(new Font("Arial", Font.BOLD, 40));
        lnum.setBounds(20, 40, 200, 50);
        p.add(lnum);

        return p;
    }
}