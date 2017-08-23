package br.com.player.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Integer> {	
	
	
//	Page<Player> findByPositionAndRatingAndNameAllIgnoringCase(String position, int rating, String name, Pageable pageable);
//	
//	Page<Player> findByPositionAndRatingAllIgnoringCase(String position, int rating, String name, Pageable pageable);
//	
//	Page<Player> findByRatingAndNameAllIgnoringCase(String position, int rating, String name, Pageable pageable);
//	
//	Page<Player> findByPositionAndNameAllIgnoringCase(String position, int rating, String name, Pageable pageable);
	
	Page<Player> findByPositionIgnoreCaseAndRatingGreaterThanEqual(String position, int rating, Pageable pageable);
	
	Page<Player> findByNameIgnoreCase(String name, Pageable pageable);
	
	Page<Player> findByOriginalId(int originalId, Pageable pageable);
	
	Page<Player> findByIdLeague(int idLeague, Pageable pageable);

}
