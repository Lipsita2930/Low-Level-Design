package Questions.ParkingLot.Gate;

public class EntryGate {
    
    private final int gateId;

    public EntryGate(int gateId) {
        this.gateId = gateId;
    }

    public int getGateId() {
        return gateId;
    }

    public void openGate() {
        System.out.println("🚪 Entry Gate #" + gateId + " is opened.");
    }

    public void closeGate() {
        System.out.println("🚪 Entry Gate #" + gateId + " is closed.");
    }
}
