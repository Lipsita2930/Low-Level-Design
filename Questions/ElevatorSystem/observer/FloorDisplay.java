package Questions.ElevatorSystem.observer;

import Questions.ElevatorSystem.model.Elevator;

public class FloorDisplay implements ElevatorObserver {

    @Override
    public void update(Elevator elevator, String event, Object data) {
        if (event.equals("FLOOR_CHANGED")) {
            System.out.println("[Display] Elevator " + elevator.getId()
                    + " moved to floor " + data);
        }
        if (event.equals("STATE_CHANGED")) {
            System.out.println("[Display] Elevator " + elevator.getId()
                    + " state is now " + data);
        }
    }
}
