package gr.ed.technikon.Repositories;

//import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PropertyRepositoryInterface<T, K> {
    
    Optional<T> findById(K id);
    
//    List<T> findByDateRange(Date startDate, Date endDate);

    List<T> findByOwnerId(K ownerId);

    List<T> findAll();

    Optional<T> save(T property);

    boolean deleteById(K id);
    
    Optional<T> update(T entity);
}
