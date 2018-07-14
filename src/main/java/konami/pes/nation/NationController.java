package konami.pes.nation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

@RestController
@CrossOrigin
@RequestMapping("/nation")
public class NationController {

	private NationService nationService;

	@Autowired
	public NationController(NationService nationService) {
		this.nationService = nationService;
	}

	@RequestMapping()
	public ResponseEntity<?> readNations(@RequestParam(name="continentId", required=false) Integer continentId) {

		return ResponseEntity.ok(nationService.readNations(continentId));
	}
	
	@RequestMapping(value="/{nationId}", method=RequestMethod.GET)
	public ResponseEntity<?> readById(@PathVariable("nationId") Integer id) throws NoSuchEntityException{
		
		Optional<NationDto> nation=nationService.readById(id);
		if(nation.isPresent()){
			return ResponseEntity.ok(nation.get());
		}
		else{
			throw new NoSuchEntityException("There is no nation with id= "+id);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody@Validated NationDto nation) throws URISyntaxException, FieldErrorException{
		
		Integer id=nationService.create(nation);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(new URI("/nation/"+id));
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/{nationId}", method=RequestMethod.PATCH)
	public ResponseEntity<?> edit(@RequestBody@Validated NationDto nation, @PathVariable("nationId") Integer id) throws NoSuchEntityException{
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
