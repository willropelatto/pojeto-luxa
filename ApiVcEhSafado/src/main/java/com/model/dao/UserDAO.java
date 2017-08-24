package com.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.model.entity.UserDetail;

public class UserDAO {
	private final EntityManager entityManager;
	
	public UserDAO() {
		this.entityManager = EntityManagerEnum.INSTANCE.getEntityManager();				
	}
	
	public void Save(UserDetail userEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(userEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void Update(UserDetail userEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(userEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public UserDetail getUser(Integer id) {
		return this.entityManager.find(UserDetail.class, id);	
	}
	
    public void Delete(Integer id) {
    	UserDetail userEntity = this.getUser(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(userEntity);
    	this.entityManager.getTransaction().commit();
    }	
    
    public UserDetail getUserByLogin(String login) {
    	try {
	    	Object result = this.entityManager.createQuery("SELECT u FROM UserEntity u where u.login = :login ORDER BY u.id")
	    			.setParameter("login", login).getSingleResult();
	    	
	    	if (result == null) return null;
	        
	    	return (UserDetail) result;
	    	
    	} catch (Exception e) {
    		return null;// TODO: handle exception
		}     	


    }
    
	public List<UserDetail> TodosUsuarios(){
 
		return this.entityManager.createQuery("SELECT u FROM UserEntity u ORDER BY u.id", UserDetail.class).getResultList();
	}    
}
