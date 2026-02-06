package Questions.vendingMachine;

import java.util.List;

public class ItemShelf {

    private int code;
    private List<Item> itemList;
    private boolean isSoldOut;

    public boolean isSoldOut() {
        return isSoldOut;
    }
    public void setSoldOut(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void addItem(Item item){
        itemList.add(item);
        if(isSoldOut){
            isSoldOut = false;
        }
    }

    public void removeItem(Item item){
        itemList.remove(item);
        if(itemList.isEmpty()){
            this.isSoldOut = true;
        }
    }


    
    
}
