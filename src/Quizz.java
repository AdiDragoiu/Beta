import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Quizz extends JFrame implements ActionListener {
    List <String> intrebari = new ArrayList<String>();

    List<Integer> raspunsuri = new ArrayList<Integer>();
    String[][] variante= {
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
            {"","","",""},
    };
    int index = 0;
    char answer;
    int correct_guesses = 0;
    private JPanel quizzFrame;
    private JButton dButton;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JLabel aLabel;
    private JLabel bLabel;
    private JLabel cLabel;
    private JLabel dLabel;
    private JLabel quizzLabel;
    private JLabel quizzNumber;
    private JLabel resultLabel;
    private JButton backToMenu;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2= null;
    ResultSet rs = null;
    int q=0;

    Quizz(){
        while( q < 10 ){
            JDBC();
            q++;
        }
        this.setContentPane(quizzFrame);
        resultLabel.setVisible(false);
        this.setSize(800,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        backToMenu.setVisible(false);
        quizzLabel.setMaximumSize(new Dimension(50,50));
        actionEvent();
        this.setLocationRelativeTo(null);
        nextQuestion();


    }
    public void actionEvent(){
        aButton.addActionListener(this);
        bButton.addActionListener(this);
        cButton.addActionListener(this);
        dButton.addActionListener(this);
        backToMenu.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        if(e.getSource() == backToMenu)
        {
            this.setVisible(false);
            new loginSuccesfull().setVisible(true);
        }
        if(e.getSource() ==aButton)
        {

            if( raspunsuri.get(index) == 1) {
                quizzFrame.setBackground(Color.green);
                correct_guesses++;
            }

        }
        if(e.getSource() ==bButton)
        {
            if( raspunsuri.get(index) == 2) {
                quizzFrame.setBackground(Color.green);
                correct_guesses++;
            }

        }
        if(e.getSource() ==cButton)
        {

            if( raspunsuri.get(index) == 3){
                quizzFrame.setBackground(Color.green);
                correct_guesses++;
            }
        }
        if(e.getSource() ==dButton)
        {

            if( raspunsuri.get(index) == 4){
                correct_guesses++;
                quizzFrame.setBackground(Color.green);

        }}
        displayAnswer();
    }
    public void displayAnswer(){
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);

        if( raspunsuri.get(index) != 1)
            aLabel.setForeground(new Color(255,0,0));
        if( raspunsuri.get(index) != 2)
            bLabel.setForeground(new Color(255,0,0));
        if( raspunsuri.get(index) != 3)
            cLabel.setForeground(new Color(255,0,0));
        if( raspunsuri.get(index) != 4)
            dLabel.setForeground(new Color(255,0,0));
        Timer pause = new Timer(2000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                aLabel.setForeground(new Color(0x030303));
                bLabel.setForeground(new Color(0x030303));
                cLabel.setForeground(new Color(0x030303));
                dLabel.setForeground(new Color(0x030303));
                aButton.setEnabled(true);
                bButton.setEnabled(true);
                cButton.setEnabled(true);
                dButton.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void nextQuestion(){
        quizzFrame.setBackground(new Color(148, 148, 148));
        if(index == 10){
            result();
            System.out.println("asfsa");
        }
        else {
            quizzNumber.setText("Quizz number " + (index + 1));
            quizzLabel.setText(intrebari.get(index));
            aLabel.setText(variante[index][0]);
            bLabel.setText(variante[index][1]);
            cLabel.setText(variante[index][2]);
            dLabel.setText(variante[index][3]);
        }
    }
    public void result(){
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        quizzNumber.setVisible(false);
        backToMenu.setVisible(true);
       // question1.setAlignmentX(0);
       // question1.setAlignmentY(50);
        quizzLabel.setText("                                                                                 RESULT!");
        aLabel.setText(" ");
        bLabel.setText(" ");
        cLabel.setText(" ");
        dLabel.setText(" ");
        resultLabel.setVisible(true);
        resultLabel.setText("    Ati raspuns corect la "+correct_guesses + " intrebari!");
        profileUpdate();

    }
    int i = 0;
    public void profileUpdate(){

    }
    public void JDBC(){
        int categorie =0  ;
        try{
            String query = "Select chosenq_type from users where loggedin= true ";
            con = DriverManager.getConnection(url,user,password);
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                categorie = rs.getInt("chosenq_type");
            }
        }catch(Exception z){
            JOptionPane.showMessageDialog(null,z);
        }
        if(categorie == 0){
            try {

                String query = "Select question_id,question,ans_1,ans_2,ans_3,ans_4,correct_ans from questions order by random() LIMIT 1";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int questionId = rs.getInt("question_id");
                    variante[i][0] = rs.getString("ans_1");
                    variante[i][1] = rs.getString("ans_2");
                    variante[i][2] = rs.getString("ans_3");
                    variante[i][3] = rs.getString("ans_4");
                    raspunsuri.add(rs.getInt("correct_ans"));
                    i++;


                    intrebari.add(rs.getString("question"));
                }

            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, x);

            }
        }
        else {
            try {

                String query = "Select question_id,question,ans_1,ans_2,ans_3,ans_4,correct_ans from questions where q_type = ? and currently_used=0 order by random() LIMIT 1";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                pst.setInt(1, categorie);
                // pst.setInt(1,3);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int questionId = rs.getInt("question_id");
                    variante[i][0] = rs.getString("ans_1");
                    variante[i][1] = rs.getString("ans_2");
                    variante[i][2] = rs.getString("ans_3");
                    variante[i][3] = rs.getString("ans_4");
                    raspunsuri.add(rs.getInt("correct_ans"));
                    i++;
                    intrebari.add(rs.getString("question"));

                    pst2 = con.prepareStatement("UPDATE questions SET currently_used='1' where question_id=?");
                    pst2.setInt(1,questionId);
                    pst2.executeUpdate();
                }

            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, x);

            }
        }
    }
    public void printString(String s ){
        System.out.println(s);
    }

}
