package konami.pes.player;

import java.util.List;
import java.util.Optional;

import konami.pes.converters.ModelJpaConverterService;
import konami.pes.exceptions.NoSuchEntityException;

public interface PlayerService extends ModelJpaConverterService<Player, PlayerDto>{

	List<PlayerDto> readAll();
	Optional<PlayerDto> readById(Integer id);
	Integer create(PlayerDto player);
	void edit(Integer id, PlayerDto player) throws NoSuchEntityException;
}
