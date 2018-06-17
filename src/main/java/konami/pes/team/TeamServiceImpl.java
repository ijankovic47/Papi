package konami.pes.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService{

	private TeamDao teamDao;
	
	@Autowired
	public TeamServiceImpl(TeamDao teamDao) {
		this.teamDao=teamDao;
	}
	
	@Override
	public Optional<TeamDto> readById(Integer id) {
		
		return Optional.ofNullable(teamDao.readById(id)).map(team->convertJpaToModel(team));
	}

	@Override
	public TeamDto convertJpaToModel(Team jpa) {
		
		TeamDto dto=new TeamDto();
		dto.setId(jpa.getId());
		dto.setName(jpa.getName());
		dto.setEmblem(jpa.getEmblem());
		return dto;
	}

	@Override
	public Team convertModelToJpa(TeamDto model) {
		// TODO Auto-generated method stub
		return null;
	}

}
