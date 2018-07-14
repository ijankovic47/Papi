package konami.pes.exhibition;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import konami.pes.exceptions.FieldErrorException;

@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {

	private ExhibitionService exhibitionService;
	
	@Autowired
	public ExhibitionController(ExhibitionService exhibitionService) {
		
		this.exhibitionService=exhibitionService;
	}
	
	@RequestMapping(value="/{playerId}",method=RequestMethod.GET)
	public ResponseEntity<?> readByPlayer(@PathVariable("playerId") Integer id){
		
		return ResponseEntity.ok(exhibitionService.readByPlayer(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody@Validated ExhibitionDto exhibition) throws FieldErrorException, URISyntaxException{
		
		Integer id=exhibitionService.create(exhibition);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(new URI("/exhibition/"+id));
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
