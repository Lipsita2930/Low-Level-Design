package Questions.MeetingScheduler.SchedulingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import Questions.MeetingScheduler.Entity.TimeSlot;
import Questions.MeetingScheduler.Entity.User;

public class EarliestAvailableSlotStrategy implements SchedulingStrategy {

    private static final int BUSINESS_START = 9;   // 9 AM
    private static final int BUSINESS_END = 17;    // 5 PM

    @Override
    public Optional<TimeSlot> findAvailableSlot(
            List<User> participants,
            Duration duration,
            LocalDateTime from
    ) {

        LocalDateTime current = from;
        LocalDateTime end = from.plusDays(7);

        while (current.isBefore(end)) {

            // Before business hours
            if (current.getHour() < BUSINESS_START) {
                current = current.withHour(BUSINESS_START)
                                 .withMinute(0);
            }

            // After business hours → move to next day
            if (current.getHour() >= BUSINESS_END) {
                current = current.plusDays(1)
                                 .withHour(BUSINESS_START)
                                 .withMinute(0);
                continue;
            }

            // Create candidate slot
            TimeSlot candidate = new TimeSlot(
                    current,
                    current.plus(duration)
            );

            // Ensure meeting ends before business close
            if (candidate.getEnd().getHour() <= BUSINESS_END &&
                    isSlotAvailable(participants, candidate)) {

                return Optional.of(candidate);
            }

            // Move to next 30-min slot
            current = current.plusMinutes(30);
        }

        return Optional.empty();
    }

    private boolean isSlotAvailable(
            List<User> users,
            TimeSlot candidate
    ) {

        for (User user : users) {

            if (!user.getCalender().isAvailable(candidate)) {
                return false;
            }
        }

        return true;
    }
}