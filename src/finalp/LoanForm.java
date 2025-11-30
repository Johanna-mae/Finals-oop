package finalp;

import java.awt.*;
import javax.swing.*;

class LoanForm extends JPanel {
    public LoanForm() {
        setLayout(null); //outside the form
        setBounds(280, 0, 1090, 800);
        setBackground(new Color(0xFFFFFF));

        JLabel header = new JLabel("Create New Loan");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("Enter details to avail loan");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        //for the form
        JPanel box = new JPanel();
        box.setLayout(null);
        box.setBackground(new Color(240, 240, 240));
        box.setBounds(170, 135, 750, 340);
        add(box);

        JLabel lbl1 = new JLabel("Client's Name");
        lbl1.setBounds(20, 30, 150, 30);
        lbl1.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl1);

        JTextField tf1 = new JTextField();
        tf1.setBounds(180, 30, 550, 30);
        box.add(tf1);

        JLabel lbl2 = new JLabel("Loan Amount");
        lbl2.setBounds(20, 80, 150, 30);
        lbl2.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl2);

        JTextField tf2 = new JTextField();
        tf2.setBounds(180, 80, 550, 30);
        box.add(tf2);

        JLabel lbl3 = new JLabel("Interest Rate (%)");
        lbl3.setBounds(20, 130, 150, 30);
        lbl3.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl3);

        String[] rates = {"1%", "2%", "5%", "10%", "15%", "20%"};
        JComboBox<String> rate = new JComboBox<>(rates);
        rate.setBounds(180, 130, 550, 30);
        box.add(rate);

        JLabel lbl4 = new JLabel("Loan Term");
        lbl4.setBounds(20, 180, 150, 30);
        lbl4.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl4);

        String[] terms = {"1 month", "3 months", "6 months", "12 months", "24 months"};
        JComboBox<String> loanterm = new JComboBox<>(terms);
        loanterm.setBounds(180, 180, 550, 30);
        box.add(loanterm);

        JLabel lbl5 = new JLabel("Estimated Amount");
        lbl5.setBounds(20, 230, 150, 30);
        lbl5.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl5);
        
        JTextField tf5 = new JTextField();
        tf5.setBounds(180, 230, 550, 30);
        box.add(tf5);

        JLabel lbl6 = new JLabel("Status");
        lbl6.setBounds(20, 280, 150, 30);
        lbl6.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lbl6);
        
        JTextField tf6 = new JTextField();
        tf6.setBounds(180, 280, 550, 30);
        box.add(tf6);
        
        setVisible(true);
        
        
        //for buttons
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(620, 500, 120, 40);
        add(cancel);

        JButton create = new JButton("Create Loan");
        create.setBounds(770, 500, 150, 40);
        add(create);
    }
}
