package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.model.entity.LeagueEntity;

public class LeagueDAO {
	
	
	private final EntityManager entityManager;
	
	public LeagueDAO() {
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();		
		
	}
	
	public void Save(LeagueEntity leagueEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(leagueEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void Update(LeagueEntity leagueEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(leagueEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public LeagueEntity getLeague(Integer id) {
		return this.entityManager.find(LeagueEntity.class, id);	
	}
	
    public void Delete(Integer id) {
    	LeagueEntity leagueEntity = this.getLeague(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(leagueEntity);
    	this.entityManager.getTransaction().commit();
    }
    
    public List<LeagueEntity> getLeagues() {
    	return this.entityManager.createQuery("SELECT p FROM LeagueEntity p ORDER BY p.id", LeagueEntity.class).getResultList();
    }



}