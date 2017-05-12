package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entity.BidEntity;
import com.model.out.BidInfo;

public class BidInfoDAO {	

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	public BidInfoDAO() {			
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}


	public BidEntity getItem(int playerId) {
		return (BidEntity) this.entityManager.createQuery("SELECT p FROM BidEntity p ORDER BY p.id").getSingleResult();		
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

	@SuppressWarnings("unchecked")
	public List<BidInfo> getList() {
		return this.entityManager.createQuery("SELECT p FROM BidEntity p ORDER BY p.id").getResultList();
	}

}
