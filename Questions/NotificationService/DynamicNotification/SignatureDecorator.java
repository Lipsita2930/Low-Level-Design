package Questions.NotificationService.DynamicNotification;

import java.time.LocalDateTime;

import Questions.NotificationService.Notification.Notification;

public class SignatureDecorator extends NotificationDecorator {

    private String sign;

    public SignatureDecorator(Notification notification, String sign) {
        super(notification);
        this.sign = sign;
    }


     @Override
    public String getContent() {
        return notification.getContent() + sign;
    }

    
    
}
