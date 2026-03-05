package Pattern.Behavioral.Observer;


public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
    
}

