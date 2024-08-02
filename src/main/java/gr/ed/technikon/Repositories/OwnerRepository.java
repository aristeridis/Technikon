package gr.ed.technikon.Repositories;

import gr.ed.technikon.models.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OwnerRepository implements OwnerRepositoryInterface<Owner, Long, String> {

    private final EntityManager entityManager;

    public OwnerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Owner> findByVatNumber(Long VatNumber) {
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(getEntityClass(), VatNumber);
            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            log.debug("Could not find an Owner with this VAT ");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(getEntityClass(), email);
            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            log.debug("Could not find Owner with this email");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> save(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            log.debug("Could not save user");
        }
        return Optional.empty();
    }

    // STH TELIKH TO KANOUME KAI VAT
    @Override
    public boolean deleteById(Long ownerId) {
        Owner persistentInstance = entityManager.find(getEntityClass(), ownerId);
        if (persistentInstance != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(persistentInstance);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                log.debug("Could not delete Owner");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Owner> findAll() {
        TypedQuery<Owner> query
                = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    private Class<Owner> getEntityClass() {
        return Owner.class;
    }

    private String getEntityClassName() {
        return Owner.class.getName();
    }

}
