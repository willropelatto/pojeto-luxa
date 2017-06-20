package com.model.dao;

import javax.persistence.EntityManager;

import com.model.entity.BidEntityLog;

public class BidLogDAO {	


	private final EntityManager entityManager;

	public BidLogDAO() {			
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();		
	}

	public void save(BidEntityLog bid) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(bid);
		this.entityManager.getTransaction().commit();
	}




}
