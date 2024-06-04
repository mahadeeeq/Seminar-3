package model;

/**
 * Class to encapsulate an item and a running total.
 */
public class ItemAndRunningTotalDTO {
    private final ItemDTO item;
    private final Amount runningTotal;
    private final Amount runningVat;
    
    /**
     * Creates new instance.
     * 
     * @param item current item being purchased.
     * @param runningTotal running total of current sale.
     * @param runningVat running VAT of current sale.
     */
    public ItemAndRunningTotalDTO(ItemDTO item, Amount runningTotal, Amount runningVat) {
        this.item = item;
        this.runningTotal = runningTotal;
        this.runningVat = runningVat;
    }
    
    /**
     * Returns the item attribute.
     * 
     * @return the item.
     */
    public ItemDTO getItem() {
        return item;
    }
    
    /**
     * Returns the runningTotal attribute.
     * 
     * @return the running total.
     */
    public Amount getRunningTotal() {
        return runningTotal;
    }
    
    /**
     * Returns the runningVat attribute.
     * 
     * @return the running vat.
     */
    public Amount getRunningVat() {
        return runningVat;
    }
}
