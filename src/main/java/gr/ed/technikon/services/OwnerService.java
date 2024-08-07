package gr.ed.technikon.services;

import gr.ed.technikon.Repositories.OwnerRepositoryInterface;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
public class OwnerService implements OwnerServiceInterface {

    private final PropertyRepositoryInterface propertyRepository;
    //private final OwnerRepositoryInterface ownerRepository;

    @Override
    public boolean acceptance(Repair repair) {
        System.out.println(repair.toString());
        System.out.println("Do you accept the proposed repair ?");
        Scanner scanner = new Scanner(System.in);
        boolean acceptance = scanner.nextBoolean();
        return acceptance;

    }

    public OwnerService(PropertyRepositoryInterface propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> getPropertiesByOwnerId(Long ownerId) {
        return propertyRepository.findByOwnerId(ownerId);
    }
    
//     @Override
//    public boolean updateEmail(int ownerId, String email) {
//        Optional ownerR = ownerRepository.findByOwnerId(ownerId);
//        if (ownerR == null ) {
//            return false;
//        }
//        if (email.isBlank()) {
//            return false;
//        }
//        updateEmail(ownerId, email);
//        return true;
//
//    }
    
////    @Override
//    public Owner findOwner(long vatNumber) {
//        Owner ownerR= ownerRepositoryInterface.findVatNumber(vatNumber);
//        if (ownerRetrieved == null ) {
//            return null;
//        }
//        return ownerRetrieved;
//    }
}
     