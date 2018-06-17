package konami.pes.team;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class TeamDaoImpl extends GenericDaoImpl<Team, Integer> implements TeamDao{

	public TeamDaoImpl() {
		super(Team.class);
	}

}
