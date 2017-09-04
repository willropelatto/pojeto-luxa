package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BidTiteLogRepository extends PagingAndSortingRepository<BidTiteLog, Integer> {
	
	Page<BidTite> findByPlayerId(Integer playerid, Pageable pageable);
	
	Page<BidTite> findByTeamId(Integer teamid, Pageable pageable);	

}
