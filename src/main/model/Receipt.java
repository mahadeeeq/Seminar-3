package model;

import java.util.ArrayList;

/**
 * Represents a receipt, proves payment for one sale.
 */
public class Receipt {
    private final Sale sale;
    private final CashPayment payment;
    
    /**
     * Creates a new instance.
     * 
     * @param sale current sale.
     * @param payment payment for the current sale.
     */
    public Receipt(Sale sale, CashPayment payment){
        this.sale = sale;
        this.payment = payment;
    }
    
    /**
     * Returns a formatted String with the information in the Receipt.
     * 
     * @return a String of the Receipt object.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("----------------------- BEGIN RECEIPT -----------------------" + "\n");
        builder.append("Time of sale: " + sale.getDateOfSale() + " " + sale.getTimeOfSale().withNano(0) + "\n");
        ArrayList<SaleItem> itemList = sale.getItemList();
        for (int i = 0; i < itemList.size(); i++){
            builder.append(itemList.get(i).toString() + "\n");
        }
        builder.append("Total: " + sale.getRunningTotal() + "\n");
        builder.append("VAT: " + sale.getRunningVat() + "\n");
        builder.append("\n");
        builder.append("Cash: " + payment.getPaidAmount() + "\n");
        builder.append("Change: " + payment.getChange() + "\n");
        builder.append("----------------------- END RECEIPT -----------------------");
        return builder.toString();        
    }
}
