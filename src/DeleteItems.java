import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DeleteItems extends JPanel {

    private JTable tableItem;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm;
    private Item item;
    private Item[][] data = {};
    private String[] columnNames = {"IdItemCode","Descripcion","State","Price","Creation Date","Creator"};
    ConnectionSql connectionSql = new ConnectionSql();
    Hibernate hibernate = new Hibernate();


    public DeleteItems(){
        tableItem = new JTable();
        tableItem.setVisible(true);
        tableItem.setBounds(100, 300, 379, 130);
        tableItem = refreshTable(getItemList());

        jScrollPane = new JScrollPane(tableItem);
        jScrollPane.setBounds(100, 300, 379, 130);
        jScrollPane.setVisible(true);


        tableItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    for(int i = 0; i<getItemList().size();i++){
                        if((int)tableItem.getValueAt(tableItem.getSelectedRow(),0)
                                == getItemList().get(i).getIdItem()){
                            setItem(getItemList().get(i));
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
        Item[] items = getItemList().toArray(new Item[itemList.size()]);
        for (Item value : items) {
            Object[] newRow = {value.getIdItem(), value.getDescription(),
                    State.getFromId(value.getStateId()), value.getPrice(), value.getCreationDate(),
                    hibernate.findUserbyName( value.getCreatorName())};
            dtm.addRow(newRow);
        }

        return tablaItem;

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItemList() {
        return hibernate.showItemsList();
    }


    public void deleteItem(){
        hibernate.deletePriceReductionsList(getItem().getIdItem());
        hibernate.deleteItem(getItem());
    }

    public void cargarDatos(List<Item> newList){
        remove(jScrollPane);
        tableItem = refreshTable(newList);
        tableItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    for(int i = 0; i<getItemList().size();i++){
                        if((int)tableItem.getValueAt(tableItem.getSelectedRow(),0)
                                == getItemList().get(i).getIdItem()){
                            setItem(getItemList().get(i));
                            return;
                        }
                    }
                }
            }
        });
        jScrollPane = new JScrollPane(tableItem);
        add(jScrollPane);
    }

}
