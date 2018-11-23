package br.com.pofexo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pofexo.model.bean.PlayerMO;
import br.com.pofexo.model.misc.PlayerFilter;

@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerCore playerCore;

	@CrossOrigin
	@GetMapping("/get/{playerId}")
	public PlayerMO getPlayerFromId(@PathVariable("playerId") Integer playerId) {		
		return playerCore.findPlayerById(playerId);		
	}

	@CrossOrigin
	@GetMapping("/getByName/{name}")
	public Page<PlayerMO> getPlayerByName(@PathVariable("name") String name,
			@PageableDefault(value = 20) Pageable pageable) {		
		return playerCore.findPlayersByName(name, pageable);
	}

	@CrossOrigin
	@GetMapping("/list")
	public Page<PlayerMO> getAllPlayers(@PageableDefault(value = 5) Pageable pageable) {		
		return playerCore.findAllPlayers(pageable);
	}

	@CrossOrigin
	@PostMapping("/getPlayers")
	public Page<PlayerMO> getPlayers(@RequestBody PlayerFilter input,
			@PageableDefault(value = 50) Pageable pageable) {
		return playerCore.findPlayers(input, pageable);
	}

}
