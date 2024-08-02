package gr.ed.technikon.Repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T, K, S> {
    Optional<T> findByVatNumber(K vt);
    Optional<T>   findByEmail(S s);
    List<T> findAll();
    Optional<T> save(T t); 
    boolean deleteById(K id);
}

