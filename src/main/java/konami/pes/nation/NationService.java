package konami.pes.nation;

import java.util.List;
import java.util.Optional;

import konami.pes.converters.ModelJpaConverterService;
import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

public interface NationService extends ModelJpaConverterService<Nation, NationDto>{

	List<NationDto> readNations(Integer continentId);
	Integer create(NationDto nation) throws FieldErrorException;
	Optional<NationDto> readById(Integer id);
	void edit(Integer id, NationDto nation) throws NoSuchEntityException;
}
