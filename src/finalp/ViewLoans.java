package finalp;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class ViewLoans extends JPanel{
    Color NORMAL = new Color(0xAAC3DD);
    Color ACTIVE = new Color(0x8AA1B9);
    
    ArrayList<JPanel> cards = new ArrayList<>();
    
    JPanel content;
    
    JScrollPane scroll;
    
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

        // ===== SCROLLABLE CONTENT =====
        content = new JPanel(null);
        content.setBackground(Color.WHITE);
        content.setPreferredSize(new Dimension(1090, 0));

        scroll = new JScrollPane(content);
        scroll.setBounds(0, 100, 1090, 700);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.setBorder(null); 
        add(scroll);

        
        int y = 20; 
        for (int i = 0; i < 4; i++) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setBackground(NORMAL);
            card.setBounds(70, y, 950, 110);
            card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
            content.add(card);

            cards.add(card);
            
            JPanel pic = new JPanel(null);
            pic.setBackground(ACTIVE);
            pic.setBounds(30, 25, 60, 60);
            pic.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            card.add(pic);

            ImageIcon boy = new ImageIcon(Finalp.class.getResource("/finalp/images/boy2.png"));
            JLabel iconLabel = new JLabel(boy);
            iconLabel.setBounds(0, 0, 60, 60);
            pic.add(iconLabel);
            
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
            status.setBackground(NORMAL);
            status.setFocusPainted(false);
            status.setBorderPainted(false); 
            card.add(status);

            //details
            JPanel moredetail = new JPanel(null);
            moredetail.setBounds(0, 110, 950, 80);
            moredetail.setBackground(NORMAL);
            moredetail.setVisible(false);
            card.add(moredetail);

            JLabel accNo = new JLabel("Account No: 2024-00123");
            accNo.setBounds(110, 10, 300, 20);
            accNo.setFont(new Font("Arial", Font.PLAIN, 12));
            moredetail.add(accNo);

            JLabel loanType = new JLabel("Loan Type: Personal Loan");
            loanType.setBounds(110, 35, 300, 20);
            loanType.setFont(new Font("Arial", Font.PLAIN, 12));
            moredetail.add(loanType);

            JLabel loanTerm = new JLabel("Loan Term: 12 months");
            loanTerm.setBounds(450, 10, 300, 20);
            loanTerm.setFont(new Font("Arial", Font.PLAIN, 12));
            moredetail.add(loanTerm);

            JLabel interest = new JLabel("Interest Rate: 5%");
            interest.setBounds(450, 35, 300, 20);
            interest.setFont(new Font("Arial", Font.PLAIN, 12));
            moredetail.add(interest);

            //drop down details
            final int index = i;      
            final int EXPAND = 80;   

            status.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                boolean open = moredetail.isVisible();
                
                moredetail.setVisible(!open);

                card.setSize(950, open ? 110 : 110 + EXPAND);

                for (int j = index + 1; j < cards.size(); j++) {
                    JPanel below = cards.get(j);
                    below.setLocation(
                        below.getX(),
                        below.getY() + (open ? -EXPAND : EXPAND)
                    );
                }

                updateContentHeight();
                }
            });

            y += 130;
        }

        updateContentHeight();
    }

    // pang scroll
    private void updateContentHeight() {
        int maxY = 0;

        for (JPanel card : cards) {
            int bottom = card.getY() + card.getHeight();
            if (bottom > maxY) {
                maxY = bottom;
            }
        }

        content.setPreferredSize(new Dimension(
            content.getPreferredSize().width,
            maxY + 150
        ));

        content.revalidate();
        content.repaint();
    }
}