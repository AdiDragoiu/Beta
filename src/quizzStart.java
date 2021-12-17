import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class quizzStart extends JFrame implements ActionListener {

    private JLabel label;
    private JPanel frame;
    private JLabel label2;
    private JButton STARTButton;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    quizzStart(String s){
        this.setTitle(s);
        this.setContentPane(frame);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700,500);
        label.setText("Acesta este un quizz de 10 intrebari cu raspuns unic din categoria " +s );
        label2.setText("Succes!!!");
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addActionEvent();
        resetCurrently_used();
    }
    public void addActionEvent(){
        STARTButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == STARTButton)
        {
            this.setVisible(false);
            new Quizz().setVisible(true);
        }
    }
    public void resetCurrently_used() {
        try {
            String query = "UPDATE questions SET currently_used = '0'";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.executeUpdate();
            } catch (Exception e) {
                     System.out.println(e);
                     }
    }




}
