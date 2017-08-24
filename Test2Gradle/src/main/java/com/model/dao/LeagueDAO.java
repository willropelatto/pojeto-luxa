package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.league.model.LeagueTite;

public class LeagueDAO {
	
	
	private final EntityManager entityManager;
	
	public LeagueDAO() {
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();		
		
	}
	
	public void Save(LeagueTite leagueEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(leagueEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void Update(LeagueTite leagueEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(leagueEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public LeagueTite getLeague(Integer id) {
		return this.entityManager.find(LeagueTite.class, id);	
	}
	
    public void Delete(Integer id) {
    	LeagueTite leagueEntity = this.getLeague(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(leagueEntity);
    	this.entityManager.getTransaction().commit();
    }
    
    public List<LeagueTite> getLeagues() {
    	return this.entityManager.createQuery("SELECT p FROM LeagueEntity p ORDER BY p.id", LeagueTite.class).getResultList();
    }



}
