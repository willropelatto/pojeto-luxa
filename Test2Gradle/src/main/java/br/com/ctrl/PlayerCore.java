package br.com.ctrl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.bean.LeagueMO;
import br.com.model.bean.PlayerAttributeAssociationMO;
import br.com.model.bean.PlayerAttributesMO;
import br.com.model.bean.PlayerMO;
import br.com.model.input.Attributes;
import br.com.model.input.FullPlayer;
import br.com.model.input.League;
import br.com.model.repo.PlayerAttributesRepo;
import br.com.model.repo.PlayerRepo;


@Service
public class PlayerCore {
	
	@Autowired
	private PlayerAttributesRepo attributesDao;
	@Autowired
	private PlayerRepo playerDao;
	@Autowired
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
		
		this.leagues = new ArrayList<League>();
	}

	public boolean validPlayer(FullPlayer player) {		

		
/*
 * 		if (plbase == null) {
			return true;
		}

		Integer ori, dst;

		ori = Integer.parseInt(player.getOriginalId());
		dst = Integer.parseInt(plbase.getOriginalId());

		if (Integer.compare(ori, dst) > 0) {
			player.setId(plbase.getId());
			return true;
		}

		return false;
	}*/		
		
		return ((player.getPlayerType().equals("rare") || player.getPlayerType().equals("standard"))
				&& !player.getColor().isEmpty());			
		
	}
	
	private boolean savePlayer(PlayerMO player) {
		PlayerMO plbase = playerDao.findOneByBaseId(player.getBaseId());
		if (plbase != null) {
			Integer nuId, oldId;

			nuId = Integer.parseInt(player.getOriginalId());
			oldId = Integer.parseInt(plbase.getOriginalId());

			if (Integer.compare(nuId, oldId) > 0) {
				player.setId(plbase.getId());
				return true;
			} 

		} else
			return true;
		
		return false;
	}
	
	public void persistImportPlayer(FullPlayer fpl) {						
		LeagueMO lg = lgCore.persistLeague(leagues, fpl.getLeague());		
		PlayerMO player = convertPlayer(fpl, lg);
		
		if (savePlayer(player)) {
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
			player = playerDao.save(player);
		}		
	}
				
	
}
