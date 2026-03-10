package Questions.ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

import Questions.ElevatorSystem.model.Direction;
import Questions.ElevatorSystem.model.Elevator;
import Questions.ElevatorSystem.model.Floor;
import Questions.ElevatorSystem.request.ElevatorRequest;
import Questions.ElevatorSystem.request.ExternalRequest;
import Questions.ElevatorSystem.request.InternalRequest;
import Questions.ElevatorSystem.schedulingStrategy.LookSchedulingStrategy;
import Questions.ElevatorSystem.schedulingStrategy.SchedulingStrategy;


/**
 * ElevatorController — the brain of the elevator system.
 *
 * Responsibilities:
 *  - Manages all elevators in the building
 *  - Receives and dispatches requests to the best elevator
 *  - Uses a SchedulingStrategy to determine next stops
 *  - Steps the simulation forward
 *
 * Patterns used:
 *  - Singleton: Only one controller exists per building
 *  - Strategy: Scheduling algorithm is swappable at runtime
 *  - Factory: Use SchedulingStrategyFactory to set the algorithm (wired externally)
 */
public class ElevatorController {


    private final List<Elevator> elevators;
    private final List<Floor> floors;
    private SchedulingStrategy schedulingStrategy;


    // -----------------------------------------------------------------------
    // Singleton — single instance
    // -----------------------------------------------------------------------

    private static ElevatorController instance;

    private ElevatorController(int numberOfElevators, int numberOfFloors) {
        this.elevators = new ArrayList<>();
        this.floors    = new ArrayList<>();

        // Default strategy — LOOK is the best for real-world use
        this.schedulingStrategy = new LookSchedulingStrategy();

        // Initialize elevators
        for (int i = 1; i <= numberOfElevators; i++) {
            Elevator elevator = new Elevator(i, numberOfFloors);
            elevators.add(elevator);
        }

        // Initialize floors
        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    /**
     * Get or create the singleton ElevatorController instance.
     */
    public static ElevatorController getInstance(int numberOfElevators, int numberOfFloors) {
        if (instance == null) {
            instance = new ElevatorController(numberOfElevators, numberOfFloors);
        }
        return instance;
    }

    /**
     * Get existing singleton instance (must be initialized first).
     */
    public static ElevatorController getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                "ElevatorController not initialized. Call getInstance(elevators, floors) first."
            );
        }
        return instance;
    }




    // -----------------------------------------------------------------------
    // Strategy Pattern — swap algorithm at runtime
    // -----------------------------------------------------------------------

    public void setSchedulingStrategy(SchedulingStrategy strategy) {
        this.schedulingStrategy = strategy;
        System.out.println("[Controller] Scheduling strategy changed to: "
                + strategy.getClass().getSimpleName());
    }

    // -----------------------------------------------------------------------
    // Request Handling
    // -----------------------------------------------------------------------

   // ADD this new method — this is what execute() calls into
    public void processRequest(ElevatorRequest request) {
        if (request instanceof ExternalRequest ext) {
            Elevator best = findBestElevator(ext);
            best.addRequest(request);
            System.out.println("[Controller] ExternalRequest assigned to Elevator " + best.getId());
        } else if (request instanceof InternalRequest inr) {
            Elevator elevator = getElevatorById(inr.getElevatorId());
            if (elevator != null) elevator.addRequest(request);
            System.out.println("[Controller] InternalRequest for Elevator " + inr.getElevatorId());
        }
    }

    // SLIM DOWN requestElevator — just create and fire, no manual dispatch
    public void requestElevator(int floorNumber, Direction direction) {
        String requestId = "EXT-" + floorNumber + "-" + direction + "-" + System.currentTimeMillis();
        new ExternalRequest(requestId, floorNumber, direction).execute();  // execute() does the work now
    }

    // SLIM DOWN requestFloor — same
    public void requestFloor(int elevatorId, int floorNumber) {
        String requestId = "INT-" + elevatorId + "-" + floorNumber + "-" + System.currentTimeMillis();
        new InternalRequest(requestId, elevatorId, floorNumber).execute();  // execute() does the work now
    }

    /**
     * Find the best elevator to serve an external request.
     * Picks the idle elevator closest to the requested floor,
     * or the elevator already heading in the same direction.
     */
    private Elevator findBestElevator(ExternalRequest request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloorNumber());

            boolean isIdle = elevator.getDirection() == Direction.IDLE;
            boolean sameDirection = elevator.getDirection() == request.getDirection();
            boolean headingToward = (request.getDirection() == Direction.UP
                    && elevator.getCurrentFloor() <= request.getFloorNumber())
                    || (request.getDirection() == Direction.DOWN
                    && elevator.getCurrentFloor() >= request.getFloorNumber());

            // Prefer idle or same-direction elevators heading toward the floor
            if ((isIdle || (sameDirection && headingToward)) && distance < minDistance) {
                minDistance = distance;
                best = elevator;
            }
        }

        // Fallback — just pick the closest elevator
        if (best == null) {
            for (Elevator elevator : elevators) {
                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloorNumber());
                if (distance < minDistance) {
                    minDistance = distance;
                    best = elevator;
                }
            }
        }

        return best;
    }

    // -----------------------------------------------------------------------
    // Simulation Step
    // -----------------------------------------------------------------------

    /**
     * Advance the simulation by one step.
     * Each elevator moves to its next stop as determined by the strategy.
     */
    public void step() {
        for (Elevator elevator : elevators) {
            if (!elevator.getRequestsQueue().isEmpty()) {
                int nextStop = schedulingStrategy.getNextStop(elevator);
                elevator.moveToNextStop(nextStop);
            }
        }
    }

    // -----------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Elevator getElevatorById(int elevatorId) {
        return elevators.stream()
                .filter(e -> e.getId() == elevatorId)
                .findFirst()
                .orElse(null);
    }

    public void setCurrentElevator(int elevatorId) {
        // Used to set focus elevator for display purposes
        System.out.println("[Controller] Focus set to Elevator " + elevatorId);
    }

    @Override
    public String toString() {
        return "ElevatorController{elevators=" + elevators.size()
                + ", floors=" + floors.size()
                + ", strategy=" + schedulingStrategy.getClass().getSimpleName()
                + "}";
    }
}