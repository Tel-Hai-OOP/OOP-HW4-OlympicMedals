import static org.junit.Assert.*;

public class NationTest {

    Nation USA, Israel, Canada, Denmark;

    @org.junit.Before
    public void setUp() {
        USA = new Nation("USA", "Star Spangled Banner");
        Israel = new Nation("Israel", "התקוה");
        Canada = new Nation("Canada", "O Canada");
        Denmark = new Nation("Denmark", "Der er et yndigt land");
    }

    @org.junit.Test
    public void getLock() {
        assertNotNull(USA.getLock());
        assertFalse(USA.getLock().isLocked());
        USA.getLock().lock();
        assertTrue(USA.getLock().isLocked());
        USA.getLock().unlock();
        assertFalse(USA.getLock().isLocked());
    }

    @org.junit.Test
    public void getNationName() {
        assertEquals("USA", USA.getNationName());
        assertEquals("Israel", Israel.getNationName());
        assertEquals("Canada", Canada.getNationName());
        assertEquals("Denmark", Denmark.getNationName());
    }

    @org.junit.Test
    public void getAnthem() {
        assertEquals("Star Spangled Banner", USA.getAnthem());
        assertEquals("התקוה", Israel.getAnthem());
        assertEquals("O Canada", Canada.getAnthem());
        assertEquals("Der er et yndigt land", Denmark.getAnthem());
    }

    @org.junit.Test
    public void testEquals() {
        assertFalse(USA.equals(Israel));
        assertTrue(USA.equals(USA));
        assertFalse(USA.equals(null));
        assertFalse(USA.equals("USA"));

        Nation copyUSA = new Nation("USA", "Star Spangled Banner");
        assertTrue(USA.equals(copyUSA));
        assertTrue(copyUSA.equals(USA));

        Nation copy2USA = new Nation("USA", "The Star Spangled Banner");
        assertTrue(USA.equals(copy2USA));
        assertTrue(copy2USA.equals(USA));
    }

    @org.junit.Test
    public void testToString() {
        assertEquals("USA          with anthem Star Spangled Banner          ", USA.toString());
        assertEquals("Israel       with anthem התקוה                         ", Israel.toString());
        assertEquals("Canada       with anthem O Canada                      ", Canada.toString());
        assertEquals("Denmark      with anthem Der er et yndigt land         ", Denmark.toString());
    }

    @org.junit.Test
    public void compareTo() {
        assertTrue(USA.compareTo(Israel) > 0);
        assertTrue(USA.compareTo(Canada) > 0);
        assertTrue(USA.compareTo(Denmark) > 0);
        assertTrue(USA.compareTo(USA) == 0);

        Nation USA2 = new Nation("USA", "The Other Anthem");
        assertTrue(USA.compareTo(USA2) == 0);

        assertTrue(Israel.compareTo(USA) < 0);
        assertTrue(Israel.compareTo(Canada) > 0);
        assertTrue(Israel.compareTo(Denmark) > 0);
        assertTrue(Israel.compareTo(Israel) == 0);

        assertTrue(Canada.compareTo(USA) < 0);
        assertTrue(Canada.compareTo(Israel) < 0);
        assertTrue(Canada.compareTo(Denmark) < 0);
        assertTrue(Canada.compareTo(Canada) == 0);

        assertTrue(Denmark.compareTo(USA) < 0);
        assertTrue(Denmark.compareTo(Israel) < 0);
        assertTrue(Denmark.compareTo(Canada) > 0);
        assertTrue(Denmark.compareTo(Denmark) == 0);
    }

    @org.junit.Test
    public void buildNation() {
        // Correctly formatted string - should work
        String romaniaString = "Romania;Deșteaptă-te, române!";
        Nation Romania = Nation.buildNation(romaniaString);
        assertEquals("Romania", Romania.getNationName());
        assertEquals("Deșteaptă-te, române!", Romania.getAnthem());

        // missing semicolon should fail
        try {
            String missingSemicolon = "Romania Deșteaptă-te, române!";
            Nation n = Nation.buildNation(missingSemicolon);
            fail();
        } catch (Exception e) {
            // good!
            assertTrue(true);
        }

        // having too many parts should fail
        try {
            String extraPart = "Romania;Deșteaptă-te, române!;Europe";
            Nation n = Nation.buildNation(extraPart);
            fail();
        } catch (Exception e) {
            // good!
            assertTrue(true);
        }
    }
}