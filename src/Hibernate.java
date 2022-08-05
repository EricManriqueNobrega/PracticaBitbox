import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;



public class Hibernate {
    public final static Logger log = Logger.getLogger(Hibernate.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
        new Hibernate();
    }

    public Hibernate() {
        //System.out.println(showItem(1));
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
    }

    public boolean findUserClient(String name, String password){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserClient userClient = (UserClient) session.createQuery("from UserClient Where NAMEUSER" +
                "= '" + name + "' AND PASSWORDUSER= '" + password + "'" ).uniqueResult();
        session.close();
        if(userClient == null) return false;
        return true;
    }


    public UserClient findUserbyName(String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserClient userClient = (UserClient) session.createQuery("from UserClient Where NAMEUSER" +
                "= '" + name + "'").uniqueResult();
        session.close();
        if(userClient == null) return null;
        return userClient;
    }

    public boolean findAdmin(String name, String password){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Administrator admin = (Administrator) session.createQuery("from Administrator Where nameAdmin" +
                "= '" + name + "' AND passwordAdmin= '" + password + "'" ).uniqueResult();
        session.close();
        if(admin == null) return false;

        return true;
    }


    public Item showItem(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Item item = (Item) session.createQuery("from Item Where IdItem" +
                "=" + id).uniqueResult();
        session.close();
        if(item == null) return null;
        return item;
    }

    public List<UserClient> showUserClientList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<UserClient> result = (List<UserClient>)session.createQuery("from UserClient").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> showItemsList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Item> result = (List<Item>)session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Supplier> showSupplierList(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Supplier> result = (List<Supplier>)session.createQuery("from Supplier Where " +
                "ID_ITEM =" + id).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<PriceReductions> showPriceReductionsList(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PriceReductions> result = (List<PriceReductions>)session.createQuery("from PriceReductions Where " +
                "ID_ITEM =" + id).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public PriceReductions findPriceReductions(int id , double reduce){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        PriceReductions PriceReductions = (PriceReductions) session.createQuery("from PriceReductions Where ID_ITEM" +
                "=" + id + "AND PRICEREDUCED = " + reduce).uniqueResult();
        session.close();
        if(PriceReductions == null) return null;
        return PriceReductions;
    }

    public List<Item> showItemsActive() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Item> result = (List<Item>)session.createQuery("from Item where StateITEM = " + 1).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> showItemsDiscontinued() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Item> result = (List<Item>)session.createQuery("from Item where StateITEM = " + 2).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }


    public void saveItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if(item == null){
            session.close();
            return;
        }
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePriceReductionsList(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PriceReductions> result = (List<PriceReductions>)session.createQuery("from PriceReductions Where " +
                "ID_ITEM =" + id).list();
        for(int i = 0; i< result.size();i++){
            session.delete(result.get(i));
        }
        session.getTransaction().commit();
        session.close();
    }

    public void editItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void saveUser(UserClient userClient) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(userClient);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(UserClient userClient) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if(userClient == null){
            session.close();
            return;
        }
        session.delete(userClient);
        session.getTransaction().commit();
        session.close();
    }

    public void saveSupplier(Supplier supplier) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(supplier);
        session.getTransaction().commit();
        session.close();
    }

    public void savePriceReductions(PriceReductions priceReductions) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(priceReductions);
        session.getTransaction().commit();
        session.close();
    }

    public void deactivateItem(Item item){
        item.setState(State.DISCONTINUED);
        item.setStateId(2);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Supplier> showSuppliers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Supplier> result = (List<Supplier>)session.createQuery("from Supplier").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<PriceReductions> showPriceReductions(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PriceReductions> result = (List<PriceReductions>) session.createQuery("from PriceReductions").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }


}

