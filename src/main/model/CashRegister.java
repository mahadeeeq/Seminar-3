package model;

/**
 * Represents the cash register in the point of sale. Keeps track of available balance.
 */
public class CashRegister {
    private Amount balance;
    
    /**
     * Creates a new instance of CashRegister. Sets balance to 0 by default.
     */
    public CashRegister(){
        balance = new Amount(0);
    }
    
    /**
     * Adds the payment from the sale to the cash register.
     * 
     * @param amountAdded the amount added by the sale.
     */
    public void addPayment(Amount amountAdded){
        balance = balance.plus(amountAdded);
    }
    
    /**
     * Returns balance attribute.
     * 
     * @return balance.
     */
    public Amount getBalance(){
        return balance;
    }
}
