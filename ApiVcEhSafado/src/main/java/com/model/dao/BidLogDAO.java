package com.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entity.BidEntityLog;

public class BidLogDAO {	

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;

	public BidLogDAO() {			
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void save(BidEntityLog bid) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(bid);
		this.entityManager.getTransaction().commit();
	}




}
