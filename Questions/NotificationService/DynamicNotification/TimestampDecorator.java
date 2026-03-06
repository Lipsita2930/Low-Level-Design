package Questions.NotificationService.DynamicNotification;

import java.time.LocalDateTime;

import Questions.NotificationService.Notification.Notification;

public class TimestampDecorator extends NotificationDecorator {

    public TimestampDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public String getContent() {
        return notification.getContent() + 
               " | Time: " + LocalDateTime.now();
    }

}