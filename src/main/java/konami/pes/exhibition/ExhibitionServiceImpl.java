package konami.pes.exhibition;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konami.pes.exceptions.FieldErrorException;
import konami.pes.player.Player;
import konami.pes.player.PlayerDao;
import konami.pes.team.Team;
import konami.pes.team.TeamDao;

@Service
@Transactional
public class ExhibitionServiceImpl implements ExhibitionService{

	private ExhibitionDao exhibitionDao;
	private TeamDao teamDao;
	private PlayerDao playerDao;
	
	@Autowired
	public ExhibitionServiceImpl(ExhibitionDao exhibitionDao, TeamDao teamDao, PlayerDao playerDao) {
	
		this.exhibitionDao=exhibitionDao;
		this.teamDao=teamDao;
		this.playerDao=playerDao;
	}
	
	@Override
	public Integer create(ExhibitionDto exhibition) throws FieldErrorException {
		
		Exhibition jpa=convertModelToJpa(exhibition);
		Optional<Player> p1=Optional.ofNullable(playerDao.readById(jpa.getPlayer1().getId()));
		if(p1.isPresent()){
			jpa.setPlayer1(p1.get());
		}
		else{
			throw new FieldErrorException("Player 1 not selected !");
		}
		Optional<Player> p2=Optional.ofNullable(playerDao.readById(jpa.getPlayer2().getId()));
		if(p2.isPresent()){
			jpa.setPlayer2(p2.get());
		}
		else{
			throw new FieldErrorException("Player 2 not selected !");
		}
		Optional<Team> t1=Optional.ofNullable(teamDao.readById(jpa.getTeam1().getId()));
		if(t1.isPresent()){
			jpa.setTeam1(t1.get());
		}
		else{
			throw new FieldErrorException("Team 1 not selected !");
		}
		Optional<Team> t2=Optional.ofNullable(teamDao.readById(jpa.getTeam2().getId()));
		if(t1.isPresent()){
			jpa.setTeam1(t2.get());
		}
		else{
			throw new FieldErrorException("Team 2 not selected !");
		}
		
		return exhibitionDao.create(jpa);
	}
	@Override
	public List<ExhibitionDto> readByPlayer(Integer playerId) {
		
		return exhibitionDao.readByPlayer(playerId).stream().map(exhibition->convertJpaToModel(exhibition)).collect(Collectors.toList());
	}

	@Override
	public ExhibitionDto convertJpaToModel(Exhibition jpa) {
		
		ExhibitionDto dto=new ExhibitionDto();
		dto.setId(jpa.getId());
		dto.setDate(jpa.getDate());
		dto.setScore1(jpa.getScore1());
		dto.setScore2(jpa.getScore2());
		dto.setPlayer1(jpa.getPlayer1().getId());
		dto.setPlayer2(jpa.getPlayer2().getId());
		dto.setTeam1(jpa.getTeam1().getId());
		dto.setTeam2(jpa.getTeam2().getId());
		return dto;
	}
	@Override
	public Exhibition convertModelToJpa(ExhibitionDto model) {
		
		Exhibition jpa=new Exhibition();
		jpa.setDate(model.getDate());
		jpa.setScore1(model.getScore1());
		jpa.setScore2(model.getScore2());
		Player p1=new Player();
		Player p2=new Player();
		Team t1=new Team();
		Team t2=new Team();
		p1.setId(model.getPlayer1());
		p2.setId(model.getPlayer2());
		t1.setId(model.getTeam1());
		t2.setId(model.getTeam2());
		jpa.setPlayer1(p1);
		jpa.setPlayer2(p2);
		jpa.setTeam1(t1);
		jpa.setTeam2(t2);
		return jpa;
	}
}
