package Questions.ParkingLot.ParkingSpot;

import Questions.ParkingLot.Vehicle.Vehicle;
import Questions.ParkingLot.Vehicle.VehicleType;

public abstract class ParkingSpot {

    private int spotId;
    private VehicleType spotType;
    private Vehicle vehicle;
    private boolean isOccupied;

    public ParkingSpot(int spotId, VehicleType spotType, Vehicle vehicle, boolean isOccupied) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.vehicle = vehicle;
        this.isOccupied = isOccupied;
    }

    public int getSpotID() {
        return spotId;
    }
    public void setSpotID(int spotId) {
        this.spotId = spotId;
    }

    public VehicleType getSpotType() {
        return spotType;
    }
    public void setSpotType(VehicleType spotType) {
        this.spotType = spotType;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public abstract boolean canParkVehicle(VehicleType type);

    public void parkVehicle(Vehicle vehicle){

        if(!canParkVehicle(vehicle.getType())){
            throw new IllegalArgumentException("Not Suitable for the parkingSpot");
        }
        else if(isOccupied()){
            throw new IllegalStateException("Spot is already occupied.");
        }
        else{
            this.vehicle = vehicle;
            this.isOccupied = true;
        }

    }

    public void removeVehicle(){

        if(!isOccupied()){
            throw new IllegalStateException("Spot is already vaccated.");
        }

        this.vehicle = null;
        this.isOccupied = false;

    }

   
   

    
    
}
