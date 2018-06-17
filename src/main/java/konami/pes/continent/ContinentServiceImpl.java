package konami.pes.continent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konami.pes.exceptions.NoSuchEntityException;

@Service
@Transactional
public class ContinentServiceImpl implements ContinentService {

	private ContinentDao continentDao;
	
	@Autowired
	public ContinentServiceImpl(ContinentDao continentDao) {
		
		this.continentDao=continentDao;
	}
	@Override
	public List<ContinentDto> readAll() {
		
		return continentDao.readAll().stream().map(continent->convertJpaToModel(continent)).collect(Collectors.toList());
	}
	
	public Optional<ContinentDto> readById(Integer id) {
		
		return Optional.ofNullable(continentDao.readById(id)).map(continent->convertJpaToModel(continent));
	}
	@Override
	public Integer create(ContinentDto continent) {
		
		return continentDao.create(convertModelToJpa(continent));
	}
	@Override
	public void edit(Integer id, ContinentDto continent) throws NoSuchEntityException {
		
		Continent con=continentDao.readById(id);
		if(con!=null){
			con.setName(continent.getName());
			con.setEmblem(continent.getEmblem());
		}
		else{
			throw new NoSuchEntityException("Continent with id= "+id+" does not exist");
		}
	}
	public ContinentDto convertJpaToModel(Continent jpa) {
		ContinentDto dto=new ContinentDto();
		dto.setId(jpa.getId());
		dto.setName(jpa.getName());
		dto.setEmblem(jpa.getEmblem());
		return dto;
	}

	public Continent convertModelToJpa(ContinentDto model) {
		
		Continent jpa=new Continent();
		jpa.setName(model.getName());
		jpa.setEmblem(model.getEmblem());
		return jpa;
	}
}
