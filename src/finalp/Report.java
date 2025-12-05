package finalp;

import java.awt.*;
import javax.swing.*;

class Report extends JPanel{
    public Report() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Loan Summary Report");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        add(header);
        
        JLabel sub = new JLabel("Comprehensive loan and statistics");
        sub.setBounds(23, 55, 400, 20);
        add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        JPanel content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color. GRAY);
        content.setBounds(50, 100, 980, 530);
        add(content);
        
        

        
    }
}