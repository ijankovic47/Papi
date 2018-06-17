package konami.pes.persistence;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

	T readById(PK id);
	PK create(T entity);
	List<T> readAll();
//	void update(T entity);
//	void delete(T entity);
}
