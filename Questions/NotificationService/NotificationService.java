package Questions.NotificationService;

import Questions.NotificationService.Notification.Notification;
import Questions.NotificationService.Observer.NotificationObservable;
import Questions.NotificationService.Observer.Observable;
import Questions.NotificationService.Observer.Observer;

public class NotificationService {
    
        private static volatile NotificationService instance;
        private Notification notification;
        private NotificationObservable observable;
    
        public static NotificationService getInstance() {
    
            if (instance == null) {
    
                synchronized (NotificationService.class) {
    
                    if (instance == null) {
                        instance = new NotificationService();
                    }
                }
            }
    
            return instance;
        }

 
    
        public Notification getNotification() {
            return notification;
        }

        public void setNotification(Notification notification) {
            this.notification = notification;
        }


        public Observable getObservable() {
            return observable;
        }


        public void setObservable(NotificationObservable observable) {
            this.observable = observable;
        }


        public void addObserver(Observer observer) {
            observable.addObserver(observer);
        }
    
        public void removeObserver(Observer observer) {
            observable.removeObserver(observer);
        }
    
        public void sendNotification(Notification notification) {
            observable.setNotification(notification);
        }
    }