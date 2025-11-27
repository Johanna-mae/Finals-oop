package finalp;
import javax.swing.*;
import java.awt.*;

public class Finalp {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setBounds(50, 50, 1920,1080);
        frame.setVisible(true);
        frame.setEnabled(true);
        
        Sidebar sidebar = new Sidebar();
        sidebar.setBounds(0, 0, 280, 1000);
        frame.add(sidebar);
        
        

        LoanForm form = new LoanForm();
        frame.add(form);
        
        
        
    }
    
}

class Frame extends JFrame {
    public Frame() {
        setTitle("PaLoan: Lending Made Easy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        
        ImageIcon image = new ImageIcon(Finalp.class.getResource("logo-icon.png"));
        setIconImage(image.getImage());
        getContentPane().setBackground(new Color(0xFFFFFF));
    }
}

class Sidebar extends JPanel {
    public Sidebar() {
        setLayout(null);
        setBackground(new Color(0xB1B2B3));
        setBounds(0,0, 260,900);
        setEnabled(true);
        
        JLabel loanmanagement = new JLabel("Loan Management");
        loanmanagement.setBounds(20, 30, 200, 20);
        loanmanagement.setFont(new Font("Arial", Font.BOLD, 14));
        add(loanmanagement);

        ImageIcon create = new ImageIcon(Finalp.class.getResource("create.png"));
        JButton createloan = new JButton("Create New Loan");
        createloan.setBounds(20, 60, 235, 40);
        createloan.setIcon(create);
        createloan.setHorizontalTextPosition(SwingConstants.RIGHT);
        createloan.setIconTextGap(5);
        add(createloan);

        ImageIcon view = new ImageIcon(Finalp.class.getResource("view.png"));
        JButton viewloans = new JButton("View Loans");
        viewloans.setBounds(20, 110, 235, 40);
        viewloans.setIcon(view);
        viewloans.setHorizontalTextPosition(SwingConstants.RIGHT);
        viewloans.setIconTextGap(5);
        add(viewloans);


        JLabel clients = new JLabel("Clients");
        clients.setBounds(20, 170, 200, 20);
        clients.setFont(new Font("Arial", Font.BOLD, 14));
        add(clients);

        ImageIcon list = new ImageIcon(Finalp.class.getResource("clientslist.png"));
        JButton clientList = new JButton("Clients List");
        clientList.setBounds(20, 200, 235, 40);
        clientList.setIcon(list);
        clientList.setHorizontalTextPosition(SwingConstants.RIGHT);
        clientList.setIconTextGap(5);
        add(clientList);

        ImageIcon his1 = new ImageIcon(Finalp.class.getResource("history1.png"));
        JButton history1 = new JButton("History");
        history1.setBounds(20, 250, 235, 40);
        history1.setIcon(his1);
        history1.setHorizontalTextPosition(SwingConstants.RIGHT);
        history1.setIconTextGap(5);
        add(history1);

        
        JLabel payments = new JLabel("Payments");
        payments.setBounds(20, 310, 200, 20);
        payments.setFont(new Font("Arial", Font.BOLD, 14));
        add(payments);

        ImageIcon pay = new ImageIcon(Finalp.class.getResource("payment.png"));
        JButton loanpay = new JButton("Loan Payments");
        loanpay.setBounds(20, 340, 235, 40);
        loanpay.setIcon(pay);
        loanpay.setHorizontalTextPosition(SwingConstants.RIGHT);
        loanpay.setIconTextGap(5);
        add(loanpay);

        ImageIcon his2 = new ImageIcon(Finalp.class.getResource("history2.png"));
        JButton payhistory = new JButton("History");
        payhistory.setBounds(20, 390, 235, 40);
        payhistory.setIcon(his2);
        payhistory.setHorizontalTextPosition(SwingConstants.RIGHT);
        payhistory.setIconTextGap(5);
        add(payhistory);
        
        
        JLabel reports = new JLabel("Reports");
        reports.setBounds(20, 450, 200, 20);
        reports.setFont(new Font("Arial", Font.BOLD, 14));
        add(reports);

        ImageIcon dash = new ImageIcon(Finalp.class.getResource("home.png"));
        JButton home = new JButton("Home/Dashboard");
        home.setBounds(20, 480, 235, 40);
        home.setIcon(dash);
        home.setHorizontalTextPosition(SwingConstants.RIGHT);
        home.setIconTextGap(5);
        add(home);

        ImageIcon rep = new ImageIcon(Finalp.class.getResource("reports.png"));
        JButton report = new JButton("Reports");
        report.setBounds(20, 540, 235, 40);
        report.setIcon(rep);
        report.setHorizontalTextPosition(SwingConstants.RIGHT);
        report.setIconTextGap(5);
        add(report);
        
        
        JLabel others = new JLabel("Others");
        others.setBounds(20, 600, 200, 20);
        others.setFont(new Font("Arial", Font.BOLD, 14));
        add(others);

        ImageIcon out = new ImageIcon(Finalp.class.getResource("logout.png"));
        JButton logout = new JButton("Logout");
        logout.setBounds(20, 630, 235, 40);
        logout.setIcon(out);
        logout.setHorizontalTextPosition(SwingConstants.RIGHT);
        logout.setIconTextGap(5);
        add(logout);

        setVisible(true);
    }
}

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
