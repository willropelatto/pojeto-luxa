package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerAttributesRepository extends PagingAndSortingRepository<PlayerAttributes, Integer> {	
	
	Page<PlayerAttributes> findByName(String name, Pageable pageable);
	
	PlayerAttributes findOneByName(String name);
	
}
