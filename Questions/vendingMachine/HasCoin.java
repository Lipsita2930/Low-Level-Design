package Questions.vendingMachine;

public class HasCoin implements VendingMachineState {

    @Override
    public StateType getStateName() {
        return StateType.IDLE;
    }

    @Override
    public void next(VendingMachineContext context) {

        if(context.getCoinList().isEmpty()){
            context.setCurrentState(context.getStateFactory().createState(StateType.IDLE));
        }

        if(context.getCurrentState() instanceof HasCoin && !context.getCoinList().isEmpty()){
            context.setCurrentState(context.getStateFactory().createState(StateType.SELECTPRODUCT));
        }

    }


    
}
