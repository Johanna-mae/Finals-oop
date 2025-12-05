package finalp;

import java.awt.*;
import javax.swing.*;

class Sidebar extends JPanel {
    public JButton createloan;
    public JButton viewloans;
    public JButton clientList;
    public JButton history1;
    public JButton loanpay;
    public JButton payhistory;
    public JButton home;
    public JButton report;
    public JButton logout;
    
    public Sidebar() {
        setLayout(null);
        setBackground(new Color(0xB1B2B3));
        setBounds(0,0, 260,900);
        setEnabled(true);
        
        JLabel loanmanagement = new JLabel("Loan Management");
        loanmanagement.setBounds(20, 30, 200, 20);
        loanmanagement.setFont(new Font("Arial", Font.BOLD, 14));
        add(loanmanagement);

        ImageIcon create = new ImageIcon(Finalp.class.getResource("/finalp/images/create.png"));
        createloan = new JButton("Create New Loan");
        createloan.setBounds(20, 60, 235, 40);
        createloan.setIcon(create);
        createloan.setHorizontalTextPosition(SwingConstants.RIGHT);
        createloan.setIconTextGap(5);
        add(createloan);

        ImageIcon view = new ImageIcon(Finalp.class.getResource("/finalp/images/view.png"));
        viewloans = new JButton("View Loans");
        viewloans.setBounds(20, 110, 235, 40);
        viewloans.setIcon(view);
        viewloans.setHorizontalTextPosition(SwingConstants.RIGHT);
        viewloans.setIconTextGap(5);
        add(viewloans);


        JLabel clients = new JLabel("Clients");
        clients.setBounds(20, 170, 200, 20);
        clients.setFont(new Font("Arial", Font.BOLD, 14));
        add(clients);

        ImageIcon list = new ImageIcon(Finalp.class.getResource("/finalp/images/clientslist.png"));
        clientList = new JButton("Clients List");
        clientList.setBounds(20, 200, 235, 40);
        clientList.setIcon(list);
        clientList.setHorizontalTextPosition(SwingConstants.RIGHT);
        clientList.setIconTextGap(5);
        add(clientList);

        ImageIcon his1 = new ImageIcon(Finalp.class.getResource("/finalp/images/history1.png"));
        history1 = new JButton("History");
        history1.setBounds(20, 250, 235, 40);
        history1.setIcon(his1);
        history1.setHorizontalTextPosition(SwingConstants.RIGHT);
        history1.setIconTextGap(5);
        add(history1);

        
        JLabel payments = new JLabel("Payments");
        payments.setBounds(20, 310, 200, 20);
        payments.setFont(new Font("Arial", Font.BOLD, 14));
        add(payments);

        ImageIcon pay = new ImageIcon(Finalp.class.getResource("/finalp/images/payment.png"));
        loanpay = new JButton("Loan Payments");
        loanpay.setBounds(20, 340, 235, 40);
        loanpay.setIcon(pay);
        loanpay.setHorizontalTextPosition(SwingConstants.RIGHT);
        loanpay.setIconTextGap(5);
        add(loanpay);

        ImageIcon his2 = new ImageIcon(Finalp.class.getResource("/finalp/images/history2.png"));
        payhistory = new JButton("History");
        payhistory.setBounds(20, 390, 235, 40);
        payhistory.setIcon(his2);
        payhistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        payhistory.setIconTextGap(5);
        add(payhistory);
        
        
        JLabel reports = new JLabel("Reports");
        reports.setBounds(20, 450, 200, 20);
        reports.setFont(new Font("Arial", Font.BOLD, 14));
        add(reports);

        ImageIcon dash = new ImageIcon(Finalp.class.getResource("/finalp/images/home.png"));
        home = new JButton("Home/Dashboard");
        home.setBounds(20, 480, 235, 40);
        home.setIcon(dash);
        home.setHorizontalTextPosition(SwingConstants.RIGHT);
        home.setIconTextGap(5);
        add(home);

        ImageIcon rep = new ImageIcon(Finalp.class.getResource("/finalp/images/reports.png"));
        report = new JButton("Reports");
        report.setBounds(20, 530, 235, 40);
        report.setIcon(rep);
        report.setHorizontalTextPosition(SwingConstants.RIGHT);
        report.setIconTextGap(5);
        add(report);
        
        
        JLabel others = new JLabel("Others");
        others.setBounds(20, 590, 200, 20);
        others.setFont(new Font("Arial", Font.BOLD, 14));
        add(others);

        ImageIcon out = new ImageIcon(Finalp.class.getResource("/finalp/images/logout.png"));
        logout = new JButton("Logout");
        logout.setBounds(20, 620, 235, 40);
        logout.setIcon(out);
        logout.setHorizontalTextPosition(SwingConstants.RIGHT);
        logout.setIconTextGap(5);
        add(logout);

        setVisible(true);
    }
}
