package Questions.ElevatorSystem.model;

import java.util.TreeSet;

public class Elevator {

    private int id;
    private int currentFLOOR;
    private Direction currDirection;
    private ElevatorDoorState doorState;
    private TreeSet<Integer> internalRequest;
    private final int maxFloor;
    private final int minFloor;

    public Elevator(int id, int currentFLOOR, Direction currDirection, ElevatorDoorState doorState,
        TreeSet<Integer> internalRequest, int maxFloor, int minFloor) {
    this.id = id;
    this.currentFLOOR = 0;
    this.currDirection = Direction.IDLE;
    this.doorState = ElevatorDoorState.CLOSE;
    this.internalRequest = internalRequest;
    this.maxFloor = maxFloor;
    this.minFloor = minFloor;
   }


   public void addInternalRequest(int floor){
        addInternalRequest(floor, true);
   }

   public void addInternalRequest(int floor, boolean isInternal){

        if(floor >= minFloor && floor <= maxFloor){

                if(currDirection.equals(Direction.IDLE)){

                    if(floor > currentFLOOR){
                        currDirection = Direction.UP;
                    }
                    if(floor < currentFLOOR){
                        currDirection = Direction.DOWN;
                    }
                }

                if (isInternal) {
                    System.out.println("📥 Elevator " + id + " received INSIDE request to floor " + floor);
                } else {
                    System.out.println("📤 Elevator " + id + " received EXTERNAL assignment to floor " + floor);
                }
            } 
            else {
                System.out.println("⚠️ Invalid floor " + floor + " ignored by Elevator " + id);
            }

        }

    

   

    



    
}
