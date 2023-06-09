import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class CeremonyTestGraded {
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
    public void buildCeremony() {
        // Balance Beam - that should work
        String balanceBeamString = "Balance Beam;Alice;Bob;Cid;5";
        Ceremony balanceBeamCeremony2 = Ceremony.buildCeremony(balanceBeamString);

        assertEquals(balanceBeam, balanceBeamCeremony2.getEvent());
        assertEquals(Alice, balanceBeamCeremony2.getFirstPlace());
        assertEquals(Bob, balanceBeamCeremony2.getSecondPlace());
        assertEquals(Cid, balanceBeamCeremony2.getThirdPlace());
        assertEquals(5, balanceBeamCeremony2.getDuration());

        // Unknown event should fail
        try {
            String unknownEventString = "Dressage;Alice;Bob;Cid;5";
            Ceremony c = Ceremony.buildCeremony(unknownEventString);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // event missing an athlete should fail
        try {
            String missingAthlete = "Balance Beam;Bob;Cid;5";
            Ceremony c = Ceremony.buildCeremony(missingAthlete);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // event missing duration
        try {
            String missingDuration = "Balance Beam;Alice;Bob;Cid";
            Ceremony c = Ceremony.buildCeremony(missingDuration);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // event with extra Athlete
        try {
            String extraAthlete = "Balance Beam;Alice;Bob;Cid;Dan;5";
            Ceremony c = Ceremony.buildCeremony(extraAthlete);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // event with too many durations
        try {
            String extraDuration = "Balance Beam;Alice;Bob;Dan;5;6";
            Ceremony c = Ceremony.buildCeremony(extraDuration);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }
    }

    @Test
    public void testToString() {
        // check each Ceremony above
        assertEquals("Ceremony for Event  Balance Beam                 for sport Gymnastics duration 10 and winners Athlete Alice         from USA          with anthem Star Spangled Banner           Athlete Bob           from Israel       with anthem Hatikva                        and Athlete Cid           from Canada       with anthem O Canada                      ", balanceBeamCeremony.toString());
        assertEquals("Ceremony for Event  All Around                   for sport Gymnastics duration 5 and winners Athlete Bob           from Israel       with anthem Hatikva                        Athlete Cid           from Canada       with anthem O Canada                       and Athlete Dan           from Denmark      with anthem Der er et yndigt land         ", allAroundCeremony.toString());
        assertEquals("Ceremony for Event  Uneven Bars                  for sport Gymnastics duration 7 and winners Athlete Cid           from Canada       with anthem O Canada                       Athlete Dan           from Denmark      with anthem Der er et yndigt land          and Athlete Alice         from USA          with anthem Star Spangled Banner          ", unevenBarsCeremony.toString());
        assertEquals("Ceremony for Event  Parallel Bars                for sport Gymnastics duration 8 and winners Athlete Dan           from Denmark      with anthem Der er et yndigt land          Athlete Bob           from Israel       with anthem Hatikva                        and Athlete Cid           from Canada       with anthem O Canada                      ", parallelBarsCeremony.toString());
    }

    @Test
    public void testBalanceBeamRun() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream captureOutput = new PrintStream(baos);
        PrintStream standardOut = System.out;
        System.setOut(captureOutput);

        // run the balance beam alone
        balanceBeamCeremony.run();
        // wait for it to finish

        String results = baos.toString();

        System.setOut(standardOut);
        Pattern startLine = Pattern.compile("Start  ceremony  Balance Beam               at \\d\\d?:\\d\\d?:\\d\\d? \\(duration  \\d+\\)");
        Matcher startLineMatch = startLine.matcher(results);
        assertTrue(startLineMatch.find());
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked Canada    "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked Israel    "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Alice         from USA          with anthem Star Spangled Banner          " + System.lineSeparator() +
                "Silver Medal Athlete Bob           from Israel       with anthem Hatikva                       " + System.lineSeparator() +
                "Bronze Medal Athlete Cid           from Canada       with anthem O Canada                      " + System.lineSeparator()));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked     Canada"));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked     Israel"));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked        USA"));
        Pattern endLine = Pattern.compile("End    ceremony  Balance Beam               at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endLineMatch = endLine.matcher(results);
        assertTrue(endLineMatch.find());
    }
}
