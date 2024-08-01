package gr.ed.technikon.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString



public class Owner {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long OwnerId;
    
    @Column(unique=true)
    private long VatNumber;
    
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private String SurName;
    private String Address;
    @Column(nullable = false)
    private String PhoneNumber;
    @Column(nullable = false,unique = true)
    private String Email;
    @Column(nullable = false)
    private String Username;
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy= "owner")
    private List<Property>  propertyList;
    
    @OneToMany(mappedBy= "owner")
    private List<Repair>  repairList;
}
