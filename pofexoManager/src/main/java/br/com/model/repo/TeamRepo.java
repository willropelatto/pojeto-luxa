package br.com.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.model.bean.TeamMO;

public interface TeamRepo extends JpaRepository<TeamMO, Integer>, QueryDslPredicateExecutor<TeamMO> {

	TeamMO findOneByIdUser(Integer iduser);
	
}
