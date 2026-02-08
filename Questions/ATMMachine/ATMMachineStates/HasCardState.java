package Questions.ATMMachine.ATMMachineStates;

public class HasCardState  implements ATMMachineState{

    @Override
    public String getStateName() {
        return "HasCardState";
    }

    @Override
    public void next(ATMMachineContext context) {

        if(context.getCurentState() instanceof HasCardState && context.getAccounts()!=null){
            context.setCurentState(context.getAtmStateFactory().createATMState(StateType.SelectOperationState));
        }
   
    }
    
    
}
