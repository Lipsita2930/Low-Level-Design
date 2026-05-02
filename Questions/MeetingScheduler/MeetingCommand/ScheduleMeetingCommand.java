package Questions.MeetingScheduler.MeetingCommand;

import java.util.List;

import Questions.MeetingScheduler.Entity.Meeting;
import Questions.MeetingScheduler.NotificationService.MeetingEventListener;
import Questions.MeetingScheduler.Repository.MeetingRepository;

public class ScheduleMeetingCommand  implements MeetingCommand{

    private Meeting meeting;
    private MeetingRepository repo;
    private List<MeetingEventListener> listeners;

    
    public ScheduleMeetingCommand(Meeting meeting, MeetingRepository repo, List<MeetingEventListener> listeners) {
        this.meeting = meeting;
        this.repo = repo;
        this.listeners = listeners;
    }

    @Override
    public void execute() {

        repo.save(meeting);
        meeting.getParticipants().forEach(u -> u.getCalender().addMeeting(meeting));
        meeting.getRoom().book(meeting.getTimeSlot());
        listeners.forEach(l -> l.onMeetingScheduled(meeting));
        
    }

    @Override
    public void undo() {
        meeting.getParticipants().forEach(u -> u.getCalender().removeMeeting(meeting));
        meeting.getRoom().unbook(meeting.getTimeSlot());
        listeners.forEach(l -> l.onMeetingCancelled(meeting));
        repo.delete(meeting.getId());
    }


    
}
