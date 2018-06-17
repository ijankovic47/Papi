package konami.pes.exhibition;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class ExhibitionDaoImpl extends GenericDaoImpl<Exhibition, Integer> implements ExhibitionDao{

	public ExhibitionDaoImpl() {
		super(Exhibition.class);
	}

	@Override
	public List<Exhibition> readByPlayer(Integer playerId) {
		
		CriteriaBuilder criteriaBuilder=sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Exhibition> criteria=criteriaBuilder.createQuery(this.type);
		Root<Exhibition> root=criteria.from(this.type);
		criteria.select(root);
		Predicate p1=criteriaBuilder.equal(root.get("player1").get("id"), playerId);
		Predicate p2=criteriaBuilder.equal(root.get("player2").get("id"), playerId);
		Predicate fin=criteriaBuilder.or(p1,p2);
		criteria.where(fin);
		return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
	}

}
