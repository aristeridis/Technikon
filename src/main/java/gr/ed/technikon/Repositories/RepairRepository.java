package gr.ed.technikon.Repositories;

import gr.ed.technikon.models.Repair;
import gr.ed.technikon.utility.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepairRepository implements RepairInterface<Repair, Long, Date> {

	private final EntityManager entityManager;

	public RepairRepository() {
		 entityManager = JPAUtil.getEntityManager();
	}

	@Override
	public List<Repair> findByOwnerId(Long ownerId) {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Repair> query = entityManager.createQuery("SELECT repair FROM Repair repair WHERE repair.owner.id=:ownerId", Repair.class);
			query.setParameter("ownerId", ownerId);
			entityManager.getTransaction().commit();
			return query.getResultList();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.debug("Could not find an Owner with this ID" + ownerId);
		}
		return List.of();

	}

	@Override
	public List<Repair> findByDate(Date dateOfStart) {
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Repair> query = entityManager.createQuery("SELECT repair FROM Repair repair WHERE repair.dateOfStart=:dateOfStart", Repair.class);
			query.setParameter("dateOfStart", dateOfStart);
			entityManager.getTransaction().commit();
			return query.getResultList();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.debug("Could not find any Repair to that date" + dateOfStart);
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
			entityManager.getTransaction().commit();
			return query.getResultList();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.debug("Could not find any Repair to that date range" + dateOfStart + " " + dateOfEnd);
		}
		return List.of();
	}

	@Override
	public List<Repair> findAll() {
		TypedQuery<Repair> query
			= entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
		return query.getResultList();
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
			log.debug("Could not find Repair to update" + repair.toString());
		}
		return Optional.empty();
	}

	@Override
	public Optional<Repair> update(Repair repair) {
		try {
			entityManager.getTransaction().begin();
			repair.setRepairId(repair.getRepairId());
			repair.setOwner(repair.getOwner());
			repair.setRepairType(repair.getRepairType());
			repair.setDateOfSubmission(repair.getDateOfSubmission());
			repair.setDescriptionOfWork(repair.getDescriptionOfWork());
			repair.setProposedDateOfStart(repair.getProposedDateOfStart());
			repair.setProposedDateOfEnd(repair.getProposedDateOfEnd());
			repair.setProposedCost(repair.getProposedCost());
			repair.setAcceptance(repair.isAcceptance());
			repair.setRepairStatus(repair.getRepairStatus());
			repair.setDateOfStart(repair.getDateOfStart());
			repair.setDateOfStart(repair.getDateOfStart());
			repair.setDateOfEnd(repair.getDateOfEnd());
			entityManager.getTransaction().commit();
			return Optional.of(repair);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			log.debug("Could not find any Repair to that date" + repair.toString());
		}
		return Optional.empty();
	}

	@Override
	public boolean deleteById(Long repairId) {
		Repair persistentInstance = entityManager.find(getEntityClass(), repairId);
		if (persistentInstance != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(persistentInstance);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				log.debug("Could not delete Repair id" + repairId);
				return false;
			}
			return true;
		}
		return false;
	}

	private Class<Repair> getEntityClass() {
		return Repair.class;
	}

	private String getEntityClassName() {
		return Repair.class.getName();
	}
}