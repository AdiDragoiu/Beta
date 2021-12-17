import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstFrame extends JFrame implements ActionListener {
    private JPanel firstFrame;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;
    private JLabel welcome;

    public firstFrame(){
        setTitle("QuizzApp");
        welcome.setFont(new Font("Courier", Font.BOLD,30));
        this.setContentPane(firstFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addActionEvent2();
        this.setSize(600,500);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public void addActionEvent2(){
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        exitButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            this.setVisible(false);
            new loginFrame().setVisible(true);
        }
        if(e.getSource() == registerButton){
            this.setVisible(false);
            new registerInteface().setVisible(true);
        }
        if(e.getSource() == exitButton)
            System.exit(0);
    }



}
