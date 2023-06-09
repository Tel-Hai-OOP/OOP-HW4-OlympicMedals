public class Event {
    private String eventName;
    private String sportName;

    public Event(String eventName, String sportName) {
        this.eventName = eventName;
        this.sportName = sportName;
    }

    public static Event buildEvent(String string) {
        /// TODO: Fill in building from a String
        return null;
    }

    @Override
    public String toString() {
        /// TODO: Fill in converting to a String
        return "";
    }

    public String getEventName() {
        return eventName;
    }

    public String getSportName() {
        return sportName;
    }
}
