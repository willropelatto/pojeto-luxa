package br.com;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.model.in.FullPlayer;
import com.model.in.League;
import com.model.in.PageIn;

import br.com.league.model.LeagueTite;
import br.com.league.model.LeagueTiteRepository;
import br.com.player.model.PlayerTite;
import br.com.player.model.PlayerTiteRepository;

@RestController
public class EnterData {

	@Autowired
	private PlayerTiteRepository playerDao;	
	@Autowired
	private LeagueTiteRepository leagueDao;	
	
	
	private PlayerTite convertPlayer(FullPlayer fullpl) {
		PlayerTite player = new PlayerTite();
		Integer idLeague = new Integer(fullpl.getLeague().getId());
		player.setId(0);
		player.setName(fullpl.getName());
		player.setPosition(fullpl.getPosition());
		player.setBaseId(fullpl.getBaseId());
		player.setRating(fullpl.getRating());
		player.setIdLeague(idLeague); 
		player.setOriginalId(fullpl.getId());
		player.setHasBid(false);
		
		return player;
	}		


	@CrossOrigin
	@RequestMapping("/main/update")	
	public void UpdateStoredPlayers() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=0");						
		String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);	    
		Gson gson = new Gson();	    
		PageIn pg = gson.fromJson(json, PageIn.class);		
		PlayerTite player;
		FullPlayer fullPlay;	  
		ArrayList<League> leagues = new ArrayList<League>();
		ArrayList<Integer> list = new ArrayList<>();
		int baseId = 0;
	
		while ((pg != null) && (pg.getTotalPages() >= pg.getPage())) {
			for (int i = 0; i < pg.getItems().length; i++) {
				fullPlay = pg.getItems()[i];    		

				if ((fullPlay.getPlayerType().equals("rare") || fullPlay.getPlayerType().equals("standard")) &&
						!fullPlay.getColor().isEmpty())	{
					League league = fullPlay.getLeague();
					
					if (!leagues.contains(league)) {
						if (leagueDao.findOneByOriginalId(league.getId()) == null) {
							leagueDao.save(convertLeague(league));
						}
						leagues.add(league);
					}

					
					player = convertPlayer(fullPlay);
					baseId = player.getBaseId();
					if (!list.contains(baseId)){
					  System.out.println(baseId);
					  list.add(baseId);
					  playerDao.save(player);		
					}
				}	    		

			}

			int pageToGo = pg.getPage()+1;	   

			if (pg.getPage() < pg.getTotalPages()) {	    	
				System.out.println("Indo para a pagina:"+pageToGo);
				try {
					target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page="+pageToGo);
					json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
					gson = new Gson();
					pg = gson.fromJson(json, PageIn.class);						
				} catch (Exception e) { 
					System.out.println("ERRO "+e.getMessage());
				}

			}
		}
		
//		return 1;
	}			
	
	private LeagueTite convertLeague(League league) {		
		LeagueTite entity = new LeagueTite();				
		entity.setId(0);
		entity.setAbbrName(league.getAbbrName());
		entity.setImgUrl(league.getImgUrl());
		entity.setName(league.getName());
		entity.setOriginalId(league.getId());
		
		return entity;	
	}	
	
}
