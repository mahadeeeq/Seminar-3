package view;

import controller.Controller;
import model.Amount;
import model.ItemAndRunningTotalDTO;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to
 * all system operations in the Controller.
 */
public class View {
    private Controller contr;
    
    /**
     * Creates a new instance of View.
     * 
     * @param contr Controller that is used for all operations.
     */
    public View(Controller contr){
        this.contr = contr;
    }
    
    /**
     * Performs a fake sale, calls all the system operations in the controller.
     */
    public void runFakeExecution() {
        contr.startSale();
        
        // Add first item
        ItemAndRunningTotalDTO multipleItem = contr.enterIdentifier("abc123");
        System.out.println("Add 1 item with item id abc123:");
        System.out.println(itemAndRunningTotalDTOString(multipleItem));
        
        // Add the same item again
        multipleItem = contr.enterIdentifier("abc123");
        System.out.println("Add 1 item with item id abc123:");
        System.out.println(itemAndRunningTotalDTOString(multipleItem));
        
        // Add another different item
        ItemAndRunningTotalDTO singleItem = contr.enterIdentifier("def456");
        System.out.println("Add 1 item with item id def456:");
        System.out.println(itemAndRunningTotalDTOString(singleItem));
        
        // End the sale
        System.out.println("End sale:");
        Amount totalPrice = contr.endSale();
        System.out.println("Total cost (incl VAT): " + totalPrice.toString() + "\n");
        
        // Customer pays
        Amount paidAmount = new Amount(10000);
        System.out.println("Customer pays " + paidAmount + ":");
        Amount change = contr.pay(paidAmount);
        System.out.println("Change to give the customer: " + change);
    }
    
    private String itemAndRunningTotalDTOString(ItemAndRunningTotalDTO item) {
        StringBuilder builder = new StringBuilder();
        builder.append("Item ID: ").append(item.getItem().getIdentifier()).append("\n");
        builder.append("Item name: ").append(item.getItem().getName()).append("\n");
        builder.append("Item cost: ").append(item.getItem().getPrice().plus(item.getItem().getVat()).toString()).append("\n");
        builder.append("VAT: ").append(item.getItem().getVatRate()).append("% \n");
        builder.append("Item description: ").append(item.getItem().getDescription()).append("\n");
        builder.append("\n");
        builder.append("Total cost (incl VAT): ").append(item.getRunningTotal().toString()).append("\n");
        builder.append("Total VAT: ").append(item.getRunningVat().toString()).append("\n");
        return builder.toString();
    }
}
