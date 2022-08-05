import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.List;

public class LoginFrame extends JFrame{


    Container container=getContentPane();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JTextField passwordField=new JTextField();
    JButton newUserButton = new JButton("New User");
    JButton loginButton =new JButton("LOGIN");
    Hibernate hibernate = new Hibernate();



    public LoginFrame()
    {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        loginButton.addActionListener(new LoginAction());
        newUserButton.addActionListener(new NewUserAction());
        addComponentsToContainer();

    }
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(50,150,100,30);
        passwordLabel.setBounds(50,220,100,30);
        userTextField.setBounds(150,150,150,30);
        passwordField.setBounds(150,220,150,30);
        loginButton.setBounds(50,300,100,30);
        newUserButton.setBounds(200,300,100,30);


    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(newUserButton);
        container.add(loginButton);
    }


    public class LoginAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

                String name = userTextField.getText();
                String password = passwordField.getText();

                if(hibernate.findAdmin(name,password)){
                    AdministratorMenu adminMenu = new AdministratorMenu();
                    adminMenu.setVisible(true);
                    setVisible(false);
                    return;
                }


                if(hibernate.findUserClient(name,password)){
                    UserMenu userMenu = new UserMenu();
                    userMenu.setVisible(true);
                    setVisible(false);
                    return;
                }

                JOptionPane.showMessageDialog(null, "User name or password are incorrect.");
                userTextField.setText("");
                passwordField.setText("");

        }
    }


    public class NewUserAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            userTextField.setText("");
            passwordField.setText("");
            String name = JOptionPane.showInputDialog("Enter your User name");
            String password = JOptionPane.showInputDialog("Enter your User password");
            UserClient userClient = new UserClient(name,password);
            if(hibernate.findUserClient(name,password)){
                JOptionPane.showMessageDialog(null,"this user is already exists");
                return;
            }

            hibernate.saveUser(userClient);
            JOptionPane.showMessageDialog(null,"user is register success");



        }
    }



}
