package startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Amount;

public class MainTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
    }
    
    @Test
    public void testMainPrintsFirstItem() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedItemID = "abc123";
        String expectedItemName = "Cornflakes";
        String expectedPrice = new Amount(2821).toString();
        String expectedVat = "6%";
        String expectedDescription = "Cornflakes 500 g, choclate oats";
        assertTrue(output.contains(expectedItemID), "Output does not contain item ID.");
        assertTrue(output.contains(expectedItemName), "Output does not contain item name.");
        assertTrue(output.contains(expectedPrice), "Output does not contain item price.");
        assertTrue(output.contains(expectedVat), "Output does not contain VAT rate.");
        assertTrue(output.contains(expectedDescription), "Output does not contain item description.");
    }
    
    @Test
    public void testMainPrintsSecondItem() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedItemID = "def456";
        String expectedItemName = "Muffins";
        String expectedPrice = new Amount(1406).toString();
        String expectedVat = "6%";
        String expectedDescription = "Muffins 240 g, low sugar , blueberry flavour";
        assertTrue(output.contains(expectedItemID), "Output does not contain item ID.");
        assertTrue(output.contains(expectedItemName), "Output does not contain item name.");
        assertTrue(output.contains(expectedPrice), "Output does not contain item price.");
        assertTrue(output.contains(expectedVat), "Output does not contain VAT rate.");
        assertTrue(output.contains(expectedDescription), "Output does not contain item description.");
    }
    
    @Test
    public void testMainPrintsRunningTotal() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedRunningTotalAfterFirstItem = new Amount(2821).toString();
        String expectedRunningTotalAfterSecondItem = new Amount(5642).toString();
        String expectedRunningTotalAfterThirdItem = new Amount(7048).toString();
        assertTrue(output.contains(expectedRunningTotalAfterFirstItem), "Output does not contain running total after first item.");
        assertTrue(output.contains(expectedRunningTotalAfterSecondItem), "Output does not contain running total after second item.");
        assertTrue(output.contains(expectedRunningTotalAfterThirdItem), "Output does not contain running total after third item."); 
    }

    @Test
    public void testMainPrintsRunningVat() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedRunningVatAfterFirstItem = new Amount(169).toString();
        String expectedRunningVatAfterSecondItem = new Amount(338).toString();
        String expectedRunningVatAfterThirdItem = new Amount(422).toString();
        assertTrue(output.contains(expectedRunningVatAfterFirstItem), "Output does not contain VAT after first item.");
        assertTrue(output.contains(expectedRunningVatAfterSecondItem), "Output does not contain VAT after second item.");
        assertTrue(output.contains(expectedRunningVatAfterThirdItem), "Output does not contain VAT after third item."); 
    }
    
    @Test
    public void testMainPrintsTotalCost() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedOutput = "Total cost (incl VAT): 70:48 SEK";
        assertTrue(output.contains(expectedOutput), "Output does not contain total cost.");
    }
    
    @Test
    public void testMainPrintsReceipt() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedBeginReceipt = "----------------------- BEGIN RECEIPT -----------------------";
        String expectedTimeOfSale = "Time of sale: " + LocalDate.now().toString() + " " + LocalTime.now().withNano(0).toString();
        String expectedFirstItemLine = "Cornflakes       2 x 28:21 SEK    56:42 SEK";
        String expectedSecondItemLine = "Muffins       1 x 14:06 SEK    14:06 SEK";
        String expectedTotal = "Total: 70:48 SEK";
        String expectedVat = "VAT: 4:22 SEK";
        String expectedCash = "Cash: 100:00 SEK";
        String expectedChange = "Change: 29:52 SEK";
        String expectedEndReceipt = "----------------------- END RECEIPT -----------------------";
        assertTrue(output.contains(expectedBeginReceipt), "Output does not contain start of receipt.");
        assertTrue(output.contains(expectedTimeOfSale), "Output does not contain time of sale.");
        assertTrue(output.contains(expectedFirstItemLine), "Output does not contain first item line.");
        assertTrue(output.contains(expectedSecondItemLine), "Output does not contain second item line.");
        assertTrue(output.contains(expectedTotal), "Output does not contain total price.");
        assertTrue(output.contains(expectedVat), "Output does not contain VAT.");
        assertTrue(output.contains(expectedCash), "Output does not contain paid amount.");
        assertTrue(output.contains(expectedChange), "Output does not contain change.");
        assertTrue(output.contains(expectedEndReceipt), "Output does not contain end of receipt.");
    }
    
    @Test
    public void testMainPrintsChange() {
        String[] args = null;
        Main.main(args);
        String output = outContent.toString();
        String expectedOutput = "Change to give the customer: 29:52 SEK";
        assertTrue(output.contains(expectedOutput), "Output does not contain change to give to customer.");
    }
}
