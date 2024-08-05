package gr.ed.technikon.services;

import gr.ed.technikon.Repositories.OwnerRepository;
import gr.ed.technikon.Repositories.PropertyRepository;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.Repositories.RepairRepository;
import gr.ed.technikon.exceptions.CustomException;
import gr.ed.technikon.exceptions.OwnerNotFoundException;
import gr.ed.technikon.exceptions.ValidationException;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.models.Repair;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;



@Slf4j
public class OwnerService implements OwnerServiceInterface{
 private final PropertyRepositoryInterface propertyRepository;

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

}



 









/*
@Slf4j
public class OwnerService implements OwnerServiceInterface {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner createOwner(Owner owner) throws ValidationException, CustomException {
        if (owner == null) {
            throw new ValidationException("Owner cannot be null");
        }

        if (ownerRepository.findByEmail(owner.getEmail()).isPresent()) {
            throw new ValidationException("An owner with this email already exists");
        }

        if (ownerRepository.findByVatNumber(owner.getVatNumber()).isPresent()) {
            throw new ValidationException("An owner with this VAT number already exists");
        }

        Optional<Owner> savedOwner = ownerRepository.save(owner);
        if (savedOwner.isEmpty()) {
            throw new CustomException("Error saving owner");
        }
        return savedOwner.get();
    }

    @Override
    public Optional<Owner> findOwnerByVatNumber(Long vatNumber) throws OwnerNotFoundException, CustomException {
        Optional<Owner> owner = ownerRepository.findByVatNumber(vatNumber);
        if (owner.isEmpty()) {
            log.debug("Owner with VAT number {} not found", vatNumber);
            throw new OwnerNotFoundException("Owner with VAT number " + vatNumber + " not found");
        }
        return owner;
    }

    @Override
    public Optional<Owner> findOwnerByEmail(String email) throws OwnerNotFoundException, CustomException {
        Optional<Owner> owner = ownerRepository.findByEmail(email);
        if (owner.isEmpty()) {
            log.debug("Owner with email {} not found", email);
            throw new OwnerNotFoundException("Owner with email " + email + " not found");
        }
        return owner;
    }

    @Override
    public boolean deleteOwnerById(Long ownerId) throws OwnerNotFoundException, CustomException {
        boolean deleted = ownerRepository.deleteById(ownerId);
        if (!deleted) {
            throw new OwnerNotFoundException("Owner with ID " + ownerId + " not found");
        }
        return true;
    }

    @Override
    public List<Owner> findAllOwners() throws CustomException {
        List<Owner> owners = ownerRepository.findAllOwners();
        if (owners.isEmpty()) {
            throw new CustomException("Error retrieving all owners or no owners found");
        }
        return owners;
    }

  @Override
    public Owner updateOwner(Owner owner) throws ValidationException, CustomException {
        if (owner == null) {
            throw new ValidationException("Owner cannot be null");
        }
        Optional<Owner> existingOwner = ownerRepository.findByVatNumber(owner.getVatNumber());
        if (existingOwner.isEmpty()) {
            throw new OwnerNotFoundException("Owner with VAT number " + owner.getVatNumber() + " not found");
        }
        Optional<Owner> updatedOwner = ownerRepository.update(owner);
        if (updatedOwner.isEmpty()) {
            throw new CustomException("Error updating owner");
        }
        return updatedOwner.get();
    }
}

*/