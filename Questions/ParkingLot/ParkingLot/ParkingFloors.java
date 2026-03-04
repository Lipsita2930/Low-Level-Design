package Questions.ParkingLot.ParkingLot;

import java.util.List;

import Questions.ParkingLot.ParkingSpot.ParkingSpot;
import Questions.ParkingLot.Vehicle.Vehicle;

public class ParkingFloors {

    private int floor;
    private List<ParkingSpot> ParkingSpots;

    public ParkingFloors(int floor, List<ParkingSpot> parkingSpots) {
        this.floor = floor;
        ParkingSpots = parkingSpots;
    }

    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public List<ParkingSpot> getParkingSpots() {
        return ParkingSpots;
    }
    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        ParkingSpots = parkingSpots;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle){

        for(ParkingSpot spot : ParkingSpots){
            spot.parkVehicle(vehicle);
            return spot;
        }

        return null;

    }

    public void removeVehicle(ParkingSpot spot, Vehicle vehicle){

        if (spot != null && spot.isOccupied() && spot.getVehicle().equals(vehicle)) {
            spot.removeVehicle();
            System.out.println("Vehicle " + vehicle.getType() +
                               " vacated spot #" + spot.getSpotID() + " on floor " + floor);
        } else {
            System.out.println("Invalid operation! Either the spot is vacant or the vehicle doesn't match.");
        }
    }


    public boolean containsSpot(ParkingSpot spot){
        return ParkingSpots.contains(spot);
    }

}


   




