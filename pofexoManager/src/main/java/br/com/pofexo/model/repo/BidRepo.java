package br.com.pofexo.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.pofexo.model.bean.BidMO;

public interface BidRepo extends JpaRepository<BidMO, Integer>, QueryDslPredicateExecutor<BidMO>  {
/*	
	Page<BidMO> findByPlayerId(Integer playerid, Pageable pageable);
	
	Page<BidMO> findByTeamId(Integer teamid, Pageable pageable);
	
	List<BidMO> findByTeamId(Integer teamid);
	
	BidMO findOneByPlayerId(Integer plid);	
	*/
}