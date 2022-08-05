import junit.framework.TestCase;

public class AdministratorTest extends TestCase {

    Administrator admin = new Administrator("Luis","1234");
    public void testGetNameAdmin() {
        assertEquals("Luis", admin.getNameAdmin());
    }

    public void testGetPasswordAdmin() {
        assertEquals("1234",admin.getPasswordAdmin());
    }
}