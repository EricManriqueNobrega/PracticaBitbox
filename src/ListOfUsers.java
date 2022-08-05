import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListOfUsers extends JPanel {
    private JTable tableUsers;
    private JScrollPane jScrollPane;
    private UserClient userClient;
    private Item[][] data = {};
    private String[] columnNames = {"Name","Password"};
    ConnectionSql connectionSql = new ConnectionSql();
    Hibernate hibernate = new Hibernate();



    public ListOfUsers(){
        tableUsers = new JTable();
        tableUsers.setVisible(true);
        tableUsers.setBounds(100, 300, 379, 130);
        tableUsers = refreshTable(getUserList());

        jScrollPane = new JScrollPane(tableUsers);
        jScrollPane.setBounds(100, 300, 379, 130);
        jScrollPane.setVisible(true);
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    for(int i = 0; i<getUserList().size();i++) {
                        if (tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)
                                == getUserList().get(i).getNameUser()) {
                            setUser(getUserList().get(i));
                            return;
                        }
                    }
                }
            }
        });







        add(jScrollPane);
    }

    public JTable refreshTable(List<UserClient> userClientList){
        DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaItem = new JTable(dtm);
        UserClient[] userClient = getUserList().toArray(new UserClient[userClientList.size()]);
        for (UserClient client : userClient) {
            Object[] newRow = {client.getNameUser(), client.getPasswordUser()};
            dtm.addRow(newRow);
        }

        return tablaItem;

    }

    public UserClient getUser() {
        return userClient;
    }

    public void setUser(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<UserClient> getUserList() {
        return hibernate.showUserClientList();
    }


    public void deleteUser(){
        hibernate.deleteUser(getUser());
    }

    public void createUser(){
        String name = JOptionPane.showInputDialog("Enter a name: ");
        String password = JOptionPane.showInputDialog("Enter a password");
        UserClient userClient = new UserClient(name,password);
        hibernate.saveUser(userClient);
    }
    public void cargarDatos(List<UserClient> newList){
        remove(jScrollPane);
        tableUsers = refreshTable(newList);
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    for(int i = 0; i<getUserList().size();i++) {
                        if (tableUsers.getValueAt(tableUsers.getSelectedRow(), 0)
                                == getUserList().get(i).getNameUser()) {
                            setUser(getUserList().get(i));
                            return;
                        }
                    }
                }
            }
        });
        jScrollPane = new JScrollPane(tableUsers);
        add(jScrollPane);
    }
}
