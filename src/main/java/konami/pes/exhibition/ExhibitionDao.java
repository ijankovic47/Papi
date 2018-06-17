package konami.pes.exhibition;

import java.util.List;

import konami.pes.persistence.GenericDao;

public interface ExhibitionDao extends GenericDao<Exhibition, Integer>{

	List<Exhibition> readByPlayer(Integer playerId);
}
