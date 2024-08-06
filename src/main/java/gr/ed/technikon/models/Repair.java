package gr.ed.technikon.models;

import java.math.BigDecimal;
import gr.ed.technikon.enums.RepairType;
import gr.ed.technikon.enums.RepairStatus;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class Repair {

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
    private Property property;

}
