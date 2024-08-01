package gr.ed.technikon.models;

import gr.ed.technikon.enums.PropertyType;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Property implements PersistentClass {
      
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "property_id")
    private long propertyId;
    private String address;
    private int yearOfConstruction;
    private PropertyType propertyType;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "ownerId") //na doume ta joins
    private Owner owner;
    
    @OneToMany(mappedBy = "property")
    private List<Repair>  repairList;

    public Property(int propertyId, String address, int yearOfConstruction, PropertyType propertyType, Owner owner) {
        this.propertyId = propertyId;
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.propertyType = propertyType;
        this.owner = owner;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
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
        return "Property{" + "propertyId=" + propertyId + 
                ", address=" + address + ", yearOfConstruction=" + yearOfConstruction + ", propertyType=" + propertyType + ", owner=" + owner + '}';
    }
    
}