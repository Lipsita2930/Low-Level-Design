package Questions.ElevatorSystem.observer;

import Questions.ElevatorSystem.model.Elevator;

public interface ElevatorObserver {
    void update(Elevator elevator, String event, Object data);
}
