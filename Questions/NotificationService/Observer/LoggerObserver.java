package Questions.NotificationService.Observer;

import Questions.NotificationService.Notification.Notification;

public class LoggerObserver implements Observer {

    @Override
    public void update(Notification notification) {
        System.out.println("Logging Notification: " 
                + notification.getContent());
    }

}
