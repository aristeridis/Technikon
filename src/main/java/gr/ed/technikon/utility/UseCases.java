package gr.ed.technikon.utility;

import gr.ed.technikon.Repositories.OwnerRepository;
import gr.ed.technikon.Repositories.OwnerRepositoryInterface;
import gr.ed.technikon.Repositories.PropertyRepository;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.Repositories.RepairInterface;
import gr.ed.technikon.Repositories.RepairRepository;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.services.IOService;
import gr.ed.technikon.services.IOServiceInterface;
import gr.ed.technikon.services.OwnerService;
import gr.ed.technikon.services.OwnerServiceInterface;
import static java.io.File.separator;

public class UseCases {
    
    private static final OwnerRepositoryInterface ownerRepo = new OwnerRepository();
    private static final PropertyRepositoryInterface propertyRepo = new PropertyRepository();
    private static final RepairInterface repairRepo = new RepairRepository();

    private static final IOServiceInterface ioService = new IOService(ownerRepo, propertyRepo, repairRepo);
    private static final OwnerServiceInterface ownerService = new OwnerService(propertyRepo);
    //private static final AdministratorService adminService = new AdministratorServiceImpl(repairRepo);

    private static Owner owner;
    
     public static void dataPopulation() {
        System.out.println("|-------------------START-------------------|");

        System.out.println();
        System.out.println("|-------------------Read Data from csv files-------------------|");
        ioService.readOwnersCsv("data" + separator + "owners.csv");
        ioService.readPropertiesCsv("data" + separator + "property.csv");
//        ioService.readRepairFromCsv("data" + separator + "repair.csv");

        System.out.println();
        System.out.println("|-------------------Reading Completing-------------------|");
    }
}
