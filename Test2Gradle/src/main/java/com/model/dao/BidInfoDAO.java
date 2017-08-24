package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.mkt.model.BidTite;

public class BidInfoDAO {	

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	public BidInfoDAO() {			
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}


	public BidTite getItem(int playerId) {
		
		try {
			return (BidTite) this.entityManager.createQuery("SELECT p FROM BidEntity p WHERE p.playerID = :playerID ORDER BY p.id", BidTite.class)
	    			.setParameter("playerID", playerId).getSingleResult();
		} catch (NoResultException e) {
		    System.out.println(e.getMessage());
		}
		return null;		
			
	}

	public void save(BidTite bid) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(bid);
		this.entityManager.getTransaction().commit();
	}
	
    public void delete(BidTite bid) {	    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(bid);
    	this.entityManager.getTransaction().commit();
    }	

	public List<BidTite> getList() {
		return this.entityManager.createQuery("SELECT p FROM BidEntity p ORDER BY p.id", BidTite.class).getResultList();
	}
	
	public List<BidTite> getListTeam(Integer teamID) {
		return this.entityManager.createQuery("SELECT p FROM BidEntity p WHERE p.teamID = :teamID ORDER BY p.id", BidTite.class)
				.setParameter("teamID", teamID).getResultList();
	}

}
