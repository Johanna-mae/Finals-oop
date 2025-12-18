package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
    
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    JButton selected = null;

    public Sidebar() {
        setLayout(null);
        setBackground(new Color(0xAAC3DD));
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
        createloan.setBackground(NORMAL);
        createloan.setHorizontalTextPosition(SwingConstants.RIGHT);
        createloan.setIconTextGap(5);
        add(createloan);

        ImageIcon view = new ImageIcon(Finalp.class.getResource("/finalp/images/view.png"));
        viewloans = new JButton("View Loans");
        viewloans.setBounds(20, 110, 235, 40);
        viewloans.setIcon(view);
        viewloans.setBackground(NORMAL);
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
        clientList.setBackground(NORMAL);
        clientList.setHorizontalTextPosition(SwingConstants.RIGHT);
        clientList.setIconTextGap(5);
        add(clientList);

        ImageIcon his1 = new ImageIcon(Finalp.class.getResource("/finalp/images/history1.png"));
        history1 = new JButton("History");
        history1.setBounds(20, 250, 235, 40);
        history1.setIcon(his1);
        history1.setBackground(NORMAL);
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
        loanpay.setBackground(NORMAL);
        loanpay.setHorizontalTextPosition(SwingConstants.RIGHT);
        loanpay.setIconTextGap(5);
        add(loanpay);

        ImageIcon his2 = new ImageIcon(Finalp.class.getResource("/finalp/images/history2.png"));
        payhistory = new JButton("History");
        payhistory.setBounds(20, 390, 235, 40);
        payhistory.setIcon(his2);
        payhistory.setBackground(NORMAL);
        payhistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        payhistory.setIconTextGap(5);
        add(payhistory);
        
        
        JLabel reports = new JLabel("Reports");
        reports.setBounds(20, 450, 200, 20);
        reports.setFont(new Font("Arial", Font.BOLD, 14));
        add(reports);

        ImageIcon dashboard = new ImageIcon(Finalp.class.getResource("/finalp/images/home.png"));
        home = new JButton("Home/Dashboard");
        home.setBounds(20, 480, 235, 40);
        home.setIcon(dashboard);
        home.setBackground(NORMAL);
        home.setHorizontalTextPosition(SwingConstants.RIGHT);
        home.setIconTextGap(5);
        add(home);

        ImageIcon reportsIcon = new ImageIcon(Finalp.class.getResource("/finalp/images/reports.png"));
        report = new JButton("Reports");
        report.setBounds(20, 530, 235, 40);
        report.setIcon(reportsIcon);
        report.setBackground(NORMAL);
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
        logout.setBackground(NORMAL);
        logout.setHorizontalTextPosition(SwingConstants.RIGHT);
        logout.setIconTextGap(5);
        add(logout);

        setVisible(true);
        
        //functions of the buttons mouselistener
        createloan.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != createloan) {
                    createloan.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != createloan) {
                    createloan.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = createloan;

                createloan.setBackground(ACTIVE);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        viewloans.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != viewloans) {
                    viewloans.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != viewloans) {
                    viewloans.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = viewloans;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(ACTIVE);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        clientList.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != clientList) {
                    clientList.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != clientList) {
                    clientList.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = clientList;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(ACTIVE);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        history1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != history1) {
                    history1.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != history1) {
                    history1.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = history1;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(ACTIVE);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        loanpay.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != loanpay) {
                    loanpay.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != loanpay) {
                    loanpay.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = loanpay;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(ACTIVE);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        payhistory.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != payhistory) {
                    payhistory.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != payhistory) {
                    payhistory.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = payhistory;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(ACTIVE);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        home.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != home) {
                    home.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != home) {
                    home.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = home;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(ACTIVE);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        report.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != report) {
                    report.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != report) {
                    report.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = report;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(ACTIVE);
                logout.setBackground(NORMAL);
            }
        });
        
        logout.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != logout) {
                    logout.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != logout) {
                    logout.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = logout;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                history1.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                home.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(ACTIVE);
            }
        });

    }
}
