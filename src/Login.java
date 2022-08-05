import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Login {

    public static void main(String[] a) {

        /*ConnectionSql conn = new ConnectionSql();
        conn.probar(1,"2022-08-02","2022-08-03",1);*/

        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);





    }
}
