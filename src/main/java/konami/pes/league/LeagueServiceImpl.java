package konami.pes.league;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;
import konami.pes.nation.Nation;
import konami.pes.nation.NationDao;

@Service
@Transactional
public class LeagueServiceImpl implements LeagueService  {


	private LeagueDao leagueDao;
	private NationDao nationDao;
	
	@Autowired
	public LeagueServiceImpl(LeagueDao leagueDao, NationDao nationDao) {
		this.leagueDao=leagueDao;
		this.nationDao=nationDao;
	}
	
	public List<LeagueDto> readAll() {
		return leagueDao.readAll().stream().map(league->convertJpaToModel(league)).collect(Collectors.toList());
	}
	
	@Override
	public Optional<LeagueDto> readById(Integer id) {
		
		return Optional.ofNullable(leagueDao.readById(id)).map(league->convertJpaToModel(league));
	}
	@Override
	public Integer create(LeagueDto league) throws FieldErrorException {
		
		League jpa=convertModelToJpa(league);
		Optional<Nation> nation=Optional.ofNullable(nationDao.readById(jpa.getNation().getId()));
		if(nation.isPresent()){
			jpa.setNation(nation.get());
			return leagueDao.create(jpa);
		}
		else{
			throw new FieldErrorException("There is no nation with id= "+jpa.getNation().getId());
		}
	}
	@Override
	public void edit(Integer id, LeagueDto league) throws NoSuchEntityException {
		
		League l=leagueDao.readById(id);
		
		if(l==null){
			throw new NoSuchEntityException("There is no league with id= "+id);
		}
		Nation n=nationDao.readById(league.getNation());
		if(n==null){
			throw new NoSuchEntityException("There is no nation with id= "+league.getNation());
		}
			l.setName(league.getName());
			l.setEmblem(league.getEmblem());
			l.setNation(n);
	}

	@Override
	public LeagueDto convertJpaToModel(League jpa) {
		
		LeagueDto dto=new LeagueDto();
		dto.setId(jpa.getId());
		dto.setName(jpa.getName());
		dto.setEmblem(jpa.getEmblem());
		dto.setNation(jpa.getNation().getId());
		return dto;
	}

	@Override
	public League convertModelToJpa(LeagueDto model) {
		
		League jpa=new League();
		jpa.setName(model.getName());
		jpa.setEmblem(jpa.getEmblem());
		Nation nation=new Nation();
		nation.setId(model.getNation());
		jpa.setNation(nation);
		return jpa;
	}
}
