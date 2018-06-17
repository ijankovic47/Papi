package konami.pes.club;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class ClubDaoImpl extends GenericDaoImpl<Club, Integer> implements ClubDao{

	public ClubDaoImpl() {
		super(Club.class);
	}

	@Override
	public List<Club> readByLeague(Integer leagueId) {
		
		CriteriaBuilder critreiaBuilder=sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Club> criteria=critreiaBuilder.createQuery(Club.class);
		Root<Club> root=criteria.from(Club.class);
		criteria.select(root);
		criteria.where(critreiaBuilder.equal(root.get("league").get("id"), leagueId));
		return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
	}

}
