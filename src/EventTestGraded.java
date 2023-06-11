import org.junit.Before;
import org.junit.Test;

import javax.management.remote.rmi.RMIConnectionImpl;

import static org.junit.Assert.*;

public class EventTestGraded {
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
    public void testToString() {
        // test all events here
        assertEquals("Event  Balance Beam                 for sport Gymnastics", balanceBeam.toString());
        assertEquals("Event  All Around                   for sport Gymnastics", allAround.toString());
        assertEquals("Event  Uneven Bars                  for sport Gymnastics", unevenBars.toString());
        assertEquals("Event  Parallel Bars                for sport Gymnastics", parallelBars.toString());
        assertEquals("Event  Men's individual Archery     for sport Archery   ", mensArchery.toString());
    }

    @Test
    public void buildEvent() {
        // should work
        String fiftyMetersString = "50 metres;Athletics";
        Event fiftyMeters = Event.buildEvent(fiftyMetersString);
        assertEquals("50 metres", fiftyMeters.getEventName());
        assertEquals("Athletics", fiftyMeters.getSportName());

        // missing semicolon
        try {
            String missingSemicolon = "50 metres Athletics";
            Event e = Event.buildEvent(missingSemicolon);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // too many parts
        try {
            String tooManyParts = "50 metres;Athletics;MALE";
            Event e = Event.buildEvent(tooManyParts);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }
    }
}
