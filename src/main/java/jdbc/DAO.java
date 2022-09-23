package jdbc;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> getAll();

    Optional<T> get( long id );

    void delete( T t );

    void insert( T t );
}
