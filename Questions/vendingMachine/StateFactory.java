package Questions.vendingMachine;

public class StateFactory {


    public VendingMachineState createState(StateType type){

        switch (type) {
            case StateType.IDLE:
                return new IDLE();
            default:
                return new IDLE();
           
        }

    }
    
}
