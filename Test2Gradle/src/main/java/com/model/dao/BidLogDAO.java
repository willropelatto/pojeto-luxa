package com.model.dao;

import javax.persistence.EntityManager;

import br.com.mkt.model.BidTiteLog;

public class BidLogDAO {	


	private final EntityManager entityManager;

	public BidLogDAO() {			
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();		
	}

	public void save(BidTiteLog bid) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(bid);
		this.entityManager.getTransaction().commit();
	}




}
