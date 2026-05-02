package Questions.MeetingScheduler.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Questions.MeetingScheduler.Entity.Room;
import Questions.MeetingScheduler.Entity.TimeSlot;

public class InMemoryRoomRepository implements RoomRepository {

    private final List<Room> rooms = new ArrayList<>();
   
    @Override
    public Optional<Room> findAvailableRoom(TimeSlot slot, int minCapacity) {
    return rooms.stream()
    .filter(r -> r.canFit(minCapacity) && r.isAvailable(slot))
    .findFirst();
    }
   
    @Override public void addRoom(Room r) { 
        rooms.add(r); 
    }
    
   }
   