package Pattern.Behavioral.Iterator;

public class StudentIterator implements Iterator {

    private String[] students;
    private int position = 0;
    

    public StudentIterator(String[] students) {
        this.students = students;
    }

    @Override
    public boolean hasNext() {
        return position <  students.length;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return students[position++];
        }
        return null;
    }
    
}
