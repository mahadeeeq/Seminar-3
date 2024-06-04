package integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Amount;
import model.CashPayment;
import model.ItemDTO;
import model.Receipt;
import model.Sale;


public class PrinterTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private Sale sale;
    private CashPayment payment;
    private Receipt receipt;
    private Printer instance;
    
    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Amount paidAmount = new Amount(10000);
        sale = new Sale();
        ItemDTO item = new ItemDTO("Cornflakes", "abc123", new Amount(2821), "Cornflakes 500 g, "
                    + "choclate oats", 6);
        sale.registerItem(item);
        payment = new CashPayment(paidAmount, sale);
        receipt = new Receipt(sale, payment);
        instance = new Printer();
    }
    
    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        
        sale = null;
        payment = null;
        receipt = null;
        instance = null;
    }

    @Test
    public void testPrintReceiptForDate() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = sale.getDateOfSale().toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain date.");
    }
    
    @Test
    public void testPrintReceiptForTime() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = sale.getTimeOfSale().withNano(0).toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain time.");
    }
    
    @Test
    public void testPrintReceiptForItemInformation() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = sale.getItemList().get(0).toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain item information.");
    }
    
    @Test
    public void testPrintReceiptForTotalPrice() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = sale.getRunningTotal().toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain total price.");
    }
    
    @Test
    public void testPrintReceiptForTotalVAT() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = sale.getRunningVat().toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain total VAT.");
    }
    
    @Test
    public void testPrintReceiptForPaidAmount() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = payment.getPaidAmount().toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain paid amount.");
    }
    
    @Test
    public void testPrintReceiptForChange() {
        instance.printReceipt(receipt);
        String output = outContent.toString();
        String expectedOutput = payment.getChange().toString();
        assertTrue(output.contains(expectedOutput), "Output does not contain change.");
    }
}
