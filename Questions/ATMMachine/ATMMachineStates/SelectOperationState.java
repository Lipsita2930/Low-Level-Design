package Questions.ATMMachine.ATMMachineStates;

public class SelectOperationState implements ATMMachineState {

    @Override
    public String getStateName() {
        return "SelectOperationState";
    }

    @Override
    public void next(ATMMachineContext context) {

        if(context.getCurentState() instanceof SelectOperationState && context.getSelectedOperation()!=null){
            context.setCurentState(context.getAtmStateFactory().createATMState(StateType.TransactionState));
        }
      
    }
    
}
