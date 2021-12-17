import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminFrame extends JFrame implements ActionListener {
    private JTextField banUser;
    private int ok=0;
    private JPanel adminFrame;
    private JTextField reason;
    private JButton confirmButton;
    private JButton newQuestion;
    private JButton backButton;
    private JComboBox dropList;
    private JLabel categorieLabel;
    private JTextField t6;
    private JButton confirmq;
    private JTextField t7;
    private JTextField t8;
    private JTextField t9;
    private JTextField t5;
    private JCheckBox a;
    private JCheckBox b;
    private JCheckBox c;
    private JCheckBox d;
    private JLabel i;
    private JLabel v1;
    private JLabel v2;
    private JLabel v3;
    private JLabel v4;
    private JLabel rcorectLabel;
    private final String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    private final String user = "postgres";
    private final String password = "rel12345";
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2 = null;

    adminFrame(){
        this.setContentPane(adminFrame);
        this.setSize(500,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addActionEvent();
        setLayoutManager();
        dropList.setEditable(false);
        categorieLabel.setVisible(false);
        dropList.setVisible(false);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }
    public void setLayoutManager(){
        categorieLabel.setVisible(false);
        dropList.setVisible(false);
        t5.setVisible(false);
        t6.setVisible(false);
        t7.setVisible(false);
        t8.setVisible(false);
        t9.setVisible(false);
        i.setVisible(false);
        v1.setVisible(false);
        v2.setVisible(false);
        v3.setVisible(false);
        v4.setVisible(false);
        confirmq.setVisible(false);
        rcorectLabel.setVisible(false);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
    }

    public void addActionEvent(){

        backButton.addActionListener(this);
        newQuestion.addActionListener(this);
        confirmButton.addActionListener(this);
        confirmq.addActionListener(this);
        a.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);
        d.addActionListener(this);
    }




    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton)
        {

            this.setVisible(false);
            new firstFrame().setVisible(true);
        }
        if(e.getSource() == newQuestion){
            ok++;
            if(ok %2 != 0)
                setVisible();
            else setInvisible();
        }
        checkQuestion(e);


        if(e.getSource() == confirmq)
        {

            int answer =getCorrectAns();
            int type =  getDropSelected();

            try {
                String query = "INSERT INTO questions(question,ans_1,ans_2,ans_3,ans_4, correct_ans,q_type) VALUES(?, ?, ?, ?, ?,?,?)";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement(query);
                pst.setString(1, t5.getText());
                pst.setString(2, t6.getText());
                pst.setString(3, t7.getText());
                pst.setString(4, t8.getText());
                pst.setString(5, t9.getText());
                pst.setInt(6, answer);
                pst.setInt(7, type);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Question registration successfully");
            } catch (SQLException y)
            {
                //System.out.println("Intrebare deja existenta");
                 System.out.println(y.getMessage());
            }
            clearfields();
        }
        if(e.getSource() == confirmButton){
            if(banUser.getText().equals("") || reason.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Trebuie sa introduci informatii in ambele casete");
            else{
                int ok=0;
                try{
                    String query ="Delete from users where username=?";
                    con = DriverManager.getConnection(url,user,password);
                    pst = con.prepareStatement(query);
                    pst.setString(1,banUser.getText());
                    pst.executeQuery();
                    ok=1;
                }catch(Exception kk){
                   // JOptionPane.showMessageDialog(null, "Utilizatorul nu a fost gasit");
                }
                try{
                    String query = "Delete from profileinfo where username=?";
                    con = DriverManager.getConnection(url,user,password);
                    pst = con.prepareStatement(query);
                    pst.setString(1,banUser.getText());
                    pst.executeQuery();
                }catch(Exception xd){

                }

                    try {

                        String query = "INSERT INTO bannedusers(username,reason) Values(?,?)";
                        con = DriverManager.getConnection(url, user, password);
                        pst = con.prepareStatement(query);
                        pst.setString(1, banUser.getText());
                        pst.setString(2, reason.getText());
                        // pst.setInt(1,3);
                        pst.executeQuery();
                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(null, x);

                    }

            }
        }
    }


    public int getCorrectAns(){
        if(a.isSelected())
            return 1;
        if(b.isSelected())
            return 2;
        if(c.isSelected())
            return 3;
        if(d.isSelected())
            return 4;
        return 0;
    }

    public void setInvisible(){
        categorieLabel.setVisible(false);
        dropList.setVisible(false);
        t5.setVisible(false);
        t6.setVisible(false);
        t7.setVisible(false);
        t8.setVisible(false);
        t9.setVisible(false);
        i.setVisible(false);
        v1.setVisible(false);
        v2.setVisible(false);
        v3.setVisible(false);
        v4.setVisible(false);
        confirmq.setVisible(false);
        rcorectLabel.setVisible(false);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
    }
    public void setVisible(){
        banUser.setEnabled(false);
        reason.setEnabled(false);
        banUser.setEnabled(true);
        reason.setEnabled(true);
        categorieLabel.setVisible(true);
        dropList.setVisible(true);
        t5.setVisible(true);
        t6.setVisible(true);
        t7.setVisible(true);
        t8.setVisible(true);
        t9.setVisible(true);
        i.setVisible(true);
        v1.setVisible(true);
        v2.setVisible(true);
        v3.setVisible(true);
        v4.setVisible(true);
        confirmq.setVisible(true);
        rcorectLabel.setVisible(true);
        a.setVisible(true);
        b.setVisible(true);
        c.setVisible(true);
        d.setVisible(true);
    }

    public int getDropSelected(){
        return dropList.getSelectedIndex();
    }

    public void clearfields(){
        t5.setText(null);
        t6.setText(null);
        t7.setText(null);
        t8.setText(null);
        t9.setText(null);
        a.setSelected(false);
        b.setSelected(false);
        c.setSelected(false);
        d.setSelected(false);
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        //dropList.setSelectedIndex(0);
        ok++;

    }
    public void checkQuestion(ActionEvent e){
        if(e.getSource() == a){
            ok++;
            if(ok%2 == 0)
            {
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
            }
            else{
                b.setEnabled(true);
                c.setEnabled(true);
                d.setEnabled(true);
            }
        }
        if(e.getSource() == b){
            ok++;
            if(ok%2 == 0)
            {
                a.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
            }
            else{
                a.setEnabled(true);
                c.setEnabled(true);
                d.setEnabled(true);
            }
        }
        if(e.getSource() == c){
            ok++;
            if(ok%2 == 0)
            {
                a.setEnabled(false);
                b.setEnabled(false);
                d.setEnabled(false);
            }
            else{
                a.setEnabled(true);
                b.setEnabled(true);
                d.setEnabled(true);
            }
        }
        if(e.getSource() == d){
            ok++;
            if(ok%2 == 0)
            {
                a.setEnabled(false);
                c.setEnabled(false);
                b.setEnabled(false);
            }
            else{
                a.setEnabled(true);
                c.setEnabled(true);
                b.setEnabled(true);
            }
        }
    }

   /* public void focusGained(FocusEvent f){
        if(f.getSource() == banUser)
            banUser.setText("dadafsf");
        if(f.getSource() == reason)
            reason.setText("tgfas");
    }
    public void focusLost(FocusEvent f){
        if(f.getSource() == reason)
            reason.setText("tgfas");
    }*/

}
