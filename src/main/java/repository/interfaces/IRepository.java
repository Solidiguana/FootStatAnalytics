package repository.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {
    List<T> findAll();
    T findById(int id);
    boolean save(T entity);
    T mapRow(ResultSet rs) throws SQLException;
}

