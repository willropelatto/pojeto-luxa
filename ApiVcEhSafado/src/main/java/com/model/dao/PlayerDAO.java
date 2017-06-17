package com.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.persister.entity.Queryable;

import com.model.entity.PlayerEntity;
import com.model.entity.TeamEntity;
import com.model.in.PlayerFilter;


public class PlayerDAO {
	
	private final EntityManagerFactory entityManagerFactory;
	
	private final EntityManager entityManager; 
	
	public PlayerDAO() { 
		
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	public List<PlayerEntity> getPlayers(PlayerFilter filter) {
		
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
		
		TypedQuery<PlayerEntity> qr = this.entityManager.createQuery(sql.toString(), PlayerEntity.class);

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
	
	
	public void Save(PlayerEntity playerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(playerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void update(PlayerEntity playerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(playerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public PlayerEntity getPlayer(Integer id) {
		return this.entityManager.find(PlayerEntity.class, id);
	
	}
	
	public PlayerEntity getPlayerOriginalId(int p2) {
		return this.entityManager.createQuery("SELECT p FROM PlayerEntity p WHERE p.originalId = :p1 ORDER BY p.id", PlayerEntity.class)
    			.setParameter("p1", p2).getSingleResult();
	
	}	
	
    public void Delete(Integer id) {
    	PlayerEntity playerEntity = this.getPlayer(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(playerEntity);
    	this.entityManager.getTransaction().commit();
    }

	public List<PlayerEntity> getPlayerFromLeague(int idLeague) {
    	return this.entityManager.createQuery("SELECT p FROM PlayerEntity p WHERE p.idLeague = :idLeague ORDER BY p.id", PlayerEntity.class)
    			.setParameter("idLeague", idLeague).getResultList();
    }
    		
    public List<PlayerEntity> getAllPlayers() {
    	return this.entityManager.createQuery("SELECT p FROM PlayerEntity p ORDER BY p.id", PlayerEntity.class).getResultList();
    }
}
