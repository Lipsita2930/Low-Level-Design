package Questions.ElevatorSystem.observer;

import Questions.ElevatorSystem.model.Elevator;
import Questions.ElevatorSystem.model.ElevatorState;

public class DoorController implements ElevatorObserver {

    @Override
    public void update(Elevator elevator, String event, Object data) {
        if (event.equals("STATE_CHANGED")) {
            if (data == ElevatorState.STOPPED) {
                System.out.println("[Door] Doors OPENING");
            }
            if (data == ElevatorState.MOVING) {
                System.out.println("[Door] Doors CLOSING");
            }
        }
    }

}
