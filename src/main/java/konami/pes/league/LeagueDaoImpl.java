package konami.pes.league;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class LeagueDaoImpl extends GenericDaoImpl<League, Integer> implements LeagueDao{

	public LeagueDaoImpl() {
		super(League.class);
	}

}
