package konami.pes.nation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class NationDaoImpl extends GenericDaoImpl<Nation, Integer> implements NationDao {

	public NationDaoImpl() {
		super(Nation.class);
	}

	@Override
	public List<Nation> readNations(Integer continentId) {

		CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Nation> criteria = criteriaBuilder.createQuery(this.type);
		Root<Nation> root = criteria.from(this.type);
		criteria.select(root);
		if (continentId != null) {
			criteria.where(criteriaBuilder.equal(root.get("continent").get("id"), continentId));
		}
		return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
	}

}
