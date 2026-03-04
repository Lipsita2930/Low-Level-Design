package Questions.ParkingLot.ParkingFeeStrategy;

import Questions.ParkingLot.Vehicle.VehicleType;

public class BasicFeeCalculationStrategy implements ParkingFeeStrategy {


    @Override
    public double calculatefee(int duration, DurationType durationType, VehicleType vehicleType) {

        switch(vehicleType){

            case VehicleType.CAR :
                    return durationType == DurationType.HOURS ? duration * 10 : duration * 10 * 24;
            case VehicleType.BIKE :
                    return durationType == DurationType.HOURS ? duration * 5 : duration * 5 * 24;
            default :
                    return durationType == DurationType.HOURS ? duration * 15 : duration * 15 * 24;
       }
      
    }
    
    
}
