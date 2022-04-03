import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StationAndUtilityTest {

    private StationAndUtility stationAndUtility1;
    private StationAndUtility stationAndUtility2;

    @BeforeEach
    void setUp() {
        stationAndUtility1 = new StationAndUtility("Test Station", 50, Color.STATION);
        stationAndUtility2 = new StationAndUtility("Test Utility", 100, Color.UTILITIES);
    }

    @Test
    void testGetCost(){
        assertEquals(50, stationAndUtility1.getCost());
        assertEquals(100, stationAndUtility2.getCost());
    }
}