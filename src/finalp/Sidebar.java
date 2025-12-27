package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Sidebar extends JPanel {
    public JButton home;
    public JButton createloan;
    public JButton viewloans;
    public JButton createClient;
    public JButton clientList;
    public JButton loanpay;
    public JButton payhistory;
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
        
        // ===== CONTENT PANEL =====
        JPanel content = new JPanel(null);
        content.setBackground(NORMAL);
        content.setPreferredSize(new Dimension(260, 980)); // important for scroll
        
        // ===== SCROLL PANE =====
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBounds(0, 0, 280, 900);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(scroll);

        int y = 30;
        
        // ===== HOME (TOP) =====
        JLabel hometitle = new JLabel("Home");
        hometitle.setBounds(20, y, 200, 10);
        hometitle.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(hometitle);
        y += 23;
        
        ImageIcon dash = new ImageIcon(Finalp.class.getResource("/finalp/images/home.png"));
        home = new JButton("Home/Dashboard");
        home.setBounds(20, y, 235, 40);
        home.setIcon(dash);
        home.setBackground(NORMAL);
        home.setHorizontalTextPosition(SwingConstants.RIGHT);
        home.setIconTextGap(5);
        content.add(home);
        y += 70;
        
        // ===== LOAN MANAGEMENT =====
        JLabel loanmanagement = new JLabel("Loan Management");
        loanmanagement.setBounds(20, y, 200, 20);
        loanmanagement.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(loanmanagement);
        y += 30;

        ImageIcon create = new ImageIcon(Finalp.class.getResource("/finalp/images/create.png"));
        createloan = new JButton("Create New Loan");
        createloan.setBounds(20, y, 235, 40);
        createloan.setIcon(create);
        createloan.setBackground(NORMAL);
        createloan.setHorizontalTextPosition(SwingConstants.RIGHT);
        createloan.setIconTextGap(5);
        content.add(createloan);
        y += 50;

        ImageIcon view = new ImageIcon(Finalp.class.getResource("/finalp/images/view.png"));
        viewloans = new JButton("View Loans");
        viewloans.setBounds(20, y, 235, 40);
        viewloans.setIcon(view);
        viewloans.setBackground(NORMAL);
        viewloans.setHorizontalTextPosition(SwingConstants.RIGHT);
        viewloans.setIconTextGap(5);
        content.add(viewloans);
        y += 70;

        // ===== CLIENTS =====
        JLabel clients = new JLabel("Clients");
        clients.setBounds(20, y, 200, 20);
        clients.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(clients);
        y += 30;

        createClient = new JButton("Create Client");
        createClient.setBounds(20, y, 235, 40);
        createClient.setBackground(NORMAL);
        content.add(createClient);
        y += 50;
        
        ImageIcon list = new ImageIcon(Finalp.class.getResource("/finalp/images/clientslist.png"));
        clientList = new JButton("Clients List");
        clientList.setBounds(20, y, 235, 40);
        clientList.setIcon(list);
        clientList.setBackground(NORMAL);
        clientList.setHorizontalTextPosition(SwingConstants.RIGHT);
        clientList.setIconTextGap(5);
        content.add(clientList);
        y += 70;

        // ===== PAYMENTS =====
        JLabel payments = new JLabel("Payments");
        payments.setBounds(20, y, 200, 20);
        payments.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(payments);
        y += 30;

        ImageIcon pay = new ImageIcon(Finalp.class.getResource("/finalp/images/payment.png"));
        loanpay = new JButton("Loan Payments");
        loanpay.setBounds(20, y, 235, 40);
        loanpay.setIcon(pay);
        loanpay.setBackground(NORMAL);
        loanpay.setHorizontalTextPosition(SwingConstants.RIGHT);
        loanpay.setIconTextGap(5);
        content.add(loanpay);
        y += 50;

        ImageIcon his2 = new ImageIcon(Finalp.class.getResource("/finalp/images/history2.png"));
        payhistory = new JButton("History");
        payhistory.setBounds(20, y, 235, 40);
        payhistory.setIcon(his2);
        payhistory.setBackground(NORMAL);
        payhistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        payhistory.setIconTextGap(5);
        content.add(payhistory);
        y += 70;
        
        // ===== REPORTS =====
        JLabel reports = new JLabel("Reports");
        reports.setBounds(20, y, 200, 20);
        reports.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(reports);
        y += 30;

        ImageIcon reportsIcon = new ImageIcon(Finalp.class.getResource("/finalp/images/reports.png"));
        report = new JButton("Reports");
        report.setBounds(20, y, 235, 40);
        report.setIcon(reportsIcon);
        report.setBackground(NORMAL);
        report.setHorizontalTextPosition(SwingConstants.RIGHT);
        report.setIconTextGap(5);
        content.add(report);
        y += 70;
        
        // ===== OTHERS =====
        JLabel others = new JLabel("Others");
        others.setBounds(20, y, 200, 20);
        others.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(others);
        y += 30;

        ImageIcon out = new ImageIcon(Finalp.class.getResource("/finalp/images/logout.png"));
        logout = new JButton("Logout");
        logout.setBounds(20, y, 235, 40);
        logout.setIcon(out);
        logout.setBackground(NORMAL);
        logout.setHorizontalTextPosition(SwingConstants.RIGHT);
        logout.setIconTextGap(5);
        content.add(logout);

        setVisible(true);
        
        //functions of the buttons mouselistener
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

                home.setBackground(ACTIVE);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
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

                home.setBackground(NORMAL);
                createloan.setBackground(ACTIVE);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
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

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(ACTIVE);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });
        
        createClient.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != createClient) {
                    createClient.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != createClient) {
                    createClient.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = createClient;

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(ACTIVE);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
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

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(ACTIVE);
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

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(ACTIVE);
                payhistory.setBackground(NORMAL);
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

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(ACTIVE);
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

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
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

                home.setBackground(NORMAL);
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                payhistory.setBackground(NORMAL);
                report.setBackground(NORMAL);
                logout.setBackground(ACTIVE);
            }
        });

    }
}
