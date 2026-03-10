package Questions.ElevatorSystem.request;


public abstract class ElevatorRequest implements ElevatorCommand {

    private final String requestId;
    private final long timestamp;
    private final RequestType requestType;

    public ElevatorRequest(String requestId, RequestType requestType) {
        this.requestId = requestId;
        this.requestType = requestType;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public abstract void execute();

    public abstract int getFloor();

    // --- Getters ---

    public String getRequestId() {
        return requestId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ElevatorRequest{" +
                "requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                ", timestamp=" + timestamp +
                '}';
    }
}
