import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ITEM")
public class Item {

    @Id
    @NotNull
    @Column(name="IDITEM",unique = true,nullable = false)
    private  int idItem;
    @Column(name = "DESCRIPTIONITEM",nullable = false)
    private  String description;
    @Column(name = "PRICE")
    private  double price;
    @Column(name = "STATE")
    private int stateId;
    private  State state;
    private  List<Supplier> listSuppliers;
    private  List<PriceReductions> listPriceReductions;
    @Column(name = "PRICEREDUCED")
    private double reducedPrice;
    private  PriceReductions priceReductions;
    @Column(name = "DATECREATE")
    private  Date creationDate;
    private  UserClient creator;
    @Column(name = "NAME_USER")
    private String creatorName;

    public Item(){

    }

    public Item (int idItemCode, String descripcion, double price, State state, List<Supplier> list, Date creationDate,PriceReductions priceReductions,  UserClient creator){
        this.idItem = idItemCode;
        this.description = descripcion;
        this.price = price;
        this.state = state;
        this.listSuppliers = list;
        this.priceReductions = priceReductions;
        this.creationDate = creationDate;
        this.creator = creator;
    }

    public int getIdItem() {
        return idItem;
    }


    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public State getState() {
        return state;
    }

    public List<Supplier> getListSuppliers() {
        return listSuppliers;
    }

    public List<PriceReductions> getListPriceReductions() {
        return listPriceReductions;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public UserClient getCreator() {
        return creator;
    }

    public PriceReductions getPriceReductions() {
        return priceReductions;
    }

    public void setIdItem(int idItemCode) {
        this.idItem = idItemCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setListSuppliers(List<Supplier> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }

    public void setPriceReductions(PriceReductions priceReductions) {
        this.priceReductions = priceReductions;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreator(UserClient creator) {
        this.creator = creator;
    }

    public String getCreatorName(){
        return creatorName;
    }

    public void setCreatorName(String creatorName){
        this.creatorName = creatorName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }


}
