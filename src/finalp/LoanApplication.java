package finalp;

import javax.swing.*;
import java.awt.*;

class LoanApplication extends JPanel{
    
    Color NORMAL = new Color(0xAAC3DD);

    JTextField tfClient, tfType, tfAmount, tfEstimate, tfTerm, tfAppId, tfDate;
    JTextArea taPurpose;

    public LoanApplication() {
        setLayout(null);
        setBounds(280, 0, 1090, 800);
        setBackground(Color.WHITE);

        JLabel header = new JLabel("Pending Loan Application");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setBounds(20, 25, 500, 40);
        add(header);

        JLabel sub = new JLabel("View details to avail loan");
        sub.setBounds(23, 55, 400, 20);
        add(sub);
        
        JSeparator sep = new JSeparator();
        sep.setBounds(23, 80, 1040, 2);
        add(sep);

        JPanel box = new JPanel(null);
        box.setBackground(NORMAL);
        box.setBounds(320, 110, 450, 385);
        add(box);

        tfClient   = addField(box, "Client Name", 20, 20);
        tfType     = addField(box, "Loan Type", 20, 60);
        tfAmount   = addField(box, "Requested Amount", 20, 100);
        tfEstimate = addField(box, "Est. / Month", 20, 140);
        tfTerm     = addField(box, "Term (Months)", 20, 180);

        JLabel lblPurpose = new JLabel("Purpose");
        lblPurpose.setBounds(20, 220, 150, 25);
        box.add(lblPurpose);

        taPurpose = new JTextArea();
        taPurpose.setEditable(false);
        JScrollPane sp = new JScrollPane(taPurpose);
        sp.setBounds(180, 220, 250, 60);
        box.add(sp);

        tfAppId = addField(box, "Application ID", 20, 300);
        tfDate  = addField(box, "Date", 20, 340);
    }

    private JTextField addField(JPanel box, String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 150, 25);
        box.add(lbl);

        JTextField tf = new JTextField();
        tf.setBounds(x + 160, y, 250, 25);
        tf.setEditable(false);
        box.add(tf);

        return tf;
    }

    // 🔑 SETTER METHOD
    public void setApplicationData(
        String client,
        String type,
        String amount,
        String estimate,
        String term,
        String purpose,
        String appId,
        String date
    ) {
        tfClient.setText(client);
        tfType.setText(type);
        tfAmount.setText(amount);
        tfEstimate.setText(estimate);
        tfTerm.setText(term);
        taPurpose.setText(purpose);
        tfAppId.setText(appId);
        tfDate.setText(date);
    }
}
