package konami.pes.continent;

import java.util.List;
import java.util.Optional;

import konami.pes.converters.ModelJpaConverterService;
import konami.pes.exceptions.NoSuchEntityException;

public interface ContinentService extends ModelJpaConverterService<Continent, ContinentDto>{

	 Optional<ContinentDto> readById(Integer id);
	 List<ContinentDto> readAll();
	 Integer create(ContinentDto continent);
	 void edit(Integer id, ContinentDto continent) throws NoSuchEntityException;
}
