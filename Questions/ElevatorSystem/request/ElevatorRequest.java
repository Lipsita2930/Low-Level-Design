package Questions.ElevatorSystem.request;

public abstract class ElevatorRequest {
    
    protected int floor;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public ElevatorRequest(int floor) {
        this.floor = floor;
    }


}
