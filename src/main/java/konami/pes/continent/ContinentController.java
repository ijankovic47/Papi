package konami.pes.continent;

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
import org.springframework.web.bind.annotation.RestController;

import konami.pes.exceptions.NoSuchEntityException;

@RestController
@CrossOrigin
@RequestMapping("/continent")
public class ContinentController {

	private ContinentService continentService;

	@Autowired
	public ContinentController(ContinentService continentService) {
		this.continentService = continentService;
	}

	@RequestMapping()
	public ResponseEntity<?> readAll(){
		
		return ResponseEntity.ok(continentService.readAll());
	}
	
	@RequestMapping(value = "/{continentId}", method = RequestMethod.GET)
	public ResponseEntity<?> getContinentById(@PathVariable("continentId") String id) throws NoSuchEntityException {

		Optional<ContinentDto> continent=continentService.readById(Integer.valueOf(id));
		if(continent.isPresent()){
			return ResponseEntity.ok(continentService.readById(Integer.valueOf(id)).get());
		}
		throw new NoSuchEntityException("Continent with id= "+id+" dies not exist !");
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody@Validated ContinentDto continent) throws URISyntaxException{
		Integer id=continentService.create(continent);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(new URI("/continent/"+id));
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{continentId}", method=RequestMethod.PATCH)
	public ResponseEntity<?> edit(@RequestBody@Validated ContinentDto continent, @PathVariable("continentId") Integer id) throws NoSuchEntityException{
		
		continentService.edit(id, continent);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
