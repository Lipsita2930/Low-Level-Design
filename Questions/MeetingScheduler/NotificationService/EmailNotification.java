package Questions.MeetingScheduler.NotificationService;

import Questions.MeetingScheduler.Entity.Meeting;

public class EmailNotification implements MeetingEventListener{

    @Override
    public void onMeetingScheduled(Meeting meeting) {
       meeting.getParticipants().forEach(p -> sendEmail(p.getEmail(), "Meeting Scheduled: " + meeting.getTitle()));
    }

    @Override
    public void onMeetingCancelled(Meeting meeting) {
        meeting.getParticipants().forEach(p -> sendEmail(p.getEmail(), "Meeting Scheduled: "  + meeting.getTitle()));
    }

    private void sendEmail(String email, String message) {
        System.out.println("Email sent to " + email + " --> " + message);
    }
    
}
