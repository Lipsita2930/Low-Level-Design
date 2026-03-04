package Questions.ParkingLot.ParkingFeeStrategy;

import Questions.ParkingLot.Vehicle.VehicleType;

public class PremiumFeeCalculationStrategy  implements ParkingFeeStrategy{

     @Override
    public double calculatefee(int duration, DurationType durationType, VehicleType vehicleType) {

        switch(vehicleType){

            case VehicleType.CAR :
                    return durationType == DurationType.HOURS ? duration * 20 : duration * 20 * 24;
            case VehicleType.BIKE :
                    return durationType == DurationType.HOURS ? duration * 15 : duration * 15 * 24;
            default :
                    return durationType == DurationType.HOURS ? duration * 10 : duration * 10 * 24;
       }
      
    }
    
}
