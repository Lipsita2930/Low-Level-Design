package Questions.MeetingScheduler.Repository;

import java.util.Optional;

import Questions.MeetingScheduler.Entity.Room;
import Questions.MeetingScheduler.Entity.TimeSlot;

public interface RoomRepository {
    Optional<Room> findAvailableRoom(TimeSlot slot, int minCapacity);
    void addRoom(Room room);
}
