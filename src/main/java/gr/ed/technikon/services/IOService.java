package gr.ed.technikon.services;

import gr.ed.technikon.Repositories.OwnerRepositoryInterface;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.Repositories.RepairRepositoryInterface;
import gr.ed.technikon.enums.PropertyType;
import gr.ed.technikon.enums.RepairStatus;
import gr.ed.technikon.enums.RepairType;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
public class IOService implements IOServiceInterface {

    private final OwnerRepositoryInterface<Owner, Long, String> ownerRepository;
    private final PropertyRepositoryInterface<Property, Long> propertyRepository;
    private final RepairRepositoryInterface<Repair, Long, Date> repairRepository;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public IOService(OwnerRepositoryInterface<Owner, Long, String> ownerRepository,
                     PropertyRepositoryInterface<Property, Long> propertyRepository,
                     RepairRepositoryInterface<Repair, Long, Date> repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
    }

    @Override
    public void saveOwnersToCsv(String filename) {
        List<Owner> ownerList = ownerRepository.findAll();
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("id,username,password,vatNumber,name,surname,address,phoneNumber,email");
            for (Owner owner : ownerList) {
                pw.println(String.join(",",
                        String.valueOf(owner.getOwnerId()),
                        owner.getUsername(),
                        owner.getPassword(),
                        String.valueOf(owner.getVatNumber()),
                        owner.getName(),
                        owner.getSurName(),
                        owner.getAddress(),
                        owner.getPhoneNumber(),
                        owner.getEmail()));
            }
        } catch (FileNotFoundException e) {
            log.error("Error writing owners to CSV file '{}'", filename, e);
        }
    }

    @Override
    public void savePropertiesToCsv(String filename) {
        List<Property> propertyList = propertyRepository.findAll();
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("id,address,yearOfConstruction,ownerId,propertyType");
            for (Property property : propertyList) {
                pw.println(String.join(",",
                        String.valueOf(property.getPropertyId()),
                        property.getAddress(),
                        String.valueOf(property.getYearOfConstruction()),
                        String.valueOf(property.getOwner().getOwnerId()),
                        property.getPropertyType().name()));
            }
        } catch (FileNotFoundException e) {
            log.error("Error writing properties to CSV file '{}'", filename, e);
        }
    }

    @Override
    public void saveRepairsToCsv(String filename) {
        List<Repair> repairList = repairRepository.findAll();
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("id,propertyId,shortDescription,dateOfSubmission,descriptionOfWork,proposedDateOfStart,proposedDateOfEnd,proposedCost,acceptance,dateOfStart,dateOfEnd,repairType,repairStatus");
            for (Repair repair : repairList) {
                pw.println(String.join(",",
                        String.valueOf(repair.getRepairId()),
                        String.valueOf(repair.getProperty().getPropertyId()),
                        repair.getShortDescription(),
                        formatDate(repair.getDateOfSubmission()),
                        repair.getDescriptionOfWork(),
                        formatDate(repair.getProposedDateOfStart()),
                        formatDate(repair.getProposedDateOfEnd()),
                        repair.getProposedCost().toString(),
                        String.valueOf(repair.isAcceptance()),
                        formatDate(repair.getDateOfStart()),
                        formatDate(repair.getDateOfEnd()),
                        repair.getRepairType().name(),
                        repair.getRepairStatus().name()));
            }
        } catch (FileNotFoundException e) {
            log.error("Error writing repairs to CSV file '{}'", filename, e);
        }
    }

    @Override
    public int readOwnersCsv(String filename) {
        int rowsRead = 0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
//                if (data.length < 9) continue; 

                Owner owner = new Owner();
                owner.setUsername(data[1].trim());
                owner.setPassword(data[2].trim());
                owner.setVatNumber(parseLong(data[3]));
                owner.setName(data[4].trim());
                owner.setSurName(data[5].trim());
                owner.setAddress(data[6].trim());
                owner.setPhoneNumber(data[7].trim());
                owner.setEmail(data[8].trim());
                ownerRepository.save(owner);
                rowsRead++;
            }
        } catch (FileNotFoundException e) {
            log.error("Error reading owners from CSV file '{}'", filename, e);
        }
        return rowsRead;
    }

    @Override
    public int readPropertiesCsv(String filename) {
        int rowsRead = 0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                //if (data.length < 5) continue; 

                Property property = new Property();
//                property.setPropertyId(parseLong(data[0]));
                property.setAddress(data[1].trim());
                property.setYearOfConstruction(parseInt(data[2]));

                Optional<Owner> owner = ownerRepository.findByVatNumber(parseLong(data[3]));
                owner.ifPresent(property::setOwner);

                property.setPropertyType(PropertyType.valueOf(data[4].trim()));
                propertyRepository.save(property);
                rowsRead++;
            }
        } catch (FileNotFoundException e) {
            log.error("Error reading properties from CSV file '{}'", filename, e);
        } catch (IllegalArgumentException e) {
            log.error("Invalid property type or VAT number format in file '{}'", filename, e);
        }
        return rowsRead;
    }

    @Override
    public int readRepairsFromCsv(String filename) {
        int rowsRead = 0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length < 14) continue; // Skip malformed lines

                Repair repair = new Repair();
//                repair.setRepairId(parseLong(data[0]));

                Optional<Property> property = propertyRepository.findById(parseLong(data[1]));
                property.ifPresent(repair::setProperty);

                repair.setShortDescription(data[2].trim());
                repair.setDateOfSubmission(parseDate(data[3]));
                repair.setDescriptionOfWork(data[4].trim());
                repair.setProposedDateOfStart(parseDate(data[5]));
                repair.setProposedDateOfEnd(parseDate(data[6]));
                repair.setProposedCost(new BigDecimal(data[7].trim()));
                repair.setAcceptance(Boolean.parseBoolean(data[8]));
                repair.setDateOfStart(parseDate(data[9]));
                repair.setDateOfEnd(parseDate(data[10]));
                repair.setRepairType(RepairType.valueOf(data[11].trim()));
                repair.setRepairStatus(RepairStatus.valueOf(data[12].trim()));
                
                repairRepository.save(repair);
                rowsRead++;
            }
        } catch (FileNotFoundException e) {
            log.error("Error reading repairs from CSV file '{}'", filename, e);
        } catch (ParseException e) {
            log.error("Error parsing date in repairs CSV file '{}'", filename, e);
        } catch (IllegalArgumentException e) {
            log.error("Invalid repair type or status in file '{}'", filename, e);
        }
        return rowsRead;
    }

    private Date parseDate(String dateStr) throws ParseException {
        return dateStr.isEmpty() ? null : DATE_FORMAT.parse(dateStr);
    }

    private long parseLong(String value) {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            log.warn("Invalid number format for value '{}'", value);
            return -1; // Default or error value
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            log.warn("Invalid number format for value '{}'", value);
            return -1; 
        }
    }

    private String formatDate(Date date) {
        return date == null ? "" : DATE_FORMAT.format(date);
    }
}