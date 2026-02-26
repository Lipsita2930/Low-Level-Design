package Pattern.Behavioral.ChainOfResponsibility;

public class TeamLead extends Approver{

    @Override
    public void approve(int days) {
        if(days <= 1){
            System.out.println("Team Lead approved " + days + " days leave");
        }
        else if(next != null){
            next.approve(days);
        }

    }
    
    
}
