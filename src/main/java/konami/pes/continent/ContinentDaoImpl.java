package konami.pes.continent;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class ContinentDaoImpl extends GenericDaoImpl<Continent, Integer> implements ContinentDao{

	public ContinentDaoImpl() {
		super(Continent.class);
	}
}
