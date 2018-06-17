package konami.pes.league;

import java.util.List;
import java.util.Optional;

import konami.pes.converters.ModelJpaConverterService;
import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

public interface LeagueService extends ModelJpaConverterService<League, LeagueDto> {

	List<LeagueDto> readAll();
	Optional<LeagueDto> readById(Integer id);
	Integer create(LeagueDto league) throws FieldErrorException;
	void edit(Integer id, LeagueDto league) throws NoSuchEntityException;
}
