package gr.ed.technikon.Repositories;

import gr.ed.technikon.models.Repair;
import gr.ed.technikon.utility.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class RepairRepository implements RepairRepositoryInterface<Repair, Long, Date> {

    private final EntityManager entityManager;

    public RepairRepository() {
        entityManager = JPAUtil.getEntityManager();
    }
    
      @Override
  public Optional<Repair> findById(Long repairId) {
        try {
            Repair repair = entityManager.find(Repair.class, repairId);
            return Optional.ofNullable(repair);
        } catch (Exception e) {
            log.debug("Could not find Property with ID: " + repairId);
            entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }

    @Override
    public List<Repair> findByOwnerId(Long ownerId) {
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Repair> query = entityManager.createQuery("SELECT repair FROM Repair repair WHERE p.owner.OwnerId = :ownerId", Repair.class);
            query.setParameter("ownerId", ownerId);
            List<Repair> repairs = query.getResultList();
            entityManager.getTransaction().commit();
            return repairs;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error finding repairs by owner ID: " + ownerId, e);
        }
        return List.of();
    }

    @Override
    public List<Repair> findByDate(Date dateOfStart) {
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Repair> query = entityManager.createQuery("SELECT repair FROM Repair repair WHERE repair.dateOfStart = :dateOfStart", Repair.class);
            query.setParameter("dateOfStart", dateOfStart);
            List<Repair> repairs = query.getResultList();
            entityManager.getTransaction().commit();
            return repairs;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error finding repairs by date: " + dateOfStart, e);
        }
        return List.of();
    }

    @Override
    public List<Repair> findByRangeDates(Date dateOfStart, Date dateOfEnd) {
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Repair> query = entityManager.createQuery("SELECT repair FROM Repair repair WHERE repair.dateOfSubmission BETWEEN :dateOfStart AND :dateOfEnd", Repair.class);
            query.setParameter("dateOfStart", dateOfStart);
            query.setParameter("dateOfEnd", dateOfEnd);
            List<Repair> repairs = query.getResultList();
            entityManager.getTransaction().commit();
            return repairs;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error finding repairs by date range: " + dateOfStart + " to " + dateOfEnd, e);
        }
        return List.of();
    }

    @Override
    public List<Repair> findAll() {
        try {
            TypedQuery<Repair> query = entityManager.createQuery("SELECT r FROM Repair r", Repair.class);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error retrieving all repairs", e);
        }
        return List.of();
    }

    @Override
    public Optional<Repair> save(Repair repair) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(repair);
            entityManager.getTransaction().commit();
            return Optional.of(repair);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error saving repair: " + repair, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Repair> update(Repair repair) {
        try {
            entityManager.getTransaction().begin();
            repair = entityManager.merge(repair);
            entityManager.getTransaction().commit();
            return Optional.of(repair);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error updating repair: " + repair, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long repairId) {
        try {
            Repair repair = entityManager.find(Repair.class, repairId);
            if (repair != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(repair);
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Error deleting repair with ID: " + repairId, e);
        }
        return false;
    }

//    @Override
//    public boolean safeDeleteById(Long repairId) {
//        try {
//            Repair repair = entityManager.find(Repair.class, repairId);
//            if (repair != null) {
//                entityManager.getTransaction().begin();
//                repair.setDeletedRepair(true);
//                entityManager.merge(repair);
//                entityManager.getTransaction().commit();
//                return true;
//            }
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            log.error("Error safely deleting repair with ID: " + repairId, e);
//        }
//        return false;
//    }
}