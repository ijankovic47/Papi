package konami.pes.player;

import org.springframework.stereotype.Repository;

import konami.pes.persistence.GenericDaoImpl;

@Repository
public class PlayerDaoImpl extends GenericDaoImpl<Player, Integer> implements PlayerDao{

	public PlayerDaoImpl() {
		super(Player.class);
	}

}
