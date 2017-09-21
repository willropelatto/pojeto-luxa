package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerAttributesRepository extends PagingAndSortingRepository<PlayerAttributes, Integer> {	
	
	Page<PlayerAttributesRepository> findByName(String name, Pageable pageable);
	
}
