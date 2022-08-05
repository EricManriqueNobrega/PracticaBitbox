import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="SUPPLIER")
public class Supplier {

    @Id
    @NotNull
    @Column(name="NAMESUPPLIER",unique = true,nullable = false)
    private String nameSupplier;
    @NotNull
    @Column(name="COUNTRYSUPPLIER")
    private String countrySupplier;
    @Column(name = "ID_ITEM")
    private int idItem;
    private List<Item> listItem;

    public Supplier(){

    }

    public Supplier(String namesupplier, String countrysupplier){
        this.nameSupplier = namesupplier;
        this.countrySupplier = countrysupplier;
    }

    public Supplier(String namesupplier, String countrysupplier, int iditem){
        this.nameSupplier = namesupplier;
        this.countrySupplier = countrysupplier;
        this.idItem = iditem;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String namesupplier) {
        this.nameSupplier = namesupplier;
    }

    public String getCountrySupplier() {
        return countrySupplier;
    }

    public void setCountrySupplier(String countrysupplier) {
        this.countrySupplier = countrysupplier;
    }

    public List<Item> getListItem() {
        return listItem;
    }

    public void setListItem(List<Item> listItem) {
        this.listItem = listItem;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}
