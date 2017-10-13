package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.bean.UserAppMO;
import br.com.model.repo.UserAppRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserAppRepository userDao;

	private BCryptPasswordEncoder bCryptPasswordEncoder;	
	
	public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@CrossOrigin
	@GetMapping("/get/{code}")
	public UserAppMO GetUser(@PathVariable("code") Integer codigo) {
		return userDao.findOne(codigo);
	}

	@CrossOrigin
	@PostMapping("/register")
	public UserAppMO Register(@RequestBody UserAppMO user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@CrossOrigin
	@PostMapping("/update")
	public UserAppMO Update(UserAppMO user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@CrossOrigin
	@PostMapping("/login")
	public UserAppMO login(@RequestBody UserAppMO user) {
		try {
			UserAppMO entity = userDao.findOneByUsernameAllIgnoringCase(user.getUsername());

			if (entity != null) {

				if (entity.getPassword().equals(user.getPassword())) {
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
					entity = userDao.save(entity);
					entity.setPassword("");
					return entity;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@CrossOrigin
	@GetMapping("/list")
	public Page<UserAppMO> listUsers(@PageableDefault(value = 50) Pageable pageable) {
		return userDao.findAll(pageable);
	}

}