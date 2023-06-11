import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class CeremonyTest {

    Ceremony balanceBeamCeremony, allAroundCeremony, unevenBarsCeremony, parallelBarsCeremony;
    Event balanceBeam, allAround, unevenBars, parallelBars;
    Athlete Alice, Bob, Cid, Dan;

    @Before
    public void setUp() {
        balanceBeam = new Event("Balance Beam", "Gymnastics");
        Nation USA, Israel, Canada, Denmark;
        USA = new Nation("USA", "Star Spangled Banner");
        Israel = new Nation("Israel", "Hatikva");
        Canada = new Nation("Canada", "O Canada");
        Denmark = new Nation("Denmark", "Der er et yndigt land");
        CeremonyData.nations.clear();
        CeremonyData.nations.add(USA);
        CeremonyData.nations.add(Israel);
        CeremonyData.nations.add(Canada);
        CeremonyData.nations.add(Denmark);

        balanceBeam = new Event("Balance Beam", "Gymnastics");
        allAround = new Event("All Around", "Gymnastics");
        unevenBars = new Event("Uneven Bars", "Gymnastics");
        parallelBars = new Event("Parallel Bars", "Gymnastics");
        CeremonyData.events.clear();
        CeremonyData.events.add(balanceBeam);
        CeremonyData.events.add(allAround);
        CeremonyData.events.add(unevenBars);
        CeremonyData.events.add(parallelBars);

        Alice = new Athlete("Alice", USA);
        Bob = new Athlete("Bob", Israel);
        Cid = new Athlete("Cid", Canada);
        Dan = new Athlete("Dan", Denmark);
        CeremonyData.athletes.clear();
        CeremonyData.athletes.add(Alice);
        CeremonyData.athletes.add(Bob);
        CeremonyData.athletes.add(Cid);
        CeremonyData.athletes.add(Dan);

        balanceBeamCeremony = new Ceremony(balanceBeam, Alice, Bob, Cid, 10);
        allAroundCeremony = new Ceremony(allAround, Bob, Cid, Dan, 5);
        unevenBarsCeremony = new Ceremony(unevenBars, Cid, Dan, Alice, 7);
        parallelBarsCeremony = new Ceremony(parallelBars, Dan, Bob, Cid, 8);
    }

    @Test
    public void getEvent() {
        assertEquals(balanceBeam, balanceBeamCeremony.getEvent());
        assertEquals(allAround, allAroundCeremony.getEvent());
        assertEquals(unevenBars, unevenBarsCeremony.getEvent());
        assertEquals(parallelBars, parallelBarsCeremony.getEvent());
        assertNotEquals(balanceBeam, allAroundCeremony.getEvent());
    }

    @Test
    public void getFirstPlace() {
        assertEquals(Alice, balanceBeamCeremony.getFirstPlace());
        assertEquals(Bob, allAroundCeremony.getFirstPlace());
        assertEquals(Cid, unevenBarsCeremony.getFirstPlace());
        assertEquals(Dan, parallelBarsCeremony.getFirstPlace());
        assertNotEquals(Bob, balanceBeamCeremony.getFirstPlace());
    }

    @Test
    public void getSecondPlace() {
        assertEquals(Bob, balanceBeamCeremony.getSecondPlace());
        assertEquals(Cid, allAroundCeremony.getSecondPlace());
        assertEquals(Dan, unevenBarsCeremony.getSecondPlace());
        assertEquals(Bob, parallelBarsCeremony.getSecondPlace());
        assertNotEquals(Alice, balanceBeamCeremony.getSecondPlace());
    }

    @Test
    public void getThirdPlace() {
        assertEquals(Cid, balanceBeamCeremony.getThirdPlace());
        assertEquals(Dan, allAroundCeremony.getThirdPlace());
        assertEquals(Alice, unevenBarsCeremony.getThirdPlace());
        assertEquals(Cid, parallelBarsCeremony.getThirdPlace());
        assertNotEquals(Alice, balanceBeamCeremony.getThirdPlace());
    }

    @Test
    public void getDuration() {
        assertEquals(10, balanceBeamCeremony.getDuration());
        assertEquals(5, allAroundCeremony.getDuration());
        assertEquals(7, unevenBarsCeremony.getDuration());
        assertEquals(8, parallelBarsCeremony.getDuration());
    }


}
