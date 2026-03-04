package Questions.ParkingLot.ParkingFeeStrategy;

import Questions.ParkingLot.Vehicle.VehicleType;

public interface ParkingFeeStrategy {
    public double calculatefee(int duration, DurationType durationType, VehicleType type);
}