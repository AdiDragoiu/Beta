import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class quizzSelector extends JFrame implements ActionListener {
    private JButton toateButton;
    private JButton muzicaButton;
    private JButton biologieButton;
    private JButton geografieButton;
    private JButton istorieButton;
    private JButton artaButton;
    private JButton inapoiButton;
    private JPanel frame;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    quizzSelector(){
        this.setContentPane(frame);
        this.setResizable(false);
        this.setSize(500,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addActionEvent();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
    public void addActionEvent(){
        inapoiButton.addActionListener(this);
        toateButton.addActionListener(this);
        muzicaButton.addActionListener(this);
        biologieButton.addActionListener(this);
        geografieButton.addActionListener(this);
        istorieButton.addActionListener(this);
        inapoiButton.addActionListener(this);
        artaButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == inapoiButton)
        {
            this.setVisible(false);
            new loginSuccesfull();
        }
        if(e.getSource() == muzicaButton){ database(1) ; this.setVisible(false); new quizzStart("muzica");}
        if(e.getSource() ==toateButton){ database(0);this.setVisible(false); new quizzStart("toate");}
        if(e.getSource() == biologieButton){ database(5); this.setVisible(false); new quizzStart("biologie");}
        if(e.getSource() ==geografieButton){ database(3) ; this.setVisible(false); new quizzStart("geografie");}
        if(e.getSource()==istorieButton){ database(4); this.setVisible(false);new quizzStart("istorie");}
        if(e.getSource()==artaButton){ database(2); this.setVisible(false);new quizzStart("arta");}



    }
    public void database(int q){
        try {
            String query = "Update users Set chosenq_type = ? where loggedin= 'true'";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.setInt(1, q);
            pst.executeUpdate();
        } catch (SQLException y)
        {
            System.out.println(y.getMessage());
        }
    }
}
