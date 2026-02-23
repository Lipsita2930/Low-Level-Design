package Pattern.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystem{

    private String name;
    private List<FileSystem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystem component) {
        children.add(component);
    }

    public void remove(FileSystem component) {
        children.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystem child : children) {
            child.showDetails();  // impt line
        }
    }

    @Override
    public int getSize() {
        int total = 0;
        for (FileSystem child : children) {
            total += child.getSize();  // impt line
        }
        return total;
    }
    
}
