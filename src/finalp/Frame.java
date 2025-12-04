package finalp;

import java.awt.Color;
import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setTitle("PaLoan: Lending Made Easy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        
        ImageIcon image = new ImageIcon(Finalp.class.getResource("/finalp/images/logo-icon.png"));
        setIconImage(image.getImage());
        getContentPane().setBackground(new Color(0xFFFFFF));
    }
}