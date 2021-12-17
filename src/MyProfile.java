import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MyProfile extends JFrame implements ActionListener {
    private JPanel myProfileFrame;
    private JLabel porfilepicture;
    private JLabel first_name;
    private JLabel last_name;
    private JLabel username;
    private JLabel email;
    private JButton total_q;
    private JButton BACKButton;
    private JLabel prfPic;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    ResultSet rs = null;

    MyProfile(){
        this.setTitle("QuizzApp");
        this.setContentPane(myProfileFrame);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        infoJDBC();
        actionEvent();
    }
    public void actionEvent(){
        BACKButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == BACKButton){
            this.setVisible(false);
            new loginSuccesfull();
        }
    }


    public void infoJDBC(){
        try {

            String query = "SELECT * from users FULL OUTER JOIN profileinfo ON users.username = profileinfo.username WHERE loggedin= true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                first_name.setText(rs.getString("first_name"));
                last_name.setText( rs.getString("last_name")) ;
                username.setText( rs.getString("username"));
                total_q.setText(rs.getString("totalq"));
                email.setText(rs.getString("email"));
            }
        } catch (SQLException y)
        {
             System.out.println(y.getMessage());
        }
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        porfilepicture= new JLabel(new ImageIcon("iconpic.png"));
    }

}
