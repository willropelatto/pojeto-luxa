package br.com.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.bean.LeagueMO;

public interface LeagueTiteRepository extends PagingAndSortingRepository<LeagueMO, Integer> {
	
	Page<LeagueMO> findByNameAllIgnoringCase(String name, Pageable pageable);
	
	LeagueMO findOneByOriginalId(int originalId);

	LeagueMO findOneByName(String name);
}
