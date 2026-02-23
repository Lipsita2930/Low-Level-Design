package Pattern.Structural.Composite;

public class client {

    public static void main(String[] args) {
        
        File file1 = new File("resume.pdf", 200);
        File file2 = new File("photo.png", 500);
        File file3 = new File("notes.txt", 50);

        Folder subFolder = new Folder("Documents");
        subFolder.add(file1);
        subFolder.add(file3);

        Folder rootFolder = new Folder("Root");
        rootFolder.add(subFolder);
        rootFolder.add(file2);

        rootFolder.showDetails();
        System.out.println("Total Size: " + rootFolder.getSize() + "KB");

    }
    
}
