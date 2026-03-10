package Questions.ElevatorSystem.model;



import Questions.ElevatorSystem.observer.ElevatorObserver;
import Questions.ElevatorSystem.request.ElevatorRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a single Elevator in the building.
 *
 * Responsibilities:
 *  - Tracks current floor, direction, and state
 *  - Maintains a queue of pending requests
 *  - Notifies observers on state/floor changes (Observer Pattern)
 *  - Moves to next stop as determined by the scheduling strategy
 *
 * Patterns used:
 *  - Observer: Elevator is the Subject — notifies ElevatorObserver instances
 */
public class Elevator {

    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private final int totalFloors;
    private List<ElevatorObserver> observers;

    // Queue of pending requests for this elevator
    private final Queue<ElevatorRequest> requests;

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    public Elevator(int id, int totalFloors) {
        this.id           = id;
        this.totalFloors  = totalFloors;
        this.currentFloor = 1;
        this.direction    = Direction.IDLE;
        this.state        = ElevatorState.IDLE;
        this.requests     = new LinkedList<>();
    }

    // -----------------------------------------------------------------------
    // Observer Pattern — Subject methods
    // -----------------------------------------------------------------------

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String event, Object data) {
        for (ElevatorObserver observer : observers) {
            observer.update(this, event, data);
        }
    }

    // -----------------------------------------------------------------------
    // Core Elevator Operations
    // -----------------------------------------------------------------------

    /**
     * Add a new request to this elevator's queue.
     */
    public void addRequest(ElevatorRequest request) {
        requests.add(request);
    }

    /**
     * Move the elevator to the given next floor.
     * Updates direction, floor, and notifies observers.
     */
    public void moveToNextStop(int nextFloor) {
        if (nextFloor == currentFloor) {
            completeArrival();
            return;
        }

        // Update direction
        setDirection(nextFloor > currentFloor ? Direction.UP : Direction.DOWN);

        // Update state to MOVING and notify
        setState(ElevatorState.MOVING);

        // Simulate moving floor by floor
        while (currentFloor != nextFloor) {
            currentFloor += (direction == Direction.UP) ? 1 : -1;
            notifyObservers("FLOOR_CHANGED", currentFloor);
        }

        completeArrival();
    }

    /**
     * Called when elevator arrives at a floor.
     * Sets state to STOPPED, then back to IDLE if no more requests.
     */
    public void completeArrival() {
        setState(ElevatorState.STOPPED);
        System.out.println("[Elevator " + id + "] Arrived at floor " + currentFloor
                + " | Direction: " + direction);

        if (requests.isEmpty()) {
            setDirection(Direction.IDLE);
            setState(ElevatorState.IDLE);
        }
    }

    // -----------------------------------------------------------------------
    // Getters & Setters
    // -----------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState newState) {
        this.state = newState;
        notifyObservers("STATE_CHANGED", newState);
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public Queue<ElevatorRequest> getRequestsQueue() {
        return requests;
    }

    public int getFloor() {
        return currentFloor;
    }

    @Override
    public String toString() {
        return "Elevator{id=" + id
                + ", currentFloor=" + currentFloor
                + ", direction=" + direction
                + ", state=" + state
                + ", pendingRequests=" + requests.size()
                + "}";
    }
}