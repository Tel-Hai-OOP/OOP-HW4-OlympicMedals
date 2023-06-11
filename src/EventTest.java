import org.junit.Before;
import org.junit.Test;

import javax.management.remote.rmi.RMIConnectionImpl;

import static org.junit.Assert.*;

public class EventTest {

    Event balanceBeam, allAround, unevenBars, parallelBars, mensArchery;
    @Before
    public void setUp() {
        balanceBeam = new Event("Balance Beam", "Gymnastics");
        allAround = new Event("All Around", "Gymnastics");
        unevenBars = new Event("Uneven Bars", "Gymnastics");
        parallelBars = new Event("Parallel Bars", "Gymnastics");
        mensArchery = new Event("Men's individual Archery", "Archery");
    }


    @Test
    public void getEventName() {
        assertEquals("Balance Beam", balanceBeam.getEventName());
        assertEquals("All Around", allAround.getEventName());
        assertEquals("Uneven Bars", unevenBars.getEventName());
        assertEquals("Parallel Bars", parallelBars.getEventName());
        assertEquals("Men's individual Archery", mensArchery.getEventName());
    }

    @Test
    public void getSportName() {
        assertEquals("Gymnastics", balanceBeam.getSportName());
        assertEquals("Gymnastics", allAround.getSportName());
        assertEquals("Gymnastics", unevenBars.getSportName());
        assertEquals("Gymnastics", parallelBars.getSportName());
        assertEquals("Archery", mensArchery.getSportName());
    }
}