package br.com.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.model.bean.PlayerMO;

@Repository
public interface PlayerRepo extends JpaRepository<PlayerMO, Integer>, 
									QueryDslPredicateExecutor<PlayerMO>,
									PlayerRepoCustom
									{		
	
	Page<PlayerMO> findByPositionIgnoreCaseAndRatingGreaterThanEqual(String position, int rating, Pageable pageable);
	
	Page<PlayerMO> findByNameIgnoreCase(String name, Pageable pageable);
	
	Page<PlayerMO> findByOriginalId(String originalId, Pageable pageable);
	
	PlayerMO findOneByBaseId(int baseId);	

}
