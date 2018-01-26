package br.com.model.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.model.bean.PlayerMO;
import br.com.model.bean.TeamMO;
import br.com.model.repo.TeamRepo;

@Controller
public class TeamCore {

	@Autowired
	private TeamRepo teamDao;	
	
	public boolean haveMoney(PlayerMO player) {		
		return !(player.getBid().getBidValue() > player.getTeam().getBudget());
	}
	
	public TeamMO decreaseBudget(PlayerMO player) {
		player.getTeam().setBudget(player.getTeam().getBudget() - player.getBid().getBidValue());
		return teamDao.save(player.getTeam());
	}

	public TeamMO increaseBudget(PlayerMO player) {
		player.getTeam().setBudget(player.getTeam().getBudget() + player.getBid().getBidValue());
		return teamDao.save(player.getTeam());
	}	
	
	public Iterable<TeamMO> findAll() {
		return teamDao.findAll();
	}
	
}
