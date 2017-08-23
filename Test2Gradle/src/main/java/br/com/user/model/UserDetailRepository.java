package br.com.user.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailRepository extends PagingAndSortingRepository<UserDetail, Integer> {

	Page<UserDetail> findByLoginAllIgnoringCase(String login, Pageable pageable);	
	
}
