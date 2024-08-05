package gr.ed.technikon.Repositories;

import gr.ed.technikon.models.Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
//import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class PropertyRepository implements PropertyRepositoryInterface<Property, Long> {

    private final EntityManager entityManager;

    public PropertyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Property> findById(Long id) {
        try {
            Property property = entityManager.find(Property.class, id);
            return Optional.ofNullable(property);
        } catch (Exception e) {
            log.debug("Could not find Property with ID: " + id, e);
            entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }
    

//    @Override
//    public List<Property> findByDateRange(Date startDate, Date endDate) {
//        try {
//            TypedQuery<Property> query = entityManager.createQuery(
//                    "SELECT p FROM Property p WHERE p.submissionDate BETWEEN :startDate AND :endDate", Property.class);
//            query.setParameter("startDate", startDate);
//            query.setParameter("endDate", endDate);
//            return query.getResultList();
//        } catch (Exception e) {
//            log.debug("Could not find Properties within date range", e);
//        }
//        return List.of();
//    }
//
//    @Override
//    public List<Property> findByOwnerId(Long ownerId) {
//        try {
//            entityManager.getTransaction().begin();
//            Property property = entityManager.find(getEntityClass(), ownerId);
//            entityManager.getTransaction().commit();
//             return List.of(property);
//        } catch (Exception e) {
//            log.debug("Could not find Properties for Owner ID: " + ownerId);
//            entityManager.getTransaction().rollback();
//        }
//        return null;
//    }
    
      @Override
    public List<Property> findByOwnerId(Long ownerId) {
        try {
            TypedQuery<Property> query = entityManager.createQuery(
                    "SELECT p FROM Property p WHERE p.owner.id = :ownerId", Property.class);
            query.setParameter("ownerId", ownerId);
            return query.getResultList();
        } catch (Exception e) {
            log.debug("Could not find Properties for Owner ID: " + ownerId, e);
        }
        return List.of();
    }

    @Override
    public List<Property> findAll() {
        TypedQuery<Property> query
			= entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
		return query.getResultList();
    }

    @Override
    public Optional<Property> save(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(property);
            entityManager.getTransaction().commit();
            return Optional.of(property);
        } catch (Exception e) {
            log.debug("Could not save Property", e);
            entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Property property = entityManager.find(Property.class, id);
            if (property != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(property);
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            log.debug("Could not delete Property with ID: " + id, e);
            entityManager.getTransaction().rollback();
        }
        return false;
    }
    
    @Override
public Optional<Property> update(Property property) {
    try {
        entityManager.getTransaction().begin();
        Property existingProperty = entityManager.find(Property.class, property.getPropertyId());
        if (existingProperty != null) {
            
            existingProperty.setAddress(property.getAddress());
            existingProperty.setYearOfConstruction(property.getYearOfConstruction());
            existingProperty.setPropertyType(property.getPropertyType());
            existingProperty.setOwner(property.getOwner());
            
            entityManager.merge(existingProperty);
            entityManager.getTransaction().commit();
            return Optional.of(existingProperty);
        } else {
            log.debug("Property with ID: " + property.getPropertyId() + " not found for update.");
        }
    } catch (Exception e) {
        log.debug("Could not update Property", e);
        entityManager.getTransaction().rollback();
    }
    return Optional.empty();
}
    
    private Class<Property> getEntityClass() {
		return Property.class;
	}
	
	private String getEntityClassName() {
		return Property.class.getName();
	}

}
