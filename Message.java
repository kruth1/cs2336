public class Message extends Event {
    //attributes
    private String messageContent; //actual content of message
    private int srcAddress; //source host address
    private int destAddress; //destination host address

    //constructor
    public Message(String messageContent, int srcAddress, int destAddress, int insertionTime) {
        super();
        this.messageContent = messageContent;
        this.srcAddress = srcAddress;
        this.destAddress = destAddress;
        this.insertionTime = insertionTime;
    }

    //getters
    public String getMessageContent() {
        return this.messageContent;
    }

    public int getSrcAddress() {
        return this.srcAddress;
    }

    public int getDestAddress() {
        return this.destAddress;
    }

    //setters

    @Override
    public void setInsertionTime(int currentTime) {
        this.insertionTime = currentTime;
        this.arrivalTime = this.insertionTime + 1; //assuming a delay ??
    }

    public void setNextHop(Host neighbor, int distance){
        this.arrivalTime = this.insertionTime + distance;
    }

    /**
     * Cancel the Event.
     * <br>
     * This occurs after the Event has been removed from the future event list, probably before the arrival time has
     * been reached.
     */
    @Override
    public void cancel(){
        System.out.println("Message cancelled: " + this.getId() + " originally set for arrival at " + this.getArrivalTime());
    }

    /**
     * Handle (or execute) the Event.
     * <br>
     * This occurs after the Event has been removed from the future event list, due to the arrival time being reached.
     * For example, if this Event represents a network message, then calling handle() will 'process' the message at the
     * destination host.  If the Event is a Timer, then this will execute whatever needs to be done upon timer expiry.
     */
    @Override
    public void handle(){
        

    }
}
