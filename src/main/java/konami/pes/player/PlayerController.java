package konami.pes.player;

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

import konami.pes.exceptions.NoSuchEntityException;

@RestController
@CrossOrigin
@RequestMapping("/player")
public class PlayerController {

	private PlayerService playerService;
	
	@Autowired
	public PlayerController(PlayerService playerService) {
	
		this.playerService=playerService;
	}
	
	@RequestMapping()
	public ResponseEntity<?> readAll(){
		
		return ResponseEntity.ok(playerService.readAll());
	}
	@RequestMapping(value="/{playerId}", method=RequestMethod.GET)
	public ResponseEntity<?> readById(@PathVariable("playerId") String id) throws NoSuchEntityException{
		Optional<PlayerDto> player=playerService.readById(Integer.valueOf(id));
		if(player.isPresent()){
			return ResponseEntity.ok(player.get());
		}
		throw new NoSuchEntityException("Player with id= "+id+" does not exist !");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody PlayerDto player) throws URISyntaxException{
		Integer id=playerService.create(player);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(new URI("/player/"+id));
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value="/{playerId}", method=RequestMethod.PATCH)
	public ResponseEntity<?> edit(@RequestBody PlayerDto player, @PathVariable("playerId") Integer id) throws NoSuchEntityException{
		
		playerService.edit(id, player);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
