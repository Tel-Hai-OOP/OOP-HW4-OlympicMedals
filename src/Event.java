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
        /// Put the event name left justified in a 28 character field
        /// Put the sport name left justified in a 10 character field
        return "";
    }

    public String getEventName() {
        return eventName;
    }

    public String getSportName() {
        return sportName;
    }
}
