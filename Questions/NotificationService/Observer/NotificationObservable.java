package Questions.NotificationService.Observer;
import java.util.List;
import Questions.NotificationService.Notification.Notification;

public class NotificationObservable implements Observable {

    private Notification notification;
    private List<Observer> observers;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
        notifyObservers();
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for(Observer observer : observers){
            observer.update(notification);
        }
        
    }

    
    
}
