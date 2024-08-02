package gr.ed.technikon.models;

import java.math.BigDecimal;
import gr.ed.technikon.enums.RepairType;
import gr.ed.technikon.enums.RepairStatus;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor


public class Repair implements PersistentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long repairId;

    @Column(nullable = false)
    private RepairType repairType;
    
    private String shortDescription;
    
    @Column(nullable = false)
    private Date dateOfSubmission;
    
    private String descriptionOfWork;
    
    @Column(nullable = false)
    private Date proposedDateOfStart;
    
    @Column(nullable = false)
    private Date proposedDateOfEnd;
    
    @Column(nullable = false)
    private BigDecimal proposedCost;
    
    private boolean acceptance;
    
    private RepairStatus repairStatus;
    
    @Column(nullable = false)
    private Date dateOfStart;
    
    @Column(nullable = false)
    private Date dateOfEnd;

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

   /* public Repair(long repairId, RepairType repairType, String shortDescription, Date dateOfSubmission, String descriptionOfWork, Date proposedDateOfStart, Date proposedDateOfEnd, BigDecimal proposedCost, boolean acceptance, RepairStatus repairStatus, Date dateOfStart, Date dateOfEnd, Owner owner, Property property) {
        this.repairId = repairId;
        this.repairType = repairType;
        this.shortDescription = shortDescription;
        this.dateOfSubmission = dateOfSubmission;
        this.descriptionOfWork = descriptionOfWork;
        this.proposedDateOfStart = proposedDateOfStart;
        this.proposedDateOfEnd = proposedDateOfEnd;
        this.proposedCost = proposedCost;
        this.acceptance = acceptance;
        this.repairStatus = repairStatus;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.owner = owner;
        this.property = property;
    }
*/
}



/*
public class Repair implements PersistentClass {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long repairId;
    
    @Column(nullable = false)
    private RepairType repairType;
    private String shortDescription;
    @Column(nullable = false)
    private Date dateOfSubmission;
    private String descriptionOfWork;
    @Column(nullable = false)
    private Date proposedDateOfStart;
    @Column(nullable = false)
    private Date proposedDateOfEnd;
    @Column(nullable = false)
    private BigDecimal proposedCost;
    private boolean acceptance;
    private RepairStatus repairStatus;
    @Column(nullable = false)
    private Date dateOfStart;
    @Column(nullable = false)
    private Date dateOfEnd;
    @Column(nullable = false)
    @ManyToOne 
    @JoinColumn(name = "ownerId") //na doume ta joins 
    private Owner owner;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "property_id") //na doume ta joins
    private Property property;
     

    public Repair(long repairId, RepairType repairType, String shortDescription, Date dateOfSubmission, String descriptionOfWork, Date proposedDateOfStart, Date proposedDateOfEnd, BigDecimal proposedCost, boolean acceptance, RepairStatus repairStatus, Date dateOfStart, Date dateOfEnd, Owner owner, Property property) {
        this.repairId = repairId;
        this.repairType = repairType;
        this.shortDescription = shortDescription;
        this.dateOfSubmission = dateOfSubmission;
        this.descriptionOfWork = descriptionOfWork;
        this.proposedDateOfStart = proposedDateOfStart;
        this.proposedDateOfEnd = proposedDateOfEnd;
        this.proposedCost = proposedCost;
        this.acceptance = acceptance;
        this.repairStatus = repairStatus;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.owner = owner;
        this.property = property;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(Date dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public Date getProposedDateOfStart() {
        return proposedDateOfStart;
    }

    public void setProposedDateOfStart(Date proposedDateOfStart) {
        this.proposedDateOfStart = proposedDateOfStart;
    }

    public Date getProposedDateOfEnd() {
        return proposedDateOfEnd;
    }

    public void setProposedDateOfEnd(Date proposedDateOfEnd) {
        this.proposedDateOfEnd = proposedDateOfEnd;
    }

    public BigDecimal getProposedCost() {
        return proposedCost;
    }

    public void setProposedCost(BigDecimal proposedCost) {
        this.proposedCost = proposedCost;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Repair{"
                + "repairId=" + repairId
                + ", repairType=" + repairType
                + ", shortDescription='" + shortDescription + '\''
                + ", dateOfSubmission=" + dateOfSubmission
                + ", descriptionOfWork='" + descriptionOfWork + '\''
                + ", proposedDateOfStart=" + proposedDateOfStart
                + ", proposedDateOfEnd=" + proposedDateOfEnd
                + ", proposedCost=" + proposedCost
                + ", acceptance=" + acceptance
                + ", repairStatus=" + repairStatus
                + ", dateOfStart=" + dateOfStart
                + ", dateOfEnd=" + dateOfEnd
                + ", owner=" + owner
                + ", property=" + property
                + '}';
    }
}
*/