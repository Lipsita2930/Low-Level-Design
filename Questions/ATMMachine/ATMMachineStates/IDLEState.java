package Questions.ATMMachine.ATMMachineStates;

public class IDLEState implements ATMMachineState{

    @Override
    public String getStateName() {
        return "IdleState";
    }

    @Override
    public void next(ATMMachineContext context) {

        if(context.getCurrentCard() != null){
            context.setCurentState(context.getAtmStateFactory().createATMState(StateType.HasCardState));
        }
    
    }


}
