package Questions.NotificationService.DynamicNotification;

import Questions.NotificationService.Notification.Notification;

public abstract class NotificationDecorator implements Notification {

    protected Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String getContent() {
        return notification.getContent();
    }
}
