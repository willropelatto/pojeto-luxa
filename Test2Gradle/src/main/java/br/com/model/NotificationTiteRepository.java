package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificationTiteRepository extends PagingAndSortingRepository<NotificationTite, Integer> {
	
	Page<NotificationTite> findByTeamIdAndRead(Integer teamid, boolean read, Pageable pageable);
	
	NotificationTite findOneByTeamId(Integer teamid);	
	
	Page<NotificationTite> findByTeamIdAndReadOrderByIdDesc(Integer teamid, boolean read, Pageable pageable);
	

}
