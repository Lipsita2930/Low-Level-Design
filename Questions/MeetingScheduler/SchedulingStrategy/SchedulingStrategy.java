package Questions.MeetingScheduler.SchedulingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import Questions.MeetingScheduler.Entity.User;
import Questions.MeetingScheduler.Entity.TimeSlot;

public interface SchedulingStrategy {
    Optional<TimeSlot> findAvailableSlot(List<User> participants, Duration duration, LocalDateTime from); // The optional is safe to handle null Timeslot
}