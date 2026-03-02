package Pattern.Behavioral.Iterator;

public class StudentRepo implements StudentCollection {

    private String[] students = {"Riya", "Aman", "John", "Priya"};

    @Override
    public Iterator createIterator() {
        return new StudentIterator(students);
    }
    
}
