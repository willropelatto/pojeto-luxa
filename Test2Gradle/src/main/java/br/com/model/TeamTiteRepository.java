package br.com.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamTiteRepository extends PagingAndSortingRepository<TeamTite, Integer> {

	Page<TeamTite> findByidUser(Integer iduser, Pageable pageable);
	
	TeamTite findOneByIdUser(Integer iduser);
	
}
