package com.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entity.UserEntity;

public class UserDAO {
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	public UserDAO() {
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	public void Save(UserEntity userEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(userEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void Update(UserEntity userEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(userEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public UserEntity getUser(Integer id) {
		return this.entityManager.find(UserEntity.class, id);	
	}
	
    public void Delete(Integer id) {
    	UserEntity userEntity = this.getUser(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(userEntity);
    	this.entityManager.getTransaction().commit();
    }	
    
    public UserEntity getUserByLogin(String login) {
    	Object result = this.entityManager.createQuery("SELECT u FROM UserEntity u where u.login = :login ORDER BY u.id")
    			.setParameter("login", login).getSingleResult();

    	if (result == null) return null;
        
    	return (UserEntity) result;
    }
    
    @SuppressWarnings("unchecked")
	public List<UserEntity> TodosUsuarios(){
 
		return this.entityManager.createQuery("SELECT u FROM UserEntity u ORDER BY u.id").getResultList();
	}    
}
