package br.com.pofexo.model.repo;

import static br.com.pofexo.model.bean.QPlayerMO.playerMO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAUpdateClause;

import br.com.pofexo.model.misc.PlayerStatus;

@Repository
public class PlayerRepoImpl implements PlayerRepoCustom {

    @PersistenceContext
    private EntityManager em;      	
	
	
    @Override
	@Transactional	
	public long updateAvaliable(Integer[] ids) {
		return new JPAUpdateClause(em, playerMO).where(playerMO.league.id.notIn(ids))
			    .set(playerMO.status, PlayerStatus.UNAVAIBLE)
			    .execute();
	}

	@Override	
	@Transactional	
	public long updateContract() {
		return new JPAUpdateClause(em, playerMO).where(playerMO.status.eq(PlayerStatus.ON_BID))
			    .set(playerMO.status, PlayerStatus.CONTRACT)
			    .execute();
	}

}
