package gr.ed.technikon.utility;

import gr.ed.technikon.Repositories.OwnerRepository;
import gr.ed.technikon.Repositories.OwnerRepositoryInterface;
import gr.ed.technikon.Repositories.PropertyRepository;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.Repositories.RepairRepository;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.services.IOService;
import gr.ed.technikon.services.IOServiceInterface;
import gr.ed.technikon.services.OwnerService;
import gr.ed.technikon.services.OwnerServiceInterface;
import static java.io.File.separator;
import gr.ed.technikon.Repositories.RepairRepositoryInterface;
import gr.ed.technikon.enums.PropertyType;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import gr.ed.technikon.services.AdminService;
import gr.ed.technikon.services.AdminServiceInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UseCases {

    private static final OwnerRepositoryInterface ownerRepo = new OwnerRepository();
    private static final PropertyRepositoryInterface propertyRepo = new PropertyRepository();
    private static final RepairRepositoryInterface repairRepo = new RepairRepository();

    private static final IOServiceInterface ioService = new IOService(ownerRepo, propertyRepo, repairRepo);
    private static final OwnerServiceInterface ownerService = new OwnerService(propertyRepo);
    private static final AdminServiceInterface adminService = new AdminService();
    
    private static Property property;
    private static Owner owner;

    public static void dataPopulation() {
        System.out.println("|-------------------4.1-------------------|");
        System.out.println("|-------------------START-------------------|");

        System.out.println();
        System.out.println("|-------------------Read Data from csv files-------------------|");
        ioService.readOwnersCsv("data" + separator + "owner.csv");
        ioService.readPropertiesCsv("data" + separator + "property.csv");
        ioService.readRepairsFromCsv("data" + separator + "repair.csv");

        System.out.println();
        System.out.println("|-------------------Reading Completing-------------------|");
    }

    public static void ownerWithTwoProperties() {
        System.out.println("|-------------------4.2-------------------|");
        owner = new Owner();
        owner.setVatNumber(123456789L);
        owner.setName("Nikos");
        owner.setSurName("Koukos");
        owner.setAddress("Kleanthous 14");
        owner.setPhoneNumber("6978756512");
        owner.setEmail("nikosk@gmail.com");
        owner.setUsername("NickKouk");
        owner.setPassword("12345678");
        
        ownerRepo.save(owner);

        Property property1 = new Property(123456789L, "Kleanthous 4", 1992, PropertyType.MAISONETTE, owner);
        Property property2 = new Property(321456789L, "Kleanthous 23", 1996, PropertyType.APARTMENT_BUILDING, owner);
        
        List<Property> koukosProperties = new ArrayList();
        koukosProperties.add(property1);
        koukosProperties.add(property2);
        owner.setPropertyList(koukosProperties);
        
        System.out.println(owner.toString());
        for (Property property : owner.getPropertyList()){
            System.out.println("PropertyCode:" + property.getPropertyCode() + " Address: "+ property.getAddress());
            
        propertyRepo.save(property1);
        propertyRepo.save(property2);
        }
           
    }
    
      public static void ownerWithTwoProperties() {
        System.out.println("|-------------------4.3-------------------|");
      }
}
