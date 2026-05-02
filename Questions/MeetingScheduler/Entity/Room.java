package Questions.MeetingScheduler.Entity;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String id;
    private String name;
    private int capacity; 
    private List<TimeSlot> bookedSlot;

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.bookedSlot = new ArrayList<>();
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public List<TimeSlot> getBookedSlot() {
        return bookedSlot;
    }
    public void setBookedSlot(List<TimeSlot> bookedSlot) {
        this.bookedSlot = bookedSlot;
    }

    public boolean isAvailable(TimeSlot requested){
        return bookedSlot.stream().noneMatch(s -> s.overlap(requested));
    }

    public boolean canFit(int requestedCapacaity){
        return requestedCapacaity <= capacity;
    }

    public synchronized void book(TimeSlot slot){

        if(!isAvailable(slot)){
            throw new IllegalStateException();
        }
        bookedSlot.add(slot);
    }

    public synchronized void unbook(TimeSlot slot){
        bookedSlot.remove(slot);
    }
  

    
}
