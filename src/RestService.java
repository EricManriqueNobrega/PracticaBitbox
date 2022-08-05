
import org.h2.engine.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;


public class RestService {
    ConnectionSql connectionSql = new ConnectionSql();
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{nameuser}")
    public UserClient findUserLog(@PathParam("nameuser") String nameuser){
       UserClient client = connectionSql.findUserbyName(nameuser);
       return client;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<UserClient> getUserList(){
        List<UserClient> userClientList = connectionSql.showUserClientList();
        return userClientList;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Item> getItemList(){
        List<Item> itemList = connectionSql.showItemsList();
        return itemList;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Item> getItemListActive(){
        List<Item> itemList = connectionSql.showItemsActive();
        return itemList;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Item> getItemListDiscontinued(){
        List<Item> itemList = connectionSql.showItemsDiscontinued();
        return itemList;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{nameuser}")
    @Consumes({MediaType.APPLICATION_JSON})
    public UserClient createUser(@PathParam("nameuser") String nameuser, String password){
        if(connectionSql.findUserClientName(nameuser)){
            throw new WebApplicationException(404);
        }
        UserClient userClient = new UserClient(nameuser,password);
        connectionSql.saveUser(userClient);
        return userClient;
    }

    @PUT
    @Path("/{idItemCode}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Item createItem(@PathParam("idItemCode") int idItemCode, String description,
                           double price, State state, List<Supplier> list ,Date creationDate,
                           PriceReductions priceReductions, UserClient creator){
        if(connectionSql.findItem(idItemCode)) throw new WebApplicationException(404);
        Item item = new Item(idItemCode,description,price,state,list,creationDate,priceReductions,creator);
        connectionSql.saveItem(item,creationDate.toString());
        return item;
    }

    @PUT
    @Path("/{idItemCode}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Item editItem(@PathParam("idItemCode") int idItemCode, String description,
                         double price, State state, List<Supplier> list ,Date creationDate,
                         PriceReductions priceReductions, UserClient creator){
        Item item = connectionSql.showItem(idItemCode);
        item.setDescription(description);
        item.setPrice(price);
        item.setState(state);
        item.setListSuppliers(list);
        item.setCreationDate(creationDate);
        item.setPriceReductions(priceReductions);
        item.setCreator(creator);
        connectionSql.editItem(item);
        return item;
    }

    @DELETE
    @Path("{idItemCode}")
    public void removeItem(@PathParam("idItemCode") int iditem){
        Item item = connectionSql.showItem(iditem);
        connectionSql.deleteItem(item);
    }

    @DELETE
    @Path("{nameuser}")
    public void removeUser(@PathParam("nameuser") String nameuser){
        UserClient userClient = connectionSql.findUserbyName(nameuser);
        connectionSql.deleteUser(userClient);
    }



}
