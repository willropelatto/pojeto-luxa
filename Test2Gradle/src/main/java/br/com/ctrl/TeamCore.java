package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.bean.PlayerMO;
import br.com.model.bean.TeamMO;
import br.com.model.repo.TeamRepo;

@Service
public class TeamCore {

	@Autowired
	private TeamRepo teamDao;	
	
	public boolean haveMoney(PlayerMO player) {		
		if (player.getTeam() == null)
			player.setTeam(teamDao.findOne(player.getBid().getTeam()));		
		
		return !(player.getBid().getBidValue() > player.getTeam().getBudget());
	}
	
	public void decreaseBudget(PlayerMO player) {
		if (player.getTeam() == null)
			player.setTeam(teamDao.findOne(player.getBid().getTeam()));
		
		player.getTeam().setBudget(player.getTeam().getBudget() - player.getBid().getBidValue());
		//return teamDao.save(player.getTeam());
	}

	public void increaseBudget(TeamMO team, double value) {
		team.setBudget(team.getBudget() + value);
		
		//return teamDao.save(player.getTeam());
	}	
	
	public Iterable<TeamMO> findAll() {
		return teamDao.findAll();
	}
	
	public void persistTeam(TeamMO team) {
		teamDao.save(team);
	}
	
}
