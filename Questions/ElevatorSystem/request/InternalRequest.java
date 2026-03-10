package Questions.ElevatorSystem.request;

import Questions.ElevatorSystem.ElevatorController;

public class InternalRequest extends ElevatorRequest {

    private final int elevatorId;
    private final int destinationFloor;

    public InternalRequest(String requestId, int elevatorId, int destinationFloor) {
        super(requestId, RequestType.INTERNAL);
        this.elevatorId = elevatorId;
        this.destinationFloor = destinationFloor;
    }

    @Override
    public void execute() {
        System.out.println("[InternalRequest] Elevator " + elevatorId +
                " requested to go to floor " + destinationFloor);
        ElevatorController.getInstance().processRequest(this);
    }


    public int getElevatorId() {
        return elevatorId;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    @Override
    public int getFloor() {
        return destinationFloor;
    }

    @Override
    public String toString() {
        return "InternalRequest{" +
                "elevatorId=" + elevatorId +
                ", destinationFloor=" + destinationFloor +
                ", requestId='" + getRequestId() + '\'' +
                '}';
    }
}