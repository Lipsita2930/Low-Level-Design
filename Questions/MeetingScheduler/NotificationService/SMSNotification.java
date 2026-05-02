package Questions.MeetingScheduler.NotificationService;

import Questions.MeetingScheduler.Entity.Meeting;

class SMSNotificationService implements MeetingEventListener {

    @Override
    public void onMeetingScheduled(Meeting meeting) {
        meeting.getParticipants().forEach(
            p -> sendSMS(p.getName(), "Meeting Scheduled: " + meeting.getTitle())
        );
    }

    @Override
    public void onMeetingCancelled(Meeting meeting) {
        meeting.getParticipants().forEach(
            p -> sendSMS(p.getName(), "Meeting Cancelled: " + meeting.getTitle())
        );
    }

    private void sendSMS(String userName, String message) {
        System.out.println("SMS sent to " + userName + " --> " + message);
    }

    
}