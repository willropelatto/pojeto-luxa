package br.com.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.bean.PlayerAttributesMO;

public interface PlayerAttributesRepository extends PagingAndSortingRepository<PlayerAttributesMO, Integer> {	
	
	Page<PlayerAttributesMO> findByName(String name, Pageable pageable);
	
	PlayerAttributesMO findOneByName(String name);
	
}
