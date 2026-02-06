package Questions.vendingMachine;

public interface VendingMachineState {
    
    StateType getStateName();
    void next(VendingMachineContext context);
    
}
