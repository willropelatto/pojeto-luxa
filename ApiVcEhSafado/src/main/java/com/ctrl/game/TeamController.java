package com.ctrl.game;

import java.util.ArrayList;
import java.util.List;

import com.model.dao.TeamDAO;
import com.model.entity.TeamEntity;
import com.model.in.Team;

public class TeamController {

	private TeamDAO teamDao = new TeamDAO();
	
	public Team convertEntityToTeam(TeamEntity ent) {
		
		Team team = new Team();
		team.setBudget(ent.getBudget()); 
		team.setId(ent.getId());
		team.setManager(ent.getManager());
		team.setName(ent.getName());	
		
		return team;
	}	
	
	public TeamEntity convertTeamToEntity(Team ent) {
		
		TeamEntity team = new TeamEntity();
		team.setBudget(ent.getBudget()); 
		team.setManager(ent.getManager());
		team.setName(ent.getName());	
		team.setIdUser(ent.getIdUser());
		
		return team;
	}	
	
	
	public void registerTeam(Team team) {		
		teamDao.save(this.convertTeamToEntity(team));
	}
	
	public void updateTeam(Team team) {		
		teamDao.update(this.convertTeamToEntity(team));
	}
	
	public Team getTeamFromUser(int idUser){
		TeamEntity teamEntity =teamDao.getTeamFromUser(idUser);
		Team team = this.convertEntityToTeam(teamEntity);
		
		return team;
	}
	
	public List<Team> getTeams() {		
		
		Team team;
		PlayerController plCtrl = new PlayerController();	
		List<Team> ret = new ArrayList<Team>();
		List<TeamEntity> list = teamDao.getList();		
		
		for (TeamEntity teamEntity : list) {
			team = this.convertEntityToTeam(teamEntity);
			team.setPlayers(plCtrl.loadTeamPlayers(team.getId()));
			ret.add(team);
		}
		
		return ret;
	}
}
