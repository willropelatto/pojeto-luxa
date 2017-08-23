package com.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public enum EntityManagerEnum {

	INSTANCE;

	private EntityManager em;

	public EntityManager getEntityManager() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("persistence_unit_db_banco").createEntityManager();
		}
		return em;
	}

}
