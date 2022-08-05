import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ListOfItem extends JPanel {

    private JTable tablaItems;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm;
    private Item item;
    private Item[][] data = {};
    private String[] columnNames = {"IdItemCode","Descripcion","State","Price","Creation Date","Creator"};
    private List<Item> itemList = new ArrayList<>();
    ConnectionSql connectionSql = new ConnectionSql();
    Hibernate hibernate = new Hibernate();


    public ListOfItem(List<Item> itemList){
        setItemList(itemList);
        tablaItems = new JTable();
        tablaItems.setVisible(true);
        tablaItems.setBounds(100, 300, 379, 130);
        tablaItems = refreshTable(getItemList());

        jScrollPane = new JScrollPane(tablaItems);
        jScrollPane.setBounds(100, 300, 379, 130);
        jScrollPane.setVisible(true);

        tablaItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    for(int i = 0; i<getItemList().size();i++){
                        if((int)tablaItems.getValueAt(tablaItems.getSelectedRow(),0)
                        == getItemList().get(i).getIdItem()){
                            setItem(hibernate.showItem(getItemList().get(i).getIdItem()));
                            return;
                        }
                    }

                }
            }
        });



        add(jScrollPane);

    }

    public JTable refreshTable(List<Item> itemList){
        dtm = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        JTable tablaItem = new JTable(dtm);
        Item[] items = itemList.toArray(new Item[itemList.size()]);
        for (Item value : items) {
            Object[] newRow = {value.getIdItem(), value.getDescription(),
                    State.getFromId(value.getStateId()), value.getPrice(), value.getCreationDate(),
                    hibernate.findUserbyName( value.getCreatorName())};
            dtm.addRow(newRow);
        }

        return tablaItem;

    }

    public void cargarDatos(List<Item> newList){
        remove(jScrollPane);
        tablaItems = refreshTable(newList);
        tablaItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    for(int i = 0; i<getItemList().size();i++){
                        if((int)tablaItems.getValueAt(tablaItems.getSelectedRow(),0)
                                == getItemList().get(i).getIdItem()){
                            setItem(hibernate.showItem(getItemList().get(i).getIdItem()));
                            return;
                        }
                    }

                }
            }
        });
        jScrollPane = new JScrollPane(tablaItems);
        add(jScrollPane);
    }


    public void sortByActive(){
        List<Item> newList = hibernate.showItemsActive();
        dtm = new DefaultTableModel(data, columnNames);
        JTable tablaItem = new JTable(dtm);
        Item[] items = newList.toArray(new Item[newList.size()]);
        for (Item value : items) {
            Object[] newRow = {value.getIdItem(), value.getDescription(),
                    State.getFromId(value.getStateId()), value.getPrice(), value.getCreationDate(),
                    hibernate.findUserbyName( value.getCreatorName())};
            dtm.addRow(newRow);
        }

        remove(jScrollPane);
        tablaItems = tablaItem;
        jScrollPane = new JScrollPane(tablaItems);
        add(jScrollPane);
    }

    public void sortByDiscontinued(){
        List<Item> newList = hibernate.showItemsDiscontinued();
        dtm = new DefaultTableModel(data, columnNames);
        JTable tablaItem = new JTable(dtm);
        Item[] items = newList.toArray(new Item[newList.size()]);
        for (Item value : items){
            Object[] newRow = {value.getIdItem(), value.getDescription(),
                    State.getFromId(value.getStateId()), value.getPrice(), value.getCreationDate(),
                    hibernate.findUserbyName( value.getCreatorName())};
            dtm.addRow(newRow);
        }

        remove(jScrollPane);
        tablaItems = tablaItem;
        jScrollPane = new JScrollPane(tablaItems);
        add(jScrollPane);
    }

    public void sortByAll(){
        remove(jScrollPane);
        tablaItems = refreshTable(getItemList());
        jScrollPane = new JScrollPane(tablaItems);
        add(jScrollPane);
    }


    public Item getItem(){
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItemList() {
        return hibernate.showItemsList();
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void deactivateItem(){
        hibernate.deactivateItem(getItem());
    }


}
