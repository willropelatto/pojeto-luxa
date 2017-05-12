package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.entity.TeamPlayerEntity;

public class TeamPlayerDAO {

	private final EntityManagerFactory entityManagerFactory;	
	private final EntityManager entityManager;

	public TeamPlayerDAO() {
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();

	}

	public void save(TeamPlayerEntity tp) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(tp);
		this.entityManager.getTransaction().commit();		
	}

	public List<TeamPlayerEntity> getPlayers(int teamId) {
		return this.entityManager.createQuery("SELECT p FROM TeamPlayerEntity p WHERE idteam = :team", TeamPlayerEntity.class)
				.setParameter("team", teamId).getResultList();
	}


}
