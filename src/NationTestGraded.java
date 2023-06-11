import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NationTestGraded {
    Nation USA, Israel, Canada, Denmark;

    @Before
    public void setUp() {
        USA = new Nation("USA", "Star Spangled Banner");
        Israel = new Nation("Israel", "התקוה");
        Canada = new Nation("Canada", "O Canada");
        Denmark = new Nation("Denmark", "Der er et yndigt land");
    }

    @Test
    public void testToString() {
        assertEquals("USA          with anthem Star Spangled Banner          ", USA.toString());
        assertEquals("Israel       with anthem התקוה                         ", Israel.toString());
        assertEquals("Canada       with anthem O Canada                      ", Canada.toString());
        assertEquals("Denmark      with anthem Der er et yndigt land         ", Denmark.toString());
    }

    @Test
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
