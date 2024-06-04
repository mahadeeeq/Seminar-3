package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import integration.ExternalAccountingSystem;
import integration.ExternalInventorySystem;
import integration.ExternalSystemCreator;
import integration.Printer;
import model.Amount;
import model.CashRegister;
import model.ItemAndRunningTotalDTO;
import model.ItemDTO;
import model.Sale;

public class ControllerTest {
    private Printer printer;
    private ExternalSystemCreator creator;
    private CashRegister cashRegister;
    
    @BeforeEach
    public void setUp() {
        printer = new Printer();
        creator = new ExternalSystemCreator();
        cashRegister = new CashRegister();
    }
    
    @AfterEach
    public void tearDown() {
        printer = null;
        creator = null;
        cashRegister = null;
    }

    @Test
    public void testEnterIdentifierValidIdentifier() {
        Controller contr = new Controller(creator, printer);
        contr.startSale();
        String validIdentifier = "abc123";
        ItemDTO expectedResult = new ItemDTO("BigWheel Oatmeal", "abc123", new Amount(2821), "BigWheel Oatmeal 500 g, "
                    + "whole grain oats, high fiber, gluten free", 6);
        ItemAndRunningTotalDTO result = contr.enterIdentifier(validIdentifier);
        assertEquals(expectedResult, result.getItem(), "enterIdentifier not returning correct item.");
        assertEquals(expectedResult.getPrice().plus(expectedResult.getVat()), result.getRunningTotal(), "enterIdentifier not returning correct running total.");
    }

    @Test
    public void testEndSale() {
        Controller contr = new Controller(creator, printer);
        contr.startSale();
        ItemDTO item = new ItemDTO("BigWheel Oatmeal", "abc123", new Amount(2821), "BigWheel Oatmeal 500 g, "
                    + "whole grain oats, high fiber, gluten free", 6);
        contr.enterIdentifier("abc123");
        contr.enterIdentifier("abc123");
        Amount expectedResult = item.getPrice().plus(item.getVat()).multiply(2);
        Amount result = contr.endSale();
        assertEquals(expectedResult, result, "endSale not returning correct total price.");
    }

    @Test
    public void testPay() {
        Controller contr = new Controller(creator, printer);
        contr.startSale();
        ItemDTO item = new ItemDTO("BigWheel Oatmeal", "abc123", new Amount(2821), "BigWheel Oatmeal 500 g, "
                    + "whole grain oats, high fiber, gluten free", 6);
        contr.enterIdentifier("abc123");
        contr.enterIdentifier("abc123");
        Amount totalPrice = item.getPrice().plus(item.getVat()).multiply(2);
        Amount amountPaid = new Amount(10000);
        Amount expectedResult = amountPaid.minus(totalPrice);
        Amount result = contr.pay(amountPaid);
        assertEquals(expectedResult, result, "pay not returning correct change.");
    }
}
