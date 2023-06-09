public class Ceremony extends Thread{

    private Event event;
    private Athlete firstPlace, secondPlace, thirdPlace;
    private int duration;

    public Ceremony(Event event, Athlete firstPlace, Athlete secondPlace, Athlete thirdPlace, int duration) {
        this.event = event;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.thirdPlace = thirdPlace;
        this.duration = duration;
    }

    public Event getEvent() {
        return event;
    }

    public Athlete getFirstPlace() {
        return firstPlace;
    }

    public Athlete getSecondPlace() {
        return secondPlace;
    }

    public Athlete getThirdPlace() {
        return thirdPlace;
    }

    public int getDuration() {
        return duration;
    }

    public static Ceremony buildCeremony(String s) {

        /// TODO: Fill in creating from a String
        return null;
    }

    @Override
    public String toString() {
        /// TODO: Fill in conversion to a String
        return "";
    }

    @Override
    public void run() {
        /// TODO: Fill in running the ceremony as a Thread
    }
}
