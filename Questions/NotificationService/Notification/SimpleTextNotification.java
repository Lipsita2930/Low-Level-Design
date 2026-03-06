package Questions.NotificationService.Notification;

public class SimpleTextNotification implements Notification {

    private String text;

    public SimpleTextNotification(String text){
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }
    
}
