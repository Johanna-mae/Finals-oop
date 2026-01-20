package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Report extends JPanel{
    JButton actloan;
    JButton penloan;
    JButton overdue;

    JTable tact;
    JTable tpen;
    JTable tover;

    CardLayout card;
    JPanel tcon;
    
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);
    
    public Report() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Loan Summary Report");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);
        
        JLabel sub = new JLabel("Comprehensive loan and statistics");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.WHITE);
        
        
        //the three panels
        JPanel activeLoans = otherpanel("ACTIVE LOANS", "0");
        activeLoans.setBounds(40, 100, 330, 140);
        activeLoans.setBackground(NORMAL);
        add(activeLoans);

        JPanel pendingLoans = otherpanel("PENDING LOANS", "0");
        pendingLoans.setBounds(380, 100, 330, 140);
        pendingLoans.setBackground(NORMAL);
        add(pendingLoans);

        JPanel overdueLoans = otherpanel("OVERDUE LOANS", "0");
        overdueLoans.setBounds(720, 100, 330, 140);
        overdueLoans.setBackground(NORMAL);
        add(overdueLoans);

        // mga button sa taas ng table (tabs)
        actloan = new JButton("Active Loans");
        actloan.setBounds(40, 265, 130, 35);
        actloan.setBackground(ACTIVE);
        add(actloan);

        penloan = new JButton("Pending Loans");
        penloan.setBounds(170, 265, 150, 35);
        penloan.setBackground(NORMAL);
        add(penloan);
        
        overdue = new JButton("Overdue Loans");
        overdue.setBounds(320, 265, 160, 35);
        overdue.setBackground(NORMAL);
        add(overdue);

        // table
        card = new CardLayout();
        tcon = new JPanel(card);
        tcon.setBounds(40, 300, 1010, 300);
        tcon.setBackground(ACTIVE);
        tcon.setLayout(card);

        // actloan table
        String[] col = {"Client's Name", "Amount", "Status", "Date"};
        Object[][] data1 = {
            {"Juan Dela Cruz", "₱5,000", "Completed", "2025-02-01"},
            {"Maria Santos", "₱3,500", "Completed", "2025-02-03"}
        };
        tact = new JTable(data1, col);
        tact.setRowHeight(30);
        tact.setEnabled(true);
        tact.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        // penloan table
        String[] col2 = {"Client's Name", "Amount", "Status", "Date"};
        Object[][] data2 = {
            {"Pedro Ruiz", "₱1,000", "Pending", "2025-02-02"},
            {"Ana Reyes", "₱800", "Pending", "2025-02-05"}
        };
        tpen = new JTable(data2, col2);
        tpen.setRowHeight(30);
        tpen.setEnabled(true);
        tpen.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        
        // overdue table
        String[] col3 = {"Client's Name", "Amount", "Status", "Date"};
        Object[][] data3 = {
            {"Sarah Geronimo", "₱10,000", "Overdue", "2025-11-06"},
            {"Ana Katigbak", "₱800", "Overdue", "2025-12-05"}
        };
        tover = new JTable(data3, col3);
        tover.setRowHeight(30);
        tover.setEnabled(true);
        tover.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        tcon.add(new JScrollPane(tact), "active");
        tcon.add(new JScrollPane(tpen), "pending");
        tcon.add(new JScrollPane(tover), "overdue");

        add(tcon);

        // events ng recloan at recpay
        actloan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
                card.show(tcon, "active");
                actloan.setBackground(ACTIVE);
                penloan.setBackground(NORMAL);
                overdue.setBackground(NORMAL);
            }
        });
            

        penloan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent b){
                card.show(tcon, "pending");
                actloan.setBackground(NORMAL);
                penloan.setBackground(ACTIVE);
                overdue.setBackground(NORMAL);
            }
        });
        
        overdue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent b){
                card.show(tcon, "overdue");
                actloan.setBackground(NORMAL);
                penloan.setBackground(NORMAL);
                overdue.setBackground(ACTIVE);
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
