package konami.pes.club;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;
import konami.pes.league.League;
import konami.pes.league.LeagueDao;

@Service
@Transactional
public class ClubServiceImpl implements ClubService{

	private ClubDao clubDao;
	private LeagueDao leagueDao;
	
	@Autowired
	public ClubServiceImpl(ClubDao clubDao, LeagueDao leagueDao) {
		this.clubDao=clubDao;
		this.leagueDao=leagueDao;
	}
	
	@Override
	public List<ClubDto> readByLeague(Integer leagueId) {
		
		return clubDao.readByLeague(leagueId).stream().map(league->convertJpaToModel(league)).collect(Collectors.toList());
	}
	@Override
	public Integer create(ClubDto club) throws FieldErrorException {
		
		Club jpa=convertModelToJpa(club);
		Optional<League> league=Optional.ofNullable(leagueDao.readById(jpa.getLeague().getId()));
		if(league.isPresent()){
			jpa.setLeague(league.get());
			return clubDao.create(jpa);
		}
		else{
			throw new FieldErrorException("There is no league with id= "+jpa.getLeague().getId());
		}
	}
	
	@Override
	public Optional<ClubDto> readById(Integer id) {
		
		return Optional.ofNullable(clubDao.readById(id)).map(club->convertJpaToModel(club));
	}
	
	@Override
	public void edit(Integer id, ClubDto club) throws NoSuchEntityException {
		
		Club c=clubDao.readById(id);
		if(c==null){
			throw new NoSuchEntityException("There is no club with id= "+id);
		}
		League l=leagueDao.readById(club.getLeague());
		if(l==null){
			throw new NoSuchEntityException("There is no league with id= "+club.getLeague());
		}
		c.setName(club.getName());
		c.setEmblem(club.getEmblem());
		c.setLeague(l);
	}

	@Override
	public ClubDto convertJpaToModel(Club jpa) {
		
		ClubDto dto=new ClubDto();
		dto.setId(jpa.getId());
		dto.setName(jpa.getName());
		dto.setEmblem(jpa.getEmblem());
		dto.setLeague(jpa.getLeague().getId());
		return dto;
	}

	@Override
	public Club convertModelToJpa(ClubDto model) {
		
		Club jpa=new Club();
		jpa.setName(model.getName());
		jpa.setEmblem(model.getEmblem());
		League ljpa=new League();
		ljpa.setId(model.getLeague());
		jpa.setLeague(ljpa);
		return jpa;
	}
}
