import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class loginSuccesfull extends JFrame implements ActionListener {
    private JPanel appFrame;
    private JButton quizzesButton;
    private JButton myProfileButton;
    private JButton helpButton;
    private JButton logOutButton;
    private JButton exitButton;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    public loginSuccesfull(){
        this.setTitle("QUIZZAPP");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(appFrame);
        addActionEvent();
        myProfileButton.setForeground(Color.black);
        helpButton.setForeground(Color.black);
        quizzesButton.setForeground(Color.black);
        exitButton.setForeground(Color.black);
        logOutButton.setForeground(Color.black);
        this.setSize(500,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void addActionEvent(){
        quizzesButton.addActionListener(this);
        myProfileButton.addActionListener(this);
        helpButton.addActionListener(this);
        logOutButton.addActionListener(this);
        exitButton.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == quizzesButton)
        {
            this.setVisible(false);
            new quizzSelector().setVisible(true);

        }
        if(e.getSource() == logOutButton){
            try{
                con = DriverManager.getConnection(url, user, password);
                pst= con.prepareStatement("UPDATE users SET loggedin=false");
                pst.executeUpdate();
            }catch(Exception o){
                System.out.println(o);
            }
            this.setVisible(false);
            new loginFrame().setVisible(true);
        }
        if(e.getSource() == exitButton)
            System.exit(0);
        if(e.getSource() == helpButton)
            JOptionPane.showMessageDialog(this, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore  magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco \n " + "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        if(e.getSource() == myProfileButton)
        {
            this.setVisible(false);
            new MyProfile();
        }
    }
    
}
