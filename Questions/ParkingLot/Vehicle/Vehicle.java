package Questions.ParkingLot.Vehicle;

import Questions.ParkingLot.ParkingFeeStrategy.DurationType;
import Questions.ParkingLot.ParkingFeeStrategy.ParkingFeeStrategy;

public abstract class Vehicle {

    private String lisencePlate;
    private VehicleType type;
    private ParkingFeeStrategy feeStrategy;


    public Vehicle(String lisencePlate, VehicleType type, ParkingFeeStrategy feeStrategy) {
        this.lisencePlate = lisencePlate;
        this.type = type;
        this.feeStrategy = feeStrategy;
    }

    public String getLisencePlate() {
        return lisencePlate;
    }
    public void setLisencePlate(String lisencePlate) {
        this.lisencePlate = lisencePlate;
    }
    public VehicleType getType() {
        return type;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }
    public ParkingFeeStrategy getFeeStrategy() {
        return feeStrategy;
    }
   
    public void setFeeStrategy(ParkingFeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public double calculateFee(int duration, DurationType type){
        return feeStrategy.calculatefee(duration, type, this.getType());
    }
   
}
