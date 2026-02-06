package Questions.vendingMachine;

public class IDLE implements VendingMachineState {

    @Override
    public StateType getStateName() {
        return StateType.IDLE;
    }

    @Override
    public void next(VendingMachineContext context) {

    }


    
}
