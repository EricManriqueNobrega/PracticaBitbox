import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;


public class UserMenu extends JFrame implements ActionListener {
    private final JTabbedPane menu;
    private final JMenu createItem;
    private final JMenuItem ci;
    private final JMenu saveItem;
    private final JMenuItem si;
    private final JMenuItem s1;
    private final JMenu editItem;
    private final JMenuItem ei;
    private final JMenu deactivateItem;
    private final JMenuItem di;
    private final JMenu sortItem;
    private final JMenuItem soi;
    private final JMenuItem soi1;
    private final JMenuItem soi2;
    private final JMenu session;
    private final JMenuItem signoff;
    private ListOfItem listOfItem;
    private final ItemDetails itemDetails;
    ConnectionSql connectionSql = new ConnectionSql();
    Hibernate hibernate = new Hibernate();

    public UserMenu(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("Menu of Items");

        JMenuBar jMenu = new JMenuBar();
        createItem = new JMenu("Create Item");
        editItem = new JMenu("Edit Item");
        saveItem = new JMenu("Save Item");
        deactivateItem = new JMenu("Deactivate Item");
        sortItem = new JMenu("Sort Items by state");
        ci = new JMenuItem("Create Item");
        createItem.add(ci);
        ci.addActionListener(this);
        ei = new JMenuItem("Edit Item");
        editItem.add(ei);
        ei.addActionListener(this);
        si = new JMenuItem("Save Edited Item");
        saveItem.add(si);
        si.addActionListener(this);
        s1 = new JMenuItem("Save New Item");
        saveItem.add(s1);
        s1.addActionListener(this);
        di = new JMenuItem("Deactivate Item");
        deactivateItem.add(di);
        di.addActionListener(this);
        soi = new JMenuItem("Active");
        soi1 = new JMenuItem("Discontinued");
        soi2 = new JMenuItem("Show all the items");
        sortItem.add(soi);
        sortItem.add(soi1);
        sortItem.add(soi2);
        soi.addActionListener(this);
        soi1.addActionListener(this);
        soi2.addActionListener(this);
        session = new JMenu("Session");
        signoff = new JMenuItem("Sign off");
        session.add(signoff);
        signoff.addActionListener(this);

        jMenu.add(createItem);
        jMenu.add(editItem);
        jMenu.add(saveItem);
        jMenu.add(deactivateItem);
        jMenu.add(sortItem);
        jMenu.add(session);
        setJMenuBar(jMenu);
        menu = new JTabbedPane();
        menu.setBounds(0,0,getWidth(),getHeight());
        listOfItem = new ListOfItem(getItemList());
        itemDetails = new ItemDetails();


        menu.addTab("Items List", listOfItem);
        menu.addTab("Item Details", itemDetails);

        getContentPane().add(menu);


    }

    public List<Item> getItemList() {
        return hibernate.showItemsList();
    }



    public void createItem(){
        itemDetails.clean();
        menu.setSelectedIndex(1);
    }

    public void editItem(){

        if(listOfItem.getItem().getStateId() == 1){
            menu.setSelectedIndex(1);
            itemDetails.cargarDatos(listOfItem.getItem());
        }else{
            JOptionPane.showMessageDialog(null,
                    "Is not possible edit this item because his state is discontinued");
        }


    }

    public void deactivateItem(){
        listOfItem.deactivateItem();
        listOfItem.cargarDatos(getItemList());

    }



    public void saveEdited() throws ParseException {
        if(itemDetails.saveItemEdited() != null){
            hibernate.editItem(listOfItem.getItem());
            JOptionPane.showMessageDialog(null, "Edited item save correctly");
            menu.setSelectedIndex(0);
            itemDetails.clean();
            listOfItem.cargarDatos(getItemList());
        }

    }

    public void saveNew() throws ParseException {

            itemDetails.saveItemNew();
            menu.setSelectedIndex(0);
            itemDetails.clean();
            listOfItem.cargarDatos(getItemList()); //ConnectionSql.showItemsList()

    }

    public void sortItemActive(){
        listOfItem.sortByActive();

    }
    public void sortItemDiscontinued(){
        listOfItem.sortByDiscontinued();

    }

    public void sortItemAll(){
        listOfItem.sortByAll();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ci) {
            createItem();
        }
        if (e.getSource()==si) {
            try {
                saveEdited();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == s1){
            try {
                saveNew();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource()==ei) {
            editItem();
        }
        if(e.getSource() == di){
            deactivateItem();
            String reason = JOptionPane.showInputDialog("Reason of deactivate the item");
        }
        if(e.getSource() == soi){
            sortItemActive();
        }
        if(e.getSource() == soi1){
            sortItemDiscontinued();
        }
        if(e.getSource() == soi2){
            sortItemAll();
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
