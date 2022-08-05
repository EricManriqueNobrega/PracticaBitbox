import junit.framework.TestCase;

public class UserClientTest extends TestCase {

    UserClient user = new UserClient("pepe","1234");

    public void testGetNameUser() {
        assertEquals("pepe",user.getNameUser());
    }

    public void testGetPasswordUser() {
        assertEquals("1234", user.getPasswordUser());
    }
}