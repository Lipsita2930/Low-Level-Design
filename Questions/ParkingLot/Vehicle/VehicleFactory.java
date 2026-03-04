package Questions.ParkingLot.Vehicle;

import Questions.ParkingLot.ParkingFeeStrategy.ParkingFeeStrategy;

public class VehicleFactory {


    public  static Vehicle createVehicle(VehicleType vehicleType, String lisencePlate, ParkingFeeStrategy feeStrategy){

        switch (vehicleType) {
            case VehicleType.BIKE:
                return new BIKE(lisencePlate, vehicleType, feeStrategy);
            case VehicleType.CAR:
                return new CAR(lisencePlate, vehicleType, feeStrategy);
            default:
                return new OTHER(lisencePlate, vehicleType, feeStrategy);
        }

    }
    
}
