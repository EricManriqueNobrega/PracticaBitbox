import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemDetails extends JPanel {


    JLabel idLabel=new JLabel("ItemCode: ");
    JLabel descriptionLabel=new JLabel("Description: ");
    JLabel priceLabel=new JLabel("Price");
    JLabel stateLabel=new JLabel("State");
    JLabel listSuppliersLabel=new JLabel("Suppliers List: ");
    JLabel priceReductionslabel=new JLabel("Price Reductions: ");
    JLabel dateLabel=new JLabel("Creation Date: ");
    JLabel userLabel=new JLabel("Creator: ");
    JTextField idItemField =new JTextField(10);
    JTextArea descriptionField=new JTextArea(5,25);
    JTextField priceField = new JTextField(10);
    JComboBox cbxState = new JComboBox();
    JList<Supplier> jListSupplier = new JList<>();
    JComboBox<PriceReductions> cbxPriceReductions = new JComboBox<>();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    JFormattedTextField dateCreation = new JFormattedTextField(dateFormat);
    JTextField userField = new JTextField(10);
    JButton supplierButton=new JButton("Add Supplier");
    JButton priceReductionsButton=new JButton("Add Price Reductions");
    ConnectionSql connectionSql = new ConnectionSql();
    Hibernate hibernate = new Hibernate();




    public ItemDetails (){
        cbxState.addItem("Active");
        cbxState.addItem("Discontinued");
        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Write a name");
                String country = JOptionPane.showInputDialog("Write a country");
                Supplier supplier = new Supplier(name,country,Integer.parseInt(idItemField.getText()));
                addSupplier(supplier);
            }
        });

        priceReductionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter a Price Reductions (eg 18.23)"));
                String start = JOptionPane.showInputDialog("Enter a Start Date (eg 1999-09-09)");
                String end = JOptionPane.showInputDialog("Enter a End Date (eg 1999-09-09)");
                try {
                    Date startDate = dateFormat.parse(start);
                    Date endDate = dateFormat.parse(end);
                    PriceReductions priceReductions = new PriceReductions(price, startDate,endDate,Integer.parseInt(idItemField.getText()));
                    List<PriceReductions> prc = hibernate.showPriceReductions();
                    for(int i = 0; i < prc.size();i++){
                        if(prc.get(i).getReducedPrice() == price){
                            JOptionPane.showMessageDialog(null,"Is not possible include the price reduction. It's already associate");
                            return;
                        }
                    }
                    hibernate.savePriceReductions(priceReductions);
                    addPriceReductions(priceReductions);
                    JOptionPane.showMessageDialog(null,"The price reductions is add with success");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });

        dateCreation.setColumns(10);
        idItemField.setBounds(150,125,100,30);
        idLabel.setBounds(50,125,100,25);
        descriptionField.setBounds(150,150,100,30);
        descriptionLabel.setBounds(50,150,100,30);
        cbxState.setBounds(150,175, 100, 30);
        stateLabel.setBounds(50,175,100,30);
        priceField.setBounds(150,200,100,30);
        priceLabel.setBounds(50,200,100,30);
        jListSupplier.setBounds(150, 225, 100,30);
        listSuppliersLabel.setBounds(50,225,100,30);
        cbxPriceReductions.setBounds(150,250,100,30);
        priceReductionslabel.setBounds(50,250,100,30);
        dateCreation.setBounds(150,275,100,30);
        dateLabel.setBounds(50,275,100,30);
        userLabel.setBounds(50,300,100,30);
        userField.setBounds(150,300,100,30);
        add(idLabel);
        add(idItemField);
        add(descriptionLabel);
        add(descriptionField);
        add(priceLabel);
        add(priceField);
        add(stateLabel);
        add(cbxState);
        add(listSuppliersLabel);
        add(jListSupplier);
        add(priceReductionslabel);
        add(cbxPriceReductions);
        add(dateLabel);
        add(dateCreation);
        add(userLabel);
        add(userField);
        add(supplierButton);
        add(priceReductionsButton);
    }

    public void cargarDatos(Item item){

        idItemField.setText(Integer.toString(item.getIdItem()));
        idItemField.setEditable(false);
        descriptionField.setText(item.getDescription());
        priceField.setText(String.valueOf(item.getPrice()));
        if(item.getStateId() == 2) cbxState.setSelectedItem(1);
        if(item.getStateId() == 1) cbxState.setSelectedItem(0);
        jListSupplier.setListData(changeListToArray(getListSupliers(item.getIdItem())));
        cbxPriceReductions.removeAllItems();
        if(getListPriceReductions(item.getIdItem()) != null) {
            for (int i = 0; i < getListPriceReductions(item.getIdItem()).size(); i++) {
                cbxPriceReductions.addItem(getListPriceReductions(item.getIdItem()).get(i));
            }
            cbxPriceReductions.setSelectedItem(hibernate.findPriceReductions(item.getIdItem(), item.getReducedPrice()));
        }
        dateCreation.setText(dateFormat.format(item.getCreationDate()));
        userField.setText("");
        if(item.getCreatorName() != null)userField.setText(item.getCreatorName());
        supplierButton.setVisible(true);
        priceReductionsButton.setVisible(true);
        jListSupplier.setVisible(true);
        listSuppliersLabel.setVisible(true);
        priceReductionslabel.setVisible(true);
        cbxPriceReductions.setVisible(true);
    }

    private List<PriceReductions> getListPriceReductions(int id) {
        return hibernate.showPriceReductionsList(id);
    }


    public void clean(){
        idItemField.setText("");
        idItemField.setEditable(true);
        descriptionField.setText("");
        priceField.setText("");
        cbxState.setSelectedIndex(0);
        Supplier[] supplier = new Supplier[0];
        jListSupplier.setListData(supplier);
        cbxPriceReductions.removeAllItems();
        dateCreation.setText("");
        userField.setText("");
        supplierButton.setVisible(false);
        priceReductionsButton.setVisible(false);
        jListSupplier.setVisible(false);
        listSuppliersLabel.setVisible(false);
        priceReductionslabel.setVisible(false);
        cbxPriceReductions.setVisible(false);
    }

    public List<Supplier> getListSupliers(int id) {
        return hibernate.showSupplierList(id);
    }


    public Item saveItemEdited() {
        String s = "";
        if(!idItemField.getText().equals(s) && !descriptionField.getText().equals(s)) {

            int id = Integer.parseInt(idItemField.getText());
            String description = descriptionField.getText();
            double price = 0;
            if(!priceField.getText().equals(s)){
                price = Double.parseDouble(priceField.getText());
            }
            State state = null;
            if (cbxState.getSelectedItem().toString().equals("Discontinued")) state = State.DISCONTINUED;
            if (cbxState.getSelectedItem().toString().equals("Active")) state = State.ACTIVE;
            List<Supplier> listSupplier = getListSupliers(id);
            PriceReductions priceReductions = (PriceReductions) cbxPriceReductions.getSelectedItem();
            if(priceReductions == null) priceReductions = hibernate.findPriceReductions(1,0.0);            Date creationDate = new Date();
            String userName = userField.getText();
            UserClient userClient = new UserClient(userName);


            Item item = new Item(id, description, price, state, listSupplier,  creationDate, priceReductions, userClient);
            item.setStateId(state.getId());
            item.setReducedPrice(priceReductions.getReducedPrice());
            item.setCreatorName(userName);
            return item;
        }
        return null;
    }

    public void saveItemNew() {
        String s = "";
        if(!idItemField.getText().equals(s) && !descriptionField.getText().equals(s)) {

            int id = Integer.parseInt(idItemField.getText());
            if(connectionSql.findItem(id)){
                JOptionPane.showMessageDialog(null,"Fields Item code and " +
                        "descripcion are incomplete or item code is invalid because other item have" +
                        " the same item code");
                return;
            }
            String description = descriptionField.getText();
            double price = 0;
            if(!priceField.getText().equals(s)){
                price = Double.parseDouble(priceField.getText());
            }
            State state = State.ACTIVE;
            if (cbxState.getSelectedItem().toString().equals("Discontinued")) state = State.DISCONTINUED;
            List<Supplier> listSupplier = new ArrayList<>();
            PriceReductions priceReductions = (PriceReductions) cbxPriceReductions.getSelectedItem();
            if(priceReductions == null) priceReductions = hibernate.findPriceReductions(1,0.0);
            Date creationDate = new Date();
            String userName = userField.getText();
            UserClient userClient = new UserClient(userName);


            Item item = new Item(id, description, price, state, listSupplier,  creationDate, priceReductions, userClient);
            item.setStateId(state.getId());
            item.setReducedPrice(priceReductions.getReducedPrice());
            item.setCreatorName(userName);
            hibernate.saveItem(item);
            JOptionPane.showMessageDialog(null, "New item save correctly");
            return;
        }
        JOptionPane.showMessageDialog(null,"Fields Item code and " +
                "descripcion are incomplete or item code is invalid because other item have" +
                " the same item code");

    }

    public void addSupplier(Supplier supplier){
        List<Supplier> supp = hibernate.showSuppliers();
        for(int i = 0; i < supp.size();i++){
            if(supp.get(i).getNameSupplier().equals(supplier.getNameSupplier())){
                JOptionPane.showMessageDialog(null,"Is not possible include the suplier. It's already associate");
                return;
            }
        }

        hibernate.saveSupplier(supplier);
        jListSupplier.setListData(changeListToArray(getListSupliers(Integer.parseInt(idItemField.getText()))));
        JOptionPane.showMessageDialog(null, "The supplier is add with success");
    }

    public void addPriceReductions(PriceReductions price){
        cbxPriceReductions.addItem(price);
    }


    public Supplier[] changeListToArray(List<Supplier> listSuppliers){
        Supplier[] list = new Supplier[listSuppliers.size()];
        for(int i = 0; i<list.length;i++){
            list[i] = listSuppliers.get(i);
        }
        return list;
    }

}
