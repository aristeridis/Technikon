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
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import gr.ed.technikon.services.AdminService;
import gr.ed.technikon.services.AdminServiceInterface;

public class UseCases {

    private static final OwnerRepositoryInterface ownerRepo = new OwnerRepository();
    private static final PropertyRepositoryInterface propertyRepo = new PropertyRepository();
    private static final RepairRepositoryInterface repairRepo = new RepairRepository();

    private static final IOServiceInterface ioService = new IOService(ownerRepo, propertyRepo, repairRepo);
    private static final OwnerServiceInterface ownerService = new OwnerService(propertyRepo);
    private static final AdminServiceInterface adminService = new AdminService();

    private static Owner owner;

    public static void dataPopulation() {
        System.out.println("|-------------------START-------------------|");

        System.out.println();
        System.out.println("|-------------------Read Data from csv files-------------------|");
        ioService.readOwnersCsv("data" + separator + "owner.csv");
        ioService.readPropertiesCsv("data" + separator + "property.csv");
        ioService.readRepairsFromCsv("data" + separator + "repair.csv");

        System.out.println();
        System.out.println("|-------------------Reading Completing-------------------|");
    }

//    public static void saveChanges() {
//        System.out.println();
//        System.out.println("|-------------------Save Data from csv files-------------------|");
//
//        ioService.saveOwnersToCsv("data" + separator + "owners.csv");
//        ioService.savePropertiesToCsv("data" + separator + "properties.csv");
//        ioService.saveRepairsToCsv("data" + separator + "repairs.csv");
//    }

}
