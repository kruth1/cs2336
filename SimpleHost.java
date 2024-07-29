public class SimpleHost extends Host {

    public SimpleHost() {
        super();
    }

    /**
     * This is called when a host receives a Message event from a neighboring host.
     *
     * @param msg the Message event received
     */
    @Override
    protected void receive(Message msg){

        if("ping".equals(msg.getMessageContent())){
            Message response = new Message ("pong", getHostAddress(), msg.getSrcAddress(), getCurrentTime());
            sendToNeighbor(response);
        } else if ("pong".equals(msg.getMessageContent())){
            int rtt = getCurrentTime() - msg.getInsertionTime();
            System.out.println("RTT to  " + msg.getSrcAddress() + " is " + rtt + " time units");
        }
    }


    /**
     * This is called after a Timer event expires, and enables you to write code to do something upon timer
     * expiry.  A timer expires when the duration set for the timer is reached.
     *
     * @param eventId the event id of the Timer event which expired
     */
    @Override
    protected void timerExpired(int eventId){
    System.out.println("Timer expired: " + eventId + " at " + getCurrentTime());
    }

    /**
     * This is called after a Timer event is cancelled, and enables you to write code to do something upon timer
     * cancellation.
     *
     * @param eventId the event id of the Timer event which was cancelled
     */
    @Override
    protected void timerCancelled(int eventId){
        System.out.println("Timer cancelled: " + eventId + " at " + getCurrentTime());
    }

    /**
     *
     * @param destAddr destination address of host to send ping requests.
     * @param interval amount of time to wait between sending ping requests.
     * @param duration total amount of time in which the host will send ping requests.
     */

    public void sendPings(int destAddr, int interval, int duration){
        int count = 0;
        while (count * interval <= duration) {
            int sendTime = getCurrentTime() + (count * interval);
            Timer timer = new Timer(sendTime, this) {
                @Override
                public void handle() {
                    Message ping = new Message("ping", getHostAddress(), destAddr, getCurrentTime());
                    sendToNeighbor(ping);
                    System.out.println("Ping sent to " + destAddr + " at " + getCurrentTime());
                }
            };
            this.newTimer(interval);
            count++;
        }
    }

}
