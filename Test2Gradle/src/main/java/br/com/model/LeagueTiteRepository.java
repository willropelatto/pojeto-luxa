package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeagueTiteRepository extends PagingAndSortingRepository<LeagueTite, Integer> {
	
	Page<LeagueTite> findByNameAllIgnoringCase(String name, Pageable pageable);
	
	LeagueTite findOneByOriginalId(int originalId);

	LeagueTite findOneByName(String name);
}
