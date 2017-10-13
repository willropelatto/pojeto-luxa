package br.com.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.bean.TeamMO;

public interface TeamTiteRepository extends PagingAndSortingRepository<TeamMO, Integer> {

	Page<TeamMO> findByidUser(Integer iduser, Pageable pageable);
	
	TeamMO findOneByIdUser(Integer iduser);
	
}
