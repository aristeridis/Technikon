package gr.ed.technikon.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import gr.ed.technikon.enums.RepairType;
import gr.ed.technikon.enums.RepairStatus;

public class Repair implements PersistentClass {

    private int repairId;
    private RepairType repairType;
    private String shortDescription;
    private LocalDate dateOfSubmission;
    private String descriptionOfWork;
    private LocalDate proposedDateOfStart;
    private LocalDate proposedDateOfEnd;
    private BigDecimal proposedCost;
    private boolean acceptance;
    private RepairStatus repairStatus;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;
    private Owner owner;
    private Property property;

    public Repair(int repairId, RepairType repairType, String shortDescription, LocalDate dateOfSubmission, String descriptionOfWork, LocalDate proposedDateOfStart, LocalDate proposedDateOfEnd, BigDecimal proposedCost, boolean acceptance, RepairStatus repairStatus, LocalDate dateOfStart, LocalDate dateOfEnd, Owner owner, Property property) {
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

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
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

    public LocalDate getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(LocalDate dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public LocalDate getProposedDateOfStart() {
        return proposedDateOfStart;
    }

    public void setProposedDateOfStart(LocalDate proposedDateOfStart) {
        this.proposedDateOfStart = proposedDateOfStart;
    }

    public LocalDate getProposedDateOfEnd() {
        return proposedDateOfEnd;
    }

    public void setProposedDateOfEnd(LocalDate proposedDateOfEnd) {
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

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDate dateOfEnd) {
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
