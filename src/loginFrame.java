import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginFrame extends JFrame implements ActionListener{
    private JPanel loginFrame;
    public JTextField userText;
    private JButton confirmButton;
    private JPasswordField passText;
    private JCheckBox seePasswordCheckBox;
    private JButton cancelButton;
    private JTextField textField1;

    String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    String user = "postgres";
    String password = "rel12345";


    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2= null;
    ResultSet rs = null;


    public loginFrame(){
        setTitle("QuizzApp");
        this.setContentPane(loginFrame);
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.setResizable(false);
        userText.setBackground(new Color(69,73,70));
        userText.setForeground(Color.white);
        passText.setBackground(new Color(69,73,70));
        passText.setForeground(Color.white);
        addActionEvent();
        //this.connect();
        this.setLocationRelativeTo(null);

       // this.getUserText();
    }
   /* public String getUserText(){
        return userText.getText();
    }*/


     private void addActionEvent(){
        confirmButton.addActionListener(this);
        seePasswordCheckBox.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {

            String usrText;
            String pwdText;

            pwdText = passText.getText();
            usrText = userText.getText();
            int ok=0;
            if (usrText.equals("Adi") && pwdText.equals("2")) {
                ok=1;
                this.setVisible(false);
                new adminFrame().setVisible(true);

            }
            else
               try{
                    String query = "Select * FROM users WHERE email=? and password=?";
                    con = DriverManager.getConnection(url,user,password);
                    pst = con.prepareStatement(query);
                    pst.setString(1,userText.getText());
                    pst.setString(2,passText.getText());
                    ResultSet rs =pst.executeQuery();

                    if(rs.next()){

                        String query2 = "UPDATE users SET loggedin=true where email =?";
                        pst2 = con.prepareStatement(query2);
                        pst2.setString(1, userText.getText());
                        pst2.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Username and Password Matched");
                        this.setVisible(false);
                        new loginSuccesfull().setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Username and Password Do not Matched");
                    }

                }catch(Exception x){
                   JOptionPane.showMessageDialog(null,x);

                }
            }


        if (e.getSource() == seePasswordCheckBox) {
            if (seePasswordCheckBox.isSelected()) {
                passText.setEchoChar((char) 0);
            } else {
                passText.setEchoChar('*');
            }
        }
        if(e.getSource() == cancelButton){
            this.setVisible(false);
            new firstFrame().setVisible(true);
        }
    }
   /* public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to the PostgreSQL server succesfully");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;

    }*/
    public static void main(String[] args){

    }
}
