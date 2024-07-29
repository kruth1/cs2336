public class LinkedEventList implements FutureEventList {
    private EventNode head;
    private int size;

    //create head node
    public LinkedEventList(){
        this.head = null;
        this.size = 0;
    }

    //remove and return the event at the front of the list, which is the head node
    public Event removeFirst(){
        if (head == null) return null;
        EventNode temp = head;
        head = head.next;
        if (head != null){
            head.prev = null;
        }
        size--;
        return temp.event;
    }

    //iterate through list to find and remove the specified event
    public boolean remove(Event e){
        EventNode current = head;
        while (current != null){
            if (current.event.equals(e)){
                if (current.prev != null){
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null){
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //insert an event in to the list based on event's arrival time

    public void insert (Event e){
        EventNode newNode = new EventNode(e);
        if(head == null || e.getArrivalTime() < head.event.getArrivalTime()) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
        } else {
            EventNode current = head;
            while (current.next != null && current.next.event.getArrivalTime() <= e.getArrivalTime()) {
                current = current.next;
            }
            newNode.next = current.next;
            if (current.next != null) current.next.prev = newNode;
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
    }

    //returns number of elements in list
    public int size(){
        return size;
    }

    //capacity same as size bc linked list dynamically resizes
    public int capacity(){
        return size;
    }

    //returns arrival time of the last event, assuming last event is at the end of the list
    public int getSimulationTime(){
        if (head == null){
            return 0;
        }
        EventNode current = head;
        while(current.next != null){
            current = current.next;
        }
        return current.event.getArrivalTime();
    }
}
