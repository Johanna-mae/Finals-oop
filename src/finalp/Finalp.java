package finalp;

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

        LoginScreen loginFrame = new LoginScreen();
        loginFrame.setBounds(50, 50, 960, 720);
        loginFrame.setVisible(true);
        loginFrame.setEnabled(true);
        loginFrame.setTitle("Employee Login");
        frame.add(loginFrame);
        
    }
}