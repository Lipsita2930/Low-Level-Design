package Pattern.Behavioral.Iterator;

public class Client {

    public static void main(String[] args) {
        
        StudentRepo  repo = new StudentRepo();
        Iterator it = repo.createIterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
    
}
