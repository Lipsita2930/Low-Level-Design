package Questions.vendingMachine;

public class StateFactory {


    public VendingMachineState createState(StateType type){

        switch (type) {
            case StateType.IDLE:
                return new IDLE();
            case StateType.HASCOIN:
                return new HasCoin();
            case StateType.SELECTPRODUCT:
                return new SelectProduct();
            case StateType.DISPENSE:
                return new Dispense();
            default:
                return new IDLE();
           
        }

    }
    
}
