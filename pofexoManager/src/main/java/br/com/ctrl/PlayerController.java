package br.com.ctrl;

import static br.com.model.bean.QPlayerMO.playerMO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.model.bean.PlayerMO;
import br.com.model.misc.PlayerFilter;
import br.com.model.repo.PlayerRepo;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerRepo plDao;

	@CrossOrigin
	@GetMapping("/get/{playerId}")
	public PlayerMO getPlayerFromId(@PathVariable("playerId") Integer playerId) {		
		return plDao.findOne(playerId);		
	}

	@CrossOrigin
	@GetMapping("/getByName/{name}")
	public Page<PlayerMO> getPlayerByName(@RequestBody PlayerFilter filter,
			@PageableDefault(value = 20) Pageable pageable) {
		return plDao.findByNameIgnoreCase(filter.getName(), pageable);
	}

	@CrossOrigin
	@GetMapping("/list")
	public Page<PlayerMO> getAllPlayers(@PageableDefault(value = 50) Pageable pageable) {		
		return plDao.findAll(new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Direction.ASC, "id")));
	}

	@CrossOrigin
	@PostMapping("/getPlayers")
	public Page<PlayerMO> getPlayers(@RequestBody PlayerFilter input,
			@PageableDefault(value = 50) Pageable pageable) {

		BooleanExpression expr = playerMO.rating.gt(input.getRating());		

		if (input.getRatingend() > 0) 
			expr = expr.and(playerMO.rating.lt(input.getRatingend()));
		
		if (!input.getName().isEmpty())
			expr = expr.and(playerMO.name.containsIgnoreCase(input.getName()));

		if (input.getLeague() > 0) {			
			expr = expr.and(playerMO.league.id.eq(input.getLeague()));
		}

		if (!input.getPosition().isEmpty()) 
			expr = expr.and(playerMO.position.eq(input.getPosition()));
		
		return plDao.findAll(expr, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Direction.ASC, "id")));
	}

}
