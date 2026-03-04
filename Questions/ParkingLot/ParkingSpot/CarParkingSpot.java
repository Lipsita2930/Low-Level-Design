package Questions.ParkingLot.ParkingSpot;

import Questions.ParkingLot.Vehicle.Vehicle;
import Questions.ParkingLot.Vehicle.VehicleType;

public class CarParkingSpot extends ParkingSpot {

    public CarParkingSpot(int spotId, VehicleType spotType, Vehicle vehicle, boolean isOccupied) {
        super(spotId, spotType, vehicle, isOccupied);
    }

    @Override
    public boolean canParkVehicle(VehicleType type) {
        return type.equals(VehicleType.CAR);
    }
    
}
