package Questions.vendingMachine;

import java.util.List;

public class Inventory {

    private static Inventory instance;

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }

        return instance;
    }


    private List<ItemShelf> ItemShelf;


    public void addItem(Item item, int code) throws Exception{
        for(ItemShelf shelf : ItemShelf){
            if(shelf.getCode() == code){
                shelf.addItem(item);
            }
        }

        throw new Exception("Invalid Self Id");
    }

    public void removeItem(Item item, int code) throws Exception{
        for(ItemShelf shelf : ItemShelf){
            if(shelf.getCode() == code){
                shelf.removeItem(item);
            }
        }

        throw new Exception("Invalid Self Id");
    }

    

}
