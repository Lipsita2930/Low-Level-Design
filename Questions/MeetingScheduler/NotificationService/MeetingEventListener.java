package Questions.MeetingScheduler.NotificationService;

import Questions.MeetingScheduler.Entity.Meeting;

public interface MeetingEventListener { //observer

    void onMeetingScheduled(Meeting meeting);
    void onMeetingCancelled(Meeting meeting);
    
}
