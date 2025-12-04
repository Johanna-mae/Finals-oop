package finalp;
import java.awt.event.*;

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
        form.setVisible(false);
        frame.add(form);
        
        ViewLoans viewloan = new ViewLoans();
        viewloan.setVisible(false);
        frame.add(viewloan);

        ClientsList clientslist = new ClientsList();
        clientslist.setVisible(false);
        frame.add(clientslist);
        
        ClientHistory clienthis = new ClientHistory();
        clienthis.setVisible(false);
        frame.add(clienthis);
        
        LoanPayment loanpayment = new LoanPayment();
        loanpayment.setVisible(false);
        frame.add(loanpayment);
        
        PaymentHistory paymenthis = new PaymentHistory();
        paymenthis.setVisible(false);
        frame.add(paymenthis);
        
        Home dashboard = new Home();
        dashboard.setVisible(false);
        frame.add(dashboard);
        
        Report repo = new Report();
        repo.setVisible(false);
        frame.add(repo);

        
        sidebar.createloan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){
                form.setVisible(true);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(false);
            }
        });

        sidebar.viewloans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b){
                form.setVisible(false);
                viewloan.setVisible(true);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(false);
            } 
        });
        
        sidebar.clientList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(true);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(false);
            }
        });
        
        sidebar.history1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent d){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(true);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(false);
            }
        });
        
        sidebar.loanpay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(true);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(false);
            }
        });
        
        sidebar.payhistory.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(true);
                dashboard.setVisible(false);
                repo.setVisible(false);
            }
        });
        
        sidebar.home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(true);
                repo.setVisible(false);
            }
        });
        
        sidebar.report.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(true);
            }
        });
        
        sidebar.logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent c){
                form.setVisible(false);
                viewloan.setVisible(false);
                clientslist.setVisible(false);
                clienthis.setVisible(false);
                loanpayment.setVisible(false);
                paymenthis.setVisible(false);
                dashboard.setVisible(false);
                repo.setVisible(false);
            }
        });
        
        LoginScreen loginFrame = new LoginScreen();
        loginFrame.setBounds(50, 50, 960, 720);
        loginFrame.setVisible(true);
        loginFrame.setEnabled(true);
        loginFrame.setTitle("Employee Login");
        frame.add(loginFrame);
        
    }
}