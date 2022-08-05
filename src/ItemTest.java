import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

public class ItemTest extends TestCase {

    Item item = new Item(1, "q",20.00,State.ACTIVE,new ArrayList<Supplier>(),
            new Date(), new PriceReductions(10.00,new Date(),new Date()),new UserClient("Henry","1234"));

    public void testGetIdItemCode() {
        assertEquals(1,item.getIdItem());
    }

    public void testGetDescription() {
        assertEquals("q",item.getDescription());
    }

    public void testGetPrice() {
        assertEquals(20.00,item.getPrice());
    }

    public void testGetState() {
        assertEquals(State.ACTIVE,item.getState());
    }

    public void testGetCreationDate() {
        assertNotSame(new Date(),item.getCreationDate());
    }

    public void testGetCreator() {
        UserClient user = new UserClient("Henry","1234");
        assertNotSame(user,item.getCreator());
    }

    public void testGetPriceReductions() {
        PriceReductions price = new PriceReductions(10.00,new Date(),new Date());
        assertNotSame(price,item.getPriceReductions());
    }
}