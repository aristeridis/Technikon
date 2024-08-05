package gr.ed.technikon.services;

import gr.ed.technikon.Repositories.OwnerRepository;
import gr.ed.technikon.Repositories.OwnerRepositoryInterface;
import gr.ed.technikon.Repositories.PropertyRepository;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.Repositories.RepairInterface;
import gr.ed.technikon.Repositories.RepairRepository;
import gr.ed.technikon.enums.PropertyType;
import gr.ed.technikon.enums.RepairStatus;
import gr.ed.technikon.enums.RepairType;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOService implements IOServiceInterface {

    private final OwnerRepositoryInterface ownerRepository;
    private final PropertyRepositoryInterface propertyRepository;
    private final RepairInterface repairRepository;
    private final Logger logger;

    public IOService(OwnerRepositoryInterface ownerRepository, PropertyRepositoryInterface propertyRepository, RepairInterface repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
        this.logger = LoggerFactory.getLogger(IOService.class);
    }

    // Saving Owners to CSV
    @Override
    public void saveOwnersToCsv(String filename) {
        List<Owner> ownerList = ownerRepository.findAll();
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("id,username,password,vatNumber,name,surname,address,phoneNumber,email");
            for (Owner owner : ownerList) {
                pw.println(owner.getOwnerId() + ","
                        + owner.getUsername() + ","
                        + owner.getPassword() + ","
                        + owner.getVatNumber() + ","
                        + owner.getName() + ","
                        + owner.getSurName() + ","
                        + owner.getAddress() + ","
                        + owner.getPhoneNumber() + ","
                        + owner.getEmail());
            }
        } catch (FileNotFoundException e) {
            logger.error("Error writing owners to CSV", e);
        }
    }

    // Saving Properties to CSV
    @Override
    public void savePropertiesToCsv(String filename) {
        List<Property> propertyList = propertyRepository.findAll();
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("id,isActive,propertyId,address,yearOfConstruction,ownerId,propertyType");
            for (Property property : propertyList) {
                pw.println(property.getPropertyId() + ","
                        + property.getPropertyId() + ","
                        + property.getAddress() + ","
                        + property.getYearOfConstruction() + ","
                        + property.getOwner().getOwnerId() + ","
                        + property.getPropertyType());
            }
        } catch (FileNotFoundException e) {
            logger.error("Error writing properties to CSV", e);
        }
    }

    // Saving Repairs to CSV
    @Override
    public void saveRepairsToCsv(String filename) {
        List<Repair> repairList = repairRepository.findAll();
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("id,propertyId,shortDescription,ownerId,dateOfSubmission,"
                    + "descriptionOfWork,proposedDateOfStart,proposedDateOfEnd,proposedCost,"
                    + "acceptance,dateOfStart,dateOfEnd,repairType,RepairStatus");
            for (Repair repair : repairList) {
                pw.println(repair.getRepairId() + ","
                        + repair.getProperty().getPropertyId() + ","
                        + repair.getShortDescription() + ","
                        + repair.getOwner().getOwnerId() + ","
                        + repair.getDateOfSubmission() + ","
                        + repair.getDescriptionOfWork() + ","
                        + repair.getProposedDateOfStart() + ","
                        + repair.getProposedDateOfEnd() + ","
                        + repair.getProposedCost() + ","
                        + repair.isAcceptance() + ","
                        + repair.getDateOfStart() + ","
                        + repair.getDateOfEnd() + ","
                        + repair.getRepairType() + ","
                        + repair.getRepairStatus());
            }
        } catch (FileNotFoundException e) {
            logger.error("Error writing repairs to CSV", e);
        }
    }

    // Reading Owners from CSV
    @Override
    public int readOwnersCsv(String filename) {
        int rowsRead = 0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                Owner owner = new Owner();
                owner.setOwnerId(Long.parseLong(data[0].trim()));
                owner.setUsername(data[2].trim());
                owner.setPassword(data[3].trim());
                owner.setVatNumber(Long.parseLong(data[4].trim()));
                owner.setName(data[5].trim());
                owner.setSurName(data[6].trim());
                owner.setAddress(data[7].trim());
                owner.setPhoneNumber(data[8].trim());
                owner.setEmail(data[9].trim());
                ownerRepository.save(owner);
                rowsRead++;
            }
        } catch (FileNotFoundException e) {
            logger.error("Error reading owners from CSV", e);
        }
        return rowsRead;
    }

    // Reading Properties from CSV
    @Override
    public int readPropertiesCsv(String filename) {
        int rowsRead = 0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                Property property = new Property();
                property.setPropertyId(Long.parseLong(data[0].trim()));
                property.setPropertyId(Long.parseLong(data[2].trim()));
                property.setAddress(data[3].trim());
                property.setYearOfConstruction(Integer.parseInt(data[4].trim()));
                Optional<Owner> owner = ownerRepository.findByVatNumber(Long.valueOf(data[5].trim()));
                owner.ifPresent(property::setOwner);
                property.setPropertyType(PropertyType.valueOf(data[6].trim()));
                propertyRepository.save(property);
                rowsRead++;
            }
        } catch (FileNotFoundException e) {
            logger.error("Error reading properties from CSV", e);
        }
        return rowsRead;
    }

    // Reading Repairs from CSV
//    @Override
//    public int readRepairFromCsv(String filename) {
//        int rowsRead = 0;
//        try (Scanner scanner = new Scanner(new File(filename))) {
//            scanner.nextLine(); // Skip header
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] data = line.split(",");
//                Repair repair = new Repair();
//                repair.setRepairId(Long.parseLong(data[0].trim()));
//                Optional<Property> property = propertyRepository.findByPropertyId(Long.parseLong(data[2].trim()));
//                property.ifPresent(repair::setProperty);
//                repair.setShortDescription(data[3].trim());
//                Optional<Owner> owner = ownerRepository.findByOwnerId(Long.parseLong(data[4].trim()));
//                owner.ifPresent(repair::setOwner);
//                repair.setDateOfSubmission(Date.parse(data[5].trim()));
//                repair.setDescriptionOfWork(data[6].trim());
//                repair.setProposedDateOfStart(Date.parse(data[7].trim()));
//                repair.setProposedDateOfEnd(Date.parse(data[8].trim()));
//                repair.setProposedCost(BigDecimal.valueOf(Double.parseDouble(data[9].trim())));
//                repair.setAcceptance(Boolean.parseBoolean(data[10].trim()));
//                repair.setDateOfStart(Date.parse(data[11].trim()));
//                repair.setDateOfEnd(Date.parse(data[12].trim()));
//                repair.setRepairtype(RepairType.valueOf(data[13].trim()));
//                repair.setStatusType(RepairStatus.valueOf(data[14].trim()));
//                repairRepository.save(repair);
//                rowsRead++;
//            }
//        } catch (FileNotFoundException e) {
//            logger.error("Error reading repairs from CSV", e);
//        }
//        return rowsRead;
//    }
}
