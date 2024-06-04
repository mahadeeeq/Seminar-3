package model;

/**
 * Represents a sale item in the sale. All fields are immutable.
 */
public class SaleItemDTO {
    private final ItemDTO item;
    private final int quantity;
    
    /**
     * Creates a new instance.
     * 
     * @param saleItem the instance to be copied
     */
    SaleItemDTO(SaleItem saleItem){
        this.item = saleItem.getItem();
        this.quantity = saleItem.getQuantity();
    }
    
    /**
     * Returns item attribute.
     * 
     * @return item
     */
    public ItemDTO getItem(){
        return item;
    }

    /**
     * Returns quantity attribute.
     * 
     * @return quantity
     */
    public int getQuantity(){
        return quantity;
    }
}
