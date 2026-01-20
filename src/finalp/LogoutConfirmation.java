package finalp;

import javax.swing.*;

public class LogoutConfirmation extends JFrame{
    public LogoutConfirmation() {
        setTitle("Logout");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void showLogoutConfirmation(Frame frame) {
        int result = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to logout?",
            "Logout Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
                frame.setVisible(false);
                
                LoginScreen loginFrame = new LoginScreen();
                loginFrame.setBounds(50, 50, 960, 720);
                loginFrame.setMainFrame(frame);
                loginFrame.setVisible(true);
                loginFrame.setEnabled(true);
                loginFrame.setTitle("Employee Login");
        }
    }
}
