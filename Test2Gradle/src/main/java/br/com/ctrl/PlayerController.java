package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.OperationCriteria;
import br.com.model.PlayerFilter;
import br.com.model.PlayerSpecification;
import br.com.model.PlayerTite;
import br.com.model.PlayerTiteRepository;
import br.com.model.SearchCriteria;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerTiteRepository plDao;

	@CrossOrigin
	@GetMapping("/league/{league}")
	public Page<PlayerTite> getLeaguePlayers(@PathVariable("league") Integer league,
			@PageableDefault(value = 50) Pageable pageable) {
		return plDao.findByIdLeague(league, pageable);
	}

	@CrossOrigin
	@GetMapping("/get/{playerId}")
	public PlayerTite getPlayerFromId(@PathVariable("playerId") Integer playerId) {
		return plDao.findOne(playerId);
	}

	@CrossOrigin
	@GetMapping("/getByName/{name}")
	public Page<PlayerTite> getPlayerByName(@RequestBody PlayerFilter filter,
			@PageableDefault(value = 50) Pageable pageable) {
		return plDao.findByNameIgnoreCase(filter.getName(), pageable);
	}

	@CrossOrigin
	@GetMapping("/list")
	public Page<PlayerTite> getAllPlayers(@PageableDefault(value = 50) Pageable pageable) {
		return plDao.findAll(pageable);
	}


	@CrossOrigin
	@PostMapping("/getPlayers")
	public Page<PlayerTite> getPlayers(@RequestBody PlayerFilter filter,
			@PageableDefault(value = 50) Pageable pageable) {		

		Specifications<PlayerTite> teste = null;
		PlayerSpecification rat = new PlayerSpecification(new SearchCriteria("rating", OperationCriteria.GREATER_THAN_EQUALS, filter.getRating()));
		teste = Specifications.where(rat);

		if (!filter.getName().isEmpty()) {
			PlayerSpecification filterName = new PlayerSpecification(new SearchCriteria("name", OperationCriteria.CONTAINING, filter.getName()));	
			teste = Specifications.where(teste).and(filterName);
		}

		if (filter.getLeague() > 0) {
			PlayerSpecification filterLeague = new PlayerSpecification(new SearchCriteria("idLeague", OperationCriteria.EQUAL, filter.getLeague()));
			teste = Specifications.where(teste).and(filterLeague);
		}

		if (!filter.getPosition().isEmpty()) { 
			PlayerSpecification filterPos = new PlayerSpecification(new SearchCriteria("position", OperationCriteria.EQUAL, filter.getPosition()));
			teste = Specifications.where(teste).and(filterPos);
		}

		return plDao.findAll(teste, pageable);
	}

	@CrossOrigin
	@GetMapping("/getByLeague")
	public Page<PlayerTite> getPlayersByLeague(@RequestBody PlayerFilter filter,
			@PageableDefault(value = 50) Pageable pageable) {
		//		LeagueTite lgTite = leagueDao.findOneByName(filter.getLeague());
		//		return plDao.findByIdLeague(lgTite.getId(), pageable);

		return null;
	}

}
