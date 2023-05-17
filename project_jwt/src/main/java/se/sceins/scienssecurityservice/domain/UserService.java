package se.sceins.scienssecurityservice.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository useerRepository;
	
	public Optional<User> getUserByEmail(String email) {
		return this.useerRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		this.useerRepository.save(user);
		
	}

}
