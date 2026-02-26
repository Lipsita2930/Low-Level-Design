package Pattern.Behavioral.ChainOfResponsibility;

public class TowerLead extends Approver{

    @Override
    public void approve(int days) {
        if(days >= 5){
            System.out.println("Tower Lead approved " + days + " days leave");
        }
        else if(next != null){
            next.approve(days);
        }
    }
}