package konami.pes.club;

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
@RequestMapping("/club")
public class ClubController {

	private ClubService clubService;
	
	@Autowired
	public ClubController(ClubService clubService) {
		this.clubService=clubService;
	}
	
	@RequestMapping()
	public ResponseEntity<?> readByLeague(@RequestParam("leagueId") Integer id){
		
		return ResponseEntity.ok(clubService.readByLeague(id));
	}
	
	@RequestMapping(value="/{clubId}", method=RequestMethod.GET)
	public ResponseEntity<?> readById(@PathVariable("clubId") Integer id) throws NoSuchEntityException{
		
		Optional<ClubDto> club=clubService.readById(id);
		if(club.isPresent()){
			return ResponseEntity.ok(club.get());
		}
		else{
			throw new NoSuchEntityException("there is no club with id= "+id);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody@Validated ClubDto club) throws URISyntaxException, FieldErrorException{
		
		Integer id=clubService.create(club);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(new URI("/club/"+id));
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{clubId}", method=RequestMethod.PATCH)
	public ResponseEntity<?> edit(@RequestBody@Validated ClubDto club, @PathVariable("clubId") Integer id) throws NoSuchEntityException{
		
		clubService.edit(id, club);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
