import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionSql {

    Connection cn;
    Statement st;
    ResultSet rs;
    Connection conn;
    Statement sta;
    ResultSet res;

    public ConnectionSql() {
        try {
            Class.forName("org.h2.Driver");
            cn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
            st = cn.createStatement();
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
            sta = conn.createStatement();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


    public boolean findUserClient(String name, String password){

        String hql = "SELECT * FROM USERCLIENT";
        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                if(rs.getString("nameuser").equals(name)
                        && rs.getString("passworduser").equals(password)){
                    return true;
                }
            }

            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean findUserClientName(String name){

        String hql = "SELECT * FROM USERCLIENT";
        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                if(rs.getString("nameuser").equals(name)){
                    return true;
                }
            }

            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public UserClient findUserbyName(String nameuser){
        String hql = "SELECT * FROM USERCLIENT";
        UserClient user;
        try {
            rs = st.executeQuery(hql);
            while(rs.next()){
                String name = rs.getString("nameuser");
                if(name.equals(nameuser)){
                    user = new UserClient(name,
                            rs.getString("passworduser"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findAdmin(String name, String password){
        String hql = "SELECT * FROM ADMINISTRATOR";
        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                if(rs.getString("nameuser").equals(name)
                        && rs.getString("passworduser").equals(password)){
                    return true;
                }
            }

            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean findItem(int idItem){
        String hql = "SELECT * FROM ITEM";
        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                if(rs.getInt("iditem") == idItem){
                    return true;
                }
            }

            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public Item showItem(int idItem){
        String hql = "SELECT * FROM ITEM";
        Item item;
        try{
            res = sta.executeQuery(hql);
            while(res.next()){
                if(res.getInt("iditem") == idItem){
                    double reduce =res.getDouble("pricereduced");
                    int id = res.getInt("iditem");
                    String user = res.getString("name_user");
                    item = new Item(res.getInt("iditem"),
                            res.getString("descriptionitem"),
                            res.getDouble("price"),
                            State.getFromId(res.getInt("stateitem")),
                            showSupplierList(),
                            findDateCreation(id),
                            findPriceReductions(reduce),
                            findUserbyName(user));
                    return item;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<UserClient> showUserClientList (){
        List<UserClient> list = new ArrayList<>();
        String hql = "SELECT * FROM USERCLIENT";

        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                UserClient user = new UserClient(rs.getString("nameuser"), rs.getString("passworduser"));
                list.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    public Date findDateCreation(int iditem){
        Date date = null;
        try {
            rs = st.executeQuery("SELECT DATECREATE FROM ITEM WHERE IDITEM=" + iditem);
            while(rs.next()){
                date = rs.getDate("datecreate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }

    public List<Item> showItemsList(){
        List<Item> itemList = new ArrayList<>();
        Item item;
        String hql = "SELECT * FROM ITEM";
        try{
            res = sta.executeQuery(hql);
            while(res.next()){
                double reduce =res.getDouble("pricereduced");
                int id = res.getInt("iditem");
                String user = res.getString("name_user");
                item = new Item(res.getInt("iditem"),
                                res.getString("descriptionitem"),
                                res.getDouble("price"),
                                State.getFromId(res.getInt("stateitem")),
                                showSupplierList(),
                                findDateCreation(id),
                                findPriceReductions(reduce),
                                findUserbyName(user));

                itemList.add(item);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return itemList;
    }

    public List<Supplier> showSupplierList(){
        List<Supplier> supplierList = new ArrayList<>();
        Supplier supplier;
        String hql = "SELECT * FROM SUPPLIER";

        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                supplier = new Supplier(rs.getString("namesupplier"), rs.getString("countrysupplier"));
                supplierList.add(supplier);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return supplierList;
    }

    public List<PriceReductions> showPriceReductionsList(){
        List<PriceReductions> priceReductionsList = new ArrayList<>();
        PriceReductions priceReductions;
        String hql = "SELECT * FROM PRICEREDUCTIONS";

        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                priceReductions = new PriceReductions(rs.getDouble("pricereduced"),
                        rs.getDate("startdate"),
                        rs.getDate("enddate"));
                priceReductionsList.add(priceReductions);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return priceReductionsList;
    }

    public PriceReductions findPriceReductions(double price){
        String hql = "SELECT * FROM PRICEREDUCTIONS";
        PriceReductions priceReductions;
        try {
            rs = st.executeQuery(hql);
            while(rs.next()){
                double reduce = rs.getDouble("pricereduced");
                if( reduce == price){
                    priceReductions = new PriceReductions(reduce,
                                        rs.getDate("startdate"),
                                        rs.getDate("enddate"));
                    return priceReductions;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> showItemsActive(){
        String hql = "SELECT * FROM ITEM";
        Item item;
        List<Item> itemList = new ArrayList<>();
        try{
            res = sta.executeQuery(hql);
            while(res.next()){
                double reduce =res.getDouble("pricereduced");
                int id = res.getInt("iditem");
                String user = res.getString("name_user");
                if(res.getInt("stateitem") == 1){
                    item = new Item(res.getInt("iditem"),
                            res.getString("descriptionitem"),
                            res.getDouble("price"),
                            State.getFromId(res.getInt("stateitem")),
                            showSupplierList(),
                            findDateCreation(id),
                            findPriceReductions(reduce),
                            findUserbyName(user));

                    itemList.add(item);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return itemList;
    }

    public List<Item> showItemsDiscontinued(){
        String hql = "SELECT * FROM ITEM";
        Item item;
        List<Item> itemList = new ArrayList<>();
        try{
            res = sta.executeQuery(hql);
            while(res.next()){
                double reduce =res.getDouble("pricereduced");
                int id = res.getInt("iditem");
                String user = res.getString("name_user");
                if(res.getInt("stateitem") == 2){
                    item = new Item(res.getInt("iditem"),
                            res.getString("descriptionitem"),
                            res.getDouble("price"),
                            State.getFromId(res.getInt("stateitem")),
                            showSupplierList(),
                            findDateCreation(id),
                            findPriceReductions(reduce),
                            findUserbyName(user));

                    itemList.add(item);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return itemList;
    }

    public void saveItem(Item item, String date){

        try{
            st.execute("INSERT INTO ITEM VALUES(" +
                            item.getIdItem() + ",'" +
                            item.getDescription() + "',"+
                            item.getPrice() + ","+
                            item.getState().getId() + ",'" +
                            item.getCreator().getNameUser() + "'," +
                            item.getPriceReductions().getReducedPrice() + ",'" +
                            date + "')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteItem(Item item){
        try{
            st.execute("DELETE FROM ITEM WHERE IDITEM = " + item.getIdItem());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editItem(Item item){
        try{
            st.execute("update ITEM " + "set DESCRIPTIONITEM='" +
                    item.getDescription() + "'"+ " where IDITEM=" + item.getIdItem());
            st.execute("update ITEM " + "set DESCRIPTIONITEM= " +
                    item.getPrice() + " where IDITEM=" + item.getIdItem());
            st.execute("update ITEM " + "set STATEITEM= " +
                    item.getState().getId() + " where IDITEM=" + item.getIdItem());
            st.execute("update ITEM " + "set PRICEREDUCED= " +
                    item.getPriceReductions().getReducedPrice() + " where IDITEM=" + item.getIdItem());
            st.execute("update ITEM " + "set DATECREATE= '" +
                    item.getCreationDate() + "'" + " where IDITEM=" + item.getIdItem());
            st.execute("update ITEM " + "set NAME_USER='" +
                    item.getCreator().getNameUser() + "'" + " where IDITEM=" + item.getIdItem());

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(UserClient userClient){
        try{
            st.execute("INSERT INTO USERCLIENT VALUES('" +
                            userClient.getNameUser() + "','" +
                            userClient.getPasswordUser() + "')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(UserClient userClient){
        try{
            st.execute("DELETE FROM USERCLIENT WHERE NAMEUSER= " + "'" + userClient.getNameUser() + "'");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void saveSupplier(Supplier supplier, int id){
        try{
            st.execute("INSERT INTO SUPPLIER VALUES('" +
                            supplier.getNameSupplier() + "','" +
                            supplier.getCountrySupplier() + "'," +
                            id + ")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void savePriceReductions(double price, String start, String end, int id){
        try{
            st.execute("INSERT INTO PRICEREDUCTIONS VALUES(" +
                    price + ",'" +
                    start + "','" +
                    end + "'," +
                    id + ")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deactivateItem(Item item){
        try{
            st.execute("UPDATE ITEM" + " SET STATEITEM= "+
                            2 + "WHERE IDITEM=" +  item.getIdItem());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Supplier> showSupplierOfItem(int idItem){
        List<Supplier> supplierList = new ArrayList<>();
        Supplier supplier;
        String hql = "SELECT * FROM SUPPLIER WHERE ID_ITEM = " + idItem;

        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                supplier = new Supplier(rs.getString("NAMESUPPLIER"),
                                        rs.getString("COUNTRYSUPPLIER"));
                supplierList.add(supplier);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return supplierList;
    }

    public List<PriceReductions> showPriceReductionsOfItem(int id){
        List<PriceReductions> priceReductionsList = new ArrayList<>();
        PriceReductions priceReductions;
        String hql = "SELECT * FROM PRICEREDUCTIONS WHERE ID_ITEM=" + id;

        try{
            rs = st.executeQuery(hql);
            while(rs.next()){
                priceReductions = new PriceReductions(rs.getDouble("pricereduced"),
                        rs.getDate("startdate"),
                        rs.getDate("enddate"));
                priceReductionsList.add(priceReductions);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return priceReductionsList;
    }

}
