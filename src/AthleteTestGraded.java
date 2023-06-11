import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AthleteTestGraded {

    Athlete Alice, Bob, Cid, Dan;
    Nation USA, Israel, Canada, Denmark;
    @Before
    public void setUp() {

        USA = new Nation("USA", "Star Spangled Banner");
        Israel = new Nation("Israel", "התקוה");
        Canada = new Nation("Canada", "O Canada");
        Denmark = new Nation("Denmark", "Der er et yndigt land");
        CeremonyData.nations.clear();
        CeremonyData.nations.add(USA);
        CeremonyData.nations.add(Israel);
        CeremonyData.nations.add(Canada);
        CeremonyData.nations.add(Denmark);

        Alice = new Athlete("Alice", USA);
        Bob = new Athlete("Bob", Israel);
        Cid = new Athlete("Cid", Canada);
        Dan = new Athlete("Dan", Denmark);
    }

    @Test
    public void buildAthlete() {
        // Create a valid Athlete
        String haroldString = "Harold;USA";
        Athlete harold = Athlete.buildAthlete(haroldString);
        assertEquals(USA, harold.getNationality());
        assertEquals("Harold", harold.getAthleteName());

        // Missing ;
        try {
            String missingSemicolon = "Harold USA";
            Athlete fail = Athlete.buildAthlete(missingSemicolon);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // too many parts
        try {
            String tooManyParts = "Harold;USA;95";
            Athlete fail = Athlete.buildAthlete(tooManyParts);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }

        // unknown country
        try {
            String unknownCountry = "Harold;Japan";
            Athlete fail = Athlete.buildAthlete(unknownCountry);
            fail();
        } catch (Exception ex) {
            // good!
            assertTrue(true);
        }
    }

    @Test
    public void testToString() {
        // show all the athletes above
        assertEquals("Athlete Alice         from USA          with anthem Star Spangled Banner          ", Alice.toString());
        assertEquals("Athlete Bob           from Israel       with anthem התקוה                         ", Bob.toString());
        assertEquals("Athlete Cid           from Canada       with anthem O Canada                      ", Cid.toString());
        assertEquals("Athlete Dan           from Denmark      with anthem Der er et yndigt land         ", Dan.toString());
    }
}
