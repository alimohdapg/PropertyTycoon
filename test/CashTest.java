import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CashTest {

    private Cash cash;

    @BeforeEach
    void setUp(){
        cash = new Cash(1000);
    }

    @Test
    void testGetAmount() {
        assertEquals(1000, cash.getAmount());
    }

    @Test
    void testSetAmount() {
        cash.setAmount(987654321);
        assertEquals(987654321, cash.getAmount());
    }

    @Test
    void testAddAmount() {
        cash.addAmount(500);
        assertEquals(1500, cash.getAmount());
    }

    @Test
    void testSubtractAmount() {
        cash.subtractAmount(500);
        assertEquals(500, cash.getAmount());
    }
}
