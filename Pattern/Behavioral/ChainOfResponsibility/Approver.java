package Pattern.Behavioral.ChainOfResponsibility;

abstract class Approver {

    protected Approver next;

    public void setNextApprover(Approver nextApprover){
        this.next = nextApprover;
    }

    public abstract void approve(int days);
    
}
