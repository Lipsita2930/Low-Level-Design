package Questions.vendingMachine;

public class SelectProduct implements VendingMachineState {

    @Override
    public StateType getStateName() {
        return StateType.IDLE;
    }

    @Override
    public void next(VendingMachineContext context) {

        if(!context.getInventory().hasItem(context.getSelectProduct()) ){
            context.setCurrentState(context.getStateFactory().createState(StateType.OUTOFSTOCK));
        }

        if(context.getCurrentState() instanceof SelectProduct){
            context.setCurrentState(context.getStateFactory().createState(StateType.DISPENSE));
        }

    }


    
}
