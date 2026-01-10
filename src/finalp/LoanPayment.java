package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class LoanPayment extends JPanel {

    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    JButton selected = null;

    public LoanPayment() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Loan Payments");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("Payment to an existing loan");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        // ================= FORM BOX =================
        JPanel box = new JPanel(null);
        box.setBackground(NORMAL);
        box.setBounds(170, 110, 750, 465);
        add(box);

        // Client Name
        JLabel lblClient = new JLabel("Client Name");
        lblClient.setFont(new Font("Arial", Font.BOLD, 14));
        lblClient.setBounds(20, 25, 160, 28);
        box.add(lblClient);

        JComboBox<String> cbClient = new JComboBox<>(
                new String[]{"Juan Dela Cruz - C001", "Maria Santos - C002"}
        );
        cbClient.setBounds(178, 25, 550, 28);
        box.add(cbClient);

        // Loan
        JLabel lblLoan = new JLabel("Loan");
        lblLoan.setFont(new Font("Arial", Font.BOLD, 14));
        lblLoan.setBounds(20, 63, 160, 28);
        box.add(lblLoan);

        JComboBox<String> cbLoan = new JComboBox<>(
                new String[]{"Personal Loan - L001", "Business Loan - L002"}
        );
        cbLoan.setBounds(178, 63, 550, 28);
        box.add(cbLoan);

        // Due Date
        JLabel lblDue = new JLabel("Due Date");
        lblDue.setFont(new Font("Arial", Font.BOLD, 14));
        lblDue.setBounds(20, 101, 160, 28);
        box.add(lblDue);

        JTextField tfDue = new JTextField("2026-01-31");
        tfDue.setEditable(false);
        tfDue.setBounds(178, 101, 550, 28);
        box.add(tfDue);

        // Principal Paid
        JLabel lblPrincipal = new JLabel("Principal Paid");
        lblPrincipal.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrincipal.setBounds(20, 139, 160, 28);
        box.add(lblPrincipal);

        JTextField tfPrincipal = new JTextField();
        tfPrincipal.setBounds(178, 139, 550, 28);
        box.add(tfPrincipal);

        // Interest Paid
        JLabel lblInterest = new JLabel("Interest Paid");
        lblInterest.setFont(new Font("Arial", Font.BOLD, 14));
        lblInterest.setBounds(20, 177, 160, 28);
        box.add(lblInterest);

        JTextField tfInterest = new JTextField();
        tfInterest.setBounds(178, 177, 550, 28);
        box.add(tfInterest);

        // Amount Paid
        JLabel lblAmount = new JLabel("Amount Paid");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmount.setBounds(20, 215, 160, 28);
        box.add(lblAmount);

        JTextField tfAmount = new JTextField();
        tfAmount.setEditable(false);
        tfAmount.setBounds(178, 215, 550, 28);
        box.add(tfAmount);

        // Penalty Fee
        JLabel lblPenalty = new JLabel("Penalty Fee");
        lblPenalty.setFont(new Font("Arial", Font.BOLD, 14));
        lblPenalty.setBounds(20, 253, 160, 28);
        box.add(lblPenalty);

        JTextField tfPenalty = new JTextField("0.00");
        tfPenalty.setEditable(false);
        tfPenalty.setBounds(178, 253, 550, 28);
        box.add(tfPenalty);

        // Payment Method
        JLabel lblMethod = new JLabel("Payment Method");
        lblMethod.setFont(new Font("Arial", Font.BOLD, 14));
        lblMethod.setBounds(20, 291, 160, 28);
        box.add(lblMethod);

        JComboBox<String> cbMethod = new JComboBox<>(
                new String[]{"Cash", "Bank Transfer", "Check", "GCash", "PayMaya"}
        );
        cbMethod.setBounds(178, 291, 550, 28);
        box.add(cbMethod);

        // Reference No
        JLabel lblRef = new JLabel("Reference No.");
        lblRef.setFont(new Font("Arial", Font.BOLD, 14));
        lblRef.setBounds(20, 329, 160, 28);
        box.add(lblRef);

        JTextField tfRef = new JTextField();
        tfRef.setBounds(178, 329, 550, 28);
        box.add(tfRef);

        // Processed By
        JLabel lblEmp = new JLabel("Processed By");
        lblEmp.setFont(new Font("Arial", Font.BOLD, 14));
        lblEmp.setBounds(20, 367, 160, 28);
        box.add(lblEmp);

        JComboBox<String> cbEmp = new JComboBox<>(
                new String[]{"Employee 1", "Employee 2"}
        );
        cbEmp.setBounds(178, 367, 550, 28);
        box.add(cbEmp);

        // Remarks
        JLabel lblRemarks = new JLabel("Remarks");
        lblRemarks.setFont(new Font("Arial", Font.BOLD, 14));
        lblRemarks.setBounds(20, 405, 160, 28);
        box.add(lblRemarks);

        JScrollPane spRemarks = new JScrollPane(new JTextArea());
        spRemarks.setBounds(178, 405, 550, 35);
        box.add(spRemarks);

        // ===== AUTO COMPUTE AMOUNT PAID =====
        KeyAdapter compute = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                try {
                    double p = tfPrincipal.getText().isEmpty() ? 0 : Double.parseDouble(tfPrincipal.getText());
                    double i = tfInterest.getText().isEmpty() ? 0 : Double.parseDouble(tfInterest.getText());
                    tfAmount.setText(String.valueOf(p + i));
                } catch (NumberFormatException ex) {
                    tfAmount.setText("0.00");
                }
            }
        };
        tfPrincipal.addKeyListener(compute);
        tfInterest.addKeyListener(compute);

        // ================= BUTTONS =================
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(705, 585, 100, 40);
        cancel.setBackground(NORMAL);
        add(cancel);

        JButton pay = new JButton("Pay");
        pay.setBounds(820, 585, 100, 40);
        pay.setBackground(NORMAL);
        add(pay);

        cancel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (selected != cancel) cancel.setBackground(ACTIVE);
            }
            public void mouseExited(MouseEvent e) {
                if (selected != cancel) cancel.setBackground(NORMAL);
            }
            public void mousePressed(MouseEvent e) {
                selected = cancel;
                cancel.setBackground(ACTIVE);
                pay.setBackground(NORMAL);
            }
        });

        pay.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (selected != pay) pay.setBackground(ACTIVE);
            }
            public void mouseExited(MouseEvent e) {
                if (selected != pay) pay.setBackground(NORMAL);
            }
            public void mousePressed(MouseEvent e) {
                selected = pay;
                pay.setBackground(ACTIVE);
                cancel.setBackground(NORMAL);
            }
        });
    }
}
