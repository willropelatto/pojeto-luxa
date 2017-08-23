package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.model.entity.TeamEntity;

public class TeamDAO {
	
	private final EntityManager entityManager; 
	
	public TeamDAO() { 		
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();		
	}	
	
	public void save(TeamEntity t) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(t);
		this.entityManager.getTransaction().commit();
	}
	
	public void update(TeamEntity t) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(t);
		this.entityManager.getTransaction().commit();
	}	
	
	public TeamEntity getTeam(int id) {
		return this.entityManager.find(TeamEntity.class, id);
	}

	public void decreaseBudget(int teamID, double bidValue) {
		TeamEntity ent = this.getTeam(teamID);
		ent.setBudget(ent.getBudget() - bidValue);
		this.update(ent);		
	}

	public void increaseBudget(int teamID, double bidValue) {
		TeamEntity ent = this.getTeam(teamID);
		ent.setBudget(ent.getBudget() + bidValue);
		this.update(ent);
		
	}

	public List<TeamEntity> getList() {
		return this.entityManager.createQuery("SELECT p FROM TeamEntity p ORDER BY p.id", TeamEntity.class).getResultList();
	}
	
	public TeamEntity getTeamFromUser(int idUser) {
    	return this.entityManager.createQuery("SELECT p FROM TeamEntity p WHERE p.idUser = :idUser ORDER BY p.id", TeamEntity.class)
    			.setParameter("idUser", idUser).getSingleResult();
    }
	
	
	


}
