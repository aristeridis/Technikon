package gr.ed.technikon.Repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T, K> {
    Optional<T> findById(K id);
    List<T> findAll();
    T save(T t); 
    boolean deleteById(K id);
}

