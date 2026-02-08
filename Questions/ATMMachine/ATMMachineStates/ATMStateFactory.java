package Questions.ATMMachine.ATMMachineStates;

public class ATMStateFactory {

    public ATMMachineState createATMState(StateType stateType){

        if(stateType == StateType.IdleState){
            return new IDLEState();
        }
        else if(stateType == StateType.HasCardState){
            return new HasCardState();
        }
        else if(stateType == StateType.SelectOperationState){
            return new SelectOperationState();
        }
        else if(stateType == StateType.TransactionState){
            return new TransactionState();
        }

        return null;
    }
    
}
