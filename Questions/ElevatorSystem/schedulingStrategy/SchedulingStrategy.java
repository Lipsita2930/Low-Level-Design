package Questions.ElevatorSystem.schedulingStrategy;

import Questions.ElevatorSystem.model.Elevator;

public interface SchedulingStrategy {
    int getNextStop(Elevator elevator);
}