package Pattern.Behavioral.Observer;

public class Main {

    public static void main(String[] args) {

        Stock tesla = new Stock();

        Observer user1 = new MobileObserver("Rahul");
        Observer user2 = new MobileObserver("Ankit");

        tesla.addObserver(user1);
        tesla.addObserver(user2);

        tesla.setPrice(100);
        tesla.setPrice(120);
    }
}