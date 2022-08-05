import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministratorMenu extends JFrame implements ActionListener {
    private final JMenuBar adminMenu;
    private final JMenu createUser;
    private final JMenuItem cu;
    private final JMenu deleteUser;
    private final JMenuItem du;
    private final JMenu deleteItem;
    private final JMenuItem di;
    private final JMenu session;
    private final JMenuItem signoff;
    private final JTabbedPane administratorMenu;
    private final ListOfUsers listOfUsers;
    private final DeleteItems itemDelete;
    ConnectionSql connectionSql = new ConnectionSql();
    Hibernate hibernate = new Hibernate();

    public AdministratorMenu(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("Menu of Administrators");

        adminMenu = new JMenuBar();
        createUser = new JMenu("Create User");
        cu = new JMenuItem("Create");
        deleteUser = new JMenu("Delete User");
        du = new JMenuItem("Delete");
        deleteItem = new JMenu("Delete Item");
        di = new JMenuItem("Delete");
        session = new JMenu("Session");
        signoff = new JMenuItem("Sign off");
        createUser.add(cu);
        deleteUser.add(du);
        deleteItem.add(di);
        session.add(signoff);
        cu.addActionListener(this);
        du.addActionListener(this);
        di.addActionListener(this);
        signoff.addActionListener(this);
        adminMenu.add(createUser);
        adminMenu.add(deleteUser);
        adminMenu.add(deleteItem);
        adminMenu.add(session);
        setJMenuBar(adminMenu);
        administratorMenu = new JTabbedPane();
        listOfUsers = new ListOfUsers();
        itemDelete = new DeleteItems();

        administratorMenu.addTab("Users List", listOfUsers);
        administratorMenu.addTab("Item Delete", itemDelete);

        getContentPane().add(administratorMenu);

    }

    public List<Item> getItemList() {
        return hibernate.showItemsList();
    }
    public List<UserClient> getUserList(){return hibernate.showUserClientList();}


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cu){
            listOfUsers.createUser();
            listOfUsers.cargarDatos(getUserList());
        }
        if(e.getSource() == du){
            listOfUsers.deleteUser();
            listOfUsers.cargarDatos(getUserList());
        }
        if(e.getSource() == di){
            itemDelete.deleteItem();
            itemDelete.cargarDatos(getItemList());
        }
        if(e.getSource() == signoff){

            if(JOptionPane.showConfirmDialog(null,"Are you sure?","WARNING",JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION ){

                LoginFrame login = new LoginFrame();
                login.setVisible(true);
                login.setBounds(10, 10, 370, 600);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setResizable(false);
                setVisible(false);
            }
        }
    }

}
