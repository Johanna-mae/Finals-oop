package finalp;

import java.awt.*;
import javax.swing.*;

class ViewLoans extends JPanel{
    public ViewLoans() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("View Loans");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);

        JLabel sub = new JLabel("View active loans");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        
        int y = 120; 
        for (int i = 0; i < 4; i++) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setBackground(new Color(245, 245, 245));
            card.setBounds(70, y, 950, 110);
            card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
            add(card);

            JPanel pic = new JPanel();
            pic.setBackground(Color.LIGHT_GRAY);
            pic.setBounds(30, 25, 60, 60);
            pic.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            card.add(pic);

            JLabel name = new JLabel("John Doe dela Cruz");
            name.setFont(new Font("Arial", Font.BOLD, 18));
            name.setBounds(110, 20, 400, 30);
            card.add(name);

            JLabel details = new JLabel("Loan Amount: ₱XX,XXX   |   Loan Due Date: YYYY-MM-DD");
            details.setFont(new Font("Arial", Font.PLAIN, 14));
            details.setBounds(110, 55, 450, 25);
            card.add(details);

            JButton status = new JButton("Status");
            status.setBounds(800, 35, 100, 35);
            status.setBackground(new Color(200, 200, 200));
            status.setFocusPainted(false);
            card.add(status);

            y += 130; 
        }
    }
}