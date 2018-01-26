package br.com.model.misc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.model.bean.LeagueMO;
import br.com.model.bean.PlayerAttributeAssociationMO;
import br.com.model.bean.PlayerAttributesMO;
import br.com.model.bean.PlayerMO;
import br.com.model.input.Attributes;
import br.com.model.input.FullPlayer;
import br.com.model.input.League;
import br.com.model.repo.PlayerAttributesRepo;
import br.com.model.repo.PlayerRepo;

@Controller
public class PlayerCore {
	
	@Autowired
	private PlayerAttributesRepo attributesDao;
	@Autowired
	private PlayerRepo playerDao;
	
	private LeagueCore lgCore;
	private ArrayList<League> leagues;
	
	public PlayerMO convertPlayer(FullPlayer fullpl, LeagueMO lg) {
		PlayerMO player = new PlayerMO();
		player.setId(0);
		player.setName(fullpl.getName());
		player.setPosition(fullpl.getPosition());
		player.setBaseId(fullpl.getBaseId());
		player.setRating(fullpl.getRating());		
		player.setLeague(lg);		
		player.setOriginalId(fullpl.getId());
		player.setBid(null); //TODO ver de criar a bid basica
		player.setTeam(null);
		player.setAge(fullpl.getAge());
		player.setHeight(fullpl.getHeight());
		player.setWeight(fullpl.getWeight());
		player.setFoot(fullpl.getFoot());
		player.setAtkWorkRate(fullpl.getAtkWorkRate());
		player.setDefWorkRate(fullpl.getDefWorkRate());
		player.setHeadshotImgUrl(fullpl.getHeadshotImgUrl());
		
		if (fullpl.getClub() != null) {
			player.setClubName(fullpl.getClub().getName());
		} else {
			player.setClubName("Undefined");
		}

		return player;
	}	
	
	public PlayerCore() {
		super();
		
		this.lgCore = new LeagueCore();
		this.leagues = new ArrayList<League>();
	}

	public PageIterator initializeImport() {		
		playerDao.deleteAll();
		return new PageIterator();
	}

	public boolean validPlayer(FullPlayer player) {		
		return ((player.getPlayerType().equals("rare") || player.getPlayerType().equals("standard"))
				&& !player.getColor().isEmpty());		
	}
	
	public PlayerMO persistImportPlayer(FullPlayer fpl) {		
		LeagueMO lg = lgCore.persistLeague(leagues, fpl.getLeague()); 
		PlayerMO player = convertPlayer(fpl, lg);		
		
		for (Attributes attr : fpl.getAttributes()) {	
			String attrName = attr.getName().replaceAll("fut.attribute.", "");
			PlayerAttributesMO plAtt = attributesDao.findOneByName(attrName);
			
			if (plAtt == null) {
				plAtt = new PlayerAttributesMO();
				plAtt.setId(0);
				plAtt.setName(attrName);
			}
										
			PlayerAttributeAssociationMO association = new PlayerAttributeAssociationMO();
			association.setAttribute(plAtt);
			association.setPlayer(player);
			association.setValue(attr.getValue());
			player.getAttributes().add(association);					
			
			attributesDao.save(plAtt);							
								
		}
		
		return playerDao.save(player);			
	}
				
	
}
