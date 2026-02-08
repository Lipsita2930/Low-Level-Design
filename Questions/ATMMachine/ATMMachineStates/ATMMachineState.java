package Questions.ATMMachine.ATMMachineStates;

public interface ATMMachineState {
    
    public String getStateName();
    public void next(ATMMachineContext context);

}
