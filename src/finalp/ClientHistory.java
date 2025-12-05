package finalp;

import java.awt.*;
import javax.swing.*;

class ClientHistory extends JPanel{
    public ClientHistory() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("History");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);
        
        JLabel sub = new JLabel("View completed loans/clients");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        JLabel completed = new JLabel("Completed/Paid");
        completed.setFont(new Font("Arial", Font.BOLD, 16));
        completed.setBounds(37, 100, 300, 30);
        add(completed);

        
        int y = 150;
        for (int i = 0; i < 4; i++) {

            JPanel pic = new JPanel();
            pic.setBackground(new Color(235, 235, 235));
            pic.setBounds(47, y, 50, 50);
            pic.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            add(pic);

            JLabel name = new JLabel("John Doe dela Cruz");
            name.setFont(new Font("Arial", Font.BOLD, 18));
            name.setBounds(115, y + 10, 300, 30);
            add(name);

            JButton status = new JButton("Status");
            status.setFont(new Font("Arial", Font.PLAIN, 16));
            status.setBounds(900, y + 10, 100, 30);
            add(status);

            y += 80;
        }  
    }
}