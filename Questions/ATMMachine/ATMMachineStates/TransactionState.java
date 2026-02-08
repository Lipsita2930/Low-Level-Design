package Questions.ATMMachine.ATMMachineStates;

public class TransactionState implements ATMMachineState{

    @Override
    public String getStateName() {
        return "TransactionState";
    }

    @Override
    public void next(ATMMachineContext context) {
    
    }
    
}
