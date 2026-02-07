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


    // The inventory has that particular Item
    public boolean hasItem(int code) {
        for(ItemShelf shelf : ItemShelf){
            if(shelf.getCode() == code){
                return  !shelf.getItemList().isEmpty();
            }
        }
       return true;
    }


    // Get the item for a particular ode

    public Item getSelectedProduct(int code){

        Item item = null;

        for(ItemShelf shelf : ItemShelf){
            if(shelf.getCode() ==  code){

                if(shelf.getItemList().isEmpty()){
                    return null;
                }
                item = shelf.getItemList().get(0);
            }
        }

        return item;

    }


    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf itemShelf : ItemShelf) {
          if (itemShelf.getCode() == codeNumber) {
            if (itemShelf.getItemList().isEmpty())
              itemShelf.setSoldOut(true); // Mark the shelf as sold out
          }
        }
      }

      
    

}
