package gr.ed.technikon.Repositories;

import java.util.List;
import java.util.Optional;

public interface RepairRepositoryInterface<T, K, D> {

    List<T> findByOwnerId(K ownerId);

    List<T> findByDate(D date);

    List<T> findByRangeDates(D date1, D date2);

    List<T> findAll();

    Optional<T> update(T t);

    Optional<T> save(T t);

    boolean deleteById(K repairId);
    
    boolean safeDeleteById(K repairId);

}
