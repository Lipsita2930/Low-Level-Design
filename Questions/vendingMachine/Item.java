package Questions.vendingMachine;

public class Item {

    private double price;
    private ItemType ItemType;
    

    public Item() {
    }

    public Item(double price, ItemType itemType) {
        this.price = price;
        ItemType = itemType;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public ItemType getItemType() {
        return ItemType;
    }
    public void setItemType(ItemType itemType) {
        ItemType = itemType;
    }



    
}
