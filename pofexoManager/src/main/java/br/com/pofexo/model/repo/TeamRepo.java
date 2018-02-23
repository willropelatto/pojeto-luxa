package br.com.pofexo.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.pofexo.model.bean.TeamMO;

public interface TeamRepo extends JpaRepository<TeamMO, Integer>, QueryDslPredicateExecutor<TeamMO> {

	TeamMO findOneByIdUser(Integer iduser);
	
}
