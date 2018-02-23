package br.com.pofexo.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pofexo.model.bean.LeagueMO;

public interface LeagueRepo extends PagingAndSortingRepository<LeagueMO, Integer> {
	
	Page<LeagueMO> findByNameAllIgnoringCase(String name, Pageable pageable);
	
	LeagueMO findOneByOriginalId(int originalId);

	LeagueMO findOneByName(String name);
}
