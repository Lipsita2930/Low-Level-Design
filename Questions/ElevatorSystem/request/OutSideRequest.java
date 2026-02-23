package Questions.ElevatorSystem.request;

import Questions.ElevatorSystem.model.Direction;

public class OutSideRequest extends ElevatorRequest{

    protected final Direction direction;

    public OutSideRequest(int floor, Direction direction) {
            super(floor);
            this.direction = direction;     
    }

    public Direction getDirection() {
        return direction;
    }

    
    
        



    
}
