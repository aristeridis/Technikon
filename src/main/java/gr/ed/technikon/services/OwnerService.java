package gr.ed.technikon.services;

import gr.ed.technikon.exceptions.CustomException;
import gr.ed.technikon.exceptions.OwnerNotFoundException;
import gr.ed.technikon.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerService {


    private List<Owner> owners; 

    public OwnerService() {
        this.owners = new ArrayList<>();
    }

    // Create a new owner
    public Owner createOwner(long vatNumber, int ownerId, String name, String surname, String address, String phoneNumber, String email, String username, String password, int id, int ownerid) throws CustomException {
        if (owners.stream().anyMatch(owner -> owner.getVatNumber() == vatNumber)) {
            throw new CustomException("VAT number already exists");
        }
        if (owners.stream().anyMatch(owner -> owner.getEmail().equals(email))) {
            throw new CustomException("Email already exists");
        }
        
        Owner owner = new Owner(vatNumber, ownerId, name, surname, address, phoneNumber, email, username, password);
        owners.add(owner);
        return owner;
    }

        // Searching for an owner by VAT number
    public Owner searchOwnerByVATNumber(long vatNumber) {
        return owners.stream()
                     .filter(owner -> owner.getVatNumber() == vatNumber)
                     .findFirst()
                     .orElseThrow(() -> new OwnerNotFoundException("Owner with VAT number " + vatNumber + " not found"));
    }

    // Searching for an owner by email
    public Owner searchOwnerByEmail(String email) {
        return owners.stream()
                     .filter(owner -> owner.getEmail().equals(email))
                     .findFirst()
                     .orElseThrow(() -> new OwnerNotFoundException("Owner with email " + email + " not found"));
    }
    
    
   // Updating Owner
    public void updateOwner(long vatNumber, String address, String email, String password) {
        Owner owner = owners.stream()
                            .filter(o -> o.getVatNumber() == vatNumber)
                            .findFirst()
                            .orElseThrow(() -> new OwnerNotFoundException("Owner not found"));
        
        owner.setAddress(address);
        owner.setEmail(email);
        owner.setPassword(password);
    }

    // Delete an owner
    public void deleteOwner(long vatNumber) {
        Owner owner = owners.stream()
                            .filter(o -> o.getVatNumber() == vatNumber)
                            .findFirst()
                            .orElseThrow(() -> new OwnerNotFoundException("Owner not found"));
        
        owners.remove(owner);
    }



    // List all owners // logika tha allajei molis valoume vash
    public List<Owner> listAllOwners() {
        return owners;
    }
}