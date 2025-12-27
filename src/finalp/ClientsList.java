package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class ClientsList extends JPanel{
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);

    JButton selected = null;
    
    public ClientsList() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        // ===== CONTENT PANEL 
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(1090, 990));

        // ===== SCROLL PANE =====
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(0, 0, 1090, 800);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(scrollPane);
        
        // ===== HEADER =====
        JLabel header = new JLabel("Clients List");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 400, 40);
        contentPanel.add(header);

        JLabel sub = new JLabel("View current clients");
        sub.setBounds(23, 55, 400, 20);
        contentPanel.add(sub);

        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        contentPanel.add(sep);

        // ===== ACTIVE CLIENTS =====
        JLabel activeLbl = new JLabel("Active / Ongoing");
        activeLbl.setFont(new Font("Arial", Font.BOLD, 16));
        activeLbl.setBounds(37, 100, 300, 30);
        contentPanel.add(activeLbl);

        int y = 150;

        for (int i = 0; i < 4; i++) { 

            JPanel pic = new JPanel(null);
            pic.setBounds(47, y, 50, 50);
            pic.setBackground(NORMAL);
            contentPanel.add(pic);

            JLabel name = new JLabel("John Doe dela Cruz");
            name.setFont(new Font("Arial", Font.BOLD, 18));
            name.setBounds(115, y + 10, 300, 30);
            contentPanel.add(name);

            JButton status = new JButton("Status");
            status.setBounds(900, y + 10, 100, 30);
            status.setBackground(NORMAL);
            status.setBorderPainted(false);
            contentPanel.add(status);

            status.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    status.setBackground(ACTIVE);
                }
                public void mouseExited(MouseEvent e) {
                    status.setBackground(NORMAL);
                }
            });

            y += 80;
        }

        // ===== COMPLETED / PAID =====
        JLabel historyLbl = new JLabel("Completed / Paid");
        historyLbl.setFont(new Font("Arial", Font.BOLD, 16));
        historyLbl.setBounds(37, y + 10, 300, 30);
        contentPanel.add(historyLbl);

        y += 70;

        for (int i = 0; i < 4; i++) {

            JPanel pic = new JPanel(null);
            pic.setBounds(47, y, 50, 50);
            pic.setBackground(NORMAL);
            contentPanel.add(pic);

            JLabel name = new JLabel("Jane Doe");
            name.setFont(new Font("Arial", Font.BOLD, 18));
            name.setBounds(115, y + 10, 300, 30);
            contentPanel.add(name);

            JButton status = new JButton("Status");
            status.setBounds(900, y + 10, 100, 30);
            status.setBackground(NORMAL);
            status.setBorderPainted(false);
            contentPanel.add(status);
            
            status.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    status.setBackground(ACTIVE);
                }
                public void mouseExited(MouseEvent e) {
                    status.setBackground(NORMAL);
                }
            });

            y += 80;
        }
    }
}