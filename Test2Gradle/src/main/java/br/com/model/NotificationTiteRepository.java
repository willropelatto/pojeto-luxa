package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificationTiteRepository extends PagingAndSortingRepository<NotificationTite, Integer> {
	
	Page<NotificationTite> findByTeamId(Integer teamid, Pageable pageable);
	
	NotificationTite findOneByTeamId(Integer teamid);	
	
	Page<NotificationTite> findByTeamIdOrderByIdDesc(Integer teamid, Pageable pageable);
	

}
