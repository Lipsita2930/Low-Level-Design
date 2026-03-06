package Questions.NotificationService;

import java.util.Arrays;
import Questions.NotificationService.DynamicNotification.SignatureDecorator;
import Questions.NotificationService.DynamicNotification.TimestampDecorator;
import Questions.NotificationService.Notification.Notification;
import Questions.NotificationService.Notification.SimpleTextNotification;
import Questions.NotificationService.NotificationStrategy.EmailStrategy;
import Questions.NotificationService.NotificationStrategy.SmsStrategy;
import Questions.NotificationService.Observer.LoggerObserver;
import Questions.NotificationService.Observer.NotificationEngineObserver;

public class client {

    public static void main(String[] args) {

        NotificationService service =
                NotificationService.getInstance();

        // observers
        LoggerObserver logger = new LoggerObserver();

        NotificationEngineObserver engine =
                new NotificationEngineObserver(
                        Arrays.asList(
                                new EmailStrategy(),
                                new SmsStrategy()
                        )
                );

        service.addObserver(logger);
        service.addObserver(engine);

        Notification notification = new SimpleTextNotification("Order placed");

        notification = new TimestampDecorator(notification);
        notification = new SignatureDecorator(notification, "sign");

        
        service.sendNotification(notification);
    }
}