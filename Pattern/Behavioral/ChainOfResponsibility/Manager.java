package Pattern.Behavioral.ChainOfResponsibility;

public class Manager extends Approver{

    @Override
    public void approve(int days) {
        if(days <= 5){
            System.out.println("Manager approved " + days + " days leave");
        }
        else if(next != null){
            next.approve(days);
        }
    }
}
