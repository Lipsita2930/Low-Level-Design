package Questions.NotificationService.Observer;

import java.util.List;
import Questions.NotificationService.Notification.Notification;
import Questions.NotificationService.NotificationStrategy.NotificationStrategy;

public class NotificationEngineObserver implements Observer{

    List<NotificationStrategy> notificationStrategy;

    public NotificationEngineObserver(List<NotificationStrategy> notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    @Override
    public void update(Notification notification) {
        
        for(NotificationStrategy strategy : notificationStrategy){
            strategy.sendNotification(notification.getContent());
        }

    }
    
}
