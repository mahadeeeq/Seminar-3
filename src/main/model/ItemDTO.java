package model;

/**
 * Data transfer object that represents an item in the inventory.
 */
public class ItemDTO {
    private final String name;
    private final String identifier;
    private final Amount price;
    private final String description;
    private final int vatRate;
    private final Amount vat;
    
    /**
     * Creates a new instance of ItemDTO. Calculates the Amount of vat based on price and VAT rate.
     * 
     * @param name Name of the item.
     * @param identifier A number that identifies the item in the external inventory system.
     * @param price Price of the item in ören.
     * @param description Description of the item.
     * @param vatRate VAT rate for the item.
     */
    public ItemDTO(String name, String identifier, Amount price, String description, int vatRate){
        this.name = name;
        this.identifier = identifier;
        this.price = price;
        this.description = description;
        this.vatRate = vatRate;
        this.vat = price.multiply((double) vatRate / 100);
    }
    
    /**
     * Two <code>ItemDTO</code> instances are equal if all fields are equal.
     *
     * @param otherObj The <code>ItemDTO</code> to compare with this
     *                 <code>ItemDTO</code>.
     * @return <code>true</code> if all fields in the specified
     *         <code>ItemDTO</code> are equal to corresponding fields in this
     *         <code>ItemDTO</code>, <code>false</code> if they are not.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        final ItemDTO other = (ItemDTO) otherObj;
        if (!name.equals(other.name)) {
            return false;
        }
        if (!identifier.equals(other.identifier)){
            return false;
        }
        if (!price.equals(other.price)){
            return false;
        }
        if (!description.equals(other.description)){
            return false;
        }
        if (vatRate != other.vatRate){
            return false;
        }
        if (!vat.equals(other.vat)){
            return false;
        }
        return true;
    }
    
    /**
     * Returns the name attribute of the given ItemDTO.
     * 
     * @return the name.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns the identifier attribute of the given ItemDTO.
     * 
     * @return the identifier.
     */
    public String getIdentifier() {
        return identifier;
    }
    
    /**
     * Returns the price attribute of the given ItemDTO.
     * 
     * @return the price.
     */
    public Amount getPrice(){
        return this.price;
    }
    
    /**
     * Returns the description attribute of the given ItemDTO.
     * 
     * @return the description.
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * Returns the vatRate attribute of the given ItemDTO.
     * 
     * @return the vat rate.
     */
    public int getVatRate(){
        return vatRate;
    }
    
    /**
     * Returns the vat attribute of the given ItemDTO.
     * 
     * @return the total vat.
     */
    public Amount getVat(){
        return vat;
    }
}
