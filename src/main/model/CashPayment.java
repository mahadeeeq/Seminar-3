package model;

/**
 * Represents one payment.
 */
public class CashPayment {
    private final Amount paidAmount;
    private final Amount change;
    
    /**
     * Creates a new instance.
     * 
     * @param paidAmount the amount that will pay for the sale.
     * @param sale the current sale being paid for.
     */
    public CashPayment(Amount paidAmount, Sale sale){
        this.paidAmount = paidAmount;
        this.change = paidAmount.minus(sale.getRunningTotal());
    }
    
    /**
     * Returns the change attribute.
     * 
     * @return the change of the sale.
     */
    public Amount getChange(){
        return change;
    }
    
    /**
     * Returns the paidAmount attribute.
     * 
     * @return paidAmount.
     */
    public Amount getPaidAmount(){
        return paidAmount;
    }
}
