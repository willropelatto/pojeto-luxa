package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.model.entity.BidEntity;

public class BidInfoDAO {	

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	public BidInfoDAO() {			
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}


	public BidEntity getItem(int playerId) {
		
		try {
			return (BidEntity) this.entityManager.createQuery("SELECT p FROM BidEntity p WHERE p.playerID = :playerID ORDER BY p.id", BidEntity.class)
	    			.setParameter("playerID", playerId).getSingleResult();
		} catch (NoResultException e) {
		    System.out.println(e.getMessage());
		}
		return null;		
			
	}

	public void save(BidEntity bid) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(bid);
		this.entityManager.getTransaction().commit();
	}
	
    public void delete(BidEntity bid) {	    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(bid);
    	this.entityManager.getTransaction().commit();
    }	

	public List<BidEntity> getList() {
		return this.entityManager.createQuery("SELECT p FROM BidEntity p ORDER BY p.id", BidEntity.class).getResultList();
	}
	
	public List<BidEntity> getListTeam(Integer teamID) {
		return this.entityManager.createQuery("SELECT p FROM BidEntity p WHERE p.teamID = :teamID ORDER BY p.id", BidEntity.class)
				.setParameter("teamID", teamID).getResultList();
	}

}
