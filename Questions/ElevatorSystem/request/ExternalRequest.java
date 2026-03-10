package Questions.ElevatorSystem.request;

import Questions.ElevatorSystem.ElevatorController;
import Questions.ElevatorSystem.model.Direction;

/**
 * Represents a request made from OUTSIDE the elevator (on a floor).
 * User presses UP or DOWN button on a floor.
 *
 * Pattern: Command — execute() tells the controller to assign an elevator to this floor.
 */
public class ExternalRequest extends ElevatorRequest {

    private final int floorNumber;
    private final Direction direction;

    public ExternalRequest(String requestId, int floorNumber, Direction direction) {
        super(requestId, RequestType.EXTERNAL);
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    /**
     * Command execution: instructs the controller to assign the best elevator
     * to pick up the user from this floor.
     * In full implementation, this would call ElevatorController.getInstance().processRequest(this).
     */
    @Override
    public void execute() {
        System.out.println("[ExternalRequest] Elevator requested at floor " + floorNumber +
                " going " + direction);
        ElevatorController.getInstance().processRequest(this);
    }

    // --- Getters ---

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * For scheduling strategies — external request floor = floor where button was pressed.
     */
    @Override
    public int getFloor() {
        return floorNumber;
    }

    @Override
    public String toString() {
        return "ExternalRequest{" +
                "floorNumber=" + floorNumber +
                ", direction=" + direction +
                ", requestId='" + getRequestId() + '\'' +
                '}';
    }
}