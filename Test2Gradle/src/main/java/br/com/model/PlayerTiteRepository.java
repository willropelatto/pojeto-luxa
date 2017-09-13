package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerTiteRepository extends PagingAndSortingRepository<PlayerTite, Integer>, 
												JpaRepository<PlayerTite, Integer>, 
												JpaSpecificationExecutor<PlayerTite> {		
	
	Page<PlayerTite> findByPositionIgnoreCaseAndRatingGreaterThanEqual(String position, int rating, Pageable pageable);
	
	Page<PlayerTite> findByNameIgnoreCase(String name, Pageable pageable);
	
	Page<PlayerTite> findByOriginalId(String originalId, Pageable pageable);
	
	Page<PlayerTite> findByIdLeague(Integer idLeague, Pageable pageable);
	
	PlayerTite findOneByBaseId(int baseId);	
	
}
