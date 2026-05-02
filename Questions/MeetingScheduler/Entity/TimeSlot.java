package Questions.MeetingScheduler.Entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeSlot {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end){

        if(end.isBefore(start)){
            throw new IllegalArgumentException("Start must be before end");
        }
        this.start = start;
        this.end = end;
    }

    public boolean overlap(TimeSlot other){
        return this.start.isBefore(other.end) && this.end.isAfter(other.start);
     }

    public Duration geDuration(){
        return Duration.between(start, end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

}
