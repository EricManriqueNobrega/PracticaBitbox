import com.sun.istack.internal.NotNull;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PRICEREDUCTIONS")
public class PriceReductions {

    @Id
    @NotNull
    @Column(name = "PRICEREDUCED", unique = true)
    private double reducedPrice;
    @Column(name = "STARTDATE")
    private Date startDate;
    @Column(name = "ENDDATE")
    private Date endDate;
    @Column(name = "ID_ITEM")
    private int idItem;

    public PriceReductions(){

    }

    public PriceReductions (double reducedPrice, Date startDate, Date endDate){
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PriceReductions (double reducedPrice, Date startDate, Date endDate, int iditem){
        this.reducedPrice = reducedPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idItem = iditem;
    }

    public double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
}
