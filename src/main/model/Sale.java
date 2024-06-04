package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents one sale, paid for by one payment.
 */
public class Sale {
    private LocalDate dateOfSale;
    private LocalTime timeOfSale;
    private Amount runningTotal;
    private Amount runningVat;
    private ArrayList<SaleItem> itemList;
    
    /**
     * Creates a new instance and saves the date and time of sale.
     */
    public Sale(){
        runningTotal = new Amount(0);
        runningVat = new Amount(0);
        itemList = new ArrayList<>();
        
        dateOfSale = LocalDate.now();
        timeOfSale = LocalTime.now();
    }
    
    /**
     * Registers the entered item in the sale, returns the item and the current running total.
     * 
     * @param item current item being registered.
     * @return the item and running total.
     */
    public ItemAndRunningTotalDTO registerItem (ItemDTO item){
        int indexOfItem = indexIfAlreadyRegistered(item);
        if (indexOfItem != -1){
            itemList.get(indexOfItem).addQuantity(1);
        }
        else {
            itemList.add(new SaleItem(item, 1));
        }
        runningTotal = runningTotal.plus(item.getPrice()).plus(item.getVat());
        runningVat = runningVat.plus(item.getVat());
        return new ItemAndRunningTotalDTO(item, runningTotal, runningVat);
    }
    
    private int indexIfAlreadyRegistered(ItemDTO searchedItem){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItem().equals(searchedItem)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns the runningTotal attribute.
     * 
     * @return runningTotal.
     */
    public Amount getRunningTotal(){
        return runningTotal;
    }
    
    /**
     * Returns the runningVat attribute.
     * 
     * @return runningVat.
     */
    public Amount getRunningVat(){
        return runningVat;
    }
    
    /**
     * Returns a deep copy of itemList attribute.
     * 
     * @return itemList.
     */
    public ArrayList<SaleItemDTO> getItemListDTO(){
        ArrayList<SaleItemDTO> copy = new ArrayList<>();
        for (SaleItem element : itemList) {
            copy.add(new SaleItemDTO(element)); 
        }
        return copy;
    }
    
    /**
     * Returns the itemList attribute.
     * 
     * @return itemList.
     */
    public ArrayList<SaleItem> getItemList(){
        return itemList;
    }
    
    /**
     * Returns the dateOfSale attribute.
     * 
     * @return dateOfSale.
     */
    public LocalDate getDateOfSale(){
        return dateOfSale;
    }
    
    /**
     * Returns the timeOfSale attribute.
     * 
     * @return timeOfSale.
     */
    public LocalTime getTimeOfSale(){
        return timeOfSale;
    }
}
