package br.com.pofexo.model.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pofexo.model.bean.NotificationMO;

public interface NotificationRepo extends PagingAndSortingRepository<NotificationMO, Integer> {
	
	Page<NotificationMO> findByTeamIdAndRead(Integer teamid, boolean read, Pageable pageable);		
	
	Page<NotificationMO> findByTeamIdAndReadOrderByIdDesc(Integer teamid, boolean read, Pageable pageable);
	
	List<NotificationMO> findByTeamIdAndRead(Integer teamid, boolean read);
}
