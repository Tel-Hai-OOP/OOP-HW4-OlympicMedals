import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AthleteTest {

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
    public void getAthleteName() {
        assertEquals("Alice", Alice.getAthleteName());
        assertEquals("Bob", Bob.getAthleteName());
        assertEquals("Cid", Cid.getAthleteName());
        assertEquals("Dan", Dan.getAthleteName());
    }

    @Test
    public void getNationality() {
        assertEquals(USA, Alice.getNationality());
        assertEquals(Israel, Bob.getNationality());
        assertEquals(Canada, Cid.getNationality());
        assertEquals(Denmark, Dan.getNationality());
    }


}