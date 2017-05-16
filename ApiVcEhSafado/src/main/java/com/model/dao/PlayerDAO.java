package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entity.PlayerEntity;


public class PlayerDAO {
	
	private final EntityManagerFactory entityManagerFactory;
	
	private final EntityManager entityManager; 
	
	public PlayerDAO() { 
		
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	
	public void Save(PlayerEntity playerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(playerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void Update(PlayerEntity playerEntity) {
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
