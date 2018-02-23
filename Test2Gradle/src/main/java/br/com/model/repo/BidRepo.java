package br.com.model.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.bean.BidMO;

public interface BidRepo extends PagingAndSortingRepository<BidMO, Integer> {
/*	
	Page<BidMO> findByPlayerId(Integer playerid, Pageable pageable);
	
	Page<BidMO> findByTeamId(Integer teamid, Pageable pageable);
	
	List<BidMO> findByTeamId(Integer teamid);
	
	BidMO findOneByPlayerId(Integer plid);	
	*/
}