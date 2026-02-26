package Pattern.Behavioral.ChainOfResponsibility;

public class Client {

    public static void main(String[] args) {
        
        Approver teamLead = new TeamLead();
        Approver manager = new Manager();
        Approver towerLead = new TowerLead();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(towerLead);

        teamLead.approve(100);

    }
    
}
