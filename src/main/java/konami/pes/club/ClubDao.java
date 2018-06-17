package konami.pes.club;

import java.util.List;

import konami.pes.persistence.GenericDao;

public interface ClubDao extends GenericDao<Club, Integer>{

	List<Club> readByLeague(Integer leagueId);
}
