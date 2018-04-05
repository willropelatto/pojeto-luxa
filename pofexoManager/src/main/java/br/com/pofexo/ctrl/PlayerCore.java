package br.com.pofexo.ctrl;

import static br.com.pofexo.model.bean.QPlayerMO.playerMO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.pofexo.model.bean.LeagueMO;
import br.com.pofexo.model.bean.PlayerAttributeAssociationMO;
import br.com.pofexo.model.bean.PlayerAttributesMO;
import br.com.pofexo.model.bean.PlayerMO;
import br.com.pofexo.model.input.Attributes;
import br.com.pofexo.model.input.FullPlayer;
import br.com.pofexo.model.input.League;
import br.com.pofexo.model.misc.BidStatus;
import br.com.pofexo.model.misc.PlayerFilter;
import br.com.pofexo.model.misc.PlayerStatus;
import br.com.pofexo.model.repo.PlayerAttributesRepo;
import br.com.pofexo.model.repo.PlayerRepo;

@Service
public class PlayerCore {
	
	@Autowired
	private PlayerAttributesRepo attributesDao;
	@Autowired
	private PlayerRepo playerDao;
	@Autowired
	private LeagueCore lgCore;
	
	private BooleanExpression basicExpr = playerMO.status.ne(PlayerStatus.UNAVAIBLE);
	
	private PlayerMO convertPlayer(FullPlayer fullpl, LeagueMO lg) {
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
		player.setStatus(PlayerStatus.AVAIBLE);
		
		if (fullpl.getClub() != null) {
			player.setClubName(fullpl.getClub().getName());
		} else {
			player.setClubName("Undefined");
		}

		return player;
	}	
	
	public PlayerCore() {
		super();		
	}

	public boolean validPlayer(FullPlayer player) {		
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
	
	public void persistImportPlayer(FullPlayer fpl, List<League> leagues) {						
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
	
	
	public long setContractPlayers() {	
		return playerDao.updateContract();
	}
	
	public PlayerMO getPlayer(int id) {
		return playerDao.findOne(id);		
	}
	
	public PlayerMO persistPlayerBid(PlayerMO player) {		
		return playerDao.save(player);		
	}
	
	public PlayerMO preparePlayerBid(PlayerMO player, PlayerMO base) {
		player.getBid().setId(base.getBid().getId()); //Manter o mesmo id, pq? boa pergunta...				
		player.setAttributes(base.getAttributes()); 			
		player.setLeague(base.getLeague()); 
		player.getBid().setStatus(BidStatus.APROVED);
		player.setStatus(PlayerStatus.ON_BID);
		return player;
	}
	
	public long setAvaiblePlayers(Integer[] ids ) {		
		return playerDao.updateAvaliable(ids);		
	}
	
	public PlayerMO findPlayerById(Integer playerId) {		
		BooleanExpression expr = basicExpr.and(playerMO.id.eq(playerId));
		return playerDao.findOne(expr);		
	}

	public Page<PlayerMO> findPlayersByName(String name, Pageable pageable) {		
		BooleanExpression expr = basicExpr.and(playerMO.name.equalsIgnoreCase(name));
		return playerDao.findAll(expr, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Direction.ASC, "id")));
	}

	public Page<PlayerMO> findAllPlayers(Pageable pageable) {		
		return playerDao.findAll(basicExpr, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Direction.ASC, "id")));
	}

	public Page<PlayerMO> findPlayers(PlayerFilter input, Pageable pageable) {
		BooleanExpression expr = basicExpr.and(playerMO.rating.gt(input.getRating()));

		if (input.getRatingend() > 0) 
			expr = expr.and(playerMO.rating.lt(input.getRatingend()));
		
		if (!input.getName().isEmpty())
			expr = expr.and(playerMO.name.containsIgnoreCase(input.getName()));

		if (input.getLeague() > 0) 			
			expr = expr.and(playerMO.league.id.eq(input.getLeague()));		

		if (!input.getPosition().isEmpty()) 
			expr = expr.and(playerMO.position.eq(input.getPosition()));
		
		return playerDao.findAll(expr, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Direction.ASC, "id")));
	}				
	
}
