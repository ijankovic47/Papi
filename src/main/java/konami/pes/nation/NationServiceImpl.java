package konami.pes.nation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konami.pes.continent.Continent;
import konami.pes.continent.ContinentDao;
import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

@Service
@Transactional
public class NationServiceImpl implements NationService {

	private NationDao nationDao;
	private ContinentDao continentDao;

	@Autowired
	public NationServiceImpl(NationDao nationDao, ContinentDao continentDao) {

		this.continentDao = continentDao;
		this.nationDao = nationDao;
	}
	
	@Override
	public List<NationDto> readNations(Integer continentId) {

		return nationDao.readNations(continentId).stream().map(nation -> convertJpaToModel(nation))
				.collect(Collectors.toList());
	}

	@Override
	public Integer create(NationDto nation) throws FieldErrorException {

		Nation jpa = convertModelToJpa(nation);
		Optional<Continent> continent = Optional.ofNullable(continentDao.readById(jpa.getContinent().getId()));
		if (continent.isPresent()) {
			jpa.setContinent(continent.get());
			return nationDao.create(jpa);
		} else {
			throw new FieldErrorException("There is no continent with id= " + jpa.getContinent().getId());
		}
	}
	@Override
	public Optional<NationDto> readById(Integer id) {
		
		return Optional.ofNullable(nationDao.readById(id)).map(n->convertJpaToModel(n));
	}
	@Override
	public void edit(Integer id, NationDto nation) throws NoSuchEntityException {

		Nation n=nationDao.readById(id);
		if(n==null){
			throw new NoSuchEntityException("There is no nation with id= "+id);
		}
		Continent c=continentDao.readById(nation.getContinent());
		if(c==null){
			throw new NoSuchEntityException("There is no continent with id= "+nation.getContinent());
		}
		n.setName(nation.getName());
		n.setEmblem(nation.getEmblem());
		n.setContinent(c);
	}

	@Override
	public NationDto convertJpaToModel(Nation jpa) {

		NationDto dto = new NationDto();
		dto.setId(jpa.getId());
		dto.setName(jpa.getName());
		dto.setEmblem(jpa.getEmblem());
		dto.setContinent(jpa.getContinent().getId());
		return dto;
	}

	@Override
	public Nation convertModelToJpa(NationDto model) {

		Nation jpa = new Nation();
		jpa.setName(model.getName());
		jpa.setEmblem(model.getEmblem());
		Continent cjpa = new Continent();
		cjpa.setId(model.getContinent());
		jpa.setContinent(cjpa);
		return jpa;
	}
}
