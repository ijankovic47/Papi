package konami.pes.player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import konami.pes.exceptions.NoSuchEntityException;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

	private PlayerDao playerDao;
	
	@Autowired
	public PlayerServiceImpl(PlayerDao playerDao) {
		this.playerDao=playerDao;
	}
	
	@Override
	public List<PlayerDto> readAll() {
		
		return playerDao.readAll().stream().map(player->convertJpaToModel(player)).collect(Collectors.toList());
	}

	@Override
	public Optional<PlayerDto> readById(Integer id) {
		
		return Optional.ofNullable(playerDao.readById(id)).map(player->convertJpaToModel(player));
	}
	
	@Override
	public PlayerDto convertJpaToModel(Player jpa) {
		
		PlayerDto dto=new PlayerDto();
		dto.setId(jpa.getId());
		dto.setName(jpa.getName());
		dto.setImage(jpa.getImage());
		return dto;
	}

	@Override
	public void edit(Integer id, PlayerDto player) throws NoSuchEntityException {
		
		Player p=playerDao.readById(id);
		if(p!=null){
			p.setName(player.getName());
			p.setImage(player.getImage());
		}
		else{
			throw new NoSuchEntityException("There is no player with id= "+id);
		}
	}

	@Override
	public Player convertModelToJpa(PlayerDto model) {
		
		Player jpa=new Player();
		jpa.setName(model.getName());
		jpa.setImage(model.getImage());
		return jpa;
	}

	@Override
	public Integer create(PlayerDto player) {
		
		return playerDao.create(convertModelToJpa(player));
	}
}
