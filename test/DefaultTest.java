import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultTest {

    private Default testDefault1;
    private Default testDefault2;
    private Default testDefault3;

    @BeforeEach
    void setUp() {
        testDefault1 = new Default("Go");
        testDefault2 = new Default("Pot Luck");
        testDefault3 = new Default("Opportunity Knocks");
    }

    @Test
    void testGetName(){
        assertEquals("Go", testDefault1.getName());
        assertEquals("Pot Luck", testDefault2.getName());
        assertEquals("Opportunity Knocks", testDefault3.getName());
    }
}