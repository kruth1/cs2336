public class EventNode {
    //attributes
    public Event event;
    public EventNode prev;
    public EventNode next;
    //constructor
    public EventNode(Event event){
        this.event = event;
        this.prev = null;
        this.next = null;
    }
}
