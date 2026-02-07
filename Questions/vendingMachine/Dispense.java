package Questions.vendingMachine;

public class Dispense implements VendingMachineState {

    @Override
    public StateType getStateName() {
        return StateType.IDLE;
    }

    @Override
    public void next(VendingMachineContext context) {

        if(context.getCurrentState() instanceof Dispense){
            context.setCurrentState(context.getStateFactory().createState(StateType.IDLE));
        }

    }


    
}
