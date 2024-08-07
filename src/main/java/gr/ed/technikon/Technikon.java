package gr.ed.technikon;

import gr.ed.technikon.services.AdminService;
import gr.ed.technikon.utility.JPAUtil;
import gr.ed.technikon.utility.UseCases;
import jakarta.persistence.EntityManager;
import java.text.ParseException;

public class Technikon {

    public static void main(String[] args) throws ParseException {

        EntityManager m = JPAUtil.getEntityManager();
        System.out.println("Welcome to Technikon");
        System.out.println("1. Admin");
        System.out.println("2. Owner");
        System.out.println("3. Exit");
        UseCases.dataPopulation();
//        UseCases.ownerWithTwoProperties();
//        UseCases.repairsForPropertiesOwner(9L);
//        UseCases.selectPropertiesFromOwnerById(3);
//        UseCases.ownerAcceptanceOfRepairs(true, 3);
//        UseCases.adminGetsPendingRepairs();
//        UseCases.getProposedCost();
//        UseCases.adminchecksTheDates();
        UseCases.adminGetAllStartAndEndDates();
        UseCases.adminGetAllPendingRepairsWithProposedCostAndDates();

        /*   Admin menu
        1. Owner Management
           1. Display All Owners
           2. Create Owner
           3. Search Owner
           4. Update Owner
           5. Delete Owner
        
        2. Property Management
           1.Display All Properties
           2. Create Property
           3. Search Property
           4. Update Property
           5. Delete Property
        
        3. Repair Management
           1. Display All Repairs
           2. Create Repair
           3. Search Repairs
           4. Update Repair
           5. Delete Repair
        
        4. Repair Administration
           1. Propose Repair Cost and Dates
           2. Check Start of Work // den eimai sigouros gi afto
           3. Check End of Work  //  kai afto
        
        5.Reports // isws tha to doume kai mazi
        
        Owner Menu
        1. Profile Management
          1.  View Profile
          2.  Update Profile
          3.  Delete Profile
        
        2. Property Management
          1.  Display My Properties
          2.  Create Property
          3.  Search Property
          4.  Update Property
          5.  Delete Property
        3. Repair Management
          1.  Display My Repairs
          2.  Create Repair
          3.  Search Repairs
          4.  Update Repair
          5.  Delete Repair
        
        4. Reports // to idio kai edw

        oti allo prokuspei to prosthetoume  
         */
    }
}
