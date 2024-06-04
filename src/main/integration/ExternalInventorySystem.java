package integration;

import model.Amount;
import model.ItemDTO;
import model.SaleDTO;

/**
 * Handles all communication with the external inventory system.
 */
public class ExternalInventorySystem {
    private ItemDTO[] itemArray;
    
    /**
     * Creates an instance of ExternalInventorySystem. Since it does not have a database
     * to communicate with some ItemDTOs will be created and kept in an array to simulate
     * the fetching from the database.
     */
    public ExternalInventorySystem(){
        this.itemArray = createItemArray();
    }
    
    private ItemDTO[] createItemArray() {
        ItemDTO[] itemArray = {
            new ItemDTO("Cornflakes", "abc123", new Amount(2821), "Cornflakes 500 g, "
                    + "choclate oats", 6),
            new ItemDTO("Muffins", "def456", new Amount(1406), "Muffins 240 g, "
                    + "low sugar , blueberry flavour", 6),
        };
        return itemArray;
    }
    
    /**
     * Searches the array of ItemDTOs to find an item with the given item identifier. If not found it returns null.
     * 
     * @param itemIdentifier item identifier of the searched item.
     * @return the searched item as ItemDTO, if not found it returns null.
     */
    public ItemDTO findItem(String itemIdentifier){
        for (ItemDTO item : itemArray) {
            if (item.getIdentifier().equals(itemIdentifier)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Updates the external inventory system with quantities sold during sale.
     * 
     * @param sale the current sale.
     */
    public void update(SaleDTO sale){
        // Implementation of updating the external inventory system can be added here.
    }
}
