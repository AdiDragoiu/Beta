import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registerInteface extends JFrame implements ActionListener {
    private JPanel registerFrame;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JButton cancelButton;
    private JButton confirmButton1;
    private JPasswordField password1;
    private JPasswordField password2;
    private JTextField username;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2 = null;
   // ResultSet rs = null;

    public registerInteface()
    {
        setTitle("QuizzApp");
        this.setContentPane(registerFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addActionEvent3();
        this.setSize(600,500);
        this.setLocationRelativeTo(null);

    }

    public void addActionEvent3()
    {
        cancelButton.addActionListener(this);
        confirmButton1.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == cancelButton)
        {
            this.setVisible(false);
            new firstFrame().setVisible(true);
        }
        if (e.getSource() == confirmButton1)
        {
            int ok = 0;
            String firstPass = password1.getText();
            String secondPass = password2.getText();
            if (firstPass.length() < 5 || secondPass.length() < 5) {
                JOptionPane.showMessageDialog(this, "The password must have at least 5 characters");
                ok = 1;
            }



            if (!firstPass.equalsIgnoreCase(secondPass) || ok == 1)
                JOptionPane.showMessageDialog(this, "Passwords not matching");



            if (firstPass.equalsIgnoreCase(secondPass) && ok == 0)
            {
                try {
                    String query = "INSERT INTO users(first_name,last_name,username,email,password) VALUES(?, ?, ?, ?, ?)";
                    String query2 = "Insert into profileinfo(username) Values(?)";
                    con = DriverManager.getConnection(url, user, password);
                    pst = con.prepareStatement(query);
                    pst2 = con.prepareStatement(query2);
                    pst.setString(1, firstName.getText());
                    pst.setString(2, lastName.getText());
                    pst.setString(3, username.getText());
                    pst.setString(4, email.getText());
                    pst.setString(5, password1.getText());
                    pst2.setString(1, username.getText());
                    pst.executeUpdate();
                    pst2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "REGISTER SUCCESSFULLY");
                    } catch (SQLException y)
                {
                    JOptionPane.showMessageDialog(null,"Utilizator deja inregistrat in baza noastra de date");
                    // System.out.println(x.getMessage());
                }
                this.setVisible(false);
                new firstFrame().setVisible(true);
            }


        }
    }







    public static void main(String[] a){

        registerInteface rFrame = new registerInteface();
    }
}
