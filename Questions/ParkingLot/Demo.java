package Questions.ParkingLot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Questions.ParkingLot.Gate.EntryGate;
import Questions.ParkingLot.Gate.ExitGate;
import Questions.ParkingLot.ParkingFeeStrategy.BasicFeeCalculationStrategy;
import Questions.ParkingLot.ParkingFeeStrategy.DurationType;
import Questions.ParkingLot.ParkingFeeStrategy.ParkingFeeStrategy;
import Questions.ParkingLot.ParkingFeeStrategy.PremiumFeeCalculationStrategy;
import Questions.ParkingLot.ParkingLot.ParkingFloors;
import Questions.ParkingLot.ParkingLot.ParkingLot;
import Questions.ParkingLot.ParkingSpot.CarParkingSpot;
import Questions.ParkingLot.ParkingSpot.ParkingSpot;
import Questions.ParkingLot.Payment.CashPayment;
import Questions.ParkingLot.Payment.CreditCardPayment;
import Questions.ParkingLot.Payment.PaymentStrategy;
import Questions.ParkingLot.Vehicle.Vehicle;
import Questions.ParkingLot.Vehicle.VehicleFactory;
import Questions.ParkingLot.Vehicle.VehicleType;

public class Demo {

    private static final HashMap<String, Ticket> activeTickets = new HashMap<>();

    public static void main(String[] args) {


        // Let Create the parking Spots;

        ParkingSpot carParkingSpot1 = new CarParkingSpot(1, VehicleType.CAR, null, false);
        ParkingSpot carParkingSpot2 = new CarParkingSpot(2, VehicleType.CAR, null, false);

        ParkingSpot bikeParkingSpot1 = new CarParkingSpot(3, VehicleType.BIKE, null, false);
        ParkingSpot bikeParkingSpot2 = new CarParkingSpot(4, VehicleType.BIKE, null, false);

        List<ParkingSpot> parkingFloor1 = new ArrayList<>();
        parkingFloor1.add(bikeParkingSpot1);
        parkingFloor1.add(bikeParkingSpot2);
        

        List<ParkingSpot> parkingFloor2 = new ArrayList<>();
        parkingFloor2.add(carParkingSpot1);
        parkingFloor2.add(carParkingSpot2);

        List<ParkingFloors> parkingFloors = new ArrayList<>();

        ParkingLot parkingLot = ParkingLot.getParkingInstance(parkingFloors);

        ParkingFeeStrategy basicFee = new BasicFeeCalculationStrategy();
        ParkingFeeStrategy premiumFee = new PremiumFeeCalculationStrategy();


        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "CAR123", basicFee);
        Vehicle bike1 = VehicleFactory.createVehicle(VehicleType.BIKE,"BIKE456",  premiumFee);

        // Parking start

        EntryGate entry = new EntryGate(1);
        ExitGate exit = new ExitGate(1);

        entry.openGate();

        parkVehicle(parkingLot, car1);
        parkVehicle(parkingLot, bike1);

        entry.closeGate();


        exit.openGate();

        Scanner sc = new Scanner(System.in);

        int paymentMethod = getPaymentMethod(sc);

        processVehicleExit(parkingLot, car1, paymentMethod);
        processVehicleExit(parkingLot, bike1, paymentMethod);

        exit.closeGate();
        sc.close();
        System.out.println("\n✅ Thank you for using our parking service!");

    
    }

    private static void processVehicleExit(ParkingLot parkingLot, Vehicle vehicle, int paymentMethod) {
        Ticket ticket = activeTickets.get(vehicle.getLisencePlate());
        if (ticket == null) {
            System.out.println("No active ticket for vehicle " + vehicle.getLisencePlate());
            return;
        }

        long durationInHours = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();
        durationInHours = Math.max(1, durationInHours); // minimum 1 hour

        double fee = vehicle.calculateFee((int) durationInHours, DurationType.HOURS);
        PaymentStrategy strategy = getPaymentStrategy(paymentMethod, fee);
        strategy.processPayment(fee);

        parkingLot.vacateSpot(ticket.getSpot(), vehicle);
        activeTickets.remove(vehicle.getLisencePlate());
        System.out.println("🚗 Vehicle " + vehicle.getLisencePlate() + " exited.\n");
    }

     private static PaymentStrategy getPaymentStrategy(int paymentMethod, double fee) {
        switch (paymentMethod) {
            case 1:
                return new CreditCardPayment(fee);
            case 2:
                return new CashPayment(fee);
            default:
                System.out.println("Invalid choice! Default to Credit card payment.");
                return new CreditCardPayment(fee);
        }
    }

    private static int getPaymentMethod(Scanner scanner) {
        int method = 0;
        while (method != 1 && method != 2) {
            System.out.println("\nSelect payment method:");
            System.out.println("1. Credit Card");
            System.out.println("2. Cash");
            try {
                method = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next();
            }
        }
        return method;
    }


    private static void parkVehicle(ParkingLot parkingLot, Vehicle vehicle) {

        ParkingSpot spot = parkingLot.parkVehicle(vehicle);

        if (spot != null) {
            Ticket ticket = new Ticket(vehicle, spot);
            activeTickets.put(vehicle.getLisencePlate(), ticket);
            System.out.println(vehicle.getType() + " parked at spot #" +
                    spot.getSpotID());
        } else {
            System.out.println("No parking spot available for " + vehicle.getType());
        }
    }

    
}
