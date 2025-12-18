package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class LoanPayment extends JPanel{
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
        
        JLabel subtitle = new JLabel("Payment to an existing loan");
        subtitle.setBounds(23, 55, 400, 20);
        add(subtitle);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        //for the form
        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(NORMAL);
        box.setBounds(170, 135, 750, 340);
        add(box);

        JLabel lbl1 = new JLabel("Client's Name");
        lbl1.setBounds(20, 30, 150, 30);
        lbl1.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl1);

        JTextField tf1 = new JTextField();
        tf1.setBounds(180, 30, 550, 30);
        box.add(tf1);

        JLabel lbl2 = new JLabel("Account No.");
        lbl2.setBounds(20, 80, 150, 30);
        lbl2.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl2);

        JTextField tf2 = new JTextField();
        tf2.setBounds(180, 80, 550, 30);
        box.add(tf2);

        JLabel lbl3 = new JLabel("Select Payment Type");
        lbl3.setBounds(20, 130, 150, 30);
        lbl3.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl3);

        String[] type = {"Card", "E-wallet"};
        JComboBox<String> rate = new JComboBox<>(type);
        rate.setBounds(180, 130, 550, 30);
        rate.setBackground(NORMAL);
        box.add(rate);

        JLabel lbl4 = new JLabel("Payment Amount");
        lbl4.setBounds(20, 230, 150, 30);
        lbl4.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl4);
        
        JTextField tf4 = new JTextField();
        tf4.setBounds(180, 230, 550, 30);
        box.add(tf4);

        JLabel lbl5 = new JLabel("Payment Date");
        lbl5.setBounds(20, 280, 150, 30);
        lbl5.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl5);
        
        JTextField tf5 = new JTextField();
        tf5.setBounds(180, 280, 550, 30);
        box.add(tf5);
        
        JLabel lbl6 = new JLabel("Select Loan");
        lbl6.setBounds(20, 180, 150, 30);
        lbl6.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl6);

        String[] loans = {"pagpalain", "charot", "eme", "sino ka?"};
        JComboBox<String> loanterm = new JComboBox<>(loans);
        loanterm.setBounds(180, 180, 550, 30);
        box.add(loanterm);
        
        setVisible(true);
        
        
        //for buttons
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(710, 500, 100, 40);
        cancel.setBackground(NORMAL);
        add(cancel);

        JButton pay = new JButton("Pay");
        pay.setBounds(840, 500, 80, 40);
        pay.setBackground(NORMAL);
        add(pay);
        
        //functions ng button mouselistener
        cancel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != cancel) {
                    cancel.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != cancel) {
                    cancel.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = cancel;

                cancel.setBackground(ACTIVE);
                pay.setBackground(NORMAL);
            }
        });
        
        pay.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent c) {
                if (selected != pay) {
                    pay.setBackground(ACTIVE);
                }
            }
            public void mouseExited(MouseEvent e) {
                if (selected != pay) {
                    pay.setBackground(NORMAL);
                }
            }
            public void mousePressed(MouseEvent cl) {
                selected = pay;

                cancel.setBackground(NORMAL);
                pay.setBackground(ACTIVE);
            }
        });
    }
}