import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    //<editor-fold desc="Date conectare baza de date   (FOLDED)">
    /**
   * String url = "jdbc:postgresql://localhost:5432/registerDatabase";
    *String user = "postgres";
     *String password = "rel12345";
     */
    //</editor-fold>
    Connection con = null;
    PreparedStatement pst =null;
    PreparedStatement pst2= null;
    ResultSet rs = null;




    public static void main(String[] a) {

        String url = "jdbc:postgresql://localhost:5432/registerDatabase";
        String user = "postgres";
        String password = "rel12345";
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        String[] que;
        int ind=0;

        try {
            String query = "Update users SET loggedin=false WHERE loggedin=true";
            con = DriverManager.getConnection(url, user, password);
            pst = con.prepareStatement(query);
            pst.executeQuery();
        } catch (Exception x) {
            System.out.println(x);
        }

        firstFrame frame1 = new firstFrame();
        //new adminFrame();
     //   new MyProfile();




    }
    }



