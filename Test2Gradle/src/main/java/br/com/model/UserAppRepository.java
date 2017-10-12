package br.com.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAppRepository extends PagingAndSortingRepository<UserApp, Integer> {

	Page<UserApp> findByUsernameAllIgnoringCase(String username, Pageable pageable);	
	
	UserApp findOneByUsernameAllIgnoringCase(String username);
	
}
