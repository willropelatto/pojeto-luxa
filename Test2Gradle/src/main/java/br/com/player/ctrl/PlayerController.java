package br.com.player.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.player.model.Player;
import br.com.player.model.PlayerFilter;
import br.com.player.model.PlayerRepository;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerRepository plDao;		
	

	@CrossOrigin	
	@RequestMapping("/player/league/{league}")
	public Page<Player> getLeaguePlayers(@PathVariable("league") Integer league, @PageableDefault(value = 50) Pageable pageable) { 			
		return plDao.findByIdLeague(league, pageable);
	}	
	
	
	@CrossOrigin	
	@RequestMapping("/player/get/{playerId}")
	public Player getPlayerFromId(@PathVariable("playerId") Integer playerId) {
		return plDao.findOne(playerId);						
	}	
	
	
	@CrossOrigin	
	@RequestMapping("/player/getByName/{name}")
	public Page<Player> getPlayerByName(@PathVariable("name") String name,  @PageableDefault(value = 50) Pageable pageable) {
		return plDao.findByNameIgnoreCase(name, pageable);						
	}	
	
	
	@CrossOrigin
	@RequestMapping("/player/list")
	public Page<Player> getAllPlayers(@PageableDefault(value = 50) Pageable pageable) {			
		return plDao.findAll(pageable);
	}
	
	@CrossOrigin	
	@RequestMapping(value="/player/getPlayers")
	public Page<Player> getPlayers(@RequestBody PlayerFilter filter, @PageableDefault(value = 50) Pageable pageable) {		
		return plDao.findByPositionIgnoreCaseAndRatingGreaterThanEqual(filter.getPosition(), filter.getRating(), pageable);
	}
	

}
