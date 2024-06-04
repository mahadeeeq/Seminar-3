package integration;

/**
 * This class creates and passes references to the external systems.
 * 
 */
public class ExternalSystemCreator {
    private ExternalAccountingSystem externalAccountingSystem;
    private ExternalInventorySystem externalInventorySystem;
    
    /**
    * Creates a new instance which creates the external layers.
    */
    public ExternalSystemCreator(){
        this.externalAccountingSystem = new ExternalAccountingSystem();
        this.externalInventorySystem = new ExternalInventorySystem();
    }
    
    /**
     * Returns the ExternalAccountingSystem.
     * 
     * @return the ExternalAccountingSystem.
     */
    public ExternalAccountingSystem getExternalAccountingSystem(){
        return this.externalAccountingSystem;
    }
    
    /**
     * Returns the ExternalInventorySystem.
     * 
     * @return the ExternalInventorySystem.
     */
    public ExternalInventorySystem getExternalInventorySystem(){
        return this.externalInventorySystem;
    }
}
