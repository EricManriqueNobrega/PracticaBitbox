import junit.framework.TestCase;

public class StateTest extends TestCase {

    State state = State.ACTIVE;

    public void testGetId() {
        assertEquals(1,state.getId());
    }

    public void testGetValue() {
        assertEquals("Active",state.getValue());
    }


}