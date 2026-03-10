package Questions.ElevatorSystem.model;

import java.util.ArrayList;
import java.util.List;

import Questions.ElevatorSystem.ElevatorController;

public class Building {

    private final String name;
    private final int numberOfFloors;
    private final List<Floor> floors;
    private final ElevatorController elevatorController;

    public Building(String name, int numberOfFloors, int numberOfElevators) {
        this.name = name;
        this.numberOfFloors = numberOfFloors;

        // Build floor list
        this.floors = new ArrayList<>();
        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i));
        }

        // Get Singleton ElevatorController and initialize it
        this.elevatorController = ElevatorController.getInstance(numberOfElevators, numberOfFloors);
    }

    // --- Getters ---

    public String getName() {
        return name;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }

    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", numberOfFloors=" + numberOfFloors +
                ", elevators=" + elevatorController.getElevators().size() +
                '}';
    }
}