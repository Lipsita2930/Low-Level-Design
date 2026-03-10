package Questions.ElevatorSystem;

import java.util.List;

import Questions.ElevatorSystem.model.Building;
import Questions.ElevatorSystem.model.Direction;
import Questions.ElevatorSystem.model.Elevator;

/**
 * ElevatorSystemMain — Entry point for the Elevator System simulation.
 *
 * Wires together:
 *  - Building (creates floors + ElevatorController singleton)
 *  - ElevatorController (manages elevators + scheduling strategy)
 *  - Requests (Internal + External via Command pattern)
 *  - Scheduling Strategy (swappable via Strategy pattern)
 *
 * Simulation Scenarios:
 *  1. External request — user presses UP on floor 3
 *  2. Internal request — user inside elevator selects floor 7
 *  3. Multiple requests — tests LOOK strategy ordering
 *  4. Strategy swap at runtime — switch from LOOK to FCFS
 */
public class ElevatorSystemMain {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("       ELEVATOR SYSTEM SIMULATION START       ");
        System.out.println("==============================================\n");

        // -----------------------------------------------------------------------
        // 1. Initialize Building
        //    - 10 floors, 2 elevators
        //    - Building creates ElevatorController singleton internally
        // -----------------------------------------------------------------------
        Building building = new Building("TechTower", 10, 2);
        ElevatorController controller = building.getElevatorController();

        System.out.println("Building   : " + building.getName());
        System.out.println("Floors     : " + building.getNumberOfFloors());
        System.out.println("Elevators  : " + controller.getElevators().size());
        System.out.println("Strategy   : LookSchedulingStrategy (default)");
        System.out.println();

        // -----------------------------------------------------------------------
        // 2. Scenario 1 — External Request
        //    User on floor 3 presses UP button
        // -----------------------------------------------------------------------
        System.out.println("----------------------------------------------");
        System.out.println("SCENARIO 1: External Request — Floor 3 UP");
        System.out.println("----------------------------------------------");
        controller.requestElevator(3, Direction.UP);
        controller.step();
        printElevatorStatus(controller);

        // -----------------------------------------------------------------------
        // 3. Scenario 2 — Internal Request
        //    User inside Elevator 1 selects floor 7
        // -----------------------------------------------------------------------
        System.out.println("\n----------------------------------------------");
        System.out.println("SCENARIO 2: Internal Request — Elevator 1 to Floor 7");
        System.out.println("----------------------------------------------");
        controller.requestFloor(1, 7);
        controller.step();
        printElevatorStatus(controller);

        // -----------------------------------------------------------------------
        // 4. Scenario 3 — Multiple Requests (tests LOOK ordering)
        //    Elevator 2: requests from floors 6, 2, 8 in that order
        //    LOOK should serve: 6 → 8 (UP sweep), then 2 (DOWN sweep)
        // -----------------------------------------------------------------------
        System.out.println("\n----------------------------------------------");
        System.out.println("SCENARIO 3: Multiple Requests — LOOK Strategy");
        System.out.println("----------------------------------------------");
        controller.requestElevator(6, Direction.UP);
        controller.requestElevator(2, Direction.DOWN);
        controller.requestElevator(8, Direction.UP);

        // Step multiple times to serve all requests
        System.out.println("\n-- Step 1 --");
        controller.step();
        printElevatorStatus(controller);

        System.out.println("\n-- Step 2 --");
        controller.step();
        printElevatorStatus(controller);

        System.out.println("\n-- Step 3 --");
        controller.step();
        printElevatorStatus(controller);

        // -----------------------------------------------------------------------
        // 5. Scenario 4 — Strategy Swap at Runtime
        //    Switch from LOOK to FCFS and observe behavior difference
        // -----------------------------------------------------------------------
        System.out.println("\n----------------------------------------------");
        System.out.println("SCENARIO 4: Runtime Strategy Swap — LOOK → FCFS");
        System.out.println("----------------------------------------------");
        // controller.setSchedulingStrategy(new FCFSSchedulingStrategy());

        controller.requestElevator(5, Direction.DOWN);
        controller.requestElevator(9, Direction.UP);
        controller.step();
        printElevatorStatus(controller);

        // Switch to SCAN
        System.out.println("\n-- Switching to SCAN --");
        // controller.setSchedulingStrategy(new ScanSchedulingStrategy());
        controller.requestElevator(4, Direction.UP);
        controller.step();
        printElevatorStatus(controller);

        System.out.println("\n==============================================");
        System.out.println("       ELEVATOR SYSTEM SIMULATION END         ");
        System.out.println("==============================================");
    }

    // -----------------------------------------------------------------------
    // Helper — print status of all elevators
    // -----------------------------------------------------------------------

    private static void printElevatorStatus(ElevatorController controller) {
        System.out.println("\n[STATUS]");
        List<Elevator> elevators = controller.getElevators();
        for (Elevator elevator : elevators) {
            System.out.println("  " + elevator);
        }
    }
}