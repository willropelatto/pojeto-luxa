package br.com.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.bean.PlayerMO;

public interface PlayerTiteRepository extends PagingAndSortingRepository<PlayerMO, Integer>, 
												JpaRepository<PlayerMO, Integer>, 
												JpaSpecificationExecutor<PlayerMO> {		
	
	Page<PlayerMO> findByPositionIgnoreCaseAndRatingGreaterThanEqual(String position, int rating, Pageable pageable);
	
	Page<PlayerMO> findByNameIgnoreCase(String name, Pageable pageable);
	
	Page<PlayerMO> findByOriginalId(String originalId, Pageable pageable);
	
	Page<PlayerMO> findByIdLeague(Integer idLeague, Pageable pageable);
	
	PlayerMO findOneByBaseId(int baseId);	
	
}
