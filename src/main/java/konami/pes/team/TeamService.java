package konami.pes.team;

import java.util.Optional;

import konami.pes.converters.ModelJpaConverterService;

public interface TeamService extends ModelJpaConverterService<Team, TeamDto>{

	Optional<TeamDto> readById(Integer id);
}
