package konami.pes.nation;

import java.util.List;

import konami.pes.persistence.GenericDao;

public interface NationDao extends GenericDao<Nation, Integer>{

	List<Nation> readNations(Integer continentId);
}
