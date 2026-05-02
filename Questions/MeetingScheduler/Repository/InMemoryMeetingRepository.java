package Questions.MeetingScheduler.Repository;

import java.util.*;
import java.util.concurrent.*;

import Questions.MeetingScheduler.Entity.Meeting;

public class InMemoryMeetingRepository implements MeetingRepository {

 // ConcurrentHashMap — thread-safe without full method synchronization
 private final Map<String, Meeting> store = new ConcurrentHashMap<>();

 @Override public void save(Meeting m) { store.put(m.getId(), m); }
 @Override public Optional<Meeting> findById(String id) { return Optional.ofNullable(store.get(id)); }
 @Override public void delete(String id) { store.remove(id); }
 @Override public List<Meeting> findAll() { return new ArrayList<>(store.values()); }


}
