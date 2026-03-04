package Questions.ParkingLot.ParkingLot;

import java.util.List;

import Questions.ParkingLot.ParkingSpot.ParkingSpot;
import Questions.ParkingLot.Vehicle.Vehicle;

public class ParkingLot {

    private static ParkingLot instance;
    private List<ParkingFloors> parkingFloors;

    private ParkingLot(List<ParkingFloors> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public static ParkingLot getParkingInstance(List<ParkingFloors> parkingFloors){

        if(instance == null){
            instance = new ParkingLot(parkingFloors);
        }

        return instance;
    }

     public ParkingSpot parkVehicle(Vehicle vehicle) {
            for (ParkingFloors floor : parkingFloors) {
               ParkingSpot spot = floor.parkVehicle(vehicle);
               if(spot != null){
                return spot;
               }
            }
            return null;
        }
    
        
    public void vacateSpot(ParkingSpot spot, Vehicle vehicle) {

            for (ParkingFloors floor : parkingFloors) {
                if (floor.containsSpot(spot)) {
                    floor.removeVehicle(spot, vehicle);
                    return;
                }
            }
            System.out.println("Spot not found in any floor.");
        }
    
}
