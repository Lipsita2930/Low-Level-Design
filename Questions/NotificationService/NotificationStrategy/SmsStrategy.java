package Questions.NotificationService.NotificationStrategy;

public class SmsStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(String content) {
        System.out.println("Sending SMS: " + content);
    }
}