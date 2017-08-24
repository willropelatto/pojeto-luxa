package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.team.model.TeamTite;

public class TeamDAO {
	
	private final EntityManager entityManager; 
	
	public TeamDAO() { 		
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();		
	}	
	
	public void save(TeamTite t) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(t);
		this.entityManager.getTransaction().commit();
	}
	
	public void update(TeamTite t) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(t);
		this.entityManager.getTransaction().commit();
	}	
	
	public TeamTite getTeam(int id) {
		return this.entityManager.find(TeamTite.class, id);
	}

	public void decreaseBudget(int teamID, double bidValue) {
		TeamTite ent = this.getTeam(teamID);
		ent.setBudget(ent.getBudget() - bidValue);
		this.update(ent);		
	}

	public void increaseBudget(int teamID, double bidValue) {
		TeamTite ent = this.getTeam(teamID);
		ent.setBudget(ent.getBudget() + bidValue);
		this.update(ent);
		
	}

	public List<TeamTite> getList() {
		return this.entityManager.createQuery("SELECT p FROM TeamEntity p ORDER BY p.id", TeamTite.class).getResultList();
	}
	
	public TeamTite getTeamFromUser(int idUser) {
    	return (TeamTite) this.entityManager.createQuery("SELECT p FROM TeamEntity p WHERE p.idUser = :idUser ORDER BY p.id", TeamTite.class)
    			.setParameter("idUser", idUser).getSingleResult();
    }
	
	
	


}
