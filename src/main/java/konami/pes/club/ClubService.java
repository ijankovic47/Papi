package konami.pes.club;

import java.util.List;
import java.util.Optional;

import konami.pes.converters.ModelJpaConverterService;
import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

public interface ClubService extends ModelJpaConverterService<Club, ClubDto>{

	List<ClubDto> readByLeague(Integer leagueId);
	Integer create(ClubDto club) throws FieldErrorException;
	Optional<ClubDto> readById(Integer id);
	void edit(Integer id, ClubDto club) throws NoSuchEntityException;
}
