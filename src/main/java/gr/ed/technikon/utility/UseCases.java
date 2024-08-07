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
import gr.ed.technikon.enums.RepairStatus;
import gr.ed.technikon.enums.RepairType;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import gr.ed.technikon.services.AdminService;
import gr.ed.technikon.services.AdminServiceInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
//import java.sql.Date;

public class UseCases {

    private static final OwnerRepositoryInterface ownerRepo = new OwnerRepository();
    private static final PropertyRepositoryInterface propertyRepo = new PropertyRepository();
    private static final RepairRepositoryInterface repairRepo = new RepairRepository();
    

    private static final IOServiceInterface ioService = new IOService(ownerRepo, propertyRepo, repairRepo);
    private static final OwnerServiceInterface ownerService = new OwnerService(propertyRepo);
    private static final AdminServiceInterface adminService = new AdminService();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static Property property;
    private static Owner owner;
    private static Repair repair;
    private static final Scanner scanner = new Scanner(System.in);

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
        for (Property property : owner.getPropertyList()) {
            System.out.println("PropertyCode:" + property.getPropertyCode() + " Address: " + property.getAddress());

            propertyRepo.save(property1);
            propertyRepo.save(property2);
        }

    }


      public static void repairsForPropertiesOwner(long propertyId) throws ParseException {
        System.out.println("|-------------------4.3-------------------|");
        Optional <Property> propertyOwner = propertyRepo.findById(propertyId);
        property = propertyOwner.get();
        repair = new Repair();
        repair.setDateOfSubmission(DATE_FORMAT.parse("19-09-2024"));
        repair.setDeletedRepair(false);
        repair.setDescriptionOfWork("Painting of the living room walls in shades of grey.");
        repair.setShortDescription("Wall Painting");
        repair.setRepairType(RepairType.PAINTING);
        repair.setRepairStatus(RepairStatus.PENDING);
        repair.setProperty(property);
        
        
        repairRepo.save(repair);
      }
      
      public static void selectPropertiesFromOwnerById(long ownerId){
          Optional <Owner> ownerProperty = ownerRepo.findByOwnerId(ownerId);
//          owner = ownerProperty.get();
          OwnerService ownerService = new OwnerService(propertyRepo);
          for(Property property : ownerService.getPropertiesByOwnerId(ownerId)){             
              System.out.println("The property ID for this unique ownerID is/are: " + property.getPropertyId());
          }
          
          
          
    }
      
      
      private Date parseDate(String dateStr) throws ParseException {
        Date date = DATE_FORMAT.parse(dateStr);
        return date;
    }
      private long parseLong(String value) {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return -1; // Default or error value
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            
            return -1;
        }
    }

    private String formatDate(Date date) {
        return date == null ? "" : DATE_FORMAT.format(date);
    }

}
