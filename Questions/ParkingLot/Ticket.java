package Questions.ParkingLot;

import java.time.LocalDateTime;

import Questions.ParkingLot.ParkingSpot.ParkingSpot;
import Questions.ParkingLot.Vehicle.Vehicle;

public class Ticket {
    
    private static int counter = 0;

    private final int ticketId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private final ParkingSpot spot;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = ++counter;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
        this.spot = spot;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    @Override
    public String toString() {
        return "🎫 Ticket #" + ticketId + 
               " | Vehicle: " + vehicle.getLisencePlate() + 
               " | Spot: " + spot + 
               " | Entry: " + entryTime;
    }
}
