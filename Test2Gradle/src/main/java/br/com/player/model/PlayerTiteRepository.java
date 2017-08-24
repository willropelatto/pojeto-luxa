package br.com.player.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerTiteRepository extends PagingAndSortingRepository<PlayerTite, Integer> {	
	
	
//	Page<Player> findByPositionAndRatingAndNameAllIgnoringCase(String position, int rating, String name, Pageable pageable);
//	
//	Page<Player> findByPositionAndRatingAllIgnoringCase(String position, int rating, String name, Pageable pageable);
//	
//	Page<Player> findByRatingAndNameAllIgnoringCase(String position, int rating, String name, Pageable pageable);
//	
//	Page<Player> findByPositionAndNameAllIgnoringCase(String position, int rating, String name, Pageable pageable);
	
	Page<PlayerTite> findByPositionIgnoreCaseAndRatingGreaterThanEqual(String position, int rating, Pageable pageable);
	
	Page<PlayerTite> findByNameIgnoreCase(String name, Pageable pageable);
	
	Page<PlayerTite> findByOriginalId(int originalId, Pageable pageable);
	
	Page<PlayerTite> findByIdLeague(int idLeague, Pageable pageable);

}
