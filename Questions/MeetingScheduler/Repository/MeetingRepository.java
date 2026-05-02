package Questions.MeetingScheduler.Repository;

import java.util.List;
import java.util.Optional;

import Questions.MeetingScheduler.Entity.Meeting;



public interface MeetingRepository {
    void save(Meeting meeting);
    Optional<Meeting> findById(String id);
    void delete(String id);
    List<Meeting> findAll();
}

