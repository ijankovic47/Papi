package konami.pes.exhibition;

import java.util.List;

import konami.pes.converters.ModelJpaConverterService;
import konami.pes.exceptions.FieldErrorException;

public interface ExhibitionService extends ModelJpaConverterService<Exhibition, ExhibitionDto>{

	List<ExhibitionDto> readByPlayer(Integer playerId);
	Integer create(ExhibitionDto exhibition) throws FieldErrorException;
}
