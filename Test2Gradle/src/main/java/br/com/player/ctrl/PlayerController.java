package br.com.player.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.league.model.LeagueTite;
import br.com.league.model.LeagueTiteRepository;
import br.com.player.model.PlayerFilter;
import br.com.player.model.PlayerTite;
import br.com.player.model.PlayerTiteRepository;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerTiteRepository plDao;	
	@Autowired
	private LeagueTiteRepository leagueDao;
	

	@CrossOrigin
	@GetMapping("/league/{league}")
	public Page<PlayerTite> getLeaguePlayers(@PathVariable("league") Integer league, @PageableDefault(value = 50) Pageable pageable) { 			
		return plDao.findByIdLeague(league, pageable);
	}	
	
	
	@CrossOrigin	
	@GetMapping("/get/{playerId}")
	public PlayerTite getPlayerFromId(@PathVariable("playerId") Integer playerId) {
		return plDao.findOne(playerId);						
	}	
	
	
	@CrossOrigin	
	@GetMapping("/getByName/{name}")
	public Page<PlayerTite> getPlayerByName(@RequestBody PlayerFilter filter, @PageableDefault(value = 50) Pageable pageable) {	
		return plDao.findByNameIgnoreCase(filter.getName(), pageable);						
	}	
	
	
	@CrossOrigin
	@GetMapping("/list")
	public Page<PlayerTite> getAllPlayers(@PageableDefault(value = 50) Pageable pageable) {			
		return plDao.findAll(pageable);
	}
	
	@CrossOrigin	
	@GetMapping("/getPlayers")
	public Page<PlayerTite> getPlayers(@RequestBody PlayerFilter filter, @PageableDefault(value = 50) Pageable pageable) {	
		if (filter.getName().trim().length() > 0) {
			return plDao.findByNameIgnoreCase(filter.getName(), pageable);
		} else if (filter.getLeague().trim().length() > 0){
			LeagueTite lgTite = leagueDao.findOneByName(filter.getLeague());
			return plDao.findByIdLeague(lgTite.getId(), pageable);		
		} else if (filter.getPosition().trim().length() > 0) {
			return plDao.findByPositionIgnoreCaseAndRatingGreaterThanEqual(filter.getPosition(), filter.getRating(), pageable);
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping("/list")
	@RequestMapping(value="/player/getByLeague")
	public Page<PlayerTite> getPlayersByLeague(@RequestBody PlayerFilter filter, @PageableDefault(value = 50) Pageable pageable) {		
		LeagueTite lgTite = leagueDao.findOneByName(filter.getLeague());
		return plDao.findByIdLeague(lgTite.getId(), pageable);
	}
	


}
