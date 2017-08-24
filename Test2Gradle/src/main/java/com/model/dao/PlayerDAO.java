package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.com.player.model.PlayerTite;
import br.com.player.model.PlayerFilter;


public class PlayerDAO {
	
	private final EntityManager entityManager; 
	
	public PlayerDAO() { 		
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();				
	}
	
	public List<PlayerTite> getPlayers(PlayerFilter filter) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p FROM PlayerEntity p WHERE 0=0");		
		
		if (!StringUtils.isBlank(filter.getPosition())) {
			sql.append(" AND position = :pos ");			
		}
		
		if (filter.getRating() > 0) {
			sql.append(" AND rating >= :rat ");
		}
		
		if (!StringUtils.isBlank(filter.getName())) {
			sql.append(" AND name = :name ");			
		}
		
	/*	if (filter.getLeague() > 0 ) {
			sql.append(" AND idLeague = :lig ");
		}*/

		sql.append("ORDER BY p.rating desc");
		
		TypedQuery<PlayerTite> qr = this.entityManager.createQuery(sql.toString(), PlayerTite.class);

		if (!StringUtils.isBlank(filter.getPosition())) {
			qr.setParameter("pos", filter.getPosition());
		}
		
		if (!StringUtils.isBlank(filter.getName())) {
			qr.setParameter("name", filter.getName());
		}
		
		if (filter.getRating() > 0) {
			qr.setParameter("rat", filter.getRating());
		}
		
	/*	if (qr.getParameter("lig") != null) {
			qr.setParameter("lig", filter.getLeague());
		}*/		
		
		return qr.getResultList();
	}
	
	
	public void Save(PlayerTite playerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(playerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void update(PlayerTite playerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(playerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public PlayerTite getPlayer(Integer id) {
		return this.entityManager.find(PlayerTite.class, id);
	
	}
	
	public PlayerTite getPlayerOriginalId(int p2) {
		return this.entityManager.createQuery("SELECT p FROM PlayerEntity p WHERE p.originalId = :p1 ORDER BY p.id", PlayerTite.class)
    			.setParameter("p1", p2).getSingleResult();
	
	}	
	
    public void Delete(Integer id) {
    	PlayerTite playerEntity = this.getPlayer(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(playerEntity);
    	this.entityManager.getTransaction().commit();
    }

	public List<PlayerTite> getPlayerFromLeague(int idLeague) {
    	return this.entityManager.createQuery("SELECT p FROM PlayerEntity p WHERE p.idLeague = :idLeague ORDER BY p.id", PlayerTite.class)
    			.setParameter("idLeague", idLeague).getResultList();
    }
    		
    public List<PlayerTite> getAllPlayers() {
    	return this.entityManager.createQuery("SELECT p FROM PlayerEntity p ORDER BY p.id", PlayerTite.class).getResultList();
    }
}
