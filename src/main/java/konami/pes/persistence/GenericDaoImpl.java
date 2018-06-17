package konami.pes.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>{

	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Class<T> type;
	
	public GenericDaoImpl(Class<T> type) {
		this.type=type;
	}
	
	public PK create(T entity){
		PK id = (PK) sessionFactory.getCurrentSession().save(entity);
		return id;
	}
	public T readById(PK id) {
		
		return sessionFactory.getCurrentSession().get(type, id);
	}
	public List<T> readAll() {

		CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria=criteriaBuilder.createQuery(this.type);
		Root<T> root=criteria.from(this.type);
		criteria.select(root);
		return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
	}
}
