package integration;

import model.Receipt;

/**
 * Handles all communication with the external printer that prints the receipt. 
 * In this application the printing of the receipt will be simulated by printing to System.out.
 */
public class Printer {
    
    /**
     * Prints the given receipt to System.out.
     * 
     * @param receipt to be printed.
     */
    public void printReceipt(Receipt receipt){
        System.out.println(receipt.toString());
    }
}
