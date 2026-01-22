package finalp;

import java.awt.event.*;

public class Finalp {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setBounds(50, 50, 1920, 1080);
        frame.setVisible(false);
        frame.setEnabled(true);

        Sidebar sidebar = new Sidebar();
        sidebar.setBounds(0, 0, 280, 1000);
        frame.add(sidebar);

        PendingLoanApplication loanapplication = new PendingLoanApplication();
        LoanForm form = new LoanForm(loanapplication);
        form.setVisible(false);
        loanapplication.setVisible(false);
        frame.add(loanapplication);
        frame.add(form);

        ViewLoans viewloan = new ViewLoans();
        viewloan.setVisible(false);
        frame.add(viewloan);

        CreateClient createclient = new CreateClient();
        createclient.setVisible(false);
        frame.add(createclient);

        ClientsList clientslist = new ClientsList();
        clientslist.setVisible(false);
        frame.add(clientslist);

        EditClientRecord editClientRecord = new EditClientRecord();
        editClientRecord.setVisible(false);
        frame.add(editClientRecord);

        LoanPayment loanpayment = new LoanPayment();
        loanpayment.setVisible(false);
        frame.add(loanpayment);

        Register reg = new Register();
        reg.setVisible(false);
        frame.add(reg);

        sidebar.createloan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                
                form.setVisible(true);
                viewloan.setVisible(false);
                loanapplication.setVisible(false);
                createclient.setVisible(false);
                clientslist.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(false);
                
                reg.setVisible(false);
            }
        });

        sidebar.viewloans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                
                form.setVisible(false);
                viewloan.setVisible(true);
                loanapplication.setVisible(false);
                createclient.setVisible(false);
                clientslist.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(false);
                
            }
        });

        sidebar.loanApplications.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                
                form.setVisible(false);
                viewloan.setVisible(false);
                loanapplication.setVisible(true);
                createclient.setVisible(false);
                clientslist.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(false);
                
                reg.setVisible(false);
            }
        });

        sidebar.clientList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                
                form.setVisible(false);
                viewloan.setVisible(false);
                loanapplication.setVisible(false);
                clientslist.setVisible(true);
                createclient.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(false);
                
                reg.setVisible(false);
            }
        });

        sidebar.createClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                
                form.setVisible(false);
                viewloan.setVisible(false);
                loanapplication.setVisible(false);
                createclient.setVisible(true);
                clientslist.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(false);
                
                reg.setVisible(false);
            }
        });

        sidebar.btnEditClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent z) {
                
                form.setVisible(false);
                viewloan.setVisible(false);
                loanapplication.setVisible(false);
                createclient.setVisible(false);
                clientslist.setVisible(false);
                editClientRecord.setVisible(true);
                loanpayment.setVisible(false);
                
                reg.setVisible(false);
            }
        });

        sidebar.loanpay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                
                form.setVisible(false);
                viewloan.setVisible(false);
                loanapplication.setVisible(false);
                createclient.setVisible(false);
                clientslist.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(true);
                
                reg.setVisible(false);
            }
        });

        sidebar.register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                
                form.setVisible(false);
                viewloan.setVisible(false);
                createclient.setVisible(false);
                clientslist.setVisible(false);
                editClientRecord.setVisible(false);
                loanpayment.setVisible(false);
                
                reg.setVisible(true);
            }
        });

        sidebar.logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                LogoutConfirmation logOutConfirm = new LogoutConfirmation();
                logOutConfirm.showLogoutConfirmation(frame);
            }
        });

        LoginScreen loginFrame = new LoginScreen();
        loginFrame.setBounds(50, 50, 960, 720);
        loginFrame.setMainFrame(frame);
        loginFrame.setVisible(true);
        loginFrame.setEnabled(true);
        loginFrame.setTitle("Employee Login");

    }
}