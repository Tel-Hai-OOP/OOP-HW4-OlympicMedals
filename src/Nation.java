import java.util.concurrent.locks.ReentrantLock;

public class Nation implements Comparable<Nation>{

    private String nationName;
    private String anthem;
    private final ReentrantLock lock;

    public ReentrantLock getLock() {
        return lock;
    }

    public Nation(String nationName, String anthem) {
        this.nationName = nationName;
        this.anthem = anthem;
        lock = new ReentrantLock();
    }

    public String getNationName() {
        return nationName;
    }

    public String getAnthem() {
        return anthem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof  Nation) {
            return this.nationName.equals(((Nation) obj).getNationName());
        } else {
            return false;
        }

    }

    @Override
    public String toString() {

        /// TODO: Create a String representation of the Nation.
        /// Put the nation name left justified in a 12 character field
        /// Put the anthem name left justified in a 30 character field
        return "";
    }

    @Override
    public int compareTo(Nation o) {
        if (o == this) {
            return 0;
        }
        if (o != null) {
            return this.getNationName().compareToIgnoreCase(o.getNationName());
        } else
        {
            return 0;
        }
    }

    public static Nation buildNation (String s) {
        /// TODO: Fill in code to build a Nation based on a String
        /// Put the nation name left justified in a 12 character field
        /// Put the anthem name left justified in a 30 character field
        return null;
    }
}
