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

        JPanel content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.WHITE);
        
        

        
    }
}