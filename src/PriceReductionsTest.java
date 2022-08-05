import junit.framework.TestCase;

import java.util.Date;

public class PriceReductionsTest extends TestCase {

    PriceReductions priceReductions = new PriceReductions(20.00,new Date(),new Date());

    public void testGetReducedPrice() {
        assertEquals(20.00,priceReductions.getReducedPrice());
    }

    public void testGetStartDate() {
        assertNotSame(new Date(), priceReductions.getStartDate());
    }

    public void testGetEndDate() {
        assertNotSame(new Date(), priceReductions.getEndDate());
    }
}