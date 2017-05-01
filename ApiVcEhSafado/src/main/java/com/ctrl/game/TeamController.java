package com.ctrl.game;

import java.util.ArrayList;

import com.model.player.PlayerDAO;
import com.model.player.PlayerEntity;
import com.model.player.ResumedPlayer;
import com.model.team.Team;
import com.model.team.TeamDAO;
import com.model.team.TeamEntity;
import com.model.team.TeamPlayerDAO;
import com.model.team.TeamPlayerEntity;

public class TeamController {

	
	public Team convertEntityToTeam(TeamEntity ent) {
		
		Team team = new Team();
		team.setBudget(0); //TODO MOACIR ADICIONAR BUDGET NO ENTITY
		team.setId(ent.getId());
		team.setLuxa(ent.getNomeTecnico());
		team.setName(ent.getNome());	
		
		return team;
	}	
	
	
	public Team getTeamPlayers(int teamId) {

		PlayerController plCtrl = new PlayerController();
		PlayerDAO playerDao = new PlayerDAO();
		TeamPlayerDAO tpDao = new TeamPlayerDAO();		
		TeamDAO teamAcc = new TeamDAO();
		TeamEntity teamEn = teamAcc.getTeam(teamId);		
		Team team = convertEntityToTeam(teamEn);
		ArrayList<TeamPlayerEntity> players = tpDao.getPlayer(teamId);
		
		for (TeamPlayerEntity teamPlayerEntity : players) {
		
			PlayerEntity plen = playerDao.getPlayer(teamPlayerEntity.getIdPlayer());			
			ResumedPlayer rspl = plCtrl.convertPlayerEntityToResumed(plen);
			team.getPlayers().add(rspl);	
			
		}
	
		return team;
		
	}
	
}
