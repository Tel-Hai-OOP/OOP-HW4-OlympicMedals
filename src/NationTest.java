import static org.junit.Assert.*;

public class NationTest {

    Nation USA, Israel, Canada, Denmark;

    @org.junit.Before
    public void setUp() {
        USA = new Nation("USA", "Star Spangled Banner");
        Israel = new Nation("Israel", "Hatikva");
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
    }

    @org.junit.Test
    public void getAnthem() {
    }

    @org.junit.Test
    public void testEquals() {
    }

    @org.junit.Test
    public void testToString() {
    }

    @org.junit.Test
    public void compareTo() {
    }

    @org.junit.Test
    public void buildNation() {
    }
}