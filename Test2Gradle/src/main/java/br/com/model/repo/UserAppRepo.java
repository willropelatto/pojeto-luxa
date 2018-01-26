package br.com.model.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.model.bean.UserAppMO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAppRepo extends PagingAndSortingRepository<UserAppMO, Integer> {

	Page<UserAppMO> findByUsernameAllIgnoringCase(String username, Pageable pageable);	
	
	UserAppMO findOneByUsernameAllIgnoringCase(String username);
	
}
