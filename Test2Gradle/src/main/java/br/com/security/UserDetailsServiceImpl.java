package br.com.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.model.bean.UserAppMO;
import br.com.model.repo.UserAppRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserAppRepository applicationUserRepository;

    public UserDetailsServiceImpl(UserAppRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAppMO applicationUser = applicationUserRepository.findOneByUsernameAllIgnoringCase(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

}
