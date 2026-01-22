package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Sidebar extends JPanel {
    public JButton createloan;
    public JButton viewloans;
    public JButton loanApplications;
    public JButton createClient;
    public JButton clientList;
    public JButton loanpay;
    public JButton logout;
    public JButton register;
    public JButton btnEditClient;

    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    JButton selected = null;

    public Sidebar() {
        setLayout(null);
        setBackground(new Color(0xAAC3DD));
        setBounds(0, 0, 260, 900);
        setEnabled(true);

        // ===== CONTENT PANEL =====
        JPanel content = new JPanel(null);
        content.setBackground(NORMAL);
        content.setPreferredSize(new Dimension(260, 1030)); // important for scroll

        // ===== SCROLL PANE =====
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBounds(0, 0, 280, 900);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(scroll);

        int y = 30;

        // ===== LOAN MANAGEMENT =====
        JLabel loanmanagement = new JLabel("Loan Management");
        loanmanagement.setBounds(20, y, 200, 20);
        loanmanagement.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(loanmanagement);
        y += 30;

        ImageIcon create = new ImageIcon(Finalp.class.getResource("/finalp/images/create.png"));
        createloan = new JButton("Create Loan Application");
        createloan.setBounds(20, y, 235, 40);
        createloan.setIcon(create);
        createloan.setBackground(NORMAL);
        createloan.setHorizontalTextPosition(SwingConstants.RIGHT);
        createloan.setIconTextGap(5);
        content.add(createloan);
        y += 50;

        ImageIcon view = new ImageIcon(Finalp.class.getResource("/finalp/images/view.png"));
        viewloans = new JButton("View All Loans");
        viewloans.setBounds(20, y, 235, 40);
        viewloans.setIcon(view);
        viewloans.setBackground(NORMAL);
        viewloans.setHorizontalTextPosition(SwingConstants.RIGHT);
        viewloans.setIconTextGap(5);
        content.add(viewloans);
        y += 50;

        ImageIcon loanapply = new ImageIcon(Finalp.class.getResource("/finalp/images/loanapply.png"));
        loanApplications = new JButton("Approve Loan Applications");
        loanApplications.setBounds(20, y, 235, 40);
        loanApplications.setIcon(loanapply);
        loanApplications.setBackground(NORMAL);
        loanApplications.setHorizontalTextPosition(SwingConstants.RIGHT);
        loanApplications.setIconTextGap(5);
        content.add(loanApplications);
        y += 70;

        // ===== CLIENTS =====
        JLabel clients = new JLabel("Clients");
        clients.setBounds(20, y, 200, 20);
        clients.setFont(new Font("Arial", Font.BOLD, 14));
        content.add(clients);
        y += 30;

        ImageIcon createclient = new ImageIcon(Finalp.class.getResource("/finalp/images/createclient.png"));
        createClient = new JButton("Create Client");
        createClient.setBounds(20, y, 235, 40);
        createClient.setIcon(createclient);
        createClient.setBackground(NORMAL);
        createClient.setHorizontalTextPosition(SwingConstants.RIGHT);
        createClient.setIconTextGap(5);
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
        y += 50;

        ImageIcon editClientRecord = new ImageIcon(Finalp.class.getResource("/finalp/images/edit-client-records.png"));
        btnEditClient = new JButton("Edit Client Record");
        btnEditClient.setBounds(20, y, 235, 40);
        btnEditClient.setIcon(editClientRecord);
        btnEditClient.setBackground(NORMAL);
        btnEditClient.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnEditClient.setIconTextGap(5);
        content.add(btnEditClient);
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
        y += 50;
        setVisible(true);

        ImageIcon registerIcon = new ImageIcon(Finalp.class.getResource("/finalp/images/reports.png"));
        register = new JButton("Register Employee");
        register.setBounds(20, y, 235, 40);
        register.setIcon(registerIcon);
        register.setBackground(NORMAL);
        register.setHorizontalTextPosition(SwingConstants.RIGHT);
        register.setIconTextGap(5);
        content.add(register);
        y += 50;

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
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                register.setBackground(NORMAL);
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
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });

        loanApplications.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != loanApplications) {
                    loanApplications.setBackground(ACTIVE);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (selected != loanApplications) {
                    loanApplications.setBackground(NORMAL);
                }
            }

            public void mousePressed(MouseEvent cl) {
                selected = loanApplications;

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                loanApplications.setBackground(ACTIVE);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                register.setBackground(NORMAL);
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

                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(ACTIVE);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                register.setBackground(NORMAL);
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
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(ACTIVE);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                register.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });

        btnEditClient.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent z) {
                if (selected != clientList) {
                    btnEditClient.setBackground(ACTIVE);
                }
            }

            public void mouseExited(MouseEvent y) {
                if (selected != clientList) {
                    btnEditClient.setBackground(NORMAL);
                }
            }

            public void mousePressed(MouseEvent zy) {
                selected = clientList;

                ////
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(ACTIVE);
                loanpay.setBackground(NORMAL);            
                register.setBackground(NORMAL);
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

                ////
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(ACTIVE);
                //
                register.setBackground(NORMAL);
                logout.setBackground(NORMAL);
            }
        });

        register.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != register) {
                    register.setBackground(ACTIVE);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (selected != register) {
                    register.setBackground(NORMAL);
                }
            }

            public void mousePressed(MouseEvent cl) {
                selected = register;

                
                createloan.setBackground(NORMAL);
                viewloans.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);
                
                register.setBackground(ACTIVE);
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
                loanApplications.setBackground(NORMAL);
                createClient.setBackground(NORMAL);
                clientList.setBackground(NORMAL);
                btnEditClient.setBackground(NORMAL);
                loanpay.setBackground(NORMAL);                
                register.setBackground(NORMAL);
                logout.setBackground(ACTIVE);
            }
        });
    }
}