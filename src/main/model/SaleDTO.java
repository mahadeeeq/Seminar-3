package model;

import java.util.ArrayList;

/**
 * Contains information about a single sale.
 */
public class SaleDTO {
    private final Amount runningTotal;
    private final Amount runningVat;
    private final ArrayList<SaleItemDTO> itemList;

    /**
     * Creates a new instance.
     * 
     * @param sale the current sale.
     */
    public SaleDTO(Sale sale){
        this.runningTotal = sale.getRunningTotal();
        this.runningVat = sale.getRunningVat();
        this.itemList = sale.getItemListDTO();
    }
}
