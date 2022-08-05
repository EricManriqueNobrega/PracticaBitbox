import junit.framework.TestCase;

public class SupplierTest extends TestCase {

    Supplier supplier = new Supplier("QL", "Portugal");

    public void testGetNameSupplier() {
        assertEquals("QL", supplier.getNameSupplier());
    }

    public void testGetCountrySupplier() {
        assertEquals("Portugal", supplier.getCountrySupplier());
    }
}