public class Athlete {

    private String athleteName;
    private Nation nationality;

    public Athlete(String athleteName, Nation nationality) {
        this.athleteName = athleteName;
        this.nationality = nationality;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public Nation getNationality() {
        return nationality;
    }

    public static Athlete buildAthlete(String s) {
        /// TODO: Fill in build method
        return null;
    }

    @Override
    public String toString() {
        /// TODO: Fill in conversion to string
        return "";
    }
}
