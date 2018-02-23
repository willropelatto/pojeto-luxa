package br.com.pofexo.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pofexo.model.bean.PlayerAttributesMO;

public interface PlayerAttributesRepo extends PagingAndSortingRepository<PlayerAttributesMO, Integer> {	
	
	Page<PlayerAttributesMO> findByName(String name, Pageable pageable);
	
	PlayerAttributesMO findOneByName(String name);
	
}
