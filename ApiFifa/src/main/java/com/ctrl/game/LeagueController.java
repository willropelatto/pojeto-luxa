package com.profexo.controller.game;

import java.util.ArrayList;

import com.profexo.model.player.League;
import com.profexo.model.player.LeagueDAO;

public class LeagueController {

	public void ShowLeagues() {
		
		LeagueDAO ldao = new LeagueDAO();		
		ArrayList<League> list = ldao.getItems();
		
		//TODO jogar para rest no futuro		
	}
	
	
}
