package Questions.NotificationService.NotificationStrategy;

public class EmailStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(String content) {
        System.out.println("Sending EMAIL: " + content);
    }
}