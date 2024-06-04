package controller;

import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.ExternalSystemCreator;
import integration.Printer;
import model.Amount;
import model.CashPayment;
import model.CashRegister;
import model.ItemAndRunningTotalDTO;
import model.ItemDTO;
import model.Receipt;
import model.Sale;
import model.SaleDTO;

/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */
public class Controller {
    private Printer printer;
    private ExternalAccountingSystem externalAccountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    private CashRegister cashRegister;
    private Sale sale;
    
    /**
     * Creates a new Controller instance.
     * 
     * @param creator Used to get all classes that handle calls to external systems.
     * @param printer Interface to printer.
     */
    public Controller (ExternalSystemCreator creator, Printer printer){
        this.externalAccountingSystem = creator.getExternalAccountingSystem();
        this.externalInventorySystem = creator.getExternalInventorySystem();
        this.printer = printer;
        this.cashRegister = new CashRegister();
    }
    
    /**
     * Starts a new sale by creating and storing a Sale object. Must be carried 
     * out before doing anything else during a sale.
     */
    public void startSale(){
        sale = new Sale();
    }
    
    /**
     * Searches for an item with the specified item identifier. Registers it in 
     * the sale and returns the item and current running total of the sale.
     * 
     * @param itemIdentifier identifier of the searched for item.
     * @return item and running total of sale.
     */
    public ItemAndRunningTotalDTO enterIdentifier(String itemIdentifier){
        ItemDTO searchedItem = externalInventorySystem.findItem(itemIdentifier);
        return sale.registerItem(searchedItem);
    }
    
    /**
     * Ends the current sale and returns the total price.
     * 
     * @return total price.
     */
    public Amount endSale(){
        return sale.getRunningTotal();
    }
    
    /**
     * Pays for the current sale. Updates the balance of the cash register where
     * the payment was performed. Calculates change. Prints the receipt.
     * 
     * @param paidAmount the amount the customer has paid.
     * @return the change for the sale.
     */
    public Amount pay(Amount paidAmount){
        CashPayment payment = new CashPayment(paidAmount, sale);
        
        SaleDTO saleDTO = new SaleDTO(sale);
        externalAccountingSystem.update(saleDTO);
        externalInventorySystem.update(saleDTO);
        
        Receipt receipt = new Receipt(sale, payment);
        printer.printReceipt(receipt);
        
        cashRegister.addPayment(sale.getRunningTotal());
        
        return payment.getChange();
    }
}
