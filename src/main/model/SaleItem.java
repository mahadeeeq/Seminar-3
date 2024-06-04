package model;

/**
 * Represents a sale item in the sale.
 */
public class SaleItem {
    private ItemDTO item;
    private int quantity;
    
    /**
     * Creates a new instance.
     * 
     * @param item current item being registered.
     * @param quantity quantity of that item.
     */
    SaleItem(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    
    /**
     * Adds a quantity to the existing quantity.
     * 
     * @param quantity the quantity to be added.
     */
    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
    
    /**
     * Returns the ItemDTO stored in item.
     * 
     * @return the ItemDTO.
     */
    public ItemDTO getItem(){
        return item;
    }
    
    /**
     * Returns the quantity attribute.
     * 
     * @return quantity.
     */
    public int getQuantity(){
        return quantity;
    }
    
    /**
     * Returns a formatted String with the values of the given SaleItem
     * 
     * @return a String of SaleItem
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(item.getName() + "       ");
        builder.append(quantity + " x " + item.getPrice().plus(item.getVat()) + "    ");
        builder.append(item.getPrice().plus(item.getVat()).multiply(quantity));
        return builder.toString();
    }
}
