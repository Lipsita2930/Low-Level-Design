package Questions.ElevatorSystem.schedulingStrategy;

import java.util.PriorityQueue;
import java.util.Queue;

import Questions.ElevatorSystem.model.Direction;
import Questions.ElevatorSystem.model.Elevator;
import Questions.ElevatorSystem.request.ElevatorRequest;

/**
 * LOOK Scheduling Strategy.
 *
 * Improvement over SCAN — the elevator only travels as far as the
 * last request in each direction, instead of going to the building's
 * last floor. This avoids unnecessary travel.
 *
 * How it works:
 *  - Moving UP: serve all floors above current floor, then reverse
 *  - Moving DOWN: serve all floors below current floor, then reverse
 *  - IDLE: pick nearest request, set direction accordingly
 *
 * Best for: Real-world elevators, high-traffic buildings.
 * This is the recommended default strategy.
 */
public class LookSchedulingStrategy implements SchedulingStrategy {

    @Override
    public int getNextStop(Elevator elevator) {
        Direction elevatorDirection = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requests = elevator.getRequestsQueue();

        // No requests — stay idle on current floor
        if (requests.isEmpty()) {
            elevator.setDirection(Direction.IDLE);
            return currentFloor;
        }

        // Min-heap: upward requests sorted by floor ascending
        PriorityQueue<ElevatorRequest> upQueue =
            new PriorityQueue<>((a, b) -> a.getFloor() - b.getFloor());

        // Max-heap: downward requests sorted by floor descending
        PriorityQueue<ElevatorRequest> downQueue =
            new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());

        // Partition requests relative to current floor
        for (ElevatorRequest request : requests) {
            if (request.getFloor() > currentFloor) {
                upQueue.add(request);
            } else {
                downQueue.add(request);
            }
        }

        // Handle IDLE state
        if (elevatorDirection == Direction.IDLE) {
            return handleIdleState(elevator, upQueue, downQueue, currentFloor);
        }

        // Moving UP — serve upward requests; if none left, switch to DOWN
        if (elevatorDirection == Direction.UP) {
            return !upQueue.isEmpty()
                ? upQueue.poll().getFloor()
                : switchToDown(elevator, downQueue);
        }

        // Moving DOWN — serve downward requests; if none left, switch to UP
        return !downQueue.isEmpty()
            ? downQueue.poll().getFloor()
            : switchToUp(elevator, upQueue);
    }

    /**
     * IDLE state — pick the nearest request and set direction.
     */
    private int handleIdleState(
            Elevator elevator,
            PriorityQueue<ElevatorRequest> upQueue,
            PriorityQueue<ElevatorRequest> downQueue,
            int currentFloor) {

        int nearestUp   = upQueue.isEmpty()  ? Integer.MAX_VALUE : upQueue.peek().getFloor();
        int nearestDown = downQueue.isEmpty() ? Integer.MIN_VALUE : downQueue.peek().getFloor();

        if (upQueue.isEmpty()) {
            elevator.setDirection(Direction.DOWN);
            return downQueue.poll().getFloor();
        }

        if (downQueue.isEmpty()) {
            elevator.setDirection(Direction.UP);
            return upQueue.poll().getFloor();
        }

        // Both have requests — go to the closer one
        boolean upIsCloser = Math.abs(nearestUp - currentFloor)
                           <= Math.abs(nearestDown - currentFloor);

        if (upIsCloser) {
            elevator.setDirection(Direction.UP);
            return upQueue.poll().getFloor();
        } else {
            elevator.setDirection(Direction.DOWN);
            return downQueue.poll().getFloor();
        }
    }

    /**
     * No more upward requests — switch direction to DOWN.
     * LOOK advantage: we stop here, not at the top floor.
     */
    private int switchToDown(Elevator elevator, PriorityQueue<ElevatorRequest> downQueue) {
        elevator.setDirection(Direction.DOWN);
        return downQueue.isEmpty()
            ? elevator.getCurrentFloor()
            : downQueue.poll().getFloor();
    }

    /**
     * No more downward requests — switch direction to UP.
     * LOOK advantage: we stop here, not at the bottom floor.
     */
    private int switchToUp(Elevator elevator, PriorityQueue<ElevatorRequest> upQueue) {
        elevator.setDirection(Direction.UP);
        return upQueue.isEmpty()
            ? elevator.getCurrentFloor()
            : upQueue.poll().getFloor();
    }
}
