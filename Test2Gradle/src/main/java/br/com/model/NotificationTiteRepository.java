package br.com.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificationTiteRepository extends PagingAndSortingRepository<NotificationTite, Integer> {
	
	Page<NotificationTite> findByTeamIdAndRead(Integer teamid, boolean read, Pageable pageable);		
	
	Page<NotificationTite> findByTeamIdAndReadOrderByIdDesc(Integer teamid, boolean read, Pageable pageable);
	
	List<NotificationTite> findByTeamIdAndRead(Integer teamid, boolean read);
}
