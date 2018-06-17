package konami.pes.league;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import konami.pes.exceptions.FieldErrorException;
import konami.pes.exceptions.NoSuchEntityException;

@RestController
@CrossOrigin
@RequestMapping("/league")
public class LeagueController {

	private LeagueService leagueService;
	
	@Autowired
	public LeagueController(LeagueService leagueService) {

		this.leagueService=leagueService;
	}

	@RequestMapping()
	public ResponseEntity<?> readAll(){
		
		return ResponseEntity.ok(leagueService.readAll());
	}
	
	@RequestMapping(value="/{leagueId}", method=RequestMethod.GET)
	public ResponseEntity<?> readById(@PathVariable("leagueId") String id) throws NoSuchEntityException{
		
		Optional<LeagueDto> league=leagueService.readById(Integer.valueOf(id));
		if(league.isPresent()){
			return ResponseEntity.ok(league.get());
		}
		else{
			throw new NoSuchEntityException("There is no league with id= "+id);
		}
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody LeagueDto league) throws FieldErrorException, URISyntaxException{
		
		Integer id=leagueService.create(league);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(new URI("/league/"+id));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	@RequestMapping(value="/{leagueId}", method=RequestMethod.PATCH)
	public ResponseEntity<?> edit(@RequestBody LeagueDto league, @PathVariable("leagueId") Integer id) throws NoSuchEntityException{
		
		leagueService.edit(id, league);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
