package Pattern.Behavioral.Observer;

public class MobileObserver implements Observer {

    private String user;

    MobileObserver(String user){
        this.user = user;
    }

    @Override
    public void update(double price) {
      System.out.println(String.format("The updated stock price is for the user %s %f" , user, price));
    }
    
    
}
