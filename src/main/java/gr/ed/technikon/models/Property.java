package gr.ed.technikon.models;

import gr.ed.technikon.enums.PropertyType;

public class Property implements PersistentClass {

    private int propertyId;
    private String address;
    private int yearOfConstruction;
    private PropertyType propertyType;
    private Owner owner;

    public Property(int propertyId, String address, int yearOfConstruction, PropertyType propertyType, Owner owner) {
        this.propertyId = propertyId;
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.propertyType = propertyType;
        this.owner = owner;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(int yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    public long getOwnerVatNumber() {
        return owner.getVatNumber();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Property{" + "propertyId=" + propertyId + ", address=" + address + ", yearOfConstruction=" + yearOfConstruction + ", propertyType=" + propertyType + ", owner=" + owner + '}';
    }

}