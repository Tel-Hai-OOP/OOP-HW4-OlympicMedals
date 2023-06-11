import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CeremonyParallelTest {

    Ceremony balanceBeamCeremony, allAroundCeremony, unevenBarsCeremony, parallelBarsCeremony;
    Event balanceBeam, allAround, unevenBars, parallelBars;
    Athlete USA1, Israel1, Canada1, USA2, USA3, Israel2;

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

        USA1 = new Athlete("Alice", USA);
        Israel1 = new Athlete("Bob", Israel);
        Canada1 = new Athlete("Cid", Canada);
        USA2 = new Athlete("Dan", USA);
        USA3 = new Athlete("Ed", USA);
        Israel2 = new Athlete("Fran", Israel);
        CeremonyData.athletes.clear();
        CeremonyData.athletes.add(USA1);
        CeremonyData.athletes.add(Israel1);
        CeremonyData.athletes.add(Canada1);
        CeremonyData.athletes.add(USA2);
        CeremonyData.athletes.add(USA3);
        CeremonyData.athletes.add(Israel2);

        balanceBeamCeremony = new Ceremony(balanceBeam, USA1, Israel1, Canada1, 6);
        allAroundCeremony = new Ceremony(allAround, Canada1, USA2, USA1, 4);
        unevenBarsCeremony = new Ceremony(unevenBars, Canada1, Israel1, USA1, 3);
        parallelBarsCeremony = new Ceremony(parallelBars, USA2, USA1, USA3, 5);
    }

    @Test
    public void testAllOverlapping() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream captureOutput = new PrintStream(baos);
        PrintStream standardOut = System.out;
        System.setOut(captureOutput);

        balanceBeamCeremony.start();
        allAroundCeremony.start();
        unevenBarsCeremony.start();
        parallelBarsCeremony.start();

        try {
            Thread.sleep(60*1000);
        } catch (Exception ex) {
            fail();
            // shouldn't happen
        }

        String results = baos.toString();
        System.setOut(standardOut);

        // all around
        Pattern startAllAroundLine = Pattern.compile("Start  ceremony  All Around                 at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   4\\)");
        Matcher startAllAroundLineMatcher = startAllAroundLine.matcher(results);
        assertTrue(startAllAroundLineMatcher.find());
        assertTrue(results.contains("Event  All Around                   for sport Gymnastics locked Canada    "));
        assertTrue(results.contains("Event  All Around                   for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Event  All Around                   for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Cid           from Canada       with anthem O Canada                      "));
        assertTrue(results.contains("Silver Medal Athlete Dan           from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Bronze Medal Athlete Alice         from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Event  All Around                   for sport Gymnastics unlocked     Canada"));
        assertTrue(results.contains("Event  All Around                   for sport Gymnastics unlocked        USA"));
        assertTrue(results.contains("Event  All Around                   for sport Gymnastics unlocked        USA"));
        Pattern endAllAroundLine = Pattern.compile("End    ceremony  All Around                 at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endAllAroundLineMatch = endAllAroundLine.matcher(results);
        assertTrue(endAllAroundLineMatch.find());

        // parallel bars
        Pattern startParallelBarsLine = Pattern.compile("Start  ceremony  Parallel Bars              at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   5\\)");
        Matcher startParallelBarsLineMatcher = startParallelBarsLine.matcher(results);
        assertTrue(startParallelBarsLineMatcher.find());
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Dan           from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Silver Medal Athlete Alice         from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Bronze Medal Athlete Ed            from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics unlocked        USA"));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics unlocked        USA"));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics unlocked        USA"));
        Pattern endParallelBarsLine = Pattern.compile("End    ceremony  Parallel Bars              at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endParallelBarsLineMatch = endParallelBarsLine.matcher(results);
        assertTrue(endParallelBarsLineMatch.find());

        // uneven bars
        Pattern startUnevenBarsLine = Pattern.compile("Start  ceremony  Uneven Bars                at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   3\\)");
        Matcher startUnevenBarsLineMatcher = startUnevenBarsLine.matcher(results);
        assertTrue(startUnevenBarsLineMatcher.find());
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics locked Canada    "));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics locked Israel    "));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Cid           from Canada       with anthem O Canada                      "));
        assertTrue(results.contains("Silver Medal Athlete Bob           from Israel       with anthem Hatikva                       "));
        assertTrue(results.contains("Bronze Medal Athlete Alice         from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics unlocked     Canada"));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics unlocked     Israel"));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics unlocked        USA"));
        Pattern endUnevenBarsLine = Pattern.compile("End    ceremony  Uneven Bars                at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endUnevenBarsLineMatch = endUnevenBarsLine.matcher(results);
        assertTrue(endUnevenBarsLineMatch.find());

        // balance beam
        Pattern startBalanceBeamLine = Pattern.compile("Start  ceremony  Balance Beam               at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   6\\)");
        Matcher startBalanceBeamLineMatcher = startBalanceBeamLine.matcher(results);
        assertTrue(startBalanceBeamLineMatcher.find());
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked Canada    "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked Israel    "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Alice         from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Silver Medal Athlete Bob           from Israel       with anthem Hatikva                       "));
        assertTrue(results.contains("Bronze Medal Athlete Cid           from Canada       with anthem O Canada                      "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked     Canada"));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked     Israel"));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked        USA"));
        Pattern endBalanceBeamLine = Pattern.compile("End    ceremony  Balance Beam               at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endBalanceBeamLineMatch = endBalanceBeamLine.matcher(results);
        assertTrue(endBalanceBeamLineMatch.find());
    }

    @Test
    public void testOverlappingLocks() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream captureOutput = new PrintStream(baos);
        PrintStream standardOut = System.out;
        System.setOut(captureOutput);

        balanceBeamCeremony.start();
        unevenBarsCeremony.start();

        try {
            Thread.sleep(30*1000);
        } catch (Exception ex ) {
            fail();
            // this shouldn't happen
        }

        String results = baos.toString();
        System.setOut(standardOut);

        Pattern startUnevenBarsLine = Pattern.compile("Start  ceremony  Uneven Bars                at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   3\\)");
        Matcher startUnevenBarsLineMatcher = startUnevenBarsLine.matcher(results);
        assertTrue(startUnevenBarsLineMatcher.find());
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics locked Canada    "));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics locked Israel    "));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Cid           from Canada       with anthem O Canada                      "));
        assertTrue(results.contains("Silver Medal Athlete Bob           from Israel       with anthem Hatikva                       "));
        assertTrue(results.contains("Bronze Medal Athlete Alice         from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics unlocked     Canada"));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics unlocked     Israel"));
        assertTrue(results.contains("Event  Uneven Bars                  for sport Gymnastics unlocked        USA"));
        Pattern endUnevenBarsLine = Pattern.compile("End    ceremony  Uneven Bars                at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endUnevenBarsLineMatch = endUnevenBarsLine.matcher(results);
        assertTrue(endUnevenBarsLineMatch.find());

        Pattern startBalanceBeamLine = Pattern.compile("Start  ceremony  Balance Beam               at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   6\\)");
        Matcher startBalanceBeamLineMatcher = startBalanceBeamLine.matcher(results);
        assertTrue(startBalanceBeamLineMatcher.find());

        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked Canada    "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked Israel    "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics locked USA       "));
        assertTrue(results.contains("Gold   Medal Athlete Alice         from USA          with anthem Star Spangled Banner          "));
        assertTrue(results.contains("Silver Medal Athlete Bob           from Israel       with anthem Hatikva                       "));
        assertTrue(results.contains("Bronze Medal Athlete Cid           from Canada       with anthem O Canada                      "));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked     Canada"));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked     Israel"));
        assertTrue(results.contains("Event  Balance Beam                 for sport Gymnastics unlocked        USA"));
        Pattern endBalanceBeamLine = Pattern.compile("End    ceremony  Balance Beam               at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endBalanceBeamLineMatch = endBalanceBeamLine.matcher(results);
        assertTrue(endBalanceBeamLineMatch.find());
    }

    @Test
    public void testAllSameCountry() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream captureOutput = new PrintStream(baos);
        PrintStream standardOut = System.out;
        System.setOut(captureOutput);

        parallelBarsCeremony.start();
        try {

            Thread.sleep(20 * 1000);
        } catch (Exception ex) {
            fail();
            // this shouldn't happen
        }
        // ensure the output matches

        String results = baos.toString();

        System.setOut(standardOut);
        Pattern startLine = Pattern.compile("Start  ceremony  Parallel Bars              at \\d\\d?:\\d\\d?:\\d\\d? \\(duration   5\\)");
        Matcher startLineMatch = startLine.matcher(results);
        assertTrue(startLineMatch.find());
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics locked USA    "));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics locked USA    "));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics locked USA    "));
        assertTrue(results.contains("Gold   Medal Athlete Dan           from USA          with anthem Star Spangled Banner          " + System.lineSeparator() +
                "Silver Medal Athlete Alice         from USA          with anthem Star Spangled Banner          " + System.lineSeparator() +
                "Bronze Medal Athlete Ed            from USA          with anthem Star Spangled Banner          " + System.lineSeparator()));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics unlocked        USA"));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics unlocked        USA"));
        assertTrue(results.contains("Event  Parallel Bars                for sport Gymnastics unlocked        USA"));
        Pattern endLine = Pattern.compile("End    ceremony  Parallel Bars              at \\d\\d+:\\d\\d+:\\d\\d+");
        Matcher endLineMatch = endLine.matcher(results);
        assertTrue(endLineMatch.find());
    }


}
