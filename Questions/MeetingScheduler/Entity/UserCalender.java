package Questions.MeetingScheduler.Entity;

import java.util.ArrayList;
import java.util.List;

public class UserCalender {

    private String userId;
    private List<Meeting> meetings;

    UserCalender(String userId){
        this.userId = userId;
        this.meetings = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        if (!isAvailable(meeting.getTimeSlot())) {
            throw new IllegalStateException("Time slot already occupied for user: " + userId);
        }
    
        meetings.add(meeting);
    }

    public void removeMeeting(Meeting m){
        meetings.remove(m);
    }

    public List<Meeting> getMeetings(){
        return meetings;
    }

    public boolean isAvailable(TimeSlot requested){

        return meetings.stream()
                .filter(meeting -> meeting.getStatus() == Status.SCHEDULED)
                .map(Meeting::getTimeSlot)
                .noneMatch(existingSlot ->existingSlot.overlap(requested)
                );

    }
    
    
}
