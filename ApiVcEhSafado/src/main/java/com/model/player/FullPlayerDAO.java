package com.model.player;

//import com.model.images.HeadShotImage;
//import com.model.images.SpecialImage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.model.player.FullPlayerEntity;

public class FullPlayerDAO {
	
	private final EntityManagerFactory entityManagerFactory;
	
	private final EntityManager entityManager;
	
	public FullPlayerDAO() {
		
		this.entityManagerFactory  = Persistence.createEntityManagerFactory("persistence_unit_db_banco");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	
	public void Save(FullPlayerEntity fullPlayerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(fullPlayerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public void Update(FullPlayerEntity fullPlayerEntity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(fullPlayerEntity);
		this.entityManager.getTransaction().commit();
	}
	
	public FullPlayerEntity getFullPlayer(Integer id) {
		return this.entityManager.find(FullPlayerEntity.class, id);
	
	}
	
    public void Delete(Integer id) {
    	FullPlayerEntity fullPlayer = this.getFullPlayer(id);
    	
    	this.entityManager.getTransaction().begin();
    	this.entityManager.remove(fullPlayer);
    	this.entityManager.getTransaction().commit();
    }
    
}
